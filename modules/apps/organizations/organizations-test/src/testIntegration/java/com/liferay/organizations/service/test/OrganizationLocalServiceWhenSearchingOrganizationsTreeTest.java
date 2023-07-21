/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.organizations.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.comparator.OrganizationNameComparator;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pei-Jung Lan
 */
@RunWith(Arquillian.class)
public class OrganizationLocalServiceWhenSearchingOrganizationsTreeTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		Organization rootOrganization = OrganizationTestUtil.addOrganization();

		_organization = OrganizationTestUtil.addOrganization(
			rootOrganization.getOrganizationId(), RandomTestUtil.randomString(),
			false);

		Organization suborganization = OrganizationTestUtil.addOrganization(
			_organization.getOrganizationId(), RandomTestUtil.randomString(),
			false);

		_organizations.add(suborganization);

		_organizations.add(_organization);

		_organizations.add(rootOrganization);

		_user = UserTestUtil.addUser();

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(_user);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		UserLocalServiceUtil.addOrganizationUsers(
			_organization.getOrganizationId(), new long[] {_user.getUserId()});
	}

	@After
	public void tearDown() throws Exception {
		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);
	}

	@Test
	public void testShouldIncludeSuborganizationsAsAdminUser()
		throws Exception {

		UserTestUtil.addUserGroupRole(
			_user.getUserId(), _organization.getGroupId(),
			RoleConstants.ORGANIZATION_ADMINISTRATOR);

		_assertSearch(true);
	}

	@Test
	public void testShouldIncludeSuborganizationsWitManageSuborganizationPermission()
		throws Exception {

		_role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		RoleTestUtil.addResourcePermission(
			_role, Organization.class.getName(),
			ResourceConstants.SCOPE_COMPANY,
			String.valueOf(_user.getCompanyId()),
			ActionKeys.MANAGE_SUBORGANIZATIONS);

		_userLocalService.addRoleUser(_role.getRoleId(), _user);

		_assertSearch(true);
	}

	@Test
	public void testShouldNotIncludeSuborganizationsWithoutManageSuborganizationPermission()
		throws Exception {

		_assertSearch(false);
	}

	private void _assertSearch(boolean includeSuborganizations)
		throws PortalException {

		LinkedHashMap<String, Object> organizationParams =
			LinkedHashMapBuilder.<String, Object>put(
				"organizationsTree", _user.getOrganizations(true)
			).build();

		BaseModelSearchResult<Organization> baseModelSearchResult =
			OrganizationLocalServiceUtil.searchOrganizations(
				_user.getCompanyId(),
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, null,
				organizationParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new Sort("name", false));

		List<Organization> expectedSearchResults = new ArrayList<>();

		expectedSearchResults.add(_organization);

		if (includeSuborganizations) {
			expectedSearchResults.addAll(_organization.getSuborganizations());
		}

		Assert.assertEquals(
			expectedSearchResults.size(), baseModelSearchResult.getLength());

		List<Organization> indexerSearchResults =
			baseModelSearchResult.getBaseModels();

		ListUtil.sort(
			indexerSearchResults, new OrganizationNameComparator(true));
		Assert.assertEquals(
			ListUtil.sort(
				expectedSearchResults, new OrganizationNameComparator(true)),
			indexerSearchResults);

		List<Organization> finderSearchResults =
			OrganizationLocalServiceUtil.search(
				_user.getCompanyId(),
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, null, null,
				null, null, organizationParams, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new OrganizationNameComparator(true));

		Assert.assertEquals(indexerSearchResults, finderSearchResults);
	}

	private Organization _organization;

	@DeleteAfterTestRun
	private final List<Organization> _organizations = new ArrayList<>();

	private PermissionChecker _originalPermissionChecker;

	@DeleteAfterTestRun
	private Role _role;

	@DeleteAfterTestRun
	private User _user;

	@Inject
	private UserLocalService _userLocalService;

}