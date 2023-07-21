/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LicenseKey}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKey
 * @generated
 */
public class LicenseKeyWrapper
	extends BaseModelWrapper<LicenseKey>
	implements LicenseKey, ModelWrapper<LicenseKey> {

	public LicenseKeyWrapper(LicenseKey licenseKey) {
		super(licenseKey);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("licenseKeyId", getLicenseKeyId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userUuid", getUserUuid());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserUuid", getModifiedUserUuid());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("assetReceiptLicenseUuid", getAssetReceiptLicenseUuid());
		attributes.put("accountKey", getAccountKey());
		attributes.put("productPurchaseKey", getProductPurchaseKey());
		attributes.put("licenseEntryId", getLicenseEntryId());
		attributes.put("productKey", getProductKey());
		attributes.put("accountName", getAccountName());
		attributes.put("licenseEntryName", getLicenseEntryName());
		attributes.put("licenseEntryType", getLicenseEntryType());
		attributes.put("licenseVersion", getLicenseVersion());
		attributes.put("productName", getProductName());
		attributes.put("productId", getProductId());
		attributes.put("productVersion", getProductVersion());
		attributes.put("clusterId", getClusterId());
		attributes.put("name", getName());
		attributes.put("owner", getOwner());
		attributes.put("maxClusterNodes", getMaxClusterNodes());
		attributes.put("maxServers", getMaxServers());
		attributes.put("maxConcurrentUsers", getMaxConcurrentUsers());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("maxHttpSessions", getMaxHttpSessions());
		attributes.put("sizing", getSizing());
		attributes.put("description", getDescription());
		attributes.put("hostName", getHostName());
		attributes.put("ipAddresses", getIpAddresses());
		attributes.put("macAddresses", getMacAddresses());
		attributes.put("serverId", getServerId());
		attributes.put("key", getKey());
		attributes.put("startDate", getStartDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("additionalInfo", getAdditionalInfo());
		attributes.put("complimentary", isComplimentary());
		attributes.put("active", isActive());

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

		Long licenseKeyId = (Long)attributes.get("licenseKeyId");

		if (licenseKeyId != null) {
			setLicenseKeyId(licenseKeyId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String userUuid = (String)attributes.get("userUuid");

		if (userUuid != null) {
			setUserUuid(userUuid);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String modifiedUserUuid = (String)attributes.get("modifiedUserUuid");

		if (modifiedUserUuid != null) {
			setModifiedUserUuid(modifiedUserUuid);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String assetReceiptLicenseUuid = (String)attributes.get(
			"assetReceiptLicenseUuid");

		if (assetReceiptLicenseUuid != null) {
			setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
		}

		String accountKey = (String)attributes.get("accountKey");

		if (accountKey != null) {
			setAccountKey(accountKey);
		}

		String productPurchaseKey = (String)attributes.get(
			"productPurchaseKey");

		if (productPurchaseKey != null) {
			setProductPurchaseKey(productPurchaseKey);
		}

		Long licenseEntryId = (Long)attributes.get("licenseEntryId");

		if (licenseEntryId != null) {
			setLicenseEntryId(licenseEntryId);
		}

		String productKey = (String)attributes.get("productKey");

		if (productKey != null) {
			setProductKey(productKey);
		}

		String accountName = (String)attributes.get("accountName");

		if (accountName != null) {
			setAccountName(accountName);
		}

		String licenseEntryName = (String)attributes.get("licenseEntryName");

		if (licenseEntryName != null) {
			setLicenseEntryName(licenseEntryName);
		}

		String licenseEntryType = (String)attributes.get("licenseEntryType");

		if (licenseEntryType != null) {
			setLicenseEntryType(licenseEntryType);
		}

		Integer licenseVersion = (Integer)attributes.get("licenseVersion");

		if (licenseVersion != null) {
			setLicenseVersion(licenseVersion);
		}

		String productName = (String)attributes.get("productName");

		if (productName != null) {
			setProductName(productName);
		}

		String productId = (String)attributes.get("productId");

		if (productId != null) {
			setProductId(productId);
		}

		String productVersion = (String)attributes.get("productVersion");

		if (productVersion != null) {
			setProductVersion(productVersion);
		}

		Long clusterId = (Long)attributes.get("clusterId");

		if (clusterId != null) {
			setClusterId(clusterId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String owner = (String)attributes.get("owner");

		if (owner != null) {
			setOwner(owner);
		}

		Integer maxClusterNodes = (Integer)attributes.get("maxClusterNodes");

		if (maxClusterNodes != null) {
			setMaxClusterNodes(maxClusterNodes);
		}

		Integer maxServers = (Integer)attributes.get("maxServers");

		if (maxServers != null) {
			setMaxServers(maxServers);
		}

		Long maxConcurrentUsers = (Long)attributes.get("maxConcurrentUsers");

		if (maxConcurrentUsers != null) {
			setMaxConcurrentUsers(maxConcurrentUsers);
		}

		Long maxUsers = (Long)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Integer maxHttpSessions = (Integer)attributes.get("maxHttpSessions");

		if (maxHttpSessions != null) {
			setMaxHttpSessions(maxHttpSessions);
		}

		String sizing = (String)attributes.get("sizing");

		if (sizing != null) {
			setSizing(sizing);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String hostName = (String)attributes.get("hostName");

		if (hostName != null) {
			setHostName(hostName);
		}

		String ipAddresses = (String)attributes.get("ipAddresses");

		if (ipAddresses != null) {
			setIpAddresses(ipAddresses);
		}

		String macAddresses = (String)attributes.get("macAddresses");

		if (macAddresses != null) {
			setMacAddresses(macAddresses);
		}

		String serverId = (String)attributes.get("serverId");

		if (serverId != null) {
			setServerId(serverId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		String additionalInfo = (String)attributes.get("additionalInfo");

		if (additionalInfo != null) {
			setAdditionalInfo(additionalInfo);
		}

		Boolean complimentary = (Boolean)attributes.get("complimentary");

		if (complimentary != null) {
			setComplimentary(complimentary);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public LicenseEntry fetchLicenseEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.fetchLicenseEntry();
	}

	/**
	 * Returns the account key of this license key.
	 *
	 * @return the account key of this license key
	 */
	@Override
	public String getAccountKey() {
		return model.getAccountKey();
	}

	/**
	 * Returns the account name of this license key.
	 *
	 * @return the account name of this license key
	 */
	@Override
	public String getAccountName() {
		return model.getAccountName();
	}

	/**
	 * Returns the active of this license key.
	 *
	 * @return the active of this license key
	 */
	@Override
	public boolean getActive() {
		return model.getActive();
	}

	/**
	 * Returns the additional info of this license key.
	 *
	 * @return the additional info of this license key
	 */
	@Override
	public String getAdditionalInfo() {
		return model.getAdditionalInfo();
	}

	/**
	 * Returns the asset receipt license uuid of this license key.
	 *
	 * @return the asset receipt license uuid of this license key
	 */
	@Override
	public String getAssetReceiptLicenseUuid() {
		return model.getAssetReceiptLicenseUuid();
	}

	/**
	 * Returns the cluster ID of this license key.
	 *
	 * @return the cluster ID of this license key
	 */
	@Override
	public long getClusterId() {
		return model.getClusterId();
	}

	/**
	 * Returns the company ID of this license key.
	 *
	 * @return the company ID of this license key
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the complimentary of this license key.
	 *
	 * @return the complimentary of this license key
	 */
	@Override
	public boolean getComplimentary() {
		return model.getComplimentary();
	}

	/**
	 * Returns the create date of this license key.
	 *
	 * @return the create date of this license key
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this license key.
	 *
	 * @return the description of this license key
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the expiration date of this license key.
	 *
	 * @return the expiration date of this license key
	 */
	@Override
	public Date getExpirationDate() {
		return model.getExpirationDate();
	}

	/**
	 * Returns the host name of this license key.
	 *
	 * @return the host name of this license key
	 */
	@Override
	public String getHostName() {
		return model.getHostName();
	}

	/**
	 * Returns the ip addresses of this license key.
	 *
	 * @return the ip addresses of this license key
	 */
	@Override
	public String getIpAddresses() {
		return model.getIpAddresses();
	}

	/**
	 * Returns the key of this license key.
	 *
	 * @return the key of this license key
	 */
	@Override
	public String getKey() {
		return model.getKey();
	}

	@Override
	public LicenseEntry getLicenseEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getLicenseEntry();
	}

	/**
	 * Returns the license entry ID of this license key.
	 *
	 * @return the license entry ID of this license key
	 */
	@Override
	public long getLicenseEntryId() {
		return model.getLicenseEntryId();
	}

	/**
	 * Returns the license entry name of this license key.
	 *
	 * @return the license entry name of this license key
	 */
	@Override
	public String getLicenseEntryName() {
		return model.getLicenseEntryName();
	}

	/**
	 * Returns the license entry type of this license key.
	 *
	 * @return the license entry type of this license key
	 */
	@Override
	public String getLicenseEntryType() {
		return model.getLicenseEntryType();
	}

	/**
	 * Returns the license key ID of this license key.
	 *
	 * @return the license key ID of this license key
	 */
	@Override
	public long getLicenseKeyId() {
		return model.getLicenseKeyId();
	}

	/**
	 * Returns the license version of this license key.
	 *
	 * @return the license version of this license key
	 */
	@Override
	public int getLicenseVersion() {
		return model.getLicenseVersion();
	}

	/**
	 * Returns the mac addresses of this license key.
	 *
	 * @return the mac addresses of this license key
	 */
	@Override
	public String getMacAddresses() {
		return model.getMacAddresses();
	}

	/**
	 * Returns the max cluster nodes of this license key.
	 *
	 * @return the max cluster nodes of this license key
	 */
	@Override
	public int getMaxClusterNodes() {
		return model.getMaxClusterNodes();
	}

	/**
	 * Returns the max concurrent users of this license key.
	 *
	 * @return the max concurrent users of this license key
	 */
	@Override
	public long getMaxConcurrentUsers() {
		return model.getMaxConcurrentUsers();
	}

	/**
	 * Returns the max http sessions of this license key.
	 *
	 * @return the max http sessions of this license key
	 */
	@Override
	public int getMaxHttpSessions() {
		return model.getMaxHttpSessions();
	}

	/**
	 * Returns the max servers of this license key.
	 *
	 * @return the max servers of this license key
	 */
	@Override
	public int getMaxServers() {
		return model.getMaxServers();
	}

	/**
	 * Returns the max users of this license key.
	 *
	 * @return the max users of this license key
	 */
	@Override
	public long getMaxUsers() {
		return model.getMaxUsers();
	}

	/**
	 * Returns the modified date of this license key.
	 *
	 * @return the modified date of this license key
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the modified user name of this license key.
	 *
	 * @return the modified user name of this license key
	 */
	@Override
	public String getModifiedUserName() {
		return model.getModifiedUserName();
	}

	/**
	 * Returns the modified user uuid of this license key.
	 *
	 * @return the modified user uuid of this license key
	 */
	@Override
	public String getModifiedUserUuid() {
		return model.getModifiedUserUuid();
	}

	/**
	 * Returns the mvcc version of this license key.
	 *
	 * @return the mvcc version of this license key
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this license key.
	 *
	 * @return the name of this license key
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the owner of this license key.
	 *
	 * @return the owner of this license key
	 */
	@Override
	public String getOwner() {
		return model.getOwner();
	}

	/**
	 * Returns the primary key of this license key.
	 *
	 * @return the primary key of this license key
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public String getProductEntryName() {
		return model.getProductEntryName();
	}

	/**
	 * Returns the product ID of this license key.
	 *
	 * @return the product ID of this license key
	 */
	@Override
	public String getProductId() {
		return model.getProductId();
	}

	/**
	 * Returns the product key of this license key.
	 *
	 * @return the product key of this license key
	 */
	@Override
	public String getProductKey() {
		return model.getProductKey();
	}

	/**
	 * Returns the product name of this license key.
	 *
	 * @return the product name of this license key
	 */
	@Override
	public String getProductName() {
		return model.getProductName();
	}

	/**
	 * Returns the product purchase key of this license key.
	 *
	 * @return the product purchase key of this license key
	 */
	@Override
	public String getProductPurchaseKey() {
		return model.getProductPurchaseKey();
	}

	/**
	 * Returns the product version of this license key.
	 *
	 * @return the product version of this license key
	 */
	@Override
	public String getProductVersion() {
		return model.getProductVersion();
	}

	@Override
	public String getProductVersionLabel() {
		return model.getProductVersionLabel();
	}

	/**
	 * Returns the server ID of this license key.
	 *
	 * @return the server ID of this license key
	 */
	@Override
	public String getServerId() {
		return model.getServerId();
	}

	/**
	 * Returns the sizing of this license key.
	 *
	 * @return the sizing of this license key
	 */
	@Override
	public String getSizing() {
		return model.getSizing();
	}

	/**
	 * Returns the start date of this license key.
	 *
	 * @return the start date of this license key
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the user name of this license key.
	 *
	 * @return the user name of this license key
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this license key.
	 *
	 * @return the user uuid of this license key
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this license key.
	 *
	 * @return the uuid of this license key
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this license key is active.
	 *
	 * @return <code>true</code> if this license key is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isActive() {
		return model.isActive();
	}

	/**
	 * Returns <code>true</code> if this license key is complimentary.
	 *
	 * @return <code>true</code> if this license key is complimentary; <code>false</code> otherwise
	 */
	@Override
	public boolean isComplimentary() {
		return model.isComplimentary();
	}

	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account key of this license key.
	 *
	 * @param accountKey the account key of this license key
	 */
	@Override
	public void setAccountKey(String accountKey) {
		model.setAccountKey(accountKey);
	}

	/**
	 * Sets the account name of this license key.
	 *
	 * @param accountName the account name of this license key
	 */
	@Override
	public void setAccountName(String accountName) {
		model.setAccountName(accountName);
	}

	/**
	 * Sets whether this license key is active.
	 *
	 * @param active the active of this license key
	 */
	@Override
	public void setActive(boolean active) {
		model.setActive(active);
	}

	/**
	 * Sets the additional info of this license key.
	 *
	 * @param additionalInfo the additional info of this license key
	 */
	@Override
	public void setAdditionalInfo(String additionalInfo) {
		model.setAdditionalInfo(additionalInfo);
	}

	/**
	 * Sets the asset receipt license uuid of this license key.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid of this license key
	 */
	@Override
	public void setAssetReceiptLicenseUuid(String assetReceiptLicenseUuid) {
		model.setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
	}

	/**
	 * Sets the cluster ID of this license key.
	 *
	 * @param clusterId the cluster ID of this license key
	 */
	@Override
	public void setClusterId(long clusterId) {
		model.setClusterId(clusterId);
	}

	/**
	 * Sets the company ID of this license key.
	 *
	 * @param companyId the company ID of this license key
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets whether this license key is complimentary.
	 *
	 * @param complimentary the complimentary of this license key
	 */
	@Override
	public void setComplimentary(boolean complimentary) {
		model.setComplimentary(complimentary);
	}

	/**
	 * Sets the create date of this license key.
	 *
	 * @param createDate the create date of this license key
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this license key.
	 *
	 * @param description the description of this license key
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the expiration date of this license key.
	 *
	 * @param expirationDate the expiration date of this license key
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		model.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the host name of this license key.
	 *
	 * @param hostName the host name of this license key
	 */
	@Override
	public void setHostName(String hostName) {
		model.setHostName(hostName);
	}

	/**
	 * Sets the ip addresses of this license key.
	 *
	 * @param ipAddresses the ip addresses of this license key
	 */
	@Override
	public void setIpAddresses(String ipAddresses) {
		model.setIpAddresses(ipAddresses);
	}

	/**
	 * Sets the key of this license key.
	 *
	 * @param key the key of this license key
	 */
	@Override
	public void setKey(String key) {
		model.setKey(key);
	}

	/**
	 * Sets the license entry ID of this license key.
	 *
	 * @param licenseEntryId the license entry ID of this license key
	 */
	@Override
	public void setLicenseEntryId(long licenseEntryId) {
		model.setLicenseEntryId(licenseEntryId);
	}

	/**
	 * Sets the license entry name of this license key.
	 *
	 * @param licenseEntryName the license entry name of this license key
	 */
	@Override
	public void setLicenseEntryName(String licenseEntryName) {
		model.setLicenseEntryName(licenseEntryName);
	}

	/**
	 * Sets the license entry type of this license key.
	 *
	 * @param licenseEntryType the license entry type of this license key
	 */
	@Override
	public void setLicenseEntryType(String licenseEntryType) {
		model.setLicenseEntryType(licenseEntryType);
	}

	/**
	 * Sets the license key ID of this license key.
	 *
	 * @param licenseKeyId the license key ID of this license key
	 */
	@Override
	public void setLicenseKeyId(long licenseKeyId) {
		model.setLicenseKeyId(licenseKeyId);
	}

	/**
	 * Sets the license version of this license key.
	 *
	 * @param licenseVersion the license version of this license key
	 */
	@Override
	public void setLicenseVersion(int licenseVersion) {
		model.setLicenseVersion(licenseVersion);
	}

	/**
	 * Sets the mac addresses of this license key.
	 *
	 * @param macAddresses the mac addresses of this license key
	 */
	@Override
	public void setMacAddresses(String macAddresses) {
		model.setMacAddresses(macAddresses);
	}

	/**
	 * Sets the max cluster nodes of this license key.
	 *
	 * @param maxClusterNodes the max cluster nodes of this license key
	 */
	@Override
	public void setMaxClusterNodes(int maxClusterNodes) {
		model.setMaxClusterNodes(maxClusterNodes);
	}

	/**
	 * Sets the max concurrent users of this license key.
	 *
	 * @param maxConcurrentUsers the max concurrent users of this license key
	 */
	@Override
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		model.setMaxConcurrentUsers(maxConcurrentUsers);
	}

	/**
	 * Sets the max http sessions of this license key.
	 *
	 * @param maxHttpSessions the max http sessions of this license key
	 */
	@Override
	public void setMaxHttpSessions(int maxHttpSessions) {
		model.setMaxHttpSessions(maxHttpSessions);
	}

	/**
	 * Sets the max servers of this license key.
	 *
	 * @param maxServers the max servers of this license key
	 */
	@Override
	public void setMaxServers(int maxServers) {
		model.setMaxServers(maxServers);
	}

	/**
	 * Sets the max users of this license key.
	 *
	 * @param maxUsers the max users of this license key
	 */
	@Override
	public void setMaxUsers(long maxUsers) {
		model.setMaxUsers(maxUsers);
	}

	/**
	 * Sets the modified date of this license key.
	 *
	 * @param modifiedDate the modified date of this license key
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the modified user name of this license key.
	 *
	 * @param modifiedUserName the modified user name of this license key
	 */
	@Override
	public void setModifiedUserName(String modifiedUserName) {
		model.setModifiedUserName(modifiedUserName);
	}

	/**
	 * Sets the modified user uuid of this license key.
	 *
	 * @param modifiedUserUuid the modified user uuid of this license key
	 */
	@Override
	public void setModifiedUserUuid(String modifiedUserUuid) {
		model.setModifiedUserUuid(modifiedUserUuid);
	}

	/**
	 * Sets the mvcc version of this license key.
	 *
	 * @param mvccVersion the mvcc version of this license key
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this license key.
	 *
	 * @param name the name of this license key
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the owner of this license key.
	 *
	 * @param owner the owner of this license key
	 */
	@Override
	public void setOwner(String owner) {
		model.setOwner(owner);
	}

	/**
	 * Sets the primary key of this license key.
	 *
	 * @param primaryKey the primary key of this license key
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product ID of this license key.
	 *
	 * @param productId the product ID of this license key
	 */
	@Override
	public void setProductId(String productId) {
		model.setProductId(productId);
	}

	/**
	 * Sets the product key of this license key.
	 *
	 * @param productKey the product key of this license key
	 */
	@Override
	public void setProductKey(String productKey) {
		model.setProductKey(productKey);
	}

	/**
	 * Sets the product name of this license key.
	 *
	 * @param productName the product name of this license key
	 */
	@Override
	public void setProductName(String productName) {
		model.setProductName(productName);
	}

	/**
	 * Sets the product purchase key of this license key.
	 *
	 * @param productPurchaseKey the product purchase key of this license key
	 */
	@Override
	public void setProductPurchaseKey(String productPurchaseKey) {
		model.setProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Sets the product version of this license key.
	 *
	 * @param productVersion the product version of this license key
	 */
	@Override
	public void setProductVersion(String productVersion) {
		model.setProductVersion(productVersion);
	}

	/**
	 * Sets the server ID of this license key.
	 *
	 * @param serverId the server ID of this license key
	 */
	@Override
	public void setServerId(String serverId) {
		model.setServerId(serverId);
	}

	/**
	 * Sets the sizing of this license key.
	 *
	 * @param sizing the sizing of this license key
	 */
	@Override
	public void setSizing(String sizing) {
		model.setSizing(sizing);
	}

	/**
	 * Sets the start date of this license key.
	 *
	 * @param startDate the start date of this license key
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the user name of this license key.
	 *
	 * @param userName the user name of this license key
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this license key.
	 *
	 * @param userUuid the user uuid of this license key
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this license key.
	 *
	 * @param uuid the uuid of this license key
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
	protected LicenseKeyWrapper wrap(LicenseKey licenseKey) {
		return new LicenseKeyWrapper(licenseKey);
	}

}