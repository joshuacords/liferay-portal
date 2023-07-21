/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalServiceUtil;
import com.liferay.mobile.device.rules.util.test.MDRTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@RunWith(Arquillian.class)
public class MDRRuleGroupLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		Company company = CompanyLocalServiceUtil.getCompany(
			TestPropsValues.getCompanyId());

		Group companyGroup = company.getGroup();

		_ruleGroup = MDRTestUtil.addRuleGroup(companyGroup.getGroupId());

		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testSelectGlobalRulesNotPresent() throws Exception {
		testSelectableRuleGroups(false);
	}

	@Test
	public void testSelectGlobalRulesPresent() throws Exception {
		testSelectableRuleGroups(true);
	}

	protected void testSelectableRuleGroups(boolean includeGlobalGroup)
		throws Exception {

		Layout layout = LayoutTestUtil.addLayout(_group);

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (includeGlobalGroup) {
			params.put("includeGlobalScope", Boolean.TRUE);
		}

		List<MDRRuleGroup> ruleGroups =
			MDRRuleGroupLocalServiceUtil.searchByKeywords(
				layout.getGroupId(), StringPool.BLANK, params, false,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (includeGlobalGroup) {
			Assert.assertTrue(
				ruleGroups.toString(), ruleGroups.contains(_ruleGroup));
		}
		else {
			Assert.assertFalse(
				ruleGroups.toString(), ruleGroups.contains(_ruleGroup));
		}
	}

	@DeleteAfterTestRun
	private Group _group;

	private MDRRuleGroup _ruleGroup;

}