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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.search.ml.embedding.text.TextEmbeddingDocumentContributor;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.MockedStatic;
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
		_mockFastDateFormatFactory();
		_mockLocaleUtil();
		_mockServices();
		_seedIdentifiers();
		_mockObjectDefinition(false);
		_createObjectEntry();
		_mockObjectFolder();
		_setUpNonlocalizedField();
		_initializeContributor();
		_document = _createDocument();
	}

	@After
	public void tearDown() {
		ReflectionTestUtil.setFieldValue(
			FastDateFormatFactoryUtil.class, "_fastDateFormatFactory",
			_originalFastDateFormatFactory);

		if (_localeUtilMockedStatic != null) {
			_localeUtilMockedStatic.close();

			_localeUtilMockedStatic = null;
		}
	}

	@Test
	public void testContributesLocalizedTextEmbeddings() {
		_setUpLocalizedFields(false);

		Mockito.when(
			_textEmbeddingDocumentContributor.getLanguageIds(_objectEntry)
		).thenReturn(
			Arrays.asList(_EN_US, _ES_ES)
		);

		_objectEntryModelDocumentContributor.contribute(
			_document, _objectEntry);

		_verifyLocalizedContribution(_EN_US, _enUSTextContent);
		_verifyLocalizedContribution(_ES_ES, _esESTextContent);
		_verifyNoLocalizedContributionWithContent(_nonlocalizedTextContent);
		_verifyNoGlobalContribution();
	}

	@Test
	public void testContributesMissingLocalizedTextEmbeddings() {
		_setUpLocalizedFields(true);

		Mockito.when(
			_textEmbeddingDocumentContributor.getLanguageIds(_objectEntry)
		).thenReturn(
			Arrays.asList(_EN_US, _PT_PT)
		);

		_objectEntryModelDocumentContributor.contribute(
			_document, _objectEntry);

		_verifyLocalizedContribution(_EN_US, _enUSTextContent);
		_verifyLocalizedContribution(_PT_PT, _enUSTextContent);
		_verifyNoLocalizedContributionForLanguage(_ES_ES);
		_verifyNoGlobalContribution();
	}

	@Test
	public void testObjectEntryNonlocalizedTextEmbeddings() {
		_objectEntryModelDocumentContributor.contribute(
			_document, _objectEntry);

		String expectedContent = String.format(
			"%s: %s", _NONLOCALIZED_FIELD_NAME, _textFieldValue);

		_verifyGlobalContribution(expectedContent);

		_verifyNoLocalizedContributionCalls();
	}

	private Document _createDocument() {
		Document document = new DocumentImpl();

		document.addKeyword(
			Field.ENTRY_CLASS_PK, String.valueOf(_objectEntryId));

		return document;
	}

	private void _createObjectEntry() throws Exception {
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
	}

	private void _initializeContributor() {
		_objectEntryModelDocumentContributor =
			new ObjectEntryModelDocumentContributor(
				_accountEntryOrganizationRelLocalService,
				ObjectEntry.class.getName(), _objectDefinitionLocalService,
				_objectEntryFolderLocalService, _objectEntryLocalService,
				_objectFieldLocalService, _objectFolderLocalService,
				_textEmbeddingDocumentContributor);
	}

	private void _mockFastDateFormatFactory() {
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
	}

	private void _mockLocaleUtil() {
		_localeUtilMockedStatic = Mockito.mockStatic(LocaleUtil.class);

		_localeUtilMockedStatic.when(
			LocaleUtil::getDefault
		).thenReturn(
			_LOCALE_EN_US
		);

		_localeUtilMockedStatic.when(
			() -> LocaleUtil.fromLanguageId(_EN_US, true, false)
		).thenReturn(
			_LOCALE_EN_US
		);

		_localeUtilMockedStatic.when(
			() -> LocaleUtil.fromLanguageId(_EN_US)
		).thenReturn(
			_LOCALE_EN_US
		);

		_localeUtilMockedStatic.when(
			() -> LocaleUtil.fromLanguageId(_ES_ES, true, false)
		).thenReturn(
			_LOCALE_ES_ES
		);

		_localeUtilMockedStatic.when(
			() -> LocaleUtil.fromLanguageId(_ES_ES)
		).thenReturn(
			_LOCALE_ES_ES
		);

		_localeUtilMockedStatic.when(
			() -> LocaleUtil.fromLanguageId(_PT_PT, true, false)
		).thenReturn(
			_LOCALE_PT_PT
		);

		_localeUtilMockedStatic.when(
			() -> LocaleUtil.fromLanguageId(_PT_PT)
		).thenReturn(
			_LOCALE_PT_PT
		);
	}

	private void _mockObjectDefinition(boolean enableLocalization) {
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
			enableLocalization
		);

		Mockito.when(
			_objectDefinitionLocalService.fetchObjectDefinition(
				_objectDefinitionId)
		).thenReturn(
			_objectDefinition
		);
	}

	private void _mockObjectFolder() throws Exception {
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

	private void _mockServices() {
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

		Mockito.when(
			_textEmbeddingDocumentContributor.getLanguageIds(Mockito.any())
		).thenReturn(
			Collections.emptyList()
		);
	}

	private void _seedIdentifiers() {
		_companyId = RandomTestUtil.randomLong();
		_objectDefinitionId = RandomTestUtil.randomLong();
		_objectEntryId = RandomTestUtil.randomLong();
		_textFieldValue = RandomTestUtil.randomString();
	}

	private void _setUpLocalizedFields(boolean includePortugueseFallback) {
		_localizedEnValue = RandomTestUtil.randomString();
		_localizedEsValue = RandomTestUtil.randomString();

		String localizedFieldI18nName =
			_LOCALIZED_FIELD_NAME + _LOCALIZED_FIELD_I18N_SUFFIX;

		Map<String, Object> localizedValues =
			HashMapBuilder.<String, Object>put(
				_EN_US, _localizedEnValue
			).put(
				_ES_ES, _localizedEsValue
			).build();

		if (includePortugueseFallback) {
			localizedValues.put(_PT_PT, _localizedEnValue);
		}

		_values = HashMapBuilder.<String, Serializable>put(
			_NONLOCALIZED_FIELD_NAME, _textFieldValue
		).put(
			localizedFieldI18nName, (Serializable)localizedValues
		).build();

		Mockito.when(
			_objectEntry.getValues()
		).thenReturn(
			_values
		);

		ObjectField localizedObjectField = Mockito.mock(ObjectField.class);

		Mockito.when(
			localizedObjectField.getBusinessType()
		).thenReturn(
			ObjectFieldConstants.BUSINESS_TYPE_TEXT
		);

		Mockito.when(
			localizedObjectField.getName()
		).thenReturn(
			_LOCALIZED_FIELD_NAME
		);

		Mockito.when(
			localizedObjectField.getI18nObjectFieldName()
		).thenReturn(
			localizedFieldI18nName
		);

		Mockito.when(
			localizedObjectField.getObjectFieldId()
		).thenReturn(
			RandomTestUtil.randomLong()
		);

		Mockito.when(
			localizedObjectField.isIndexed()
		).thenReturn(
			true
		);

		Mockito.when(
			localizedObjectField.isLocalized()
		).thenReturn(
			true
		);

		Mockito.when(
			_objectFieldLocalService.getObjectFields(_objectDefinitionId, false)
		).thenReturn(
			Arrays.asList(localizedObjectField, _nonlocalizedObjectField)
		);

		Mockito.when(
			_objectDefinition.isEnableLocalization()
		).thenReturn(
			true
		);

		_enUSTextContent = String.format(
			"%s: %s, %s: %s", _LOCALIZED_FIELD_NAME, _localizedEnValue,
			_NONLOCALIZED_FIELD_NAME, _textFieldValue);

		_esESTextContent = String.format(
			"%s: %s, %s: %s", _LOCALIZED_FIELD_NAME, _localizedEsValue,
			_NONLOCALIZED_FIELD_NAME, _textFieldValue);
	}

	private void _setUpNonlocalizedField() {
		_nonlocalizedObjectField = Mockito.mock(ObjectField.class);

		Mockito.when(
			_nonlocalizedObjectField.getBusinessType()
		).thenReturn(
			ObjectFieldConstants.BUSINESS_TYPE_TEXT
		);

		Mockito.when(
			_nonlocalizedObjectField.getName()
		).thenReturn(
			_NONLOCALIZED_FIELD_NAME
		);

		Mockito.when(
			_nonlocalizedObjectField.getObjectFieldId()
		).thenReturn(
			RandomTestUtil.randomLong()
		);

		Mockito.when(
			_nonlocalizedObjectField.isIndexed()
		).thenReturn(
			true
		);

		Mockito.when(
			_nonlocalizedObjectField.isLocalized()
		).thenReturn(
			false
		);

		Mockito.when(
			_objectFieldLocalService.getObjectFields(_objectDefinitionId, false)
		).thenReturn(
			Collections.singletonList(_nonlocalizedObjectField)
		);

		_values = HashMapBuilder.<String, Serializable>put(
			_NONLOCALIZED_FIELD_NAME, _textFieldValue
		).build();

		Mockito.when(
			_objectEntry.getValues()
		).thenReturn(
			_values
		);

		_nonlocalizedTextContent = String.format(
			"%s: %s", _NONLOCALIZED_FIELD_NAME, _textFieldValue);
	}

	private void _verifyGlobalContribution(String expectedContent) {
		Mockito.verify(
			_textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(_document), Mockito.eq(_objectEntry),
			Mockito.eq(expectedContent)
		);
	}

	private void _verifyLocalizedContribution(
		String languageId, String expectedContent) {

		Mockito.verify(
			_textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(_document), Mockito.eq(languageId),
			Mockito.eq(_objectEntry), Mockito.eq(expectedContent)
		);
	}

	private void _verifyNoGlobalContribution() {
		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(_document), Mockito.eq(_objectEntry), Mockito.anyString()
		);
	}

	private void _verifyNoLocalizedContributionCalls() {
		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(_document), Mockito.anyString(),
			Mockito.eq(_objectEntry), Mockito.anyString()
		);
	}

	private void _verifyNoLocalizedContributionForLanguage(String languageId) {
		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(_document), Mockito.eq(languageId),
			Mockito.eq(_objectEntry), Mockito.anyString()
		);
	}

	private void _verifyNoLocalizedContributionWithContent(String content) {
		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(_document), Mockito.anyString(),
			Mockito.eq(_objectEntry), Mockito.eq(content)
		);
	}

	private static final String _EN_US = "en_US";

	private static final String _ES_ES = "es_ES";

	private static final Locale _LOCALE_EN_US = new Locale("en", "US");

	private static final Locale _LOCALE_ES_ES = new Locale("es", "ES");

	private static final Locale _LOCALE_PT_PT = new Locale("pt", "PT");

	private static final String _LOCALIZED_FIELD_I18N_SUFFIX = "_i18n";

	private static final String _LOCALIZED_FIELD_NAME = "localizedTextField";

	private static final String _NONLOCALIZED_FIELD_NAME = "textField";

	private static final String _PT_PT = "pt_PT";

	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;
	private long _companyId;
	private Document _document;
	private String _enUSTextContent;
	private String _esESTextContent;
	private MockedStatic<LocaleUtil> _localeUtilMockedStatic;
	private String _localizedEnValue;
	private String _localizedEsValue;
	private ObjectField _nonlocalizedObjectField;
	private String _nonlocalizedTextContent;
	private ObjectDefinition _objectDefinition;
	private long _objectDefinitionId;
	private ObjectDefinitionLocalService _objectDefinitionLocalService;
	private ObjectEntry _objectEntry;
	private ObjectEntryFolderLocalService _objectEntryFolderLocalService;
	private long _objectEntryId;
	private ObjectEntryLocalService _objectEntryLocalService;
	private ObjectEntryModelDocumentContributor
		_objectEntryModelDocumentContributor;
	private ObjectFieldLocalService _objectFieldLocalService;
	private ObjectFolder _objectFolder;
	private ObjectFolderLocalService _objectFolderLocalService;
	private FastDateFormatFactory _originalFastDateFormatFactory;
	private TextEmbeddingDocumentContributor _textEmbeddingDocumentContributor;
	private String _textFieldValue;
	private Map<String, Serializable> _values;

}