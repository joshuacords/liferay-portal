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
 * This class is a wrapper for {@link ResourceBlockPermission}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockPermission
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ResourceBlockPermissionWrapper
	extends BaseModelWrapper<ResourceBlockPermission>
	implements ModelWrapper<ResourceBlockPermission>, ResourceBlockPermission {

	public ResourceBlockPermissionWrapper(
		ResourceBlockPermission resourceBlockPermission) {

		super(resourceBlockPermission);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put(
			"resourceBlockPermissionId", getResourceBlockPermissionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("resourceBlockId", getResourceBlockId());
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

		Long resourceBlockPermissionId = (Long)attributes.get(
			"resourceBlockPermissionId");

		if (resourceBlockPermissionId != null) {
			setResourceBlockPermissionId(resourceBlockPermissionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long resourceBlockId = (Long)attributes.get("resourceBlockId");

		if (resourceBlockId != null) {
			setResourceBlockId(resourceBlockId);
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
	 * Returns the action IDs of this resource block permission.
	 *
	 * @return the action IDs of this resource block permission
	 */
	@Override
	public long getActionIds() {
		return model.getActionIds();
	}

	/**
	 * Returns the company ID of this resource block permission.
	 *
	 * @return the company ID of this resource block permission
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the mvcc version of this resource block permission.
	 *
	 * @return the mvcc version of this resource block permission
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this resource block permission.
	 *
	 * @return the primary key of this resource block permission
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the resource block ID of this resource block permission.
	 *
	 * @return the resource block ID of this resource block permission
	 */
	@Override
	public long getResourceBlockId() {
		return model.getResourceBlockId();
	}

	/**
	 * Returns the resource block permission ID of this resource block permission.
	 *
	 * @return the resource block permission ID of this resource block permission
	 */
	@Override
	public long getResourceBlockPermissionId() {
		return model.getResourceBlockPermissionId();
	}

	/**
	 * Returns the role ID of this resource block permission.
	 *
	 * @return the role ID of this resource block permission
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action IDs of this resource block permission.
	 *
	 * @param actionIds the action IDs of this resource block permission
	 */
	@Override
	public void setActionIds(long actionIds) {
		model.setActionIds(actionIds);
	}

	/**
	 * Sets the company ID of this resource block permission.
	 *
	 * @param companyId the company ID of this resource block permission
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the mvcc version of this resource block permission.
	 *
	 * @param mvccVersion the mvcc version of this resource block permission
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this resource block permission.
	 *
	 * @param primaryKey the primary key of this resource block permission
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the resource block ID of this resource block permission.
	 *
	 * @param resourceBlockId the resource block ID of this resource block permission
	 */
	@Override
	public void setResourceBlockId(long resourceBlockId) {
		model.setResourceBlockId(resourceBlockId);
	}

	/**
	 * Sets the resource block permission ID of this resource block permission.
	 *
	 * @param resourceBlockPermissionId the resource block permission ID of this resource block permission
	 */
	@Override
	public void setResourceBlockPermissionId(long resourceBlockPermissionId) {
		model.setResourceBlockPermissionId(resourceBlockPermissionId);
	}

	/**
	 * Sets the role ID of this resource block permission.
	 *
	 * @param roleId the role ID of this resource block permission
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	@Override
	protected ResourceBlockPermissionWrapper wrap(
		ResourceBlockPermission resourceBlockPermission) {

		return new ResourceBlockPermissionWrapper(resourceBlockPermission);
	}

}