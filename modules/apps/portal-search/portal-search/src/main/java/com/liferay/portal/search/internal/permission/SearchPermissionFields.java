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

import java.util.Set;

/**
 * @author Preston Crary
 */
public class SearchPermissionFields {

	public SearchPermissionFields(String[] groupRoleIds, Long[] roleIds) {
		_groupRoleIds = groupRoleIds;
		_roleIds = roleIds;

		_inheritedRoleIdCombinations = null;
	}

	public SearchPermissionFields(
		String[] groupRoleIds, Set<Set<String>> inheritedRoleIdCombinations,
		Long[] roleIds) {

		_groupRoleIds = groupRoleIds;
		_inheritedRoleIdCombinations = inheritedRoleIdCombinations;
		_roleIds = roleIds;
	}

	public String[] getGroupRoleIds() {
		return _groupRoleIds;
	}

	public Set<Set<String>> getInheritedRoleIdCombinations() {
		return _inheritedRoleIdCombinations;
	}

	public Long[] getRoleIds() {
		return _roleIds;
	}

	private final String[] _groupRoleIds;
	private final Set<Set<String>> _inheritedRoleIdCombinations;
	private final Long[] _roleIds;

}