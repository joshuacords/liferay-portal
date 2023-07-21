/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContactRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactRole
 * @generated
 */
public class ContactRoleWrapper
	extends BaseModelWrapper<ContactRole>
	implements ContactRole, ModelWrapper<ContactRole> {

	public ContactRoleWrapper(ContactRole contactRole) {
		super(contactRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("contactRoleId", getContactRoleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("contactRoleKey", getContactRoleKey());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("type", getType());
		attributes.put("system", isSystem());

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

		Long contactRoleId = (Long)attributes.get("contactRoleId");

		if (contactRoleId != null) {
			setContactRoleId(contactRoleId);
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

		String contactRoleKey = (String)attributes.get("contactRoleKey");

		if (contactRoleKey != null) {
			setContactRoleKey(contactRoleKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean system = (Boolean)attributes.get("system");

		if (system != null) {
			setSystem(system);
		}
	}

	/**
	 * Returns the company ID of this contact role.
	 *
	 * @return the company ID of this contact role
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the contact role ID of this contact role.
	 *
	 * @return the contact role ID of this contact role
	 */
	@Override
	public long getContactRoleId() {
		return model.getContactRoleId();
	}

	/**
	 * Returns the contact role key of this contact role.
	 *
	 * @return the contact role key of this contact role
	 */
	@Override
	public String getContactRoleKey() {
		return model.getContactRoleKey();
	}

	/**
	 * Returns the create date of this contact role.
	 *
	 * @return the create date of this contact role
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this contact role.
	 *
	 * @return the description of this contact role
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks() {

		return model.getExternalLinks();
	}

	/**
	 * Returns the modified date of this contact role.
	 *
	 * @return the modified date of this contact role
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this contact role.
	 *
	 * @return the mvcc version of this contact role
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this contact role.
	 *
	 * @return the name of this contact role
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this contact role.
	 *
	 * @return the primary key of this contact role
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the system of this contact role.
	 *
	 * @return the system of this contact role
	 */
	@Override
	public boolean getSystem() {
		return model.getSystem();
	}

	/**
	 * Returns the type of this contact role.
	 *
	 * @return the type of this contact role
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this contact role.
	 *
	 * @return the user ID of this contact role
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this contact role.
	 *
	 * @return the user uuid of this contact role
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this contact role.
	 *
	 * @return the uuid of this contact role
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public boolean isMember() {
		return model.isMember();
	}

	/**
	 * Returns <code>true</code> if this contact role is system.
	 *
	 * @return <code>true</code> if this contact role is system; <code>false</code> otherwise
	 */
	@Override
	public boolean isSystem() {
		return model.isSystem();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this contact role.
	 *
	 * @param companyId the company ID of this contact role
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the contact role ID of this contact role.
	 *
	 * @param contactRoleId the contact role ID of this contact role
	 */
	@Override
	public void setContactRoleId(long contactRoleId) {
		model.setContactRoleId(contactRoleId);
	}

	/**
	 * Sets the contact role key of this contact role.
	 *
	 * @param contactRoleKey the contact role key of this contact role
	 */
	@Override
	public void setContactRoleKey(String contactRoleKey) {
		model.setContactRoleKey(contactRoleKey);
	}

	/**
	 * Sets the create date of this contact role.
	 *
	 * @param createDate the create date of this contact role
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this contact role.
	 *
	 * @param description the description of this contact role
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the modified date of this contact role.
	 *
	 * @param modifiedDate the modified date of this contact role
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this contact role.
	 *
	 * @param mvccVersion the mvcc version of this contact role
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this contact role.
	 *
	 * @param name the name of this contact role
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this contact role.
	 *
	 * @param primaryKey the primary key of this contact role
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this contact role is system.
	 *
	 * @param system the system of this contact role
	 */
	@Override
	public void setSystem(boolean system) {
		model.setSystem(system);
	}

	/**
	 * Sets the type of this contact role.
	 *
	 * @param type the type of this contact role
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this contact role.
	 *
	 * @param userId the user ID of this contact role
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this contact role.
	 *
	 * @param userUuid the user uuid of this contact role
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this contact role.
	 *
	 * @param uuid the uuid of this contact role
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
	protected ContactRoleWrapper wrap(ContactRole contactRole) {
		return new ContactRoleWrapper(contactRole);
	}

}