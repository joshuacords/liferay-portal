/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class PermissionDisplay
	implements Comparable<PermissionDisplay>, Serializable {

	public PermissionDisplay(
		Permission permission, Resource resource, String portletName,
		String portletLabel, String modelName, String modelLabel,
		String actionId, String actionLabel) {

		_permission = permission;
		_resource = resource;
		_portletName = portletName;
		_portletLabel = portletLabel;
		_modelName = modelName;
		_modelLabel = modelLabel;
		_actionId = actionId;
		_actionLabel = actionLabel;
	}

	@Override
	public int compareTo(PermissionDisplay permissionDisplay) {
		int value = getPortletLabel().compareTo(
			permissionDisplay.getPortletLabel());

		if (value == 0) {
			value = getModelLabel().compareTo(
				permissionDisplay.getModelLabel());

			if (value == 0) {
				value = getActionLabel().compareTo(
					permissionDisplay.getActionLabel());
			}
		}

		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PermissionDisplay)) {
			return false;
		}

		PermissionDisplay permissionDisplay = (PermissionDisplay)obj;

		if (_portletName.equals(permissionDisplay.getPortletName()) &&
			_modelName.equals(permissionDisplay.getModelName()) &&
			_actionId.equals(permissionDisplay.getActionId())) {

			return true;
		}

		return false;
	}

	public String getActionId() {
		return _actionId;
	}

	public String getActionLabel() {
		return _actionLabel;
	}

	public String getModelLabel() {
		return _modelLabel;
	}

	public String getModelName() {
		return _modelName;
	}

	public Permission getPermission() {
		return _permission;
	}

	public String getPortletLabel() {
		return _portletLabel;
	}

	public String getPortletName() {
		return _portletName;
	}

	public Resource getResource() {
		return _resource;
	}

	@Override
	public int hashCode() {
		return _portletName.concat(
			_modelName
		).concat(
			_actionId
		).hashCode();
	}

	private final String _actionId;
	private final String _actionLabel;
	private final String _modelLabel;
	private final String _modelName;
	private final Permission _permission;
	private final String _portletLabel;
	private final String _portletName;
	private final Resource _resource;

}