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
			_companyId, RoleConstants.GUEST);

		_guestRoleId = String.valueOf(guestRole.getRoleId());
	}

	public void addPermissionRoleIdSet(Set<String> roleIdSet) {
		int size = _roleIdSets.size();

		_roleIdSets.add(roleIdSet);

		if (_roleIdSets.size() > size) {
			_removeRedundantSets();
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	public Set<Set<String>> getRoleIdSets() {
		return _roleIdSets;
	}

	private void _removeRedundantSets() {
		Iterator<Set<String>> comparingIterator = _roleIdSets.iterator();

		while (comparingIterator.hasNext()) {
			Set<String> comparingSet = comparingIterator.next();

			Iterator<Set<String>> searchingIterator = _roleIdSets.iterator();

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

	private final long _companyId;
	private final long _groupId;
	private final String _guestRoleId;
	private final Set<Set<String>> _roleIdSets = new HashSet<>();

}