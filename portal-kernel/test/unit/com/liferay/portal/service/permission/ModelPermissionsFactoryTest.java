/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleWrapper;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceWrapper;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.service.permission.ModelPermissionsFactory;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Jorge Ferrer
 */
@PrepareForTest(ResourceActionsUtil.class)
@RunWith(PowerMockRunner.class)
public class ModelPermissionsFactoryTest extends PowerMockito {

	@BeforeClass
	public static void setUpClass() throws Exception {
		ReflectionTestUtil.setFieldValue(
			RoleLocalServiceUtil.class, "_service",
			new RoleLocalServiceWrapper(null) {

				@Override
				public Role getDefaultGroupRole(long groupId) {
					return new RoleWrapper(null) {

						@Override
						public String getName() {
							return RoleConstants.SITE_MEMBER;
						}

					};
				}

				@Override
				public Role getRole(long companyId, String name) {
					return new RoleWrapper(null) {

						@Override
						public String getName() {
							return name;
						}

					};
				}

			});
	}

	@Test
	public void testCreateWithEmptyPermissions() throws Exception {
		String[] groupPermissions = {};
		String[] guestPermissions = {};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 0, roleNames.size());
	}

	@Test
	public void testCreateWithGroupPermissions() throws Exception {
		String[] groupPermissions = {ActionKeys.VIEW};
		String[] guestPermissions = {};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 1, roleNames.size());

		Iterator<String> iterator = roleNames.iterator();

		String roleName = iterator.next();

		Assert.assertEquals(
			RoleConstants.PLACEHOLDER_DEFAULT_GROUP_ROLE, roleName);
		Assert.assertEquals(
			ListUtil.fromArray(groupPermissions),
			modelPermissions.getActionIdsList(roleName));
	}

	@Test
	public void testCreateWithGuestAndGroupPermissions() throws Exception {
		String[] groupPermissions = {ActionKeys.VIEW};
		String[] guestPermissions = {ActionKeys.VIEW};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 2, roleNames.size());

		Collection<String> viewActionIdRoleNames =
			modelPermissions.getRoleNames(ActionKeys.VIEW);

		Assert.assertEquals(
			viewActionIdRoleNames.toString(), 2, viewActionIdRoleNames.size());
	}

	@Test
	public void testCreateWithGuestPermissions() throws Exception {
		String[] groupPermissions = {};
		String[] guestPermissions = {ActionKeys.VIEW};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 1, roleNames.size());

		Iterator<String> iterator = roleNames.iterator();

		String roleName = iterator.next();

		Assert.assertEquals(RoleConstants.GUEST, roleName);
		Assert.assertArrayEquals(
			guestPermissions, modelPermissions.getActionIds(roleName));
	}

	@Test
	public void testCreateWithNullPermissions() throws Exception {
		String[] groupPermissions = null;
		String[] guestPermissions = null;

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertTrue(roleNames.toString(), roleNames.isEmpty());
	}

	@Test
	public void testCreateWithoutParameters() throws Exception {
		Map<String, String[]> parameterMap = new HashMap<>();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setParameters(parameterMap);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			mockHttpServletRequest);

		Assert.assertNull(modelPermissions);
	}

	@Test
	public void testCreateWithoutParametersAndWithClassName() throws Exception {
		Map<String, String[]> parameterMap = new HashMap<>();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setParameters(parameterMap);

		String className = RandomTestUtil.randomString();

		mockStatic(ResourceActionsUtil.class);

		when(
			ResourceActionsUtil.getModelResourceGroupDefaultActions(className)
		).thenReturn(
			Arrays.asList(ActionKeys.VIEW)
		);

		when(
			ResourceActionsUtil.getModelResourceGuestDefaultActions(className)
		).thenReturn(
			Arrays.asList(ActionKeys.VIEW)
		);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			mockHttpServletRequest, className);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 2, roleNames.size());

		Assert.assertTrue(roleNames.contains(RoleConstants.GUEST));

		Assert.assertTrue(
			roleNames.contains(RoleConstants.PLACEHOLDER_DEFAULT_GROUP_ROLE));

		Assert.assertArrayEquals(
			new String[] {ActionKeys.VIEW},
			modelPermissions.getActionIds(RoleConstants.GUEST));

		Assert.assertArrayEquals(
			new String[] {ActionKeys.VIEW},
			modelPermissions.getActionIds(
				RoleConstants.PLACEHOLDER_DEFAULT_GROUP_ROLE));
	}

	@Test
	public void testCreateWithParameterForOneRole() throws Exception {
		Map<String, String[]> parameterMap = HashMapBuilder.put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.GUEST,
			new String[] {ActionKeys.VIEW}
		).build();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setParameters(parameterMap);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			mockHttpServletRequest);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 1, roleNames.size());

		Assert.assertTrue(roleNames.contains(RoleConstants.GUEST));

		Assert.assertArrayEquals(
			new String[] {ActionKeys.VIEW},
			modelPermissions.getActionIds(RoleConstants.GUEST));
	}

	@Test
	public void testCreateWithParameterForOneRoleAndClassName()
		throws Exception {

		String className = RandomTestUtil.randomString();

		Map<String, String[]> parameterMap = HashMapBuilder.put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.GUEST,
			new String[] {ActionKeys.VIEW}
		).put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX + className +
				RoleConstants.GUEST,
			new String[] {ActionKeys.UPDATE, ActionKeys.VIEW}
		).build();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setParameters(parameterMap);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			mockHttpServletRequest, className);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 1, roleNames.size());

		Assert.assertTrue(roleNames.contains(RoleConstants.GUEST));

		Assert.assertArrayEquals(
			new String[] {ActionKeys.UPDATE, ActionKeys.VIEW},
			modelPermissions.getActionIds(RoleConstants.GUEST));
	}

	@Test
	public void testCreateWithParameterForTwoRoles() throws Exception {
		Map<String, String[]> parameterMap = HashMapBuilder.put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.GUEST,
			new String[] {ActionKeys.VIEW}
		).put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.SITE_MEMBER,
			new String[] {ActionKeys.UPDATE, ActionKeys.VIEW}
		).build();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setParameters(parameterMap);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			mockHttpServletRequest);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 2, roleNames.size());

		Assert.assertTrue(roleNames.contains(RoleConstants.GUEST));
		Assert.assertTrue(roleNames.contains(RoleConstants.SITE_MEMBER));

		Assert.assertArrayEquals(
			new String[] {ActionKeys.VIEW},
			modelPermissions.getActionIds(RoleConstants.GUEST));
		Assert.assertArrayEquals(
			new String[] {ActionKeys.UPDATE, ActionKeys.VIEW},
			modelPermissions.getActionIds(RoleConstants.SITE_MEMBER));
	}

	@Test
	public void testCreateWithParameterForTwoRolesAndClassName()
		throws Exception {

		String className = RandomTestUtil.randomString();

		Map<String, String[]> parameterMap = HashMapBuilder.put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.GUEST,
			new String[] {ActionKeys.VIEW}
		).put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.SITE_MEMBER,
			new String[] {ActionKeys.VIEW}
		).put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX + className +
				RoleConstants.ORGANIZATION_USER,
			new String[] {ActionKeys.DELETE, ActionKeys.VIEW}
		).put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX + className +
				RoleConstants.POWER_USER,
			new String[] {ActionKeys.UPDATE, ActionKeys.VIEW}
		).build();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setParameters(parameterMap);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			mockHttpServletRequest, className);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 2, roleNames.size());

		Assert.assertTrue(roleNames.contains(RoleConstants.POWER_USER));
		Assert.assertTrue(roleNames.contains(RoleConstants.ORGANIZATION_USER));

		Assert.assertArrayEquals(
			new String[] {ActionKeys.UPDATE, ActionKeys.VIEW},
			modelPermissions.getActionIds(RoleConstants.POWER_USER));
		Assert.assertArrayEquals(
			new String[] {ActionKeys.DELETE, ActionKeys.VIEW},
			modelPermissions.getActionIds(RoleConstants.ORGANIZATION_USER));
	}

}