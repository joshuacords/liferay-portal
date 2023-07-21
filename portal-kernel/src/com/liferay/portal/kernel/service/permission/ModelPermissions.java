/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.permission;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jorge Ferrer
 */
public class ModelPermissions implements Cloneable, Serializable {

	public ModelPermissions() {
	}

	public ModelPermissions(String resourceName) {
		setResourceName(resourceName);
	}

	public void addRolePermissions(String roleName, String actionId) {
		Set<String> roleNames = _roleNamesMap.get(actionId);

		if (roleNames == null) {
			roleNames = new HashSet<>();

			_roleNamesMap.put(actionId, roleNames);
		}

		roleNames.add(roleName);

		Set<String> actionIds = _actionIdsMap.get(roleName);

		if (actionIds == null) {
			actionIds = new HashSet<>();

			_actionIdsMap.put(roleName, actionIds);
		}

		actionIds.add(actionId);
	}

	public void addRolePermissions(String roleName, String[] actionIds) {
		if (actionIds == null) {
			return;
		}

		for (String actionId : actionIds) {
			addRolePermissions(roleName, actionId);
		}
	}

	@Override
	public Object clone() {
		return new ModelPermissions(
			new HashMap<>(_roleNamesMap), new HashMap<>(_actionIdsMap),
			_resourceName);
	}

	public String[] getActionIds(String roleName) {
		Set<String> actionIds = _actionIdsMap.get(roleName);

		if (actionIds == null) {
			return StringPool.EMPTY_ARRAY;
		}

		return actionIds.toArray(new String[0]);
	}

	public List<String> getActionIdsList(String roleName) {
		Set<String> actionIds = _actionIdsMap.get(roleName);

		return ListUtil.fromCollection(actionIds);
	}

	public String getResourceName() {
		return _resourceName;
	}

	public Collection<String> getRoleNames() {
		return _actionIdsMap.keySet();
	}

	public Collection<String> getRoleNames(String actionId) {
		Set<String> roleNames = _roleNamesMap.get(actionId);

		if (roleNames == null) {
			roleNames = new HashSet<>();
		}

		return roleNames;
	}

	public boolean isEmpty() {
		return _actionIdsMap.isEmpty();
	}

	public void setResourceName(String resourceName) {
		if (resourceName == null) {
			resourceName = _RESOURCE_NAME_ALL_RESOURCES;
		}

		_resourceName = resourceName;
	}

	protected ModelPermissions(
		Map<String, Set<String>> roleNamesMap,
		Map<String, Set<String>> actionIdsMap) {

		this(roleNamesMap, actionIdsMap, _RESOURCE_NAME_ALL_RESOURCES);
	}

	protected ModelPermissions(
		Map<String, Set<String>> roleNamesMap,
		Map<String, Set<String>> actionIdsMap, String resourceName) {

		_roleNamesMap.putAll(roleNamesMap);
		_actionIdsMap.putAll(actionIdsMap);
		_resourceName = Objects.requireNonNull(resourceName);
	}

	private static final String _RESOURCE_NAME_ALL_RESOURCES =
		ModelPermissions.class.getName() + "#ALL_RESOURCES";

	private final Map<String, Set<String>> _actionIdsMap = new HashMap<>();
	private String _resourceName = _RESOURCE_NAME_ALL_RESOURCES;
	private final Map<String, Set<String>> _roleNamesMap = new HashMap<>();

}