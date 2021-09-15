package com.liferay.portal.search.internal.permission;

import com.liferay.portal.search.spi.model.permission.context.RoleSetContributorContext;

import java.util.Set;

public class RoleSetContributorContextImpl implements
	RoleSetContributorContext {

	RoleSetContributorContextImpl(
		Set<Set<String>> viewPermissionRoleIdSets,
		Set<Set<String>> accessPermissionRoleIdSets, long companyId,
		long groupId) {

		_viewPermissionRoleIdSets = viewPermissionRoleIdSets;
		_accessPermissionRoleIdSets = accessPermissionRoleIdSets;
		_companyId = companyId;
		_groupId = groupId;
	}

	@Override
	public Set<Set<String>> getAccessPermissionRoleIdSets() {
		return _accessPermissionRoleIdSets;
	}

	@Override
	public Set<Set<String>> getViewPermissionRoleIdSets() {
		return _viewPermissionRoleIdSets;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	private Set<Set<String>> _viewPermissionRoleIdSets;
	private Set<Set<String>> _accessPermissionRoleIdSets;
	private long _companyId;
	private long _groupId;
}
