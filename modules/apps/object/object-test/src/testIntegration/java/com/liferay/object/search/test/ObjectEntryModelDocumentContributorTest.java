/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.search.test;

import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryFolderLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectFolderLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.ml.embedding.text.TextEmbeddingDocumentContributor;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
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

	@BeforeClass
	public static void setUpClass() {
		_textEmbeddingDocumentContributor = Mockito.mock(
			TextEmbeddingDocumentContributor.class);
	}

	@Before
	public void setUp() {
		Mockito.reset(_textEmbeddingDocumentContributor);

		Mockito.when(
			_textEmbeddingDocumentContributor.getLanguageIds(Mockito.any())
		).thenReturn(
			Collections.emptyList()
		);
	}

	@Test
	public void testContributesLocalizedTextEmbeddings() throws Exception {
		Mockito.when(
			_textEmbeddingDocumentContributor.getLanguageIds(Mockito.any())
		).thenReturn(
			Arrays.asList("en_US", "es_ES")
		);

		ObjectField localizedObjectField = new TextObjectFieldBuilder(
		).indexed(
			true
		).labelMap(
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString())
		).localized(
			true
		).name(
			"localizedTextField"
		).build();

		ObjectField textObjectField = new TextObjectFieldBuilder(
		).indexed(
			true
		).labelMap(
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString())
		).localized(
			false
		).name(
			"textField"
		).build();

		_objectDefinition = _addAndPublishObjectDefinition(
			true, localizedObjectField, textObjectField);

		String enLocalizedValue = RandomTestUtil.randomString();
		String esLocalizedValue = RandomTestUtil.randomString();
		String textFieldValue = RandomTestUtil.randomString();

		Map<String, Object> localizedValues =
			HashMapBuilder.<String, Object>put(
				"en_US", enLocalizedValue
			).put(
				"es_ES", esLocalizedValue
			).build();

		_objectEntry = _addObjectEntry(
			_objectDefinition,
			HashMapBuilder.<String, Serializable>put(
				"localizedTextField", enLocalizedValue
			).put(
				"localizedTextField_i18n", (Serializable)localizedValues
			).put(
				"textField", textFieldValue
			).build());

		Document document = _createDocument(_objectEntry);

		try (SafeCloseable safeCloseable =
				_replaceTextEmbeddingDocumentContributor()) {

			_objectEntryModelDocumentContributor.contribute(
				document, _objectEntry);
		}

		String enContent = String.format(
			"localizedTextField: %s, textField: %s", enLocalizedValue,
			textFieldValue);

		String esContent = String.format(
			"localizedTextField: %s, textField: %s", esLocalizedValue,
			textFieldValue);

		_verifyLocalizedContribution(
			document, "en_US", _objectEntry, enContent);

		_verifyLocalizedContribution(
			document, "es_ES", _objectEntry, esContent);

		_verifyNoGlobalContribution(document, _objectEntry);

		_verifyNoLocalizedContributionWithContent(
			document, _objectEntry,
			String.format("textField: %s", textFieldValue));
	}

	@Test
	public void testContributesMissingLocalizedTextEmbeddings()
		throws Exception {

		Mockito.when(
			_textEmbeddingDocumentContributor.getLanguageIds(Mockito.any())
		).thenReturn(
			Arrays.asList("en_US", "pt_PT")
		);

		ObjectField localizedObjectField = new TextObjectFieldBuilder(
		).indexed(
			true
		).labelMap(
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString())
		).localized(
			true
		).name(
			"localizedTextField"
		).build();

		ObjectField textObjectField = new TextObjectFieldBuilder(
		).indexed(
			true
		).labelMap(
			LocalizedMapUtil.getLocalizedMap(RandomTestUtil.randomString())
		).localized(
			false
		).name(
			"textField"
		).build();

		_objectDefinition = _addAndPublishObjectDefinition(
			true, localizedObjectField, textObjectField);

		String enLocalizedValue = RandomTestUtil.randomString();
		String esLocalizedValue = RandomTestUtil.randomString();
		String textFieldValue = RandomTestUtil.randomString();

		Map<String, Object> localizedValues =
			HashMapBuilder.<String, Object>put(
				"en_US", enLocalizedValue
			).put(
				"es_ES", esLocalizedValue
			).put(
				"pt_PT", enLocalizedValue
			).build();

		_objectEntry = _addObjectEntry(
			_objectDefinition,
			HashMapBuilder.<String, Serializable>put(
				"localizedTextField", enLocalizedValue
			).put(
				"localizedTextField_i18n", (Serializable)localizedValues
			).put(
				"textField", textFieldValue
			).build());

		Document document = _createDocument(_objectEntry);

		try (SafeCloseable safeCloseable =
				_replaceTextEmbeddingDocumentContributor()) {

			_objectEntryModelDocumentContributor.contribute(
				document, _objectEntry);
		}

		String expectedContent = String.format(
			"localizedTextField: %s, textField: %s", enLocalizedValue,
			textFieldValue);

		_verifyLocalizedContribution(
			document, "en_US", _objectEntry, expectedContent);

		_verifyLocalizedContribution(
			document, "pt_PT", _objectEntry, expectedContent);

		_verifyNoLocalizedContributionForLanguage(
			document, "es_ES", _objectEntry);

		_verifyNoGlobalContribution(document, _objectEntry);
	}

	@Test
	public void testObjectEntryNonlocalizedTextEmbeddings() throws Exception {
		Mockito.when(
			_textEmbeddingDocumentContributor.getLanguageIds(Mockito.any())
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

		_objectDefinition = _addAndPublishObjectDefinition(false, objectField);

		String textFieldValue = RandomTestUtil.randomString();

		_objectEntry = _addObjectEntry(
			_objectDefinition,
			HashMapBuilder.<String, Serializable>put(
				"textField", textFieldValue
			).build());

		Document document = _createDocument(_objectEntry);

		try (SafeCloseable safeCloseable =
				_replaceTextEmbeddingDocumentContributor()) {

			_objectEntryModelDocumentContributor.contribute(
				document, _objectEntry);
		}

		_verifyGlobalContribution(
			document, _objectEntry,
			String.format("textField: %s", textFieldValue));

		_verifyNoLocalizedContribution(document, _objectEntry);
	}

	private ObjectDefinition _addAndPublishObjectDefinition(
			boolean enableLocalization, ObjectField... objectFields)
		throws Exception {

		ObjectDefinition objectDefinition =
			ObjectDefinitionTestUtil.addCustomObjectDefinition(
				enableLocalization, Arrays.asList(objectFields));

		return _objectDefinitionLocalService.publishCustomObjectDefinition(
			TestPropsValues.getUserId(),
			objectDefinition.getObjectDefinitionId());
	}

	private ObjectEntry _addObjectEntry(
			ObjectDefinition objectDefinition, Map<String, Serializable> values)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setCompanyId(objectDefinition.getCompanyId());
		serviceContext.setScopeGroupId(0);
		serviceContext.setUserId(TestPropsValues.getUserId());

		return _objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			objectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null, values, serviceContext);
	}

	private Document _createDocument(ObjectEntry objectEntry) {
		Document document = new DocumentImpl();

		document.addKeyword(
			Field.ENTRY_CLASS_PK,
			String.valueOf(objectEntry.getObjectEntryId()));

		return document;
	}

	private SafeCloseable _replaceTextEmbeddingDocumentContributor() {
		TextEmbeddingDocumentContributor
			originalTextEmbeddingDocumentContributor =
				ReflectionTestUtil.getFieldValue(
					_objectEntryModelDocumentContributor,
					"_textEmbeddingDocumentContributor");

		ReflectionTestUtil.setFieldValue(
			_objectEntryModelDocumentContributor,
			"_textEmbeddingDocumentContributor",
			_textEmbeddingDocumentContributor);

		return () -> ReflectionTestUtil.setFieldValue(
			_objectEntryModelDocumentContributor,
			"_textEmbeddingDocumentContributor",
			originalTextEmbeddingDocumentContributor);
	}

	private void _verifyGlobalContribution(
		Document document, ObjectEntry objectEntry, String expectedContent) {

		Mockito.verify(
			_textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(document), Mockito.eq(objectEntry),
			Mockito.eq(expectedContent)
		);
	}

	private void _verifyLocalizedContribution(
		Document document, String languageId, ObjectEntry objectEntry,
		String expectedContent) {

		Mockito.verify(
			_textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(document), Mockito.eq(languageId),
			Mockito.eq(objectEntry), Mockito.eq(expectedContent)
		);
	}

	private void _verifyNoGlobalContribution(
		Document document, ObjectEntry objectEntry) {

		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.eq(objectEntry), Mockito.anyString()
		);
	}

	private void _verifyNoLocalizedContribution(
		Document document, ObjectEntry objectEntry) {

		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.anyString(), Mockito.eq(objectEntry),
			Mockito.anyString()
		);
	}

	private void _verifyNoLocalizedContributionForLanguage(
		Document document, String languageId, ObjectEntry objectEntry) {

		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.eq(languageId),
			Mockito.eq(objectEntry), Mockito.anyString()
		);
	}

	private void _verifyNoLocalizedContributionWithContent(
		Document document, ObjectEntry objectEntry, String content) {

		Mockito.verify(
			_textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.anyString(), Mockito.eq(objectEntry),
			Mockito.eq(content)
		);
	}

	private static TextEmbeddingDocumentContributor
		_textEmbeddingDocumentContributor;

	@Inject
	private AccountEntryOrganizationRelLocalService
		_accountEntryOrganizationRelLocalService;

	@DeleteAfterTestRun
	private ObjectDefinition _objectDefinition;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@DeleteAfterTestRun
	private ObjectEntry _objectEntry;

	@Inject
	private ObjectEntryFolderLocalService _objectEntryFolderLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject(
		filter = "component.name=com.liferay.object.internal.search.spi.model.index.contributor.ObjectEntryModelDocumentContributor"
	)
	private ModelDocumentContributor<ObjectEntry>
		_objectEntryModelDocumentContributor;

	@Inject
	private ObjectFieldLocalService _objectFieldLocalService;

	@Inject
	private ObjectFolderLocalService _objectFolderLocalService;

}