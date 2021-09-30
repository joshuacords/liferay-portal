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

package com.liferay.portal.search.internal.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.search.spi.model.permission.RoleSetContributorContext;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Joshua Cords
 */
public class RoleSetContributorContextImpl
	implements RoleSetContributorContext {

	public RoleSetContributorContextImpl(
			long companyId, long groupId, RoleLocalService roleLocalService)
		throws PortalException {

		_companyId = companyId;
		_groupId = groupId;

		Role guestRole = roleLocalService.getRole(
			companyId, RoleConstants.GUEST);

		Role ownerRole = roleLocalService.getRole(
			companyId, RoleConstants.OWNER);

		String guestRoleId = String.valueOf(guestRole.getRoleId());
		String ownerRoleId = String.valueOf(ownerRole.getRoleId());

		_accessPermissionRoleIdSetCombiner = new PermissionRoleIdSetCombiner(
			_companyId, _groupId, guestRoleId, ownerRoleId);

		_viewPermissionRoleIdSetCombiner = new PermissionRoleIdSetCombiner(
			_companyId, _groupId, guestRoleId, ownerRoleId);
	}

	//this might be strictly additive? I believe the lowest level access can never be removed
	@Override
	public void addAccessPermissionRoleIdSet(Set<String> roleIdSet) {
		_accessPermissionRoleIdSetCombiner.addRoleIdSet(roleIdSet);
	}

	//must be called exactly once per level
	@Override
	public void addViewPermissionRoleIdSet(Set<String> roleIdSet) {
		_viewPermissionRoleIdSetCombiner.addRoleIdSet(roleIdSet);
	}

	public Set<Set<String>> getAccessPermissionRoleIdSets() {
		return _accessPermissionRoleIdSetCombiner.getRoleIdSets();
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	public Set<Set<String>> getViewPermissionRoleIdSets() {
		return _viewPermissionRoleIdSetCombiner.getRoleIdSets();
	}

	public Set<Set<String>> getCombinedPermissionRoleIdSets() {
		return _permissionRoleIdSetCombinerUtil.combineRoleIdSets(this);
	}

	private class PermissionRoleIdSetCombinerUtil {

		public Set<Set<String>> combineRoleIdSets(
			RoleSetContributorContextImpl roleSetContributorContextImpl) {

			return combineRoleIdSets(
				roleSetContributorContextImpl.getAccessPermissionRoleIdSets(),
				roleSetContributorContextImpl.getViewPermissionRoleIdSets());
		}

		public Set<Set<String>> combineRoleIdSets(
			Set<Set<String>> roleIdSets1, Set<Set<String>> roleIdSets2) {

			Set<Set<String>> roleIdsCombinations = new HashSet<>();

			roleIdsCombinations.addAll(roleIdSets1);
			roleIdsCombinations.addAll(roleIdSets2);

			_removeRedundantSets(roleIdsCombinations);

			return roleIdsCombinations;
		}

		private void _removeRedundantSets(Set<Set<String>> roleIdSets) {
			Iterator<Set<String>> comparingIterator = roleIdSets.iterator();

			while (comparingIterator.hasNext()) {
				Set<String> comparingSet = comparingIterator.next();

				Iterator<Set<String>> searchingIterator = roleIdSets.iterator();

				while (searchingIterator.hasNext()) {
					Set<String> searchedSet = searchingIterator.next();

					if (comparingSet == searchedSet) {
						continue;
					}

					if (comparingSet.containsAll(searchedSet)) {
						comparingIterator.remove();

						break;
					}
				}
			}
		}
	}

	private final PermissionRoleIdSetCombinerUtil _permissionRoleIdSetCombinerUtil =
		new PermissionRoleIdSetCombinerUtil();
	private final PermissionRoleIdSetCombiner
		_accessPermissionRoleIdSetCombiner;
	private final long _companyId;
	private final long _groupId;
	private final PermissionRoleIdSetCombiner _viewPermissionRoleIdSetCombiner;

}