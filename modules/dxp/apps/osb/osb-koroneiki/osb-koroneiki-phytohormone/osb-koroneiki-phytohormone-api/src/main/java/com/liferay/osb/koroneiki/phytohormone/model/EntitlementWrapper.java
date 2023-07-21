/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Entitlement}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Entitlement
 * @generated
 */
public class EntitlementWrapper
	extends BaseModelWrapper<Entitlement>
	implements Entitlement, ModelWrapper<Entitlement> {

	public EntitlementWrapper(Entitlement entitlement) {
		super(entitlement);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("entitlementId", getEntitlementId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("entitlementDefinitionId", getEntitlementDefinitionId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long entitlementId = (Long)attributes.get("entitlementId");

		if (entitlementId != null) {
			setEntitlementId(entitlementId);
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

		Long entitlementDefinitionId = (Long)attributes.get(
			"entitlementDefinitionId");

		if (entitlementDefinitionId != null) {
			setEntitlementDefinitionId(entitlementDefinitionId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	 * Returns the fully qualified class name of this entitlement.
	 *
	 * @return the fully qualified class name of this entitlement
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this entitlement.
	 *
	 * @return the class name ID of this entitlement
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class pk of this entitlement.
	 *
	 * @return the class pk of this entitlement
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the company ID of this entitlement.
	 *
	 * @return the company ID of this entitlement
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this entitlement.
	 *
	 * @return the create date of this entitlement
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public EntitlementDefinition getEntitlementDefinition()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getEntitlementDefinition();
	}

	/**
	 * Returns the entitlement definition ID of this entitlement.
	 *
	 * @return the entitlement definition ID of this entitlement
	 */
	@Override
	public long getEntitlementDefinitionId() {
		return model.getEntitlementDefinitionId();
	}

	@Override
	public String getEntitlementDefinitionKey()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getEntitlementDefinitionKey();
	}

	/**
	 * Returns the entitlement ID of this entitlement.
	 *
	 * @return the entitlement ID of this entitlement
	 */
	@Override
	public long getEntitlementId() {
		return model.getEntitlementId();
	}

	/**
	 * Returns the modified date of this entitlement.
	 *
	 * @return the modified date of this entitlement
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this entitlement.
	 *
	 * @return the mvcc version of this entitlement
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this entitlement.
	 *
	 * @return the name of this entitlement
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this entitlement.
	 *
	 * @return the primary key of this entitlement
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this entitlement.
	 *
	 * @return the user ID of this entitlement
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this entitlement.
	 *
	 * @return the user uuid of this entitlement
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
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
	 * Sets the class name ID of this entitlement.
	 *
	 * @param classNameId the class name ID of this entitlement
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this entitlement.
	 *
	 * @param classPK the class pk of this entitlement
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this entitlement.
	 *
	 * @param companyId the company ID of this entitlement
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this entitlement.
	 *
	 * @param createDate the create date of this entitlement
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the entitlement definition ID of this entitlement.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID of this entitlement
	 */
	@Override
	public void setEntitlementDefinitionId(long entitlementDefinitionId) {
		model.setEntitlementDefinitionId(entitlementDefinitionId);
	}

	@Override
	public void setEntitlementDefinitionKey(String entitlementDefinitionKey) {
		model.setEntitlementDefinitionKey(entitlementDefinitionKey);
	}

	/**
	 * Sets the entitlement ID of this entitlement.
	 *
	 * @param entitlementId the entitlement ID of this entitlement
	 */
	@Override
	public void setEntitlementId(long entitlementId) {
		model.setEntitlementId(entitlementId);
	}

	/**
	 * Sets the modified date of this entitlement.
	 *
	 * @param modifiedDate the modified date of this entitlement
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this entitlement.
	 *
	 * @param mvccVersion the mvcc version of this entitlement
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this entitlement.
	 *
	 * @param name the name of this entitlement
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this entitlement.
	 *
	 * @param primaryKey the primary key of this entitlement
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this entitlement.
	 *
	 * @param userId the user ID of this entitlement
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this entitlement.
	 *
	 * @param userUuid the user uuid of this entitlement
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected EntitlementWrapper wrap(Entitlement entitlement) {
		return new EntitlementWrapper(entitlement);
	}

}