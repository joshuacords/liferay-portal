/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.item.layout.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalServiceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
public class SiteNavigationMenuItemLayoutTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddToAutoMenuFalseToMenu() throws PortalException {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
			TestPropsValues.getUserId(), _group.getGroupId(), "Auto Menu",
			SiteNavigationConstants.TYPE_DEFAULT, true, serviceContext);

		Map<Locale, String> nameMap = HashMapBuilder.put(
			LocaleUtil.getSiteDefault(), "welcome"
		).build();

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put("addToAutoMenus", Boolean.FALSE.toString());

		LayoutServiceUtil.addLayout(
			_group.getGroupId(), false, 0, nameMap, new HashMap<>(),
			new HashMap<>(), new HashMap<>(), new HashMap<>(),
			LayoutConstants.TYPE_PORTLET, typeSettingsProperties.toString(),
			false, new HashMap<>(), serviceContext);

		Assert.assertEquals(
			0,
			SiteNavigationMenuItemLocalServiceUtil.
				getSiteNavigationMenuItemsCount());
	}

	@Test
	public void testAddToAutoMenuFalseToPrimaryMenu() throws PortalException {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
			TestPropsValues.getUserId(), _group.getGroupId(), "Primary Menu",
			SiteNavigationConstants.TYPE_PRIMARY, true, serviceContext);

		Map<Locale, String> nameMap = HashMapBuilder.put(
			LocaleUtil.getSiteDefault(), "welcome"
		).build();

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put("addToAutoMenus", Boolean.FALSE.toString());

		LayoutServiceUtil.addLayout(
			_group.getGroupId(), false, 0, nameMap, new HashMap<>(),
			new HashMap<>(), new HashMap<>(), new HashMap<>(),
			LayoutConstants.TYPE_PORTLET, typeSettingsProperties.toString(),
			false, new HashMap<>(), serviceContext);

		Assert.assertEquals(
			0,
			SiteNavigationMenuItemLocalServiceUtil.
				getSiteNavigationMenuItemsCount());
	}

	@Test
	public void testAddToAutoMenuTrueToMenu() throws PortalException {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenu autoSiteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
				TestPropsValues.getUserId(), _group.getGroupId(), "Auto Menu",
				SiteNavigationConstants.TYPE_DEFAULT, true, serviceContext);

		SiteNavigationMenu primarySiteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
				TestPropsValues.getUserId(), _group.getGroupId(),
				"Primary Menu", SiteNavigationConstants.TYPE_PRIMARY, true,
				serviceContext);

		Map<Locale, String> nameMap = HashMapBuilder.put(
			LocaleUtil.getSiteDefault(), "welcome"
		).build();

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put(
			"siteNavigationMenuId",
			StringUtil.merge(
				new long[] {
					autoSiteNavigationMenu.getSiteNavigationMenuId(),
					primarySiteNavigationMenu.getSiteNavigationMenuId()
				}));

		LayoutServiceUtil.addLayout(
			_group.getGroupId(), false, 0, nameMap, new HashMap<>(),
			new HashMap<>(), new HashMap<>(), new HashMap<>(),
			LayoutConstants.TYPE_PORTLET, typeSettingsProperties.toString(),
			false, new HashMap<>(), serviceContext);

		Assert.assertEquals(
			2,
			SiteNavigationMenuItemLocalServiceUtil.
				getSiteNavigationMenuItemsCount());
	}

	@Test
	public void testAddToAutoMenuTrueToPrimaryMenu() throws PortalException {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		SiteNavigationMenu siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.addSiteNavigationMenu(
				TestPropsValues.getUserId(), _group.getGroupId(),
				"Primary Menu", SiteNavigationConstants.TYPE_PRIMARY, true,
				serviceContext);

		Map<Locale, String> nameMap = HashMapBuilder.put(
			LocaleUtil.getSiteDefault(), "welcome"
		).build();

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.put(
			"siteNavigationMenuId",
			StringUtil.merge(
				new long[] {siteNavigationMenu.getSiteNavigationMenuId()}));

		LayoutServiceUtil.addLayout(
			_group.getGroupId(), false, 0, nameMap, new HashMap<>(),
			new HashMap<>(), new HashMap<>(), new HashMap<>(),
			LayoutConstants.TYPE_PORTLET, typeSettingsProperties.toString(),
			false, new HashMap<>(), serviceContext);

		Assert.assertEquals(
			1,
			SiteNavigationMenuItemLocalServiceUtil.
				getSiteNavigationMenuItemsCount());
	}

	@DeleteAfterTestRun
	private Group _group;

}