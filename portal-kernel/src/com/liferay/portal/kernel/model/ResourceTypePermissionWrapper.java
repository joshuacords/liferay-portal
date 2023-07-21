/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ResourceTypePermission}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceTypePermission
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ResourceTypePermissionWrapper
	extends BaseModelWrapper<ResourceTypePermission>
	implements ModelWrapper<ResourceTypePermission>, ResourceTypePermission {

	public ResourceTypePermissionWrapper(
		ResourceTypePermission resourceTypePermission) {

		super(resourceTypePermission);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put(
			"resourceTypePermissionId", getResourceTypePermissionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("name", getName());
		attributes.put("roleId", getRoleId());
		attributes.put("actionIds", getActionIds());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long resourceTypePermissionId = (Long)attributes.get(
			"resourceTypePermissionId");

		if (resourceTypePermissionId != null) {
			setResourceTypePermissionId(resourceTypePermissionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long actionIds = (Long)attributes.get("actionIds");

		if (actionIds != null) {
			setActionIds(actionIds);
		}
	}

	/**
	 * Returns the action IDs of this resource type permission.
	 *
	 * @return the action IDs of this resource type permission
	 */
	@Override
	public long getActionIds() {
		return model.getActionIds();
	}

	/**
	 * Returns the company ID of this resource type permission.
	 *
	 * @return the company ID of this resource type permission
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the group ID of this resource type permission.
	 *
	 * @return the group ID of this resource type permission
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the mvcc version of this resource type permission.
	 *
	 * @return the mvcc version of this resource type permission
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this resource type permission.
	 *
	 * @return the name of this resource type permission
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this resource type permission.
	 *
	 * @return the primary key of this resource type permission
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the resource type permission ID of this resource type permission.
	 *
	 * @return the resource type permission ID of this resource type permission
	 */
	@Override
	public long getResourceTypePermissionId() {
		return model.getResourceTypePermissionId();
	}

	/**
	 * Returns the role ID of this resource type permission.
	 *
	 * @return the role ID of this resource type permission
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	@Override
	public boolean hasAction(ResourceAction resourceAction) {
		return model.hasAction(resourceAction);
	}

	@Override
	public boolean isCompanyScope() {
		return model.isCompanyScope();
	}

	@Override
	public boolean isGroupScope() {
		return model.isGroupScope();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action IDs of this resource type permission.
	 *
	 * @param actionIds the action IDs of this resource type permission
	 */
	@Override
	public void setActionIds(long actionIds) {
		model.setActionIds(actionIds);
	}

	/**
	 * Sets the company ID of this resource type permission.
	 *
	 * @param companyId the company ID of this resource type permission
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the group ID of this resource type permission.
	 *
	 * @param groupId the group ID of this resource type permission
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the mvcc version of this resource type permission.
	 *
	 * @param mvccVersion the mvcc version of this resource type permission
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this resource type permission.
	 *
	 * @param name the name of this resource type permission
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this resource type permission.
	 *
	 * @param primaryKey the primary key of this resource type permission
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the resource type permission ID of this resource type permission.
	 *
	 * @param resourceTypePermissionId the resource type permission ID of this resource type permission
	 */
	@Override
	public void setResourceTypePermissionId(long resourceTypePermissionId) {
		model.setResourceTypePermissionId(resourceTypePermissionId);
	}

	/**
	 * Sets the role ID of this resource type permission.
	 *
	 * @param roleId the role ID of this resource type permission
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	@Override
	protected ResourceTypePermissionWrapper wrap(
		ResourceTypePermission resourceTypePermission) {

		return new ResourceTypePermissionWrapper(resourceTypePermission);
	}

}