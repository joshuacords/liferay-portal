/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.provisioning.license.service.http.LicenseKeyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeySoap implements Serializable {

	public static LicenseKeySoap toSoapModel(LicenseKey model) {
		LicenseKeySoap soapModel = new LicenseKeySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setLicenseKeyId(model.getLicenseKeyId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserUuid(model.getUserUuid());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedUserUuid(model.getModifiedUserUuid());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssetReceiptLicenseUuid(
			model.getAssetReceiptLicenseUuid());
		soapModel.setAccountKey(model.getAccountKey());
		soapModel.setProductPurchaseKey(model.getProductPurchaseKey());
		soapModel.setLicenseEntryId(model.getLicenseEntryId());
		soapModel.setProductKey(model.getProductKey());
		soapModel.setAccountName(model.getAccountName());
		soapModel.setLicenseEntryName(model.getLicenseEntryName());
		soapModel.setLicenseEntryType(model.getLicenseEntryType());
		soapModel.setLicenseVersion(model.getLicenseVersion());
		soapModel.setProductName(model.getProductName());
		soapModel.setProductId(model.getProductId());
		soapModel.setProductVersion(model.getProductVersion());
		soapModel.setClusterId(model.getClusterId());
		soapModel.setName(model.getName());
		soapModel.setOwner(model.getOwner());
		soapModel.setMaxClusterNodes(model.getMaxClusterNodes());
		soapModel.setMaxServers(model.getMaxServers());
		soapModel.setMaxConcurrentUsers(model.getMaxConcurrentUsers());
		soapModel.setMaxUsers(model.getMaxUsers());
		soapModel.setMaxHttpSessions(model.getMaxHttpSessions());
		soapModel.setSizing(model.getSizing());
		soapModel.setDescription(model.getDescription());
		soapModel.setHostName(model.getHostName());
		soapModel.setIpAddresses(model.getIpAddresses());
		soapModel.setMacAddresses(model.getMacAddresses());
		soapModel.setServerId(model.getServerId());
		soapModel.setKey(model.getKey());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setAdditionalInfo(model.getAdditionalInfo());
		soapModel.setComplimentary(model.isComplimentary());
		soapModel.setActive(model.isActive());

		return soapModel;
	}

	public static LicenseKeySoap[] toSoapModels(LicenseKey[] models) {
		LicenseKeySoap[] soapModels = new LicenseKeySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LicenseKeySoap[][] toSoapModels(LicenseKey[][] models) {
		LicenseKeySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LicenseKeySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LicenseKeySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LicenseKeySoap[] toSoapModels(List<LicenseKey> models) {
		List<LicenseKeySoap> soapModels = new ArrayList<LicenseKeySoap>(
			models.size());

		for (LicenseKey model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LicenseKeySoap[soapModels.size()]);
	}

	public LicenseKeySoap() {
	}

	public long getPrimaryKey() {
		return _licenseKeyId;
	}

	public void setPrimaryKey(long pk) {
		setLicenseKeyId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLicenseKeyId() {
		return _licenseKeyId;
	}

	public void setLicenseKeyId(long licenseKeyId) {
		_licenseKeyId = licenseKeyId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getUserUuid() {
		return _userUuid;
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getModifiedUserUuid() {
		return _modifiedUserUuid;
	}

	public void setModifiedUserUuid(String modifiedUserUuid) {
		_modifiedUserUuid = modifiedUserUuid;
	}

	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAssetReceiptLicenseUuid() {
		return _assetReceiptLicenseUuid;
	}

	public void setAssetReceiptLicenseUuid(String assetReceiptLicenseUuid) {
		_assetReceiptLicenseUuid = assetReceiptLicenseUuid;
	}

	public String getAccountKey() {
		return _accountKey;
	}

	public void setAccountKey(String accountKey) {
		_accountKey = accountKey;
	}

	public String getProductPurchaseKey() {
		return _productPurchaseKey;
	}

	public void setProductPurchaseKey(String productPurchaseKey) {
		_productPurchaseKey = productPurchaseKey;
	}

	public long getLicenseEntryId() {
		return _licenseEntryId;
	}

	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntryId = licenseEntryId;
	}

	public String getProductKey() {
		return _productKey;
	}

	public void setProductKey(String productKey) {
		_productKey = productKey;
	}

	public String getAccountName() {
		return _accountName;
	}

	public void setAccountName(String accountName) {
		_accountName = accountName;
	}

	public String getLicenseEntryName() {
		return _licenseEntryName;
	}

	public void setLicenseEntryName(String licenseEntryName) {
		_licenseEntryName = licenseEntryName;
	}

	public String getLicenseEntryType() {
		return _licenseEntryType;
	}

	public void setLicenseEntryType(String licenseEntryType) {
		_licenseEntryType = licenseEntryType;
	}

	public int getLicenseVersion() {
		return _licenseVersion;
	}

	public void setLicenseVersion(int licenseVersion) {
		_licenseVersion = licenseVersion;
	}

	public String getProductName() {
		return _productName;
	}

	public void setProductName(String productName) {
		_productName = productName;
	}

	public String getProductId() {
		return _productId;
	}

	public void setProductId(String productId) {
		_productId = productId;
	}

	public String getProductVersion() {
		return _productVersion;
	}

	public void setProductVersion(String productVersion) {
		_productVersion = productVersion;
	}

	public long getClusterId() {
		return _clusterId;
	}

	public void setClusterId(long clusterId) {
		_clusterId = clusterId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getOwner() {
		return _owner;
	}

	public void setOwner(String owner) {
		_owner = owner;
	}

	public int getMaxClusterNodes() {
		return _maxClusterNodes;
	}

	public void setMaxClusterNodes(int maxClusterNodes) {
		_maxClusterNodes = maxClusterNodes;
	}

	public int getMaxServers() {
		return _maxServers;
	}

	public void setMaxServers(int maxServers) {
		_maxServers = maxServers;
	}

	public long getMaxConcurrentUsers() {
		return _maxConcurrentUsers;
	}

	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_maxConcurrentUsers = maxConcurrentUsers;
	}

	public long getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(long maxUsers) {
		_maxUsers = maxUsers;
	}

	public int getMaxHttpSessions() {
		return _maxHttpSessions;
	}

	public void setMaxHttpSessions(int maxHttpSessions) {
		_maxHttpSessions = maxHttpSessions;
	}

	public String getSizing() {
		return _sizing;
	}

	public void setSizing(String sizing) {
		_sizing = sizing;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getHostName() {
		return _hostName;
	}

	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	public String getIpAddresses() {
		return _ipAddresses;
	}

	public void setIpAddresses(String ipAddresses) {
		_ipAddresses = ipAddresses;
	}

	public String getMacAddresses() {
		return _macAddresses;
	}

	public void setMacAddresses(String macAddresses) {
		_macAddresses = macAddresses;
	}

	public String getServerId() {
		return _serverId;
	}

	public void setServerId(String serverId) {
		_serverId = serverId;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public String getAdditionalInfo() {
		return _additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		_additionalInfo = additionalInfo;
	}

	public boolean getComplimentary() {
		return _complimentary;
	}

	public boolean isComplimentary() {
		return _complimentary;
	}

	public void setComplimentary(boolean complimentary) {
		_complimentary = complimentary;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _licenseKeyId;
	private long _companyId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private String _modifiedUserUuid;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private String _assetReceiptLicenseUuid;
	private String _accountKey;
	private String _productPurchaseKey;
	private long _licenseEntryId;
	private String _productKey;
	private String _accountName;
	private String _licenseEntryName;
	private String _licenseEntryType;
	private int _licenseVersion;
	private String _productName;
	private String _productId;
	private String _productVersion;
	private long _clusterId;
	private String _name;
	private String _owner;
	private int _maxClusterNodes;
	private int _maxServers;
	private long _maxConcurrentUsers;
	private long _maxUsers;
	private int _maxHttpSessions;
	private String _sizing;
	private String _description;
	private String _hostName;
	private String _ipAddresses;
	private String _macAddresses;
	private String _serverId;
	private String _key;
	private Date _startDate;
	private Date _expirationDate;
	private String _additionalInfo;
	private boolean _complimentary;
	private boolean _active;

}