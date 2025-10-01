/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.search.test;

import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.internal.search.spi.model.index.contributor.ObjectEntryModelDocumentContributor;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryFolderLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectFolderLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.ml.embedding.text.TextEmbeddingDocumentContributor;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
@FeatureFlag("LPS-122920")
@RunWith(Arquillian.class)
public class ObjectEntryModelDocumentContributorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Test
	public void testObjectEntryNonLocalizedTextEmbeddings() throws Exception {
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor =
			Mockito.mock(TextEmbeddingDocumentContributor.class);

		Mockito.when(
			textEmbeddingDocumentContributor.getLanguageIds(
				Mockito.any())
		).thenReturn(
			Arrays.asList("en_US", "es_ES")
		);

		ObjectField objectField = new TextObjectFieldBuilder(
		).indexed(
			true
		).labelMap(
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString())
		).localized(
			false
		).name(
			"textField"
		).build();

		_objectDefinition =
			ObjectDefinitionTestUtil.addCustomObjectDefinition(
				false, Collections.singletonList(objectField));

		_objectDefinition =
			_objectDefinitionLocalService.publishCustomObjectDefinition(
				TestPropsValues.getUserId(),
				_objectDefinition.getObjectDefinitionId());

		Map<String, Serializable> values = HashMapBuilder.<String, Serializable>put(
			"textField", RandomTestUtil.randomString()
		).build();

		ServiceContext serviceContext = ServiceContextTestUtil.getServiceContext();

		serviceContext.setCompanyId(_objectDefinition.getCompanyId());
		serviceContext.setScopeGroupId(0);
		serviceContext.setUserId(TestPropsValues.getUserId());

		_objectEntry = _objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			_objectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null, values, serviceContext);

		Document document = new DocumentImpl();

		document.addKeyword(
			Field.ENTRY_CLASS_PK,
			String.valueOf(_objectEntry.getObjectEntryId()));

		ObjectEntryModelDocumentContributor objectEntryModelDocumentContributor =
			new ObjectEntryModelDocumentContributor(
				_accountEntryOrganizationRelLocalService,
				_objectDefinition.getClassName(), _objectDefinitionLocalService,
				_objectEntryFolderLocalService, _objectEntryLocalService,
				_objectFieldLocalService, _objectFolderLocalService,
				textEmbeddingDocumentContributor);

		objectEntryModelDocumentContributor.contribute(document, _objectEntry);

		Mockito.verify(
			textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(document), Mockito.eq(_objectEntry), Mockito.anyString()
		);

		Mockito.verify(
			textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.anyString(), Mockito.eq(_objectEntry),
			Mockito.anyString());
	}

	@DeleteAfterTestRun
	private ObjectDefinition _objectDefinition;

	@DeleteAfterTestRun
	private ObjectEntry _objectEntry;

	@Inject
	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject
	private ObjectEntryFolderLocalService _objectEntryFolderLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectFieldLocalService _objectFieldLocalService;

	@Inject
	private ObjectFolderLocalService _objectFolderLocalService;

}
