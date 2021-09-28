package com.liferay.portal.search.internal.permission;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.search.spi.model.permission.RoleSetContributorContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RoleSetContributorContextImpl
	implements RoleSetContributorContext {

	public RoleSetContributorContextImpl(
		long companyId, long groupId, RoleLocalService roleLocalService)
		throws PortalException {
		_companyId = companyId;
		_groupId = groupId;
		_roleLocalService = roleLocalService;
		_guestRoleId = Long.toString(_roleLocalService.getRole(
			companyId, RoleConstants.GUEST).getRoleId());
		_ownerRoleId = Long.toString(_roleLocalService.getRole(
			companyId, RoleConstants.OWNER).getRoleId());
	}

	@Override
	public void addAccessPermissionRoleIdSet(Set<String> set) {
		_accessPermissionRoleIdSets.add(set);
	}

	//must be called exactly once per level
	@Override
	public void addViewPermissionRoleIdSet(Set<String> roleIdSet) {
		if(_firstSet) {
			_firstSet = false;
			_assignFirstSet(roleIdSet);
			return;
		}

		if(_viewPermissionRoleIdSets.isEmpty() || roleIdSet.isEmpty()) {
			_viewPermissionRoleIdSets.clear();
			return;
		}

		if(roleIdSet.contains(_guestRoleId)) {
			return;
		}

		if(_viewSetContainsGuest()) {
			_addIndividuallyToViewSet(roleIdSet);
			return;
		}

		Set<String> newRoleIdsSet =  _newRoleIdsSet(permissionRoleIdSets, roleIdSet);
		_crossCombineViewPermissions();
		_viewPermissionRoleIdSets.clear();
		_viewPermissionRoleIdSets.addAll(_updatedViewPermissionRoleIdSets);
		_updatedViewPermissionRoleIdSets.clear();
	}

	private boolean _viewSetContainsGuest() {
		for(Set<String> viewPermissionSet : _viewPermissionRoleIdSets) {
			if(viewPermissionSet.contains(_guestRoleId)) {
				return true;
			}
		}

		return false;
	}

	private Set<String> _newRoleIdsSet(
		Set<Set<String>> roleIdSets,
		Set<String> roleIdSet) {
		Set<String> newRoleIdsSet = new HashSet<>();

		for(String roleId : roleIdSet) {
			if(!_matchFoundInSets(roleIdSets, roleId)) {
				newRoleIdsSet.add(roleId);
			}
		}

		return newRoleIdsSet;
	}

	private boolean _matchFoundInSets(
		Set<Set<String>> roleIdSets, String roleId) {
		boolean roleHasPermission = false;

		Iterator<Set<String>> roleIdSetsIterator = roleIdSets.iterator();

		boolean isOwnerRole = _isOwnerRole(roleId);

		while (roleIdSetsIterator.hasNext()) {
			Set<String> currentRoleIdSet = roleIdSetsIterator.next();

			if (currentRoleIdSet.contains(roleId)) {
				if (isOwnerRole &&
					_containsOtherOwnerRole(currentRoleIdSet, roleId)) {
					continue;
				}

				_updatedViewPermissionRoleIdSets.add(currentRoleIdSet);

				roleIdSetsIterator.remove();

				roleHasPermission = true;
			}
		}

		if(isOwnerRole) {
			_ownerRoleIds.add(roleId);
		}

		if (!roleHasPermission) {
			roleHasPermission = _roleIdExistsInRoleIdSetsWithPermission(roleId);
		}

		return roleHasPermission;
	}

	private boolean _roleIdExistsInRoleIdSetsWithPermission(String roleId) {

		for (Set<String> roleIdSetWithPermission :
			_updatedViewPermissionRoleIdSets) {
			if (roleIdSetWithPermission.contains(roleId)) {
				return true;
			}
		}

		return false;
	}

	private boolean _containsOtherOwnerRole(
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

	private void _assignFirstSet(Set<String> roleIdSet) {
		if(roleIdSet.contains(_guestRoleId)) {
			Set<String> set = new HashSet<String>();

			set.add(_guestRoleId);

			_viewPermissionRoleIdSets.add(set);

			return;
		}

		_addIndividuallyToViewSet(roleIdSet);
	}

	private void _addIndividuallyToViewSet(Set<String> roleIdSet) {
		for(String roleId : roleIdSet) {
			Set<String> set = new HashSet<String>();
			set.add(roleId);
			_viewPermissionRoleIdSets.add(set);
		}
	}

//	private void _combine1(Set<String> roleIdSet) {
//		Set<String> roleIdsToCombine = new HashSet<>();
//
//		Set<Set<String>> newRoleIdSetsWithPermission = new HashSet<>();
//
//		Role guestRole = _roleLocalService.getRole(
//			_companyId, RoleConstants.GUEST);
//
//		Role ownerRole = roleLocalService.fetchRole(
//			companyId, RoleConstants.OWNER);
//
//		for (Role role : roles) {
//			if (role.getRoleId() == guestRole.getRoleId()) {
//				continue;
//			}
//
//			String folderRoleId = _roleToRoleId(
//				_companyId, _groupId, className, classPK, role);
//
//			if (role.getRoleId() == ownerRole.getRoleId()) {
//				ownerRoleIds.add(folderRoleId);
//			}
//
//			boolean roleHasPermission = false;
//
//			Iterator<Set<String>> roleIdPermissionIterator =
//				roleIdSetsWithPermission.iterator();
//
//			while (roleIdPermissionIterator.hasNext()) {
//				Set<String> currentRoleIdSet = roleIdPermissionIterator.next();
//
//				if (currentRoleIdSet.contains(folderRoleId)) {
//					newRoleIdSetsWithPermission.add(currentRoleIdSet);
//
//					roleIdPermissionIterator.remove();
//
//					roleHasPermission = true;
//				}
//			}
//
//			if (!roleHasPermission) {
//				roleHasPermission = _roleIdExistsInRoleIdSetsWithPermission(
//					newRoleIdSetsWithPermission, folderRoleId);
//			}
//
//			if (!roleHasPermission) {
//				roleIdsToCombine.add(folderRoleId);
//			}
//		}
//	}

	public Set<Set<String>> getAccessPermissionRoleIdSets() {
		return _accessPermissionRoleIdSets;
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
		return _viewPermissionRoleIdSets;
	}

	private void _crossCombineViewPermissions() {
		if (_viewPermissionRoleIdSets.isEmpty() ||
			_roleIdsToCombine.isEmpty()) {

			_roleIdsToCombine.clear();
			_viewPermissionRoleIdSets.clear();

			return;
		}

		Set<Set<String>> newRoleIdSetCombinations = new HashSet<>();

		Iterator<Set<String>> _viewPermissionRoleIdSetsIterator =
			_viewPermissionRoleIdSets.iterator();

		while (_viewPermissionRoleIdSetsIterator.hasNext()) {
			Set<String> currentRoleIdSet =
				_viewPermissionRoleIdSetsIterator.next();

			Set<Set<String>> roleIdSetCopies = new HashSet<>(
				_roleIdsToCombine.size());

			for (String roleId : _roleIdsToCombine) {
				if (_isOwnerRole(roleId) &&
					_containsOtherOwnerRole(currentRoleIdSet, roleId)) {
					continue;
				}

				Set<String> roleIdSetCopy = new HashSet<>(currentRoleIdSet);

				roleIdSetCopy.add(roleId);

				roleIdSetCopies.add(roleIdSetCopy);
			}

			newRoleIdSetCombinations.addAll(roleIdSetCopies);

			_viewPermissionRoleIdSetsIterator.remove();
		}

		_updatedViewPermissionRoleIdSets.addAll(newRoleIdSetCombinations);
		_roleIdsToCombine.clear();
	}

	private Set<String> _ownerRoleIds = new HashSet<>();
	private Set<String> _roleIdsToCombine = new HashSet<>();
	private final RoleLocalService _roleLocalService;
	private final String _guestRoleId;
	private final String _ownerRoleId;
	private boolean _firstSet = true;
	private Set<Set<String>> _viewPermissionRoleIdSets = new HashSet<>();
	private Set<Set<String>> _updatedViewPermissionRoleIdSets = new HashSet<>();
	private final Set<Set<String>> _accessPermissionRoleIdSets =
		new HashSet<>();
	private final long _companyId;
	private final long _groupId;
}
