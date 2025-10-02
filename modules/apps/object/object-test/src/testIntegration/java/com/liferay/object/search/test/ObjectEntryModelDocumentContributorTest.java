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
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;
import com.liferay.portal.search.test.rule.SemanticSearchTestRule;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Joshua Cords
 */
@FeatureFlag("LPS-122920")
@RunWith(Arquillian.class)
public class ObjectEntryModelDocumentContributorTest {

   @Rule
	public SemanticSearchTestRule semanticSearchTestRule = new SemanticSearchTestRule();

    @ClassRule
    @Rule
    public static final AggregateTestRule aggregateTestRule =
        new AggregateTestRule(
            new LiferayIntegrationTestRule(),
            PermissionCheckerMethodTestRule.INSTANCE,
            SynchronousDestinationTestRule.INSTANCE);

    @Before
    public void setUp() throws Exception {
        semanticSearchTestRule.resetProviderConfiguration();
    }

    @Test
    public void testContributesLocalizedTextEmbeddings() throws Exception {
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

        _configureSemanticSearch(
            Arrays.asList("en_US", "es_ES"),
            Collections.singletonList(_objectDefinition.getClassName()));

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

		_objectEntryModelDocumentContributor.contribute(document, _objectEntry);

		String objectEntryContent = document.get("objectEntryContent");

		Assert.assertNotNull(objectEntryContent);

		Assert.assertTrue(
			objectEntryContent.contains(
				String.format("localizedTextField: %s", enLocalizedValue)));
        Assert.assertTrue(
            objectEntryContent.contains(
                String.format("textField: %s", textFieldValue)));

        _assertTextEmbedding(document, "en_US");
        _assertTextEmbedding(document, "es_ES");
        _assertNoTextEmbedding(document, "pt_PT");
    }

    @Test
    public void testContributesMissingLocalizedTextEmbeddings()
        throws Exception {

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

        _configureSemanticSearch(
            Arrays.asList("en_US", "pt_PT"),
            Collections.singletonList(_objectDefinition.getClassName()));

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

        _objectEntryModelDocumentContributor.contribute(document, _objectEntry);

        _assertTextEmbedding(document, "en_US");
        _assertTextEmbedding(document, "pt_PT");
        _assertNoTextEmbedding(document, "es_ES");
    }

    @Test
    public void testObjectEntryNonlocalizedTextEmbeddings() throws Exception {
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

        _configureSemanticSearch(
            Collections.singletonList("en_US"),
            Collections.singletonList(_objectDefinition.getClassName()));

        String textFieldValue = RandomTestUtil.randomString();

        _objectEntry = _addObjectEntry(
            _objectDefinition,
            HashMapBuilder.<String, Serializable>put(
                "textField", textFieldValue
            ).build());

        Document document = _createDocument(_objectEntry);

        _objectEntryModelDocumentContributor.contribute(document, _objectEntry);

        Assert.assertEquals(
            String.format("textField: %s", textFieldValue),
            document.get("objectEntryContent"));

        _assertTextEmbedding(document, "en_US");
        _assertNoTextEmbedding(document, "es_ES");
        _assertNoTextEmbedding(document, "pt_PT");
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

	private void _assertNoTextEmbedding(
		Document document, String languageId) {

		Assert.assertNull(
			document.getField(_getTextEmbeddingFieldName(languageId)));
	}

	private void _assertTextEmbedding(Document document, String languageId) {
		Field field = document.getField(_getTextEmbeddingFieldName(languageId));

		Assert.assertNotNull(
			"Missing text embedding for " + languageId, field);

		String[] values = field.getValues();

		Assert.assertNotNull(values);
		Assert.assertEquals(
			_EMBEDDING_VECTOR_DIMENSIONS, values.length);
	}

	private void _configureSemanticSearch(
		List<String> languageIds, List<String> modelClassNames)
		throws Exception {

		semanticSearchTestRule.configureProvider(languageIds, modelClassNames);
	}

	private String _getTextEmbeddingFieldName(String languageId) {
		return String.format(
			"text_embedding_%d_%s", _EMBEDDING_VECTOR_DIMENSIONS, languageId);
	}

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

	private static final int _EMBEDDING_VECTOR_DIMENSIONS = 768;

}
