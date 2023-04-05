/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.journal.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.configuration.DDMIndexerConfiguration;
import com.liferay.dynamic.data.mapping.form.field.type.constants.DDMFormFieldTypeConstants;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.constants.FieldConstants;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMTemplateTestUtil;
import com.liferay.dynamic.data.mapping.test.util.search.TestOrderHelper;
import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.constants.JournalArticleConstants;
import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderServiceUtil;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.GuestOrUserUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.test.util.BasePermissionSearchTestCase;
import com.liferay.portal.search.test.util.BaseSearchTestCase;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.kernel.model.Role;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/**
 * @author Juan Fernández
 * @author Tibor Lipusz
 */
@RunWith(Arquillian.class)
public class JournalArticlePermissionSearchTest extends
	BasePermissionSearchTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		ConfigurationTestUtil.saveConfiguration(
			DDMIndexerConfiguration.class.getName(),
			HashMapDictionaryBuilder.<String, Object>put(
				"enableLegacyDDMIndexFields", false
			).build());
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		ConfigurationTestUtil.deleteConfiguration(
			DDMIndexerConfiguration.class.getName());
	}

	@Before
	@Override
	public void setUp() throws Exception {
		UserTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();

		CompanyThreadLocal.setCompanyId(TestPropsValues.getCompanyId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setCompanyId(TestPropsValues.getCompanyId());

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				TestPropsValues.getUserId(), true);

		_originalPortalPreferencesXML = PortletPreferencesFactoryUtil.toXML(
			portalPreferences);

		portalPreferences.setValue("", "articleCommentsEnabled", "true");

		PortalPreferencesLocalServiceUtil.updatePreferences(
			TestPropsValues.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			PortletPreferencesFactoryUtil.toXML(portalPreferences));

		_journalServiceConfiguration =
			ConfigurationProviderUtil.getCompanyConfiguration(
				JournalServiceConfiguration.class,
				TestPropsValues.getCompanyId());
	}

	@After
	public void tearDown() throws Exception {
		PortalPreferencesLocalServiceUtil.updatePreferences(
			TestPropsValues.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			_originalPortalPreferencesXML);
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		JournalFolder folder = (JournalFolder)parentBaseModel;

		long folderId = JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (folder != null) {
			folderId = folder.getFolderId();
		}

		return JournalTestUtil.addArticleWithWorkflow(
			group.getGroupId(), folderId, keywords,
			RandomTestUtil.randomString(50), approved, serviceContext);
	}

	@Override
	protected String getPrimKey(BaseModel<?> baseModel) {
		if (baseModel instanceof JournalArticle) {
			JournalArticle journalArticle = (JournalArticle)baseModel;

			return String.valueOf(journalArticle.getResourcePrimKey());
		}
		else if (baseModel instanceof JournalFolder) {
			JournalFolder journalFolder = (JournalFolder)baseModel;

			return String.valueOf(journalFolder.getFolderId());
		}

		return null;
	}

	protected void assertEquals(
			long length, BooleanQuery query, SearchContext searchContext)
		throws Exception {

		Hits hits = IndexSearcherHelperUtil.search(searchContext, query);

		Assert.assertEquals(hits.toString(), length, hits.getLength());
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return JournalArticle.class;
	}

//	@Override
//	protected Long getBaseModelClassPK(ClassedModel classedModel) {
//		JournalArticle article = (JournalArticle)classedModel;
//
//		if ((article.isDraft() || article.isPending()) &&
//			(article.getVersion() != JournalArticleConstants.VERSION_DEFAULT)) {
//
//			return article.getPrimaryKey();
//		}
//
//		return article.getResourcePrimKey();
//	}

//	@Override
//	protected BaseModel<?> getParentBaseModel(
//			BaseModel<?> parentBaseModel, ServiceContext serviceContext)
//		throws Exception {
//
//		return JournalTestUtil.addFolder(
//			(Long)parentBaseModel.getPrimaryKeyObj(),
//			RandomTestUtil.randomString(), serviceContext);
//	}

	@Override
	protected BaseModel<?> getParentBaseModel(
			Group group, ServiceContext serviceContext)
		throws Exception {

		return JournalTestUtil.addFolder(
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), serviceContext);
	}

	@Override
	protected BaseModel<?> getParentBaseModel(
		Group group, Role role, ServiceContext serviceContext)
		throws Exception {



		return JournalTestUtil.addFolder(
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), serviceContext);
	}

//	protected BaseModel<?> getPermissionedParentBaseModel(
//		Group group, ServiceContext serviceContext)
//		throws Exception {
//
//		return JournalTestUtil.addFolder(
//			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
//			RandomTestUtil.randomString(), serviceContext);
//	}

//	@Override
//	protected String getParentBaseModelClassName() {
//		return JournalFolder.class.getName();
//	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

//	@Override
//	protected BaseModel<?> updateBaseModel(
//			BaseModel<?> baseModel, String keywords,
//			ServiceContext serviceContext)
//		throws Exception {
//
//		JournalArticle article = (JournalArticle)baseModel;
//
//		return JournalTestUtil.updateArticle(
//			article, keywords, article.getContent(), false, true,
//			serviceContext);
//	}

	private JournalServiceConfiguration _journalServiceConfiguration;
	private String _originalPortalPreferencesXML;

}