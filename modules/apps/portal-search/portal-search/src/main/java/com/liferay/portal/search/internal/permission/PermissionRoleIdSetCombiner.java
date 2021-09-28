package com.liferay.portal.search.internal.permission;

import com.liferay.petra.string.StringPool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PermissionRoleIdSetCombiner {

	public PermissionRoleIdSetCombiner(
		long companyId, long groupId, String guestRoleId, String ownerRoleId) {
		_companyId = companyId;
		_groupId = groupId;
		_guestRoleId = guestRoleId;
		_ownerRoleId = ownerRoleId;
	}

	public void addRoleIdSet(Set<String> roleIdSet) {
		if(_firstSet) {
			_firstSet = false;
			_assignFirstSet(roleIdSet);
			return;
		}

		if(_roleIdSets.isEmpty() || roleIdSet.isEmpty()) {
			_roleIdSets.clear();
			return;
		}

		if(roleIdSet.contains(_guestRoleId)) {
			return;
		}

		if(_RoleIdSetsContainGuest()) { //make into flag
			_addIndividuallyToRoleIdSets(roleIdSet);
			return;
		}

		_parseNewRoleIds(roleIdSet);
		_crossCombineNewRoleIdsWithRoleIdSets();
		_roleIdSets.clear();
		_roleIdSets.addAll(_updatedRoleIdSets);
		_updatedRoleIdSets.clear();
	}

	private void _assignFirstSet(Set<String> roleIdSet) {
		if(roleIdSet.contains(_guestRoleId)) {
			Set<String> set = new HashSet<String>();

			set.add(_guestRoleId);

			_roleIdSets.add(set);

			return;
		}

		_addIndividuallyToRoleIdSets(roleIdSet);
	}

	private void _addIndividuallyToRoleIdSets(Set<String> roleIdSet) {
		for(String roleId : roleIdSet) {
			Set<String> set = new HashSet<String>();
			set.add(roleId);
			_roleIdSets.add(set);
		}
	}

	private boolean _RoleIdSetsContainGuest() {
		//make into flag

		for(Set<String> viewPermissionSet : _roleIdSets) {
			if(viewPermissionSet.contains(_guestRoleId)) {
				return true;
			}
		}

		return false;
	}

	//return _newRoleIds
	private void _parseNewRoleIds(Set<String> roleIdSet) {
		for(String roleId : roleIdSet) {
			if(!_foundRoleIdInRoleIdSets(roleId)) {
				_newRoleIds.add(roleId);
			}
		}
	}

	private boolean _foundRoleIdInRoleIdSets(String roleId) {
		boolean foundRoleId = false;

		Iterator<Set<String>> roleIdSetsIterator =
			_roleIdSets.iterator();

		boolean isOwnerRole = _isOwnerRole(roleId);

		while (roleIdSetsIterator.hasNext()) {
			Set<String> currentRoleIdSet = roleIdSetsIterator.next();

			if (currentRoleIdSet.contains(roleId)) {
				if (isOwnerRole &&
					_containsDifferentOwnerRole(currentRoleIdSet, roleId)) {
					continue;
				}

				_updatedRoleIdSets.add(currentRoleIdSet);

				roleIdSetsIterator.remove();

				foundRoleId = true;
			}
		}

		if(isOwnerRole) {
			_ownerRoleIds.add(roleId);
		}

		if (!foundRoleId) {
			foundRoleId = _roleIdExistsInUpdatedRoleIdSets(roleId);
		}

		return foundRoleId;
	}

	private boolean _isOwnerRole(String roleId) {
		String[] roleIdArray = roleId.split(StringPool.DASH);
		if(roleIdArray.length != 2) {
			return false;
		}

		if(roleIdArray.equals(_ownerRoleId)) {
			return true;
		}

		return false;
	}

	private boolean _containsDifferentOwnerRole(
		Set<String> roleIdSet, String roleId) {

		for(String ownerRoleId : _ownerRoleIds) {
			if(ownerRoleId.equals(roleId)) {
				continue;
			}

			if(roleIdSet.contains(ownerRoleId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _roleIdExistsInUpdatedRoleIdSets(String roleId) {

		for (Set<String> roleIdSetWithPermission : _updatedRoleIdSets) {
			if (roleIdSetWithPermission.contains(roleId)) {
				return true;
			}
		}

		return false;
	}

	private void _crossCombineNewRoleIdsWithRoleIdSets() {
		if (_roleIdSets.isEmpty() ||
			_newRoleIds.isEmpty()) {

			_newRoleIds.clear();
			_roleIdSets.clear();

			return;
		}

		Set<Set<String>> newRoleIdSetCombinations = new HashSet<>();

		Iterator<Set<String>> _roleIdSetsIterator =
			_roleIdSets.iterator();

		while (_roleIdSetsIterator.hasNext()) {
			Set<String> currentRoleIdSet = _roleIdSetsIterator.next();

			Set<Set<String>> roleIdSetCopies = new HashSet<>(
				_newRoleIds.size());

			for (String roleId : _newRoleIds) {
				if (_isOwnerRole(roleId) &&
					_containsDifferentOwnerRole(currentRoleIdSet, roleId)) {
					continue;
				}

				Set<String> roleIdSetCopy = new HashSet<>(currentRoleIdSet);

				roleIdSetCopy.add(roleId);

				roleIdSetCopies.add(roleIdSetCopy);
			}

			newRoleIdSetCombinations.addAll(roleIdSetCopies);

			_roleIdSetsIterator.remove();
		}

		_updatedRoleIdSets.addAll(newRoleIdSetCombinations);
		_newRoleIds.clear();
	}

	private Set<Set<String>> _updatedRoleIdSets = new HashSet<>();
	private Set<String> _ownerRoleIds = new HashSet<>();
	private Set<String> _newRoleIds = new HashSet<>();
	private Set<Set<String>> _roleIdSets = new HashSet<>();
	private boolean _firstSet = true;
	private final long _companyId;
	private final long _groupId;
	private final String _guestRoleId;
	private final String _ownerRoleId;

}
