/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.roles.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.security.permission.test.util.BasePermissionTestCase;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Preston Crary
 */
@RunWith(Arquillian.class)
public class RoleServiceTest extends BasePermissionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testSearch() throws Exception {
		List<Role> roles = _roleService.search(
			group.getCompanyId(), StringPool.BLANK,
			new Integer[] {RoleConstants.TYPE_REGULAR}, new LinkedHashMap<>(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertTrue(
			"Role not found with permissions", roles.contains(_role));

		removePortletModelViewPermission();

		roles = _roleService.search(
			group.getCompanyId(), StringPool.BLANK,
			new Integer[] {RoleConstants.TYPE_REGULAR}, new LinkedHashMap<>(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertFalse(
			"Role found without permissions", roles.contains(_role));
	}

	@Test
	public void testSearchCount() throws Exception {
		int initialCount = _roleService.searchCount(
			group.getCompanyId(), StringPool.BLANK,
			new Integer[] {RoleConstants.TYPE_REGULAR}, new LinkedHashMap<>());

		removePortletModelViewPermission();

		Assert.assertEquals(
			initialCount - 1,
			_roleService.searchCount(
				group.getCompanyId(), StringPool.BLANK,
				new Integer[] {RoleConstants.TYPE_REGULAR},
				new LinkedHashMap<>()));
	}

	@Override
	protected void doSetUp() throws Exception {
		_role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);
	}

	@Override
	protected String getPrimKey() {
		return String.valueOf(_role.getRoleId());
	}

	@Override
	protected String getResourceName() {
		return Role.class.getName();
	}

	@Override
	protected String getRoleName() {
		return RoleConstants.USER;
	}

	@DeleteAfterTestRun
	private Role _role;

	@Inject
	private RoleService _roleService;

}