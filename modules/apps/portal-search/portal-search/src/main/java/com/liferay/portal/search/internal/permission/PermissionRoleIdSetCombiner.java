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

import com.liferay.petra.string.StringPool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Joshua Cords
 */
public class PermissionRoleIdSetCombiner {

	public PermissionRoleIdSetCombiner(
		long companyId, long groupId, String guestRoleId, String ownerRoleId) {

		_companyId = companyId;
		_groupId = groupId;
		_guestRoleId = guestRoleId;
		_ownerRoleId = ownerRoleId;
		_roleIdSetsManager = new RoleIdSetsManager();
	}

	public void addRoleIdSet(Set<String> roleIdSet) {
		if (!_assigned) {
			_assigned = true;
			_assignFirstSet(roleIdSet);

			return;
		}

		if (_roleIdSets.isEmpty() || roleIdSet.isEmpty()) {
			_roleIdSets.clear();

			if (_roleIdSetsManager.isEmpty() || roleIdSet.isEmpty()) {
				_roleIdSetsManager.clear();

				return;
			}

			return;
		}

		if (roleIdSet.contains(_guestRoleId)) {
			return;
		}

		if (_roleIdSetsContainGuest()) { //make into flag
			_roleIdSets.clear();
			_addIndividuallyToRoleIdSets(roleIdSet);

			if (_roleIdSetsManager.containsGuest()) {
				_roleIdSetsManager.clear();
				_addIndividuallyToRoleIdSets(roleIdSet);

				return;
			}

			return;
		}

		Set<String> newRoleIds = _parseNewRoleIds(roleIdSet);

		_crossCombineNewRoleIdsWithRoleIdSets(newRoleIds);

		_roleIdSets.clear();
		_roleIdSetsManager.clear();
		_roleIdSets.addAll(_updatedRoleIdSets);
		_roleIdSetsManager.addAll(_updatedRoleIdSets);
		_updatedRoleIdSets.clear();
	}

	public Set<Set<String>> getRoleIdSets() {
		return _roleIdSets;
	}

	public boolean isAssigned() {
		return _assigned;
	}

	private void _addIndividuallyToRoleIdSets(Set<String> roleIdSet) {
		for (String roleId : roleIdSet) {
			Set<String> set = new HashSet<>();

			set.add(roleId);

			_roleIdSetsManager.add(set);
			_addToRoleIdSets(set);
		}
	}

	private void _assignFirstSet(Set<String> roleIdSet) {
		if (roleIdSet.contains(_guestRoleId)) {
			Set<String> set = new HashSet<>();

			set.add(_guestRoleId);

			_roleIdSetsManager.add(set);
			_addToRoleIdSets(set);

			return;
		}

		_addIndividuallyToRoleIdSets(roleIdSet);
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

	private void _crossCombineNewRoleIdsWithRoleIdSets(Set<String> newRoleIds) {
		if (_roleIdSets.isEmpty() || newRoleIds.isEmpty()) {
			_roleIdSets.clear();

			if (_roleIdSetsManager.isEmpty() || newRoleIds.isEmpty()) {
				_roleIdSetsManager.clear();

				return;
			}

			return;
		}

		Set<Set<String>> newRoleIdSetCombinations = new HashSet<>();

//		Iterator<Set<String>> roleIdSetsIterator = _roleIdSets.iterator();
		Iterator<Set<String>> roleIdSetsIterator =
			_roleIdSetsManager.iterator();

		while (roleIdSetsIterator.hasNext()) {
			Set<String> currentRoleIdSet = roleIdSetsIterator.next();

			Set<Set<String>> roleIdSetCopies = new HashSet<>(newRoleIds.size());

			for (String roleId : newRoleIds) {
				if (_isOwnerRole(roleId) &&
					_containsDifferentOwnerRole(currentRoleIdSet, roleId)) {

					continue;
				}

				Set<String> roleIdSetCopy = new HashSet<>(currentRoleIdSet);

				roleIdSetCopy.add(roleId);

				roleIdSetCopies.add(roleIdSetCopy);
			}

			newRoleIdSetCombinations.addAll(roleIdSetCopies);

			roleIdSetsIterator.remove();
		}

		_updatedRoleIdSets.addAll(newRoleIdSetCombinations);
	}

	private boolean _foundRoleIdInRoleIdSets(String roleId) {
		boolean foundRoleId = false;

//		Iterator<Set<String>> roleIdSetsIterator = _roleIdSets.iterator();
		Iterator<Set<String>> roleIdSetsIterator = _roleIdSetsManager.iterator();

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
			foundRoleId = _roleIdExistsInUpdatedRoleIdSets(roleId);
		}

		return foundRoleId;
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

	private Set<String> _parseNewRoleIds(Set<String> roleIdSet) {
		Set<String> newRoleIds = new HashSet<>();

		for (String roleId : roleIdSet) {
			if (!_foundRoleIdInRoleIdSets(roleId)) {
				newRoleIds.add(roleId);
			}
		}

		return newRoleIds;
	}

	private boolean _roleIdExistsInUpdatedRoleIdSets(String roleId) {
		for (Set<String> roleIdSetWithPermission : _updatedRoleIdSets) {
			if (roleIdSetWithPermission.contains(roleId)) {
				return true;
			}
		}

		return false;
	}

	private boolean _roleIdSetsContainGuest() {
		//make into flag

		for (Set<String> viewPermissionSet : _roleIdSets) {
			if (viewPermissionSet.contains(_guestRoleId)) {
				return true;
			}
		}

		return false;
	}

	private class RoleIdSetsManager {

		public void add(Set<String> roleIdsSet) {
			for(String roleId : roleIdsSet) {
				if(!_containsGuest && roleId.equals(_guestRoleId)) {
					_containsGuest = true;
				}

				if(_isOwnerRole(roleId)) {
					_ownerRoleIds.add(roleId);
				}
			}

			_roleIdSets.add(roleIdsSet);
		}

		public void addAll(Set<Set<String>> roleIdSets) {
			for(Set<String> roleIdSet : roleIdSets) {
				add(roleIdSet);
			}
		}

		public boolean containsGuest() {
			return _containsGuest;
		}

		public void clear() {
			_containsGuest = false;
			_roleIdSets.clear();
		}

		public boolean isEmpty() {
			return _roleIdSets.isEmpty();
		}

		public Iterator iterator() {
			return _roleIdSets.iterator();
		}

		private boolean _containsGuest = false;
		final Set<Set<String>> _roleIdSets = new HashSet<>();
	}

	private void _addToRoleIdSets(Set<String> roleIdsSet) {

		for(String roleId : roleIdsSet) {
			if(!_containsGuest && roleId.equals(_guestRoleId)) {
				_containsGuest = true;
			}

			if(_isOwnerRole(roleId)) {
				_ownerRoleIds.add(roleId);
			}
		}

		_roleIdSets.add(roleIdsSet);
	}

	private RoleIdSetsManager _roleIdSetsManager;
	private boolean _containsGuest = false;
	private boolean _assigned = false;
	private final long _companyId;
	private final long _groupId;
	private final String _guestRoleId;
	private final String _ownerRoleId;
	private final Set<String> _ownerRoleIds = new HashSet<>();
	private final Set<Set<String>> _roleIdSets = new HashSet<>();
	private final Set<Set<String>> _updatedRoleIdSets = new HashSet<>();

}