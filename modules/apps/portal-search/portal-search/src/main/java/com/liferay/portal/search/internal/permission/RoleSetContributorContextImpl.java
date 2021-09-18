package com.liferay.portal.search.internal.permission;

import com.liferay.portal.search.spi.model.permission.RoleSetContributorContext;

import java.util.HashSet;
import java.util.Set;

public class RoleSetContributorContextImpl
	implements RoleSetContributorContext {

	public RoleSetContributorContextImpl(long companyId, long groupId) {
		_companyId = companyId;
		_groupId = groupId;
	}

	@Override
	public void addAccessPermissionRoleIdSet(Set<String> set) {
		_accessPermissionRoleIdSets.add(set);
	}

	@Override
	public void addViewPermissionRoleIdSet(Set<String> set) {
		_viewPermissionRoleIdSets.add(set);
	}

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

	private final Set<Set<String>> _viewPermissionRoleIdSets = new HashSet<>();
	private final Set<Set<String>> _accessPermissionRoleIdSets =
		new HashSet<>();
	private final long _companyId;
	private final long _groupId;
}
