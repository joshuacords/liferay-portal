/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.search.spi.model.index.contributor;

import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectFolder;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryFolderLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectFolderLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.ml.embedding.text.TextEmbeddingDocumentContributor;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
@FeatureFlag("LPS-122920")
public class ObjectEntryModelDocumentContributorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_originalFastDateFormatFactory = ReflectionTestUtil.getFieldValue(
			FastDateFormatFactoryUtil.class, "_fastDateFormatFactory");

		FastDateFormatFactory fastDateFormatFactory = Mockito.mock(
			FastDateFormatFactory.class);

		Mockito.when(
			fastDateFormatFactory.getSimpleDateFormat("yyyyMMddHHmmss")
		).thenReturn(
			new SimpleDateFormat("yyyyMMddHHmmss")
		);

		ReflectionTestUtil.setFieldValue(
			FastDateFormatFactoryUtil.class, "_fastDateFormatFactory",
			fastDateFormatFactory);

		_accountEntryOrganizationRelLocalService = Mockito.mock(
			AccountEntryOrganizationRelLocalService.class);
		_objectDefinitionLocalService = Mockito.mock(
			ObjectDefinitionLocalService.class);
		_objectEntryFolderLocalService = Mockito.mock(
			ObjectEntryFolderLocalService.class);
		_objectEntryLocalService = Mockito.mock(ObjectEntryLocalService.class);
		_objectFieldLocalService = Mockito.mock(ObjectFieldLocalService.class);
		_objectFolderLocalService = Mockito.mock(
			ObjectFolderLocalService.class);
		_textEmbeddingDocumentContributor = Mockito.mock(
			TextEmbeddingDocumentContributor.class);

		_objectEntryModelDocumentContributor =
			new ObjectEntryModelDocumentContributor(
				_accountEntryOrganizationRelLocalService,
				ObjectEntry.class.getName(), _objectDefinitionLocalService,
				_objectEntryFolderLocalService, _objectEntryLocalService,
				_objectFieldLocalService, _objectFolderLocalService,
				_textEmbeddingDocumentContributor);

		_objectDefinitionId = RandomTestUtil.randomLong();
		_objectEntryId = RandomTestUtil.randomLong();
		_companyId = RandomTestUtil.randomLong();

		_setUpObjectDefinition();
		_setUpObjectField();
		_setUpObjectFolder();
		_setUpObjectEntry();
		_setUpDocument();
	}

	@After
	public void tearDown() {
		ReflectionTestUtil.setFieldValue(
			FastDateFormatFactoryUtil.class, "_fastDateFormatFactory",
			_originalFastDateFormatFactory);
	}

	@Test
	public void testObjectEntryNonlocalizedTextEmbeddings() throws Exception {
		_objectEntryModelDocumentContributor.contribute(
			_document, _objectEntry);

		String expectedContent = String.format(
			"textField: %s", _values.get("textField"));

		Mockito.verify(
			_textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(_document), Mockito.eq(_objectEntry),
			Mockito.eq(expectedContent)
		);

		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(_document), Mockito.anyString(),
			Mockito.eq(_objectEntry), Mockito.anyString()
		);
	}

	private void _setUpDocument() {
		_document = new DocumentImpl();

		_document.addKeyword(
			Field.ENTRY_CLASS_PK, String.valueOf(_objectEntryId));
	}

	private void _setUpObjectDefinition() throws Exception {
		_objectDefinition = Mockito.mock(ObjectDefinition.class);

		Mockito.when(
			_objectDefinition.getAccountEntryRestrictedObjectFieldId()
		).thenReturn(
			RandomTestUtil.randomLong()
		);

		Mockito.when(
			_objectDefinition.getObjectFolderId()
		).thenReturn(
			RandomTestUtil.randomLong()
		);

		Mockito.when(
			_objectDefinition.getShortName()
		).thenReturn(
			RandomTestUtil.randomString()
		);

		Mockito.when(
			_objectDefinition.isEnableLocalization()
		).thenReturn(
			false
		);

		Mockito.when(
			_objectDefinitionLocalService.fetchObjectDefinition(
				_objectDefinitionId)
		).thenReturn(
			_objectDefinition
		);
	}

	private void _setUpObjectEntry() throws Exception {
		_values = HashMapBuilder.<String, Serializable>put(
			"textField", RandomTestUtil.randomString()
		).build();

		_objectEntry = Mockito.mock(ObjectEntry.class);

		Mockito.when(
			_objectEntry.getCompanyId()
		).thenReturn(
			_companyId
		);

		Mockito.when(
			_objectEntry.getObjectDefinitionId()
		).thenReturn(
			_objectDefinitionId
		);

		Mockito.when(
			_objectEntry.getObjectEntryFolderId()
		).thenReturn(
			0L
		);

		Mockito.when(
			_objectEntry.getObjectEntryId()
		).thenReturn(
			_objectEntryId
		);

		Mockito.when(
			_objectEntry.getTitleValue()
		).thenReturn(
			RandomTestUtil.randomString()
		);

		Mockito.when(
			_objectEntry.getValues()
		).thenReturn(
			_values
		);
	}

	private void _setUpObjectField() throws Exception {
		_objectField = Mockito.mock(ObjectField.class);

		Mockito.when(
			_objectField.getBusinessType()
		).thenReturn(
			ObjectFieldConstants.BUSINESS_TYPE_TEXT
		);

		Mockito.when(
			_objectField.getName()
		).thenReturn(
			"textField"
		);

		Mockito.when(
			_objectField.getObjectFieldId()
		).thenReturn(
			RandomTestUtil.randomLong()
		);

		Mockito.when(
			_objectField.isIndexed()
		).thenReturn(
			true
		);

		Mockito.when(
			_objectField.isLocalized()
		).thenReturn(
			false
		);

		Mockito.when(
			_objectFieldLocalService.getObjectFields(_objectDefinitionId, false)
		).thenReturn(
			Collections.singletonList(_objectField)
		);
	}

	private void _setUpObjectFolder() throws Exception {
		_objectFolder = Mockito.mock(ObjectFolder.class);

		Mockito.when(
			_objectFolder.getExternalReferenceCode()
		).thenReturn(
			RandomTestUtil.randomString()
		);

		Mockito.when(
			_objectFolderLocalService.getObjectFolder(
				_objectDefinition.getObjectFolderId())
		).thenReturn(
			_objectFolder
		);

		Mockito.when(
			_objectEntryFolderLocalService.fetchObjectEntryFolder(
				Mockito.anyLong())
		).thenReturn(
			null
		);
	}

	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;
	private long _companyId;
	private Document _document;
	private ObjectDefinition _objectDefinition;
	private long _objectDefinitionId;
	private ObjectDefinitionLocalService _objectDefinitionLocalService;
	private ObjectEntry _objectEntry;
	private ObjectEntryFolderLocalService _objectEntryFolderLocalService;
	private long _objectEntryId;
	private ObjectEntryLocalService _objectEntryLocalService;
	private ObjectEntryModelDocumentContributor
		_objectEntryModelDocumentContributor;
	private ObjectField _objectField;
	private ObjectFieldLocalService _objectFieldLocalService;
	private ObjectFolder _objectFolder;
	private ObjectFolderLocalService _objectFolderLocalService;
	private FastDateFormatFactory _originalFastDateFormatFactory;
	private TextEmbeddingDocumentContributor _textEmbeddingDocumentContributor;
	private Map<String, Serializable> _values;

}