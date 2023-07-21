/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model.impl;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LicenseKey in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeyCacheModel
	implements CacheModel<LicenseKey>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKeyCacheModel)) {
			return false;
		}

		LicenseKeyCacheModel licenseKeyCacheModel =
			(LicenseKeyCacheModel)object;

		if ((licenseKeyId == licenseKeyCacheModel.licenseKeyId) &&
			(mvccVersion == licenseKeyCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, licenseKeyId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(85);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", licenseKeyId=");
		sb.append(licenseKeyId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userUuid=");
		sb.append(userUuid);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedUserUuid=");
		sb.append(modifiedUserUuid);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);
		sb.append(", accountKey=");
		sb.append(accountKey);
		sb.append(", productPurchaseKey=");
		sb.append(productPurchaseKey);
		sb.append(", licenseEntryId=");
		sb.append(licenseEntryId);
		sb.append(", productKey=");
		sb.append(productKey);
		sb.append(", accountName=");
		sb.append(accountName);
		sb.append(", licenseEntryName=");
		sb.append(licenseEntryName);
		sb.append(", licenseEntryType=");
		sb.append(licenseEntryType);
		sb.append(", licenseVersion=");
		sb.append(licenseVersion);
		sb.append(", productName=");
		sb.append(productName);
		sb.append(", productId=");
		sb.append(productId);
		sb.append(", productVersion=");
		sb.append(productVersion);
		sb.append(", clusterId=");
		sb.append(clusterId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", owner=");
		sb.append(owner);
		sb.append(", maxClusterNodes=");
		sb.append(maxClusterNodes);
		sb.append(", maxServers=");
		sb.append(maxServers);
		sb.append(", maxConcurrentUsers=");
		sb.append(maxConcurrentUsers);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", maxHttpSessions=");
		sb.append(maxHttpSessions);
		sb.append(", sizing=");
		sb.append(sizing);
		sb.append(", description=");
		sb.append(description);
		sb.append(", hostName=");
		sb.append(hostName);
		sb.append(", ipAddresses=");
		sb.append(ipAddresses);
		sb.append(", macAddresses=");
		sb.append(macAddresses);
		sb.append(", serverId=");
		sb.append(serverId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", additionalInfo=");
		sb.append(additionalInfo);
		sb.append(", complimentary=");
		sb.append(complimentary);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LicenseKey toEntityModel() {
		LicenseKeyImpl licenseKeyImpl = new LicenseKeyImpl();

		licenseKeyImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			licenseKeyImpl.setUuid("");
		}
		else {
			licenseKeyImpl.setUuid(uuid);
		}

		licenseKeyImpl.setLicenseKeyId(licenseKeyId);
		licenseKeyImpl.setCompanyId(companyId);

		if (userUuid == null) {
			licenseKeyImpl.setUserUuid("");
		}
		else {
			licenseKeyImpl.setUserUuid(userUuid);
		}

		if (userName == null) {
			licenseKeyImpl.setUserName("");
		}
		else {
			licenseKeyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseKeyImpl.setCreateDate(null);
		}
		else {
			licenseKeyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedUserUuid == null) {
			licenseKeyImpl.setModifiedUserUuid("");
		}
		else {
			licenseKeyImpl.setModifiedUserUuid(modifiedUserUuid);
		}

		if (modifiedUserName == null) {
			licenseKeyImpl.setModifiedUserName("");
		}
		else {
			licenseKeyImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseKeyImpl.setModifiedDate(null);
		}
		else {
			licenseKeyImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (assetReceiptLicenseUuid == null) {
			licenseKeyImpl.setAssetReceiptLicenseUuid("");
		}
		else {
			licenseKeyImpl.setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
		}

		if (accountKey == null) {
			licenseKeyImpl.setAccountKey("");
		}
		else {
			licenseKeyImpl.setAccountKey(accountKey);
		}

		if (productPurchaseKey == null) {
			licenseKeyImpl.setProductPurchaseKey("");
		}
		else {
			licenseKeyImpl.setProductPurchaseKey(productPurchaseKey);
		}

		licenseKeyImpl.setLicenseEntryId(licenseEntryId);

		if (productKey == null) {
			licenseKeyImpl.setProductKey("");
		}
		else {
			licenseKeyImpl.setProductKey(productKey);
		}

		if (accountName == null) {
			licenseKeyImpl.setAccountName("");
		}
		else {
			licenseKeyImpl.setAccountName(accountName);
		}

		if (licenseEntryName == null) {
			licenseKeyImpl.setLicenseEntryName("");
		}
		else {
			licenseKeyImpl.setLicenseEntryName(licenseEntryName);
		}

		if (licenseEntryType == null) {
			licenseKeyImpl.setLicenseEntryType("");
		}
		else {
			licenseKeyImpl.setLicenseEntryType(licenseEntryType);
		}

		licenseKeyImpl.setLicenseVersion(licenseVersion);

		if (productName == null) {
			licenseKeyImpl.setProductName("");
		}
		else {
			licenseKeyImpl.setProductName(productName);
		}

		if (productId == null) {
			licenseKeyImpl.setProductId("");
		}
		else {
			licenseKeyImpl.setProductId(productId);
		}

		if (productVersion == null) {
			licenseKeyImpl.setProductVersion("");
		}
		else {
			licenseKeyImpl.setProductVersion(productVersion);
		}

		licenseKeyImpl.setClusterId(clusterId);

		if (name == null) {
			licenseKeyImpl.setName("");
		}
		else {
			licenseKeyImpl.setName(name);
		}

		if (owner == null) {
			licenseKeyImpl.setOwner("");
		}
		else {
			licenseKeyImpl.setOwner(owner);
		}

		licenseKeyImpl.setMaxClusterNodes(maxClusterNodes);
		licenseKeyImpl.setMaxServers(maxServers);
		licenseKeyImpl.setMaxConcurrentUsers(maxConcurrentUsers);
		licenseKeyImpl.setMaxUsers(maxUsers);
		licenseKeyImpl.setMaxHttpSessions(maxHttpSessions);

		if (sizing == null) {
			licenseKeyImpl.setSizing("");
		}
		else {
			licenseKeyImpl.setSizing(sizing);
		}

		if (description == null) {
			licenseKeyImpl.setDescription("");
		}
		else {
			licenseKeyImpl.setDescription(description);
		}

		if (hostName == null) {
			licenseKeyImpl.setHostName("");
		}
		else {
			licenseKeyImpl.setHostName(hostName);
		}

		if (ipAddresses == null) {
			licenseKeyImpl.setIpAddresses("");
		}
		else {
			licenseKeyImpl.setIpAddresses(ipAddresses);
		}

		if (macAddresses == null) {
			licenseKeyImpl.setMacAddresses("");
		}
		else {
			licenseKeyImpl.setMacAddresses(macAddresses);
		}

		if (serverId == null) {
			licenseKeyImpl.setServerId("");
		}
		else {
			licenseKeyImpl.setServerId(serverId);
		}

		if (key == null) {
			licenseKeyImpl.setKey("");
		}
		else {
			licenseKeyImpl.setKey(key);
		}

		if (startDate == Long.MIN_VALUE) {
			licenseKeyImpl.setStartDate(null);
		}
		else {
			licenseKeyImpl.setStartDate(new Date(startDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			licenseKeyImpl.setExpirationDate(null);
		}
		else {
			licenseKeyImpl.setExpirationDate(new Date(expirationDate));
		}

		if (additionalInfo == null) {
			licenseKeyImpl.setAdditionalInfo("");
		}
		else {
			licenseKeyImpl.setAdditionalInfo(additionalInfo);
		}

		licenseKeyImpl.setComplimentary(complimentary);
		licenseKeyImpl.setActive(active);

		licenseKeyImpl.resetOriginalValues();

		return licenseKeyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		licenseKeyId = objectInput.readLong();

		companyId = objectInput.readLong();
		userUuid = objectInput.readUTF();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedUserUuid = objectInput.readUTF();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		assetReceiptLicenseUuid = objectInput.readUTF();
		accountKey = objectInput.readUTF();
		productPurchaseKey = objectInput.readUTF();

		licenseEntryId = objectInput.readLong();
		productKey = objectInput.readUTF();
		accountName = objectInput.readUTF();
		licenseEntryName = objectInput.readUTF();
		licenseEntryType = objectInput.readUTF();

		licenseVersion = objectInput.readInt();
		productName = objectInput.readUTF();
		productId = objectInput.readUTF();
		productVersion = objectInput.readUTF();

		clusterId = objectInput.readLong();
		name = objectInput.readUTF();
		owner = objectInput.readUTF();

		maxClusterNodes = objectInput.readInt();

		maxServers = objectInput.readInt();

		maxConcurrentUsers = objectInput.readLong();

		maxUsers = objectInput.readLong();

		maxHttpSessions = objectInput.readInt();
		sizing = objectInput.readUTF();
		description = objectInput.readUTF();
		hostName = objectInput.readUTF();
		ipAddresses = objectInput.readUTF();
		macAddresses = objectInput.readUTF();
		serverId = objectInput.readUTF();
		key = objectInput.readUTF();
		startDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
		additionalInfo = objectInput.readUTF();

		complimentary = objectInput.readBoolean();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(licenseKeyId);

		objectOutput.writeLong(companyId);

		if (userUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userUuid);
		}

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		if (modifiedUserUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifiedUserUuid);
		}

		if (modifiedUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifiedUserName);
		}

		objectOutput.writeLong(modifiedDate);

		if (assetReceiptLicenseUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assetReceiptLicenseUuid);
		}

		if (accountKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountKey);
		}

		if (productPurchaseKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productPurchaseKey);
		}

		objectOutput.writeLong(licenseEntryId);

		if (productKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productKey);
		}

		if (accountName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountName);
		}

		if (licenseEntryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(licenseEntryName);
		}

		if (licenseEntryType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(licenseEntryType);
		}

		objectOutput.writeInt(licenseVersion);

		if (productName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productName);
		}

		if (productId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productId);
		}

		if (productVersion == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productVersion);
		}

		objectOutput.writeLong(clusterId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (owner == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(owner);
		}

		objectOutput.writeInt(maxClusterNodes);

		objectOutput.writeInt(maxServers);

		objectOutput.writeLong(maxConcurrentUsers);

		objectOutput.writeLong(maxUsers);

		objectOutput.writeInt(maxHttpSessions);

		if (sizing == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sizing);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (hostName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hostName);
		}

		if (ipAddresses == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ipAddresses);
		}

		if (macAddresses == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(macAddresses);
		}

		if (serverId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverId);
		}

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(expirationDate);

		if (additionalInfo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(additionalInfo);
		}

		objectOutput.writeBoolean(complimentary);

		objectOutput.writeBoolean(active);
	}

	public long mvccVersion;
	public String uuid;
	public long licenseKeyId;
	public long companyId;
	public String userUuid;
	public String userName;
	public long createDate;
	public String modifiedUserUuid;
	public String modifiedUserName;
	public long modifiedDate;
	public String assetReceiptLicenseUuid;
	public String accountKey;
	public String productPurchaseKey;
	public long licenseEntryId;
	public String productKey;
	public String accountName;
	public String licenseEntryName;
	public String licenseEntryType;
	public int licenseVersion;
	public String productName;
	public String productId;
	public String productVersion;
	public long clusterId;
	public String name;
	public String owner;
	public int maxClusterNodes;
	public int maxServers;
	public long maxConcurrentUsers;
	public long maxUsers;
	public int maxHttpSessions;
	public String sizing;
	public String description;
	public String hostName;
	public String ipAddresses;
	public String macAddresses;
	public String serverId;
	public String key;
	public long startDate;
	public long expirationDate;
	public String additionalInfo;
	public boolean complimentary;
	public boolean active;

}