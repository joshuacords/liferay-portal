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
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
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
import com.liferay.portal.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import com.liferay.petra.lang.SafeCloseable;

import java.io.Serializable;

import java.util.Arrays;
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

		_objectDefinition = _addAndPublishObjectDefinition(
			false, objectField);

		String textFieldValue = RandomTestUtil.randomString();

		_objectEntry = _addObjectEntry(
			_objectDefinition,
			HashMapBuilder.<String, Serializable>put(
				"textField", textFieldValue
			).build());

		Document document = _createDocument(_objectEntry);

		try (SafeCloseable safeCloseable =
				_replaceTextEmbeddingDocumentContributor(
					textEmbeddingDocumentContributor)) {

			_objectEntryModelDocumentContributor.contribute(document, _objectEntry);
		}

		_verifyGlobalContribution(
			textEmbeddingDocumentContributor, document, _objectEntry,
			String.format("textField: %s", textFieldValue));

		_verifyNoLocalizedContribution(
			textEmbeddingDocumentContributor, document, _objectEntry);
	}

	@Test
	public void testContributesLocalizedTextEmbeddings() throws Exception {
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor =
			Mockito.mock(TextEmbeddingDocumentContributor.class);

		Mockito.when(
			textEmbeddingDocumentContributor.getLanguageIds(
				Mockito.any())
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
				"localizedTextField_i18n",
				(Serializable)localizedValues
			).put(
				"textField", textFieldValue
			).build());

		Document document = _createDocument(_objectEntry);

		try (SafeCloseable safeCloseable =
				_replaceTextEmbeddingDocumentContributor(
					textEmbeddingDocumentContributor)) {

			_objectEntryModelDocumentContributor.contribute(document, _objectEntry);
		}

		String enContent = String.format(
			"localizedTextField: %s, textField: %s", enLocalizedValue,
			textFieldValue);

		String esContent = String.format(
			"localizedTextField: %s, textField: %s", esLocalizedValue,
			textFieldValue);

		_verifyLocalizedContribution(
			textEmbeddingDocumentContributor, document, "en_US", _objectEntry,
			enContent);

		_verifyLocalizedContribution(
			textEmbeddingDocumentContributor, document, "es_ES", _objectEntry,
			esContent);

		_verifyNoGlobalContribution(
			textEmbeddingDocumentContributor, document, _objectEntry);

		_verifyNoLocalizedContributionWithContent(
			textEmbeddingDocumentContributor, document, _objectEntry,
			String.format("textField: %s", textFieldValue));
	}

	@Test
	public void testContributesMissingLocalizedTextEmbeddings() throws Exception {
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor =
			Mockito.mock(TextEmbeddingDocumentContributor.class);

		Mockito.when(
			textEmbeddingDocumentContributor.getLanguageIds(
				Mockito.any())
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
				"localizedTextField_i18n",
				(Serializable)localizedValues
			).put(
				"textField", textFieldValue
			).build());

		Document document = _createDocument(_objectEntry);

		try (SafeCloseable safeCloseable =
				_replaceTextEmbeddingDocumentContributor(
					textEmbeddingDocumentContributor)) {

			_objectEntryModelDocumentContributor.contribute(document, _objectEntry);
		}

		String expectedContent = String.format(
			"localizedTextField: %s, textField: %s", enLocalizedValue,
			textFieldValue);

		_verifyLocalizedContribution(
			textEmbeddingDocumentContributor, document, "en_US", _objectEntry,
			expectedContent);

		_verifyLocalizedContribution(
			textEmbeddingDocumentContributor, document, "pt_PT", _objectEntry,
			expectedContent);

		_verifyNoLocalizedContributionForLanguage(
			textEmbeddingDocumentContributor, document, "es_ES", _objectEntry);

		_verifyNoGlobalContribution(
			textEmbeddingDocumentContributor, document, _objectEntry);
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

	@Inject(
		filter =
			"component.name=com.liferay.object.internal.search.spi.model.index.contributor.ObjectEntryModelDocumentContributor")
	private ModelDocumentContributor<ObjectEntry>
		_objectEntryModelDocumentContributor;

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
		ObjectDefinition objectDefinition,
		Map<String, Serializable> values)
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

	private SafeCloseable _replaceTextEmbeddingDocumentContributor(
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor) {

		TextEmbeddingDocumentContributor
			originalTextEmbeddingDocumentContributor =
				ReflectionTestUtil.getFieldValue(
					_objectEntryModelDocumentContributor,
					"_textEmbeddingDocumentContributor");

		ReflectionTestUtil.setFieldValue(
			_objectEntryModelDocumentContributor,
			"_textEmbeddingDocumentContributor",
			textEmbeddingDocumentContributor);

		return () -> ReflectionTestUtil.setFieldValue(
			_objectEntryModelDocumentContributor,
			"_textEmbeddingDocumentContributor",
			originalTextEmbeddingDocumentContributor);
	}

	private void _verifyGlobalContribution(
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor,
		Document document, ObjectEntry objectEntry, String expectedContent) {

		Mockito.verify(
			textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(document), Mockito.eq(objectEntry),
			Mockito.eq(expectedContent)
		);
	}

	private void _verifyLocalizedContribution(
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor,
		Document document, String languageId, ObjectEntry objectEntry,
		String expectedContent) {

		Mockito.verify(
			textEmbeddingDocumentContributor
		).contribute(
			Mockito.eq(document), Mockito.eq(languageId), Mockito.eq(objectEntry),
			Mockito.eq(expectedContent)
		);
	}

	private void _verifyNoGlobalContribution(
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor,
		Document document, ObjectEntry objectEntry) {

		Mockito.verify(
			textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.eq(objectEntry), Mockito.anyString());
	}

	private void _verifyNoLocalizedContribution(
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor,
		Document document, ObjectEntry objectEntry) {

		Mockito.verify(
			textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.anyString(), Mockito.eq(objectEntry),
			Mockito.anyString());
	}

	private void _verifyNoLocalizedContributionForLanguage(
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor,
		Document document, String languageId, ObjectEntry objectEntry) {

		Mockito.verify(
			textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.eq(languageId),
			Mockito.eq(objectEntry), Mockito.anyString());
	}

	private void _verifyNoLocalizedContributionWithContent(
		TextEmbeddingDocumentContributor textEmbeddingDocumentContributor,
		Document document, ObjectEntry objectEntry, String content) {

		Mockito.verify(
			textEmbeddingDocumentContributor, Mockito.never()
		).contribute(
			Mockito.eq(document), Mockito.anyString(), Mockito.eq(objectEntry),
			Mockito.eq(content));
	}

	private Document _createDocument(ObjectEntry objectEntry) {
		Document document = new DocumentImpl();

		document.addKeyword(
			Field.ENTRY_CLASS_PK,
			String.valueOf(objectEntry.getObjectEntryId()));

		return document;
	}

}
