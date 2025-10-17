/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.internal.exportimport.content.processor;

import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryLocalServiceUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.segments.constants.SegmentsEntryConstants;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * @author Gustavo Lima, Joshua Cords
 */
public class AssetListEntryExportImportContentProcessorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		Mockito.doReturn(
			_rootElement
		).when(
			_portletDataContext
		).getExportDataRootElement();

		Mockito.doReturn(
			_groupIdMappingsElement
		).when(
			_rootElement
		).addElement(
			Mockito.anyString()
		);

		ReflectionTestUtil.setFieldValue(
			_assetListEntryExportImportContentProcessor, "_groupLocalService",
			_groupLocalService);
	}

	@Test
	public void testAddGroupMappingsElementWithNonexistentGroup() {
		Mockito.doReturn(
			null
		).when(
			_groupLocalService
		).fetchGroup(
			Mockito.anyLong()
		);

		_assetListEntryExportImportContentProcessor.addGroupMappingsElement(
			_portletDataContext, new long[] {RandomTestUtil.randomLong()});

		Mockito.verify(
			_groupIdMappingsElement, Mockito.never()
		).addElement(
			Mockito.anyString()
		);

		Mockito.verify(
			_rootElement, Mockito.atMostOnce()
		).addElement(
			Mockito.anyString()
		);
	}

	@Test
	public void testExportRemovesMissingClassNameIds()
		throws Exception {

		AssetListEntry assetListEntry = Mockito.mock(AssetListEntry.class);

		long assetListEntryId = Math.max(
			1, Math.abs(RandomTestUtil.randomLong()));

		Mockito.doReturn(
			assetListEntryId
		).when(
			assetListEntry
		).getAssetListEntryId();

		long missingClassNameId = Math.max(
			1, Math.abs(RandomTestUtil.randomLong()));
		long validClassNameId = Math.max(
			1, Math.abs(RandomTestUtil.randomLong()));

		String classNameIdsValue = missingClassNameId + "," + validClassNameId;
		String sanitizedClassNameIdsValue = String.valueOf(validClassNameId);

		UnicodeProperties unicodeProperties = UnicodePropertiesBuilder.put(
			"classNameIds", classNameIdsValue
		).build();

		MockedStatic<AssetListEntryLocalServiceUtil>
			assetListEntryLocalServiceUtilMockedStatic = Mockito.mockStatic(
				AssetListEntryLocalServiceUtil.class);

		AtomicReference<String> sanitizedTypeSettingsReference =
			new AtomicReference<>();

		assetListEntryLocalServiceUtilMockedStatic.when(
			() ->
				AssetListEntryLocalServiceUtil.updateAssetListEntryTypeSettings(
					Mockito.eq(assetListEntryId),
					Mockito.eq(SegmentsEntryConstants.ID_DEFAULT),
					Mockito.anyString())
		).thenAnswer(
			invocation -> {
				sanitizedTypeSettingsReference.set(invocation.getArgument(2));

				return null;
			}
		);

		MockedStatic<ClassNameLocalServiceUtil>
			classNameLocalServiceUtilMockedStatic = Mockito.mockStatic(
				ClassNameLocalServiceUtil.class);

		classNameLocalServiceUtilMockedStatic.when(
			() -> ClassNameLocalServiceUtil.fetchClassName(validClassNameId)
		).thenReturn(
			Mockito.mock(ClassName.class)
		);

		Portal portal = Mockito.mock(Portal.class);

		Mockito.doThrow(
			new AssertionError(
				"Asset list export attempted lookup for missing class name id")
		).when(
			portal
		).getClassName(
			missingClassNameId
		);

		ReflectionTestUtil.setFieldValue(
			_assetListEntryExportImportContentProcessor, "_portal", portal);

		String exportedUnicodeProperties =
			_assetListEntryExportImportContentProcessor.
				replaceExportContentReferences(
					_portletDataContext, assetListEntry,
					unicodeProperties.toString(), false, false);

		_assertUpdatedUnicodePropertiesExported(
			exportedUnicodeProperties, sanitizedClassNameIdsValue);

		_assertAssetListEntryUpdated(
			assetListEntryLocalServiceUtilMockedStatic, assetListEntryId,
			sanitizedTypeSettingsReference, sanitizedClassNameIdsValue);

		Mockito.verify(
			portal, Mockito.never()
		).getClassName(
			missingClassNameId
		);
	}

	private void _assertAssetListEntryUpdated(
		MockedStatic<AssetListEntryLocalServiceUtil>
			assetListEntryLocalServiceUtilMockedStatic,
		long assetListEntryId,
		AtomicReference<String> sanitizedTypeSettingsReference,
		String sanitizedClassNameIdsValue) {

		assetListEntryLocalServiceUtilMockedStatic.verify(
			() ->
				AssetListEntryLocalServiceUtil.updateAssetListEntryTypeSettings(
					assetListEntryId, SegmentsEntryConstants.ID_DEFAULT,
					sanitizedTypeSettingsReference.get()),
			Mockito.times(1));

		UnicodeProperties sanitizedTypeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.load(
				sanitizedTypeSettingsReference.get()
			).build();

		Assert.assertEquals(
			sanitizedClassNameIdsValue,
			sanitizedTypeSettingsUnicodeProperties.getProperty("classNameIds"));
	}

	private void _assertUpdatedUnicodePropertiesExported(
		String exportedUnicodeProperties, String sanitizedClassNameIdsValue) {

		UnicodeProperties resultUnicodeProperties =
			UnicodePropertiesBuilder.load(
				exportedUnicodeProperties
			).build();

		Assert.assertEquals(
			sanitizedClassNameIdsValue,
			resultUnicodeProperties.getProperty("classNameIds"));
	}

	private final AssetListEntryExportImportContentProcessor
		_assetListEntryExportImportContentProcessor =
			new AssetListEntryExportImportContentProcessor();
	private final Element _groupIdMappingsElement = Mockito.mock(Element.class);
	private final GroupLocalService _groupLocalService = Mockito.mock(
		GroupLocalService.class);
	private final PortletDataContext _portletDataContext = Mockito.mock(
		PortletDataContext.class);
	private final Element _rootElement = Mockito.mock(Element.class);

}