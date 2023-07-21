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
 * This class is a wrapper for {@link CommonLicenseKey}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKey
 * @generated
 */
public class CommonLicenseKeyWrapper
	extends BaseModelWrapper<CommonLicenseKey>
	implements CommonLicenseKey, ModelWrapper<CommonLicenseKey> {

	public CommonLicenseKeyWrapper(CommonLicenseKey commonLicenseKey) {
		super(commonLicenseKey);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("commonLicenseKeyId", getCommonLicenseKeyId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("productGroup", getProductGroup());
		attributes.put("productEnvironment", getProductEnvironment());
		attributes.put("productVersion", getProductVersion());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());

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

		Long commonLicenseKeyId = (Long)attributes.get("commonLicenseKeyId");

		if (commonLicenseKeyId != null) {
			setCommonLicenseKeyId(commonLicenseKeyId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		String productGroup = (String)attributes.get("productGroup");

		if (productGroup != null) {
			setProductGroup(productGroup);
		}

		String productEnvironment = (String)attributes.get(
			"productEnvironment");

		if (productEnvironment != null) {
			setProductEnvironment(productEnvironment);
		}

		String productVersion = (String)attributes.get("productVersion");

		if (productVersion != null) {
			setProductVersion(productVersion);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long fileSize = (Long)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}
	}

	/**
	 * Returns the common license key ID of this common license key.
	 *
	 * @return the common license key ID of this common license key
	 */
	@Override
	public long getCommonLicenseKeyId() {
		return model.getCommonLicenseKeyId();
	}

	/**
	 * Returns the company ID of this common license key.
	 *
	 * @return the company ID of this common license key
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this common license key.
	 *
	 * @return the create date of this common license key
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the end date of this common license key.
	 *
	 * @return the end date of this common license key
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	@Override
	public String getFileDir() {
		return model.getFileDir();
	}

	/**
	 * Returns the file name of this common license key.
	 *
	 * @return the file name of this common license key
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	@Override
	public String getFilePath() {
		return model.getFilePath();
	}

	/**
	 * Returns the file size of this common license key.
	 *
	 * @return the file size of this common license key
	 */
	@Override
	public long getFileSize() {
		return model.getFileSize();
	}

	/**
	 * Returns the mvcc version of this common license key.
	 *
	 * @return the mvcc version of this common license key
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this common license key.
	 *
	 * @return the primary key of this common license key
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the product environment of this common license key.
	 *
	 * @return the product environment of this common license key
	 */
	@Override
	public String getProductEnvironment() {
		return model.getProductEnvironment();
	}

	/**
	 * Returns the product group of this common license key.
	 *
	 * @return the product group of this common license key
	 */
	@Override
	public String getProductGroup() {
		return model.getProductGroup();
	}

	/**
	 * Returns the product version of this common license key.
	 *
	 * @return the product version of this common license key
	 */
	@Override
	public String getProductVersion() {
		return model.getProductVersion();
	}

	/**
	 * Returns the start date of this common license key.
	 *
	 * @return the start date of this common license key
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the user ID of this common license key.
	 *
	 * @return the user ID of this common license key
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this common license key.
	 *
	 * @return the user name of this common license key
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this common license key.
	 *
	 * @return the user uuid of this common license key
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this common license key.
	 *
	 * @return the uuid of this common license key
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the common license key ID of this common license key.
	 *
	 * @param commonLicenseKeyId the common license key ID of this common license key
	 */
	@Override
	public void setCommonLicenseKeyId(long commonLicenseKeyId) {
		model.setCommonLicenseKeyId(commonLicenseKeyId);
	}

	/**
	 * Sets the company ID of this common license key.
	 *
	 * @param companyId the company ID of this common license key
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this common license key.
	 *
	 * @param createDate the create date of this common license key
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the end date of this common license key.
	 *
	 * @param endDate the end date of this common license key
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the file name of this common license key.
	 *
	 * @param fileName the file name of this common license key
	 */
	@Override
	public void setFileName(String fileName) {
		model.setFileName(fileName);
	}

	/**
	 * Sets the file size of this common license key.
	 *
	 * @param fileSize the file size of this common license key
	 */
	@Override
	public void setFileSize(long fileSize) {
		model.setFileSize(fileSize);
	}

	/**
	 * Sets the mvcc version of this common license key.
	 *
	 * @param mvccVersion the mvcc version of this common license key
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this common license key.
	 *
	 * @param primaryKey the primary key of this common license key
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product environment of this common license key.
	 *
	 * @param productEnvironment the product environment of this common license key
	 */
	@Override
	public void setProductEnvironment(String productEnvironment) {
		model.setProductEnvironment(productEnvironment);
	}

	/**
	 * Sets the product group of this common license key.
	 *
	 * @param productGroup the product group of this common license key
	 */
	@Override
	public void setProductGroup(String productGroup) {
		model.setProductGroup(productGroup);
	}

	/**
	 * Sets the product version of this common license key.
	 *
	 * @param productVersion the product version of this common license key
	 */
	@Override
	public void setProductVersion(String productVersion) {
		model.setProductVersion(productVersion);
	}

	/**
	 * Sets the start date of this common license key.
	 *
	 * @param startDate the start date of this common license key
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user ID of this common license key.
	 *
	 * @param userId the user ID of this common license key
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this common license key.
	 *
	 * @param userName the user name of this common license key
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this common license key.
	 *
	 * @param userUuid the user uuid of this common license key
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this common license key.
	 *
	 * @param uuid the uuid of this common license key
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected CommonLicenseKeyWrapper wrap(CommonLicenseKey commonLicenseKey) {
		return new CommonLicenseKeyWrapper(commonLicenseKey);
	}

}