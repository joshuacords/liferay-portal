/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.fragment.contributor.FragmentCollectionContributorTracker;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryLinkService;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionRequest;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionResponse;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletRenderResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.test.util.IndexerFixture;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import javax.portlet.ActionRequest;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Ricardo Couso
 */
@RunWith(Arquillian.class)
public class LayoutPublishedSearchTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_setUpLayoutIndexerFixture();
	}

	@Test
	public void testPublishedPageSearch() throws Exception {
		String name = RandomTestUtil.randomString();

		LocalizedValuesMap nameMap = new LocalizedValuesMap() {
			{
				put(LocaleUtil.US, name);
			}
		};

		LocalizedValuesMap friendlyUrlMap = new LocalizedValuesMap() {
			{
				String randomString = FriendlyURLNormalizerUtil.normalize(
					RandomTestUtil.randomString());

				put(LocaleUtil.US, StringPool.SLASH + randomString);
			}
		};

		Layout layout = LayoutLocalServiceUtil.addLayout(
			TestPropsValues.getUserId(), _group.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, nameMap.getValues(), null,
			null, null, null, LayoutConstants.TYPE_CONTENT, null, false,
			friendlyUrlMap.getValues(),
			ServiceContextTestUtil.getServiceContext());

		_layoutIndexerFixture.searchNoOne(name);

		_publishLayout(new MockHttpServletRequest(), layout);

		_layoutIndexerFixture.searchOnlyOne(name);
	}

	@Test
	public void testPublishedPrivatePageSearch() throws Exception {
		Layout layout = LayoutLocalServiceUtil.addLayout(
			TestPropsValues.getUserId(), _group.getGroupId(), true,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			LayoutConstants.TYPE_CONTENT, false, false, StringPool.BLANK,
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getGroupId(), TestPropsValues.getUserId()));

		String content = RandomTestUtil.randomString();

		_updateDraftLayout(layout, content);

		_layoutIndexerFixture.searchNoOne(content);

		_publishLayout(_getHttpServletRequest(layout), layout);

		_layoutIndexerFixture.searchOnlyOne(content);
	}

	private HttpServletRequest _getHttpServletRequest(Layout layout)
		throws Exception {

		MockHttpServletRequest httpServletRequest =
			new MockHttpServletRequest();

		httpServletRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE,
			new MockLiferayPortletRenderResponse());

		httpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY,
			_getThemeDisplay(layout, httpServletRequest));

		return httpServletRequest;
	}

	private ActionRequest _getMockLiferayPortletActionRequest(Layout layout)
		throws Exception {

		MockLiferayPortletActionRequest mockLiferayPortletActionRequest =
			new MockLiferayPortletActionRequest();

		mockLiferayPortletActionRequest.setAttribute(
			WebKeys.PORTLET_ID,
			ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET);
		mockLiferayPortletActionRequest.setAttribute(
			WebKeys.THEME_DISPLAY, _getThemeDisplay(layout, null));
		mockLiferayPortletActionRequest.setParameter(
			"groupId", String.valueOf(_group.getGroupId()));
		mockLiferayPortletActionRequest.setParameter(
			"classPK", String.valueOf(layout.getPlid()));

		return mockLiferayPortletActionRequest;
	}

	private ThemeDisplay _getThemeDisplay(
			Layout layout, HttpServletRequest httpServletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(
			_companyLocalService.getCompany(layout.getCompanyId()));
		themeDisplay.setLayout(layout);

		LayoutSet layoutSet = _group.getPublicLayoutSet();

		themeDisplay.setLayoutSet(layoutSet);

		themeDisplay.setLayoutTypePortlet(
			(LayoutTypePortlet)layout.getLayoutType());
		themeDisplay.setLookAndFeel(
			layoutSet.getTheme(), layoutSet.getColorScheme());

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(TestPropsValues.getUser());

		themeDisplay.setPermissionChecker(permissionChecker);

		themeDisplay.setRealUser(TestPropsValues.getUser());
		themeDisplay.setRequest(httpServletRequest);
		themeDisplay.setResponse(new MockHttpServletResponse());
		themeDisplay.setScopeGroupId(_group.getGroupId());
		themeDisplay.setSiteGroupId(_group.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());

		return themeDisplay;
	}

	private void _publishLayout(
			HttpServletRequest httpServletRequest, Layout layout)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				layout.getGroup(), TestPropsValues.getUserId());

		serviceContext.setRequest(httpServletRequest);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		Layout draftLayout = LayoutLocalServiceUtil.fetchLayout(
			PortalUtil.getClassNameId(Layout.class), layout.getPlid());

		_mvcActionCommand.processAction(
			_getMockLiferayPortletActionRequest(draftLayout),
			new MockLiferayPortletActionResponse());
	}

	private void _setUpLayoutIndexerFixture() {
		_layoutIndexerFixture = new IndexerFixture<>(Layout.class);
	}

	private void _updateDraftLayout(Layout layout, String value)
		throws Exception {

		FragmentEntry contributedFragmentEntry =
			_fragmentCollectionContributorTracker.getFragmentEntry(
				"BASIC_COMPONENT-heading");

		Layout draftLayout = LayoutLocalServiceUtil.fetchLayout(
			PortalUtil.getClassNameId(Layout.class), layout.getPlid());

		JSONObject inlineValueJSONObject = JSONUtil.put(
			"com.liferay.fragment.entry.processor.editable." +
				"EditableFragmentEntryProcessor",
			JSONUtil.put(
				"element-text",
				JSONUtil.put(
					"config", JSONFactoryUtil.createJSONObject()
				).put(
					"defaultValue", "default value"
				).put(
					layout.getDefaultLanguageId(), value
				)));

		_fragmentEntryLinkService.addFragmentEntryLink(
			_group.getGroupId(), 0,
			contributedFragmentEntry.getFragmentEntryId(),
			PortalUtil.getClassNameId(Layout.class), draftLayout.getPlid(),
			contributedFragmentEntry.getCss(),
			contributedFragmentEntry.getHtml(),
			contributedFragmentEntry.getJs(),
			contributedFragmentEntry.getConfiguration(),
			inlineValueJSONObject.toString(), StringPool.BLANK, 0,
			contributedFragmentEntry.getFragmentEntryKey(),
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));
	}

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject
	private FragmentCollectionContributorTracker
		_fragmentCollectionContributorTracker;

	@Inject
	private FragmentEntryLinkService _fragmentEntryLinkService;

	@DeleteAfterTestRun
	private Group _group;

	private IndexerFixture<Layout> _layoutIndexerFixture;

	@Inject(filter = "mvc.command.name=/content_layout/publish_layout")
	private MVCActionCommand _mvcActionCommand;

}