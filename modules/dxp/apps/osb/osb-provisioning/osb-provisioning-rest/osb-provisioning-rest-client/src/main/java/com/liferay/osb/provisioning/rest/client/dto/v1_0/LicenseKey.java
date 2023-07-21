/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.dto.v1_0;

import com.liferay.osb.provisioning.rest.client.function.UnsafeSupplier;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.LicenseKeySerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class LicenseKey implements Cloneable, Serializable {

	public static LicenseKey toDTO(String json) {
		return LicenseKeySerDes.toDTO(json);
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public void setAccountKey(
		UnsafeSupplier<String, Exception> accountKeyUnsafeSupplier) {

		try {
			accountKey = accountKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String accountKey;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setAccountName(
		UnsafeSupplier<String, Exception> accountNameUnsafeSupplier) {

		try {
			accountName = accountNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String accountName;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setActive(
		UnsafeSupplier<Boolean, Exception> activeUnsafeSupplier) {

		try {
			active = activeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean active;

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setAdditionalInfo(
		UnsafeSupplier<String, Exception> additionalInfoUnsafeSupplier) {

		try {
			additionalInfo = additionalInfoUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String additionalInfo;

	public String getAssetReceiptLicenseUuid() {
		return assetReceiptLicenseUuid;
	}

	public void setAssetReceiptLicenseUuid(String assetReceiptLicenseUuid) {
		this.assetReceiptLicenseUuid = assetReceiptLicenseUuid;
	}

	public void setAssetReceiptLicenseUuid(
		UnsafeSupplier<String, Exception>
			assetReceiptLicenseUuidUnsafeSupplier) {

		try {
			assetReceiptLicenseUuid =
				assetReceiptLicenseUuidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String assetReceiptLicenseUuid;

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	public void setClusterId(
		UnsafeSupplier<Long, Exception> clusterIdUnsafeSupplier) {

		try {
			clusterId = clusterIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long clusterId;

	public Boolean getComplimentary() {
		return complimentary;
	}

	public void setComplimentary(Boolean complimentary) {
		this.complimentary = complimentary;
	}

	public void setComplimentary(
		UnsafeSupplier<Boolean, Exception> complimentaryUnsafeSupplier) {

		try {
			complimentary = complimentaryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean complimentary;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date createDate;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setExpirationDate(
		UnsafeSupplier<Date, Exception> expirationDateUnsafeSupplier) {

		try {
			expirationDate = expirationDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date expirationDate;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setHostName(
		UnsafeSupplier<String, Exception> hostNameUnsafeSupplier) {

		try {
			hostName = hostNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String hostName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public String getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(String ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	public void setIpAddresses(
		UnsafeSupplier<String, Exception> ipAddressesUnsafeSupplier) {

		try {
			ipAddresses = ipAddressesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ipAddresses;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String key;

	public LicenseEntryName getLicenseEntryName() {
		return licenseEntryName;
	}

	public String getLicenseEntryNameAsString() {
		if (licenseEntryName == null) {
			return null;
		}

		return licenseEntryName.toString();
	}

	public void setLicenseEntryName(LicenseEntryName licenseEntryName) {
		this.licenseEntryName = licenseEntryName;
	}

	public void setLicenseEntryName(
		UnsafeSupplier<LicenseEntryName, Exception>
			licenseEntryNameUnsafeSupplier) {

		try {
			licenseEntryName = licenseEntryNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected LicenseEntryName licenseEntryName;

	public LicenseEntryType getLicenseEntryType() {
		return licenseEntryType;
	}

	public String getLicenseEntryTypeAsString() {
		if (licenseEntryType == null) {
			return null;
		}

		return licenseEntryType.toString();
	}

	public void setLicenseEntryType(LicenseEntryType licenseEntryType) {
		this.licenseEntryType = licenseEntryType;
	}

	public void setLicenseEntryType(
		UnsafeSupplier<LicenseEntryType, Exception>
			licenseEntryTypeUnsafeSupplier) {

		try {
			licenseEntryType = licenseEntryTypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected LicenseEntryType licenseEntryType;

	public Integer getLicenseVersion() {
		return licenseVersion;
	}

	public void setLicenseVersion(Integer licenseVersion) {
		this.licenseVersion = licenseVersion;
	}

	public void setLicenseVersion(
		UnsafeSupplier<Integer, Exception> licenseVersionUnsafeSupplier) {

		try {
			licenseVersion = licenseVersionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer licenseVersion;

	public String getMacAddresses() {
		return macAddresses;
	}

	public void setMacAddresses(String macAddresses) {
		this.macAddresses = macAddresses;
	}

	public void setMacAddresses(
		UnsafeSupplier<String, Exception> macAddressesUnsafeSupplier) {

		try {
			macAddresses = macAddressesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String macAddresses;

	public Integer getMaxClusterNodes() {
		return maxClusterNodes;
	}

	public void setMaxClusterNodes(Integer maxClusterNodes) {
		this.maxClusterNodes = maxClusterNodes;
	}

	public void setMaxClusterNodes(
		UnsafeSupplier<Integer, Exception> maxClusterNodesUnsafeSupplier) {

		try {
			maxClusterNodes = maxClusterNodesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer maxClusterNodes;

	public Integer getMaxHttpSessions() {
		return maxHttpSessions;
	}

	public void setMaxHttpSessions(Integer maxHttpSessions) {
		this.maxHttpSessions = maxHttpSessions;
	}

	public void setMaxHttpSessions(
		UnsafeSupplier<Integer, Exception> maxHttpSessionsUnsafeSupplier) {

		try {
			maxHttpSessions = maxHttpSessionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer maxHttpSessions;

	public Integer getMaxServers() {
		return maxServers;
	}

	public void setMaxServers(Integer maxServers) {
		this.maxServers = maxServers;
	}

	public void setMaxServers(
		UnsafeSupplier<Integer, Exception> maxServersUnsafeSupplier) {

		try {
			maxServers = maxServersUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer maxServers;

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setModifiedDate(
		UnsafeSupplier<Date, Exception> modifiedDateUnsafeSupplier) {

		try {
			modifiedDate = modifiedDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date modifiedDate;

	public String getModifiedUserName() {
		return modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	public void setModifiedUserName(
		UnsafeSupplier<String, Exception> modifiedUserNameUnsafeSupplier) {

		try {
			modifiedUserName = modifiedUserNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String modifiedUserName;

	public String getModifiedUserUuid() {
		return modifiedUserUuid;
	}

	public void setModifiedUserUuid(String modifiedUserUuid) {
		this.modifiedUserUuid = modifiedUserUuid;
	}

	public void setModifiedUserUuid(
		UnsafeSupplier<String, Exception> modifiedUserUuidUnsafeSupplier) {

		try {
			modifiedUserUuid = modifiedUserUuidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String modifiedUserUuid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setOwner(
		UnsafeSupplier<String, Exception> ownerUnsafeSupplier) {

		try {
			owner = ownerUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String owner;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductId(
		UnsafeSupplier<String, Exception> productIdUnsafeSupplier) {

		try {
			productId = productIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productId;

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public void setProductKey(
		UnsafeSupplier<String, Exception> productKeyUnsafeSupplier) {

		try {
			productKey = productKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productKey;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductName(
		UnsafeSupplier<String, Exception> productNameUnsafeSupplier) {

		try {
			productName = productNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productName;

	public String getProductPurchaseKey() {
		return productPurchaseKey;
	}

	public void setProductPurchaseKey(String productPurchaseKey) {
		this.productPurchaseKey = productPurchaseKey;
	}

	public void setProductPurchaseKey(
		UnsafeSupplier<String, Exception> productPurchaseKeyUnsafeSupplier) {

		try {
			productPurchaseKey = productPurchaseKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productPurchaseKey;

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public void setProductVersion(
		UnsafeSupplier<String, Exception> productVersionUnsafeSupplier) {

		try {
			productVersion = productVersionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productVersion;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public void setServerId(
		UnsafeSupplier<String, Exception> serverIdUnsafeSupplier) {

		try {
			serverId = serverIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String serverId;

	public Sizing getSizing() {
		return sizing;
	}

	public String getSizingAsString() {
		if (sizing == null) {
			return null;
		}

		return sizing.toString();
	}

	public void setSizing(Sizing sizing) {
		this.sizing = sizing;
	}

	public void setSizing(
		UnsafeSupplier<Sizing, Exception> sizingUnsafeSupplier) {

		try {
			sizing = sizingUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Sizing sizing;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStartDate(
		UnsafeSupplier<Date, Exception> startDateUnsafeSupplier) {

		try {
			startDate = startDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date startDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserName(
		UnsafeSupplier<String, Exception> userNameUnsafeSupplier) {

		try {
			userName = userNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String userName;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public void setUserUuid(
		UnsafeSupplier<String, Exception> userUuidUnsafeSupplier) {

		try {
			userUuid = userUuidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String userUuid;

	@Override
	public LicenseKey clone() throws CloneNotSupportedException {
		return (LicenseKey)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKey)) {
			return false;
		}

		LicenseKey licenseKey = (LicenseKey)object;

		return Objects.equals(toString(), licenseKey.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return LicenseKeySerDes.toJSON(this);
	}

	public static enum LicenseEntryName {

		COMMERCE_SUBSCRIPTION_BACKUP("Commerce Subscription Backup"),
		COMMERCE_SUBSCRIPTION_BACKUP_VIRTUAL_CLUSTER(
			"Commerce Subscription Backup (Virtual Cluster)"),
		COMMERCE_SUBSCRIPTION_DEVELOPMENT("Commerce Subscription Development"),
		COMMERCE_SUBSCRIPTION_NON_PRODUCTION(
			"Commerce Subscription Non-Production"),
		COMMERCE_SUBSCRIPTION_NON_PRODUCTION_VIRTUAL_CLUSTER(
			"Commerce Subscription Non-Production (Virtual Cluster)"),
		COMMERCE_SUBSCRIPTION_PRODUCTION("Commerce Subscription Production"),
		COMMERCE_SUBSCRIPTION_PRODUCTION_VIRTUAL_CLUSTER(
			"Commerce Subscription Production (Virtual Cluster)"),
		COMMERCE_SUBSCRIPTION_UNLIMITED_ENTERPRISE_WIDE(
			"Commerce Subscription Unlimited Enterprise-Wide"),
		DXP_BACKUP("DXP Backup"),
		DXP_BACKUP_VIRTUAL_CLUSTER("DXP Backup (Virtual Cluster)"),
		DXP_DEVELOPMENT("DXP Development"),
		DXP_DEVELOPMENT_CLUSTER("DXP Development (Cluster)"),
		DXP_FLEX("DXP Flex"), DXP_LIMITED("DXP Limited"),
		DXP_NON_PRODUCTION("DXP Non-Production"),
		DXP_NON_PRODUCTION_VIRTUAL_CLUSTER(
			"DXP Non-Production (Virtual Cluster)"),
		DXP_OEM("DXP OEM"), DXP_PRODUCTION("DXP Production"),
		DXP_PRODUCTION_VIRTUAL_CLUSTER("DXP Production (Virtual Cluster)"),
		DXP_UNLIMITED_ENTERPRISE_WIDE("DXP Unlimited Enterprise-Wide"),
		PORTAL_BACKUP("Portal Backup"),
		PORTAL_BACKUP_ADDITIONAL_JVM("Portal Backup (Additional JVM)"),
		PORTAL_BACKUP_CLUSTER("Portal Backup (Cluster)"),
		PORTAL_DEVELOPER("Portal Developer"),
		PORTAL_DEVELOPER_CLUSTER("Portal Developer (Cluster)"),
		PORTAL_ENTERPRISE("Portal Enterprise"),
		PORTAL_LIMITED("Portal Limited"),
		PORTAL_NON_PRODUCTION("Portal Non-Production"),
		PORTAL_NON_PRODUCTION_ADDITIONAL_JVM(
			"Portal Non-Production (Additional JVM)"),
		PORTAL_NON_PRODUCTION_CLUSTER("Portal Non-Production (Cluster)"),
		PORTAL_NON_PRODUCTION_ELASTIC("Portal Non-Production (Elastic)"),
		PORTAL_NON_PRODUCTION_MONTHLY("Portal Non-Production (Monthly)"),
		PORTAL_OEM("Portal OEM"), PORTAL_PER_USER("Portal Per User"),
		PORTAL_PRODUCTION("Portal Production"),
		PORTAL_PRODUCTION_ADDITIONAL_JVM("Portal Production (Additional JVM)"),
		PORTAL_PRODUCTION_CLUSTER("Portal Production (Cluster)");

		public static LicenseEntryName create(String value) {
			for (LicenseEntryName licenseEntryName : values()) {
				if (Objects.equals(licenseEntryName.getValue(), value) ||
					Objects.equals(licenseEntryName.name(), value)) {

					return licenseEntryName;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private LicenseEntryName(String value) {
			_value = value;
		}

		private final String _value;

	}

	public static enum LicenseEntryType {

		CLUSTER("cluster"), DEVELOPER("developer"),
		DEVELOPER_CLUSTER("developer-cluster"), ENTERPRISE("enterprise"),
		LIMITED("limited"), OEM("oem"), PER_USER("per-user"),
		PRODUCTION("production"), VIRTUAL_CLUSTER("virtual-cluster");

		public static LicenseEntryType create(String value) {
			for (LicenseEntryType licenseEntryType : values()) {
				if (Objects.equals(licenseEntryType.getValue(), value) ||
					Objects.equals(licenseEntryType.name(), value)) {

					return licenseEntryType;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private LicenseEntryType(String value) {
			_value = value;
		}

		private final String _value;

	}

	public static enum Sizing {

		SIZING_1("Sizing 1"), SIZING_2("Sizing 2"), SIZING_3("Sizing 3"),
		SIZING_4("Sizing 4");

		public static Sizing create(String value) {
			for (Sizing sizing : values()) {
				if (Objects.equals(sizing.getValue(), value) ||
					Objects.equals(sizing.name(), value)) {

					return sizing;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Sizing(String value) {
			_value = value;
		}

		private final String _value;

	}

}