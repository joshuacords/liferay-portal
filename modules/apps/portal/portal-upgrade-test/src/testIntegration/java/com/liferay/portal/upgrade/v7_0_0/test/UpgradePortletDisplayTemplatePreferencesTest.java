/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.kernel.DDMTemplate;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.template.TemplateHandlerRegistry;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.v7_0_0.UpgradePortletDisplayTemplatePreferences;
import com.liferay.portlet.dynamicdatamapping.util.test.DDMTemplateTestUtil;

import java.util.Map;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo García
 */
@RunWith(Arquillian.class)
public class UpgradePortletDisplayTemplatePreferencesTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testUpgrade() throws Exception {
		_group = GroupTestUtil.addGroup();

		long[] classNameIds = _templateHandlerRegistry.getClassNameIds();
		long resourceClassNameId = _portal.getClassNameId(
			"com.liferay.portlet.display.template.PortletDisplayTemplate");

		DDMTemplate ddmTemplate = DDMTemplateTestUtil.addTemplate(
			_group.getGroupId(), classNameIds[0], 0, resourceClassNameId);

		_layout = LayoutTestUtil.addLayout(_group);

		String displayStylePrefix62 = ReflectionTestUtil.getFieldValue(
			UpgradePortletDisplayTemplatePreferences.class,
			"DISPLAY_STYLE_PREFIX_6_2");

		setPortletDisplayStyle(
			"portlet1", displayStylePrefix62 + ddmTemplate.getUuid());

		setPortletDisplayStyle("portlet2", "testDisplayStyle");

		UpgradePortletDisplayTemplatePreferences
			upgradePortletDisplayTemplatePreferences =
				new UpgradePortletDisplayTemplatePreferences();

		upgradePortletDisplayTemplatePreferences.upgrade();

		CacheRegistryUtil.clear();

		_layout = _layoutLocalService.getLayout(_layout.getPlid());

		Assert.assertEquals(
			PortletDisplayTemplateManager.DISPLAY_STYLE_PREFIX +
				ddmTemplate.getTemplateKey(),
			getPortletDisplayStyle("portlet1"));
		Assert.assertEquals(
			"testDisplayStyle", getPortletDisplayStyle("portlet2"));
	}

	protected String getPortletDisplayStyle(String portletId) throws Exception {
		PortletPreferences portletPreferences =
			LayoutTestUtil.getPortletPreferences(_layout, portletId);

		return portletPreferences.getValue("displayStyle", StringPool.BLANK);
	}

	protected void setPortletDisplayStyle(String portletId, String displayStyle)
		throws Exception {

		Map<String, String> portletPreferencesMap = HashMapBuilder.put(
			"displayStyle", displayStyle
		).put(
			"displayStyleGroupId", String.valueOf(_group.getGroupId())
		).build();

		LayoutTestUtil.updateLayoutPortletPreferences(
			_layout, portletId, portletPreferencesMap);
	}

	@DeleteAfterTestRun
	private Group _group;

	private Layout _layout;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private Portal _portal;

	@Inject
	private TemplateHandlerRegistry _templateHandlerRegistry;

}