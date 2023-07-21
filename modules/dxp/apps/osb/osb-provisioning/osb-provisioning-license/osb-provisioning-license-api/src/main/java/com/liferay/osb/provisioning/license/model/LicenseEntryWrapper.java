/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LicenseEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntry
 * @generated
 */
public class LicenseEntryWrapper
	extends BaseModelWrapper<LicenseEntry>
	implements LicenseEntry, ModelWrapper<LicenseEntry> {

	public LicenseEntryWrapper(LicenseEntry licenseEntry) {
		super(licenseEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("licenseEntryId", getLicenseEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productKey", getProductKey());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("versionMin", getVersionMin());
		attributes.put("versionMax", getVersionMax());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long licenseEntryId = (Long)attributes.get("licenseEntryId");

		if (licenseEntryId != null) {
			setLicenseEntryId(licenseEntryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String productKey = (String)attributes.get("productKey");

		if (productKey != null) {
			setProductKey(productKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String versionMin = (String)attributes.get("versionMin");

		if (versionMin != null) {
			setVersionMin(versionMin);
		}

		String versionMax = (String)attributes.get("versionMax");

		if (versionMax != null) {
			setVersionMax(versionMax);
		}
	}

	/**
	 * Returns the create date of this license entry.
	 *
	 * @return the create date of this license entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public String getDisplayName() {
		return model.getDisplayName();
	}

	/**
	 * Returns the license entry ID of this license entry.
	 *
	 * @return the license entry ID of this license entry
	 */
	@Override
	public long getLicenseEntryId() {
		return model.getLicenseEntryId();
	}

	/**
	 * Returns the modified date of this license entry.
	 *
	 * @return the modified date of this license entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this license entry.
	 *
	 * @return the mvcc version of this license entry
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this license entry.
	 *
	 * @return the name of this license entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this license entry.
	 *
	 * @return the primary key of this license entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the product key of this license entry.
	 *
	 * @return the product key of this license entry
	 */
	@Override
	public String getProductKey() {
		return model.getProductKey();
	}

	/**
	 * Returns the type of this license entry.
	 *
	 * @return the type of this license entry
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this license entry.
	 *
	 * @return the user ID of this license entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this license entry.
	 *
	 * @return the user name of this license entry
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this license entry.
	 *
	 * @return the user uuid of this license entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the version max of this license entry.
	 *
	 * @return the version max of this license entry
	 */
	@Override
	public String getVersionMax() {
		return model.getVersionMax();
	}

	/**
	 * Returns the version min of this license entry.
	 *
	 * @return the version min of this license entry
	 */
	@Override
	public String getVersionMin() {
		return model.getVersionMin();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this license entry.
	 *
	 * @param createDate the create date of this license entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the license entry ID of this license entry.
	 *
	 * @param licenseEntryId the license entry ID of this license entry
	 */
	@Override
	public void setLicenseEntryId(long licenseEntryId) {
		model.setLicenseEntryId(licenseEntryId);
	}

	/**
	 * Sets the modified date of this license entry.
	 *
	 * @param modifiedDate the modified date of this license entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this license entry.
	 *
	 * @param mvccVersion the mvcc version of this license entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this license entry.
	 *
	 * @param name the name of this license entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this license entry.
	 *
	 * @param primaryKey the primary key of this license entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product key of this license entry.
	 *
	 * @param productKey the product key of this license entry
	 */
	@Override
	public void setProductKey(String productKey) {
		model.setProductKey(productKey);
	}

	/**
	 * Sets the type of this license entry.
	 *
	 * @param type the type of this license entry
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this license entry.
	 *
	 * @param userId the user ID of this license entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this license entry.
	 *
	 * @param userName the user name of this license entry
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this license entry.
	 *
	 * @param userUuid the user uuid of this license entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the version max of this license entry.
	 *
	 * @param versionMax the version max of this license entry
	 */
	@Override
	public void setVersionMax(String versionMax) {
		model.setVersionMax(versionMax);
	}

	/**
	 * Sets the version min of this license entry.
	 *
	 * @param versionMin the version min of this license entry
	 */
	@Override
	public void setVersionMin(String versionMin) {
		model.setVersionMin(versionMin);
	}

	@Override
	protected LicenseEntryWrapper wrap(LicenseEntry licenseEntry) {
		return new LicenseEntryWrapper(licenseEntry);
	}

}