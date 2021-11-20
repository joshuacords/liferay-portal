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

import com.liferay.petra.string.StringPool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Joshua Cords
 */
public class DynamicInheritanceRoleIdSetCombiner {

	public DynamicInheritanceRoleIdSetCombiner(
		long companyId, long groupId, String guestRoleId, String ownerRoleId) {

		_companyId = companyId;
		_groupId = groupId;
		_guestRoleId = guestRoleId;
		_ownerRoleId = ownerRoleId;
	}

	public void addRoleIdLevel(Set<String> roleIdSet) {
		if (!_assigned) {
			_assigned = true;

			if (roleIdSet.contains(_guestRoleId)) {
				Set<String> guestSet = new HashSet<>();

				guestSet.add(_guestRoleId);

				_setRoleIdsIndividuallyAsBaseLevel(guestSet);

				return;
			}

			_setRoleIdsIndividuallyAsBaseLevel(roleIdSet);

			return;
		}

		if (_roleIdSetsManager.isEmpty() || roleIdSet.isEmpty()) {
			_roleIdSetsManager.clear();

			return;
		}

		if (roleIdSet.contains(_guestRoleId)) {
			return;
		}

		if (_roleIdSetsManager.containsGuest()) {
			_setRoleIdsIndividuallyAsBaseLevel(roleIdSet);

			return;
		}

		Set<String> newRoleIds = new HashSet<>();

		for (String roleId : roleIdSet) {
			boolean foundRoleId = false;

			Iterator<Set<String>> roleIdSetsIterator =
				_roleIdSetsManager.iterator();

			boolean ownerRole = _isOwnerRole(roleId);

			while (roleIdSetsIterator.hasNext()) {
				Set<String> currentRoleIdSet = roleIdSetsIterator.next();

				if (currentRoleIdSet.contains(roleId)) {
					if (ownerRole &&
						_containsDifferentOwnerRole(currentRoleIdSet, roleId)) {

						continue;
					}

					_updatedRoleIdSets.add(currentRoleIdSet);

					roleIdSetsIterator.remove();

					foundRoleId = true;
				}
			}

			if (ownerRole) {
				_ownerRoleIds.add(roleId);
			}

			if (!foundRoleId) {
				for (Set<String> roleIdSetWithPermission : _updatedRoleIdSets) {
					if (roleIdSetWithPermission.contains(roleId)) {
						foundRoleId = true;
					}
				}
			}

			if (!foundRoleId) {
				newRoleIds.add(roleId);
			}
		}

		if (_roleIdSetsManager.isEmpty() || newRoleIds.isEmpty()) {
			_roleIdSetsManager.clear();
		}
		else {
			Set<Set<String>> newRoleIdSetCombinations = new HashSet<>();

			Iterator<Set<String>> roleIdSetsIterator =
				_roleIdSetsManager.iterator();

			while (roleIdSetsIterator.hasNext()) {
				Set<String> currentRoleIdSet = roleIdSetsIterator.next();

				Set<Set<String>> roleIdSetCopies = new HashSet<>(
					newRoleIds.size());

				for (String newRoleId : newRoleIds) {
					if (_isOwnerRole(newRoleId) &&
						_containsDifferentOwnerRole(
							currentRoleIdSet, newRoleId)) {

						continue;
					}

					Set<String> roleIdSetCopy = new HashSet<>(currentRoleIdSet);

					roleIdSetCopy.add(newRoleId);

					roleIdSetCopies.add(roleIdSetCopy);
				}

				newRoleIdSetCombinations.addAll(roleIdSetCopies);

				roleIdSetsIterator.remove();
			}

			_updatedRoleIdSets.addAll(newRoleIdSetCombinations);
		}

		_roleIdSetsManager.clear();
		_roleIdSetsManager.addAll(_updatedRoleIdSets);

		_updatedRoleIdSets.clear();
	}

	public Set<Set<String>> getRoleIdSets() {
		return _roleIdSetsManager.getRoleIdSets();
	}

	public boolean isAssigned() {
		return _assigned;
	}

	private boolean _containsDifferentOwnerRole(
		Set<String> roleIdSet, String roleId) {

		for (String ownerRoleId : _ownerRoleIds) {
			if (ownerRoleId.equals(roleId)) {
				continue;
			}

			if (roleIdSet.contains(ownerRoleId)) {
				return true;
			}
		}

		return false;
	}

	private boolean _isOwnerRole(String roleId) {
		String[] roleIdArray = roleId.split(StringPool.DASH);

		if (roleIdArray.length != 2) {
			return false;
		}

		if (roleIdArray[1].equals(_ownerRoleId)) {
			return true;
		}

		return false;
	}

	private void _setRoleIdsIndividuallyAsBaseLevel(Set<String> roleIdSet) {
		_roleIdSetsManager.clear();

		for (String roleId : roleIdSet) {
			Set<String> set = new HashSet<>();

			set.add(roleId);

			_roleIdSetsManager.add(set);
		}
	}

	private boolean _assigned;
	private final long _companyId;
	private final long _groupId;
	private final String _guestRoleId;
	private final String _ownerRoleId;
	private final Set<String> _ownerRoleIds = new HashSet<>();
	private final RoleIdSetsManager _roleIdSetsManager =
		new RoleIdSetsManager();
	private final Set<Set<String>> _updatedRoleIdSets = new HashSet<>();

	private class RoleIdSetsManager {

		public void add(Set<String> roleIdsSet) {
			for (String roleId : roleIdsSet) {
				if (!_containsGuest && roleId.equals(_guestRoleId)) {
					_containsGuest = true;
				}

				if (_isOwnerRole(roleId)) {
					_ownerRoleIds.add(roleId);
				}
			}

			_roleIdSets.add(roleIdsSet);
		}

		public void addAll(Set<Set<String>> roleIdSets) {
			for (Set<String> roleIdSet : roleIdSets) {
				add(roleIdSet);
			}
		}

		public void clear() {
			_containsGuest = false;
			_roleIdSets.clear();
		}

		public boolean containsGuest() {
			return _containsGuest;
		}

		public Set<Set<String>> getRoleIdSets() {
			return _roleIdSets;
		}

		public boolean isEmpty() {
			return _roleIdSets.isEmpty();
		}

		public Iterator<Set<String>> iterator() {
			return _roleIdSets.iterator();
		}

		private boolean _containsGuest;
		private final Set<Set<String>> _roleIdSets = new HashSet<>();

	}

}