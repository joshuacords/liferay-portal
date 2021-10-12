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

package com.liferay.portal.search.spi.model.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Joshua Cords
 */
public class DynamicInheritanceRoleSetContributorContext{

	public DynamicInheritanceRoleSetContributorContext(
		RoleSetContributorContext roleSetContributorContext)
		throws PortalException {

		_companyId = roleSetContributorContext.getCompanyId();
		_groupId = roleSetContributorContext.getGroupId();
		RoleLocalService roleLocalService =
			roleSetContributorContext.getRoleLocalService();

		Role guestRole = roleLocalService.getRole(
			_companyId, RoleConstants.GUEST);

		Role ownerRole = roleLocalService.getRole(
			_companyId, RoleConstants.OWNER);

		_guestRoleId = String.valueOf(guestRole.getRoleId());
		String ownerRoleId = String.valueOf(ownerRole.getRoleId());

		_accessRoleIdSetCombiner = new DynamicInheritanceRoleIdSetCombiner(
			_companyId, _groupId, _guestRoleId, ownerRoleId);

		_viewRoleIdSetCombiner = new DynamicInheritanceRoleIdSetCombiner(
			_companyId, _groupId, _guestRoleId, ownerRoleId);
	}

	public boolean accessAssigned() {
		return _accessRoleIdSetCombiner.isAssigned();
	}
//add layer
	public void addAccessPermissionRoleIdSet(Set<String> roleIdSet) {
		_accessRoleIdSetCombiner.addRoleIdSet(roleIdSet);
	}

	public void addViewPermissionRoleIdSet(Set<String> roleIdSet) {
		_viewRoleIdSetCombiner.addRoleIdSet(roleIdSet);
	}

	public Set<Set<String>> getCombinedPermissionRoleIdSets() {
		return _combineRoleIdSets(
			_accessRoleIdSetCombiner.getRoleIdSets(),
			_viewRoleIdSetCombiner.getRoleIdSets());
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	private Set<Set<String>> _combineRoleIdSets(
		Set<Set<String>> roleIdSets1, Set<Set<String>> roleIdSets2) {

		Set<Set<String>> roleIdsCombinations = new HashSet<>();

		if (_guestRolePresent(roleIdSets1)) {
			roleIdsCombinations.addAll(roleIdSets1);

			return roleIdsCombinations;
		}

		if (_guestRolePresent(roleIdSets2)) {
			roleIdsCombinations.addAll(roleIdSets2);

			return roleIdsCombinations;
		}

		roleIdsCombinations.addAll(roleIdSets1);
		roleIdsCombinations.addAll(roleIdSets2);

		_removeRedundantSets(roleIdsCombinations);

		return roleIdsCombinations;
	}

	private boolean _guestRolePresent(Set<Set<String>> roleIdSets) {
		if (roleIdSets.size() == 1) {
			for (Set<String> roleIdSet : roleIdSets) {
				if (roleIdSet.contains(_guestRoleId)) {
					return true;
				}
			}
		}

		return false;
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

	private final DynamicInheritanceRoleIdSetCombiner _accessRoleIdSetCombiner;
	private final long _companyId;
	private final long _groupId;
	private final String _guestRoleId;
	private final DynamicInheritanceRoleIdSetCombiner _viewRoleIdSetCombiner;

}