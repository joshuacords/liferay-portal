/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;

import java.util.Objects;

/**
 * @author Michael C. Han
 */
public class RoleAssignment extends Assignment {

	public RoleAssignment(long roleId) {
		super(AssignmentType.ROLE);

		_roleId = roleId;

		_roleName = null;
		_roleType = null;
	}

	public RoleAssignment(String roleName, String roleType) {
		super(AssignmentType.ROLE);

		_roleName = roleName;
		_roleType = roleType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RoleAssignment)) {
			return false;
		}

		RoleAssignment roleAssignment = (RoleAssignment)obj;

		if (Objects.equals(_roleName, roleAssignment._roleName) &&
			(_roleId == roleAssignment._roleId)) {

			return true;
		}

		return true;
	}

	public long getRoleId() {
		return _roleId;
	}

	public String getRoleName() {
		return _roleName;
	}

	public String getRoleType() {
		return _roleType;
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(_roleId);
		hashCode.append(_roleName);
		hashCode.append(_roleType);

		return hashCode.toHashCode();
	}

	public boolean isAutoCreate() {
		return _autoCreate;
	}

	public void setAutoCreate(boolean autoCreate) {
		_autoCreate = autoCreate;
	}

	private boolean _autoCreate;
	private long _roleId;
	private final String _roleName;
	private final String _roleType;

}