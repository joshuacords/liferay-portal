/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EntitlementDefinition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinition
 * @generated
 */
public class EntitlementDefinitionWrapper
	extends BaseModelWrapper<EntitlementDefinition>
	implements EntitlementDefinition, ModelWrapper<EntitlementDefinition> {

	public EntitlementDefinitionWrapper(
		EntitlementDefinition entitlementDefinition) {

		super(entitlementDefinition);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("entitlementDefinitionId", getEntitlementDefinitionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put(
			"entitlementDefinitionKey", getEntitlementDefinitionKey());
		attributes.put("classNameId", getClassNameId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("definition", getDefinition());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long entitlementDefinitionId = (Long)attributes.get(
			"entitlementDefinitionId");

		if (entitlementDefinitionId != null) {
			setEntitlementDefinitionId(entitlementDefinitionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String entitlementDefinitionKey = (String)attributes.get(
			"entitlementDefinitionKey");

		if (entitlementDefinitionKey != null) {
			setEntitlementDefinitionKey(entitlementDefinitionKey);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String definition = (String)attributes.get("definition");

		if (definition != null) {
			setDefinition(definition);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	 * Returns the fully qualified class name of this entitlement definition.
	 *
	 * @return the fully qualified class name of this entitlement definition
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this entitlement definition.
	 *
	 * @return the class name ID of this entitlement definition
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the company ID of this entitlement definition.
	 *
	 * @return the company ID of this entitlement definition
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this entitlement definition.
	 *
	 * @return the create date of this entitlement definition
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the definition of this entitlement definition.
	 *
	 * @return the definition of this entitlement definition
	 */
	@Override
	public String getDefinition() {
		return model.getDefinition();
	}

	/**
	 * Returns the description of this entitlement definition.
	 *
	 * @return the description of this entitlement definition
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the entitlement definition ID of this entitlement definition.
	 *
	 * @return the entitlement definition ID of this entitlement definition
	 */
	@Override
	public long getEntitlementDefinitionId() {
		return model.getEntitlementDefinitionId();
	}

	/**
	 * Returns the entitlement definition key of this entitlement definition.
	 *
	 * @return the entitlement definition key of this entitlement definition
	 */
	@Override
	public String getEntitlementDefinitionKey() {
		return model.getEntitlementDefinitionKey();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks() {

		return model.getExternalLinks();
	}

	/**
	 * Returns the modified date of this entitlement definition.
	 *
	 * @return the modified date of this entitlement definition
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this entitlement definition.
	 *
	 * @return the mvcc version of this entitlement definition
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this entitlement definition.
	 *
	 * @return the name of this entitlement definition
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this entitlement definition.
	 *
	 * @return the primary key of this entitlement definition
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this entitlement definition.
	 *
	 * @return the status of this entitlement definition
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	@Override
	public String getStatusLabel() {
		return model.getStatusLabel();
	}

	/**
	 * Returns the user ID of this entitlement definition.
	 *
	 * @return the user ID of this entitlement definition
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this entitlement definition.
	 *
	 * @return the user uuid of this entitlement definition
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this entitlement definition.
	 *
	 * @return the uuid of this entitlement definition
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void setClassName(String className) {
		model.setClassName(className);
	}

	/**
	 * Sets the class name ID of this entitlement definition.
	 *
	 * @param classNameId the class name ID of this entitlement definition
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the company ID of this entitlement definition.
	 *
	 * @param companyId the company ID of this entitlement definition
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this entitlement definition.
	 *
	 * @param createDate the create date of this entitlement definition
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the definition of this entitlement definition.
	 *
	 * @param definition the definition of this entitlement definition
	 */
	@Override
	public void setDefinition(String definition) {
		model.setDefinition(definition);
	}

	/**
	 * Sets the description of this entitlement definition.
	 *
	 * @param description the description of this entitlement definition
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the entitlement definition ID of this entitlement definition.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID of this entitlement definition
	 */
	@Override
	public void setEntitlementDefinitionId(long entitlementDefinitionId) {
		model.setEntitlementDefinitionId(entitlementDefinitionId);
	}

	/**
	 * Sets the entitlement definition key of this entitlement definition.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key of this entitlement definition
	 */
	@Override
	public void setEntitlementDefinitionKey(String entitlementDefinitionKey) {
		model.setEntitlementDefinitionKey(entitlementDefinitionKey);
	}

	/**
	 * Sets the modified date of this entitlement definition.
	 *
	 * @param modifiedDate the modified date of this entitlement definition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this entitlement definition.
	 *
	 * @param mvccVersion the mvcc version of this entitlement definition
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this entitlement definition.
	 *
	 * @param name the name of this entitlement definition
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this entitlement definition.
	 *
	 * @param primaryKey the primary key of this entitlement definition
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this entitlement definition.
	 *
	 * @param status the status of this entitlement definition
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this entitlement definition.
	 *
	 * @param userId the user ID of this entitlement definition
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this entitlement definition.
	 *
	 * @param userUuid the user uuid of this entitlement definition
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this entitlement definition.
	 *
	 * @param uuid the uuid of this entitlement definition
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected EntitlementDefinitionWrapper wrap(
		EntitlementDefinition entitlementDefinition) {

		return new EntitlementDefinitionWrapper(entitlementDefinition);
	}

}