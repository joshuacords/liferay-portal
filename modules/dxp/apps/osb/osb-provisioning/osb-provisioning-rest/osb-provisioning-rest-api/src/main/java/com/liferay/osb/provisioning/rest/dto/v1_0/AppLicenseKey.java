/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Represents a DXP App license key.", value = "AppLicenseKey"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "AppLicenseKey")
public class AppLicenseKey implements Serializable {

	public static AppLicenseKey toDTO(String json) {
		return ObjectMapperUtil.readValue(AppLicenseKey.class, json);
	}

	public static AppLicenseKey unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(AppLicenseKey.class, json);
	}

	@Schema(description = "If the license key is active or not.")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@JsonIgnore
	public void setActive(
		UnsafeSupplier<Boolean, Exception> activeUnsafeSupplier) {

		try {
			active = activeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "If the license key is active or not.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean active;

	@Schema(
		description = "If the license key counts towards the customer's purchase."
	)
	public Boolean getComplimentary() {
		return complimentary;
	}

	public void setComplimentary(Boolean complimentary) {
		this.complimentary = complimentary;
	}

	@JsonIgnore
	public void setComplimentary(
		UnsafeSupplier<Boolean, Exception> complimentaryUnsafeSupplier) {

		try {
			complimentary = complimentaryUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "If the license key counts towards the customer's purchase."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean complimentary;

	@Schema(description = "The create date of the license key.")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The create date of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date createDate;

	@Schema(description = "The description of the license key.")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The description of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@Schema(description = "The date the license key expires.")
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonIgnore
	public void setExpirationDate(
		UnsafeSupplier<Date, Exception> expirationDateUnsafeSupplier) {

		try {
			expirationDate = expirationDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The date the license key expires.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date expirationDate;

	@Schema(description = "The host name of the license key.")
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@JsonIgnore
	public void setHostName(
		UnsafeSupplier<String, Exception> hostNameUnsafeSupplier) {

		try {
			hostName = hostNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The host name of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String hostName;

	@Schema(description = "The id of the license key.")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The id of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

	@Schema(description = "The IP addresses of the license key.")
	public String getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(String ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	@JsonIgnore
	public void setIpAddresses(
		UnsafeSupplier<String, Exception> ipAddressesUnsafeSupplier) {

		try {
			ipAddresses = ipAddressesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The IP addresses of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String ipAddresses;

	@Schema(description = "The hash key of the license key.")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@JsonIgnore
	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The hash key of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String key;

	@Schema(description = "The name of the license entry of the license key.")
	@Valid
	public LicenseType getLicenseType() {
		return licenseType;
	}

	@JsonIgnore
	public String getLicenseTypeAsString() {
		if (licenseType == null) {
			return null;
		}

		return licenseType.toString();
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	@JsonIgnore
	public void setLicenseType(
		UnsafeSupplier<LicenseType, Exception> licenseTypeUnsafeSupplier) {

		try {
			licenseType = licenseTypeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The name of the license entry of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected LicenseType licenseType;

	@Schema(description = "The MAC addresses of the license key.")
	public String getMacAddresses() {
		return macAddresses;
	}

	public void setMacAddresses(String macAddresses) {
		this.macAddresses = macAddresses;
	}

	@JsonIgnore
	public void setMacAddresses(
		UnsafeSupplier<String, Exception> macAddressesUnsafeSupplier) {

		try {
			macAddresses = macAddressesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The MAC addresses of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String macAddresses;

	@Schema(description = "The last date the license key was modified.")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@JsonIgnore
	public void setModifiedDate(
		UnsafeSupplier<Date, Exception> modifiedDateUnsafeSupplier) {

		try {
			modifiedDate = modifiedDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The last date the license key was modified.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date modifiedDate;

	@Schema(
		description = "The name of the user who last modified the license key."
	)
	public String getModifiedUserName() {
		return modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	@JsonIgnore
	public void setModifiedUserName(
		UnsafeSupplier<String, Exception> modifiedUserNameUnsafeSupplier) {

		try {
			modifiedUserName = modifiedUserNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The name of the user who last modified the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String modifiedUserName;

	@Schema(
		description = "The uuid of the user who last modified the license key."
	)
	public String getModifiedUserUuid() {
		return modifiedUserUuid;
	}

	public void setModifiedUserUuid(String modifiedUserUuid) {
		this.modifiedUserUuid = modifiedUserUuid;
	}

	@JsonIgnore
	public void setModifiedUserUuid(
		UnsafeSupplier<String, Exception> modifiedUserUuidUnsafeSupplier) {

		try {
			modifiedUserUuid = modifiedUserUuidUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The uuid of the user who last modified the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String modifiedUserUuid;

	@Schema(description = "The order Id license key belongs to.")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@JsonIgnore
	public void setOrderId(
		UnsafeSupplier<String, Exception> orderIdUnsafeSupplier) {

		try {
			orderId = orderIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The order Id license key belongs to.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String orderId;

	@Schema(description = "The owner of the license key.")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@JsonIgnore
	public void setOwner(
		UnsafeSupplier<String, Exception> ownerUnsafeSupplier) {

		try {
			owner = ownerUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The owner of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String owner;

	@Schema(description = "The id of the product of the license key.")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@JsonIgnore
	public void setProductId(
		UnsafeSupplier<String, Exception> productIdUnsafeSupplier) {

		try {
			productId = productIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The id of the product of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productId;

	@Schema(description = "The name of the product of the license key.")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@JsonIgnore
	public void setProductName(
		UnsafeSupplier<String, Exception> productNameUnsafeSupplier) {

		try {
			productName = productNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The name of the product of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productName;

	@Schema(description = "The version of the product of the license key.")
	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	@JsonIgnore
	public void setProductVersion(
		UnsafeSupplier<String, Exception> productVersionUnsafeSupplier) {

		try {
			productVersion = productVersionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The version of the product of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productVersion;

	@Schema(description = "The date the license key can start being used.")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonIgnore
	public void setStartDate(
		UnsafeSupplier<Date, Exception> startDateUnsafeSupplier) {

		try {
			startDate = startDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The date the license key can start being used."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date startDate;

	@Schema(description = "The name of the user who created the license key.")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public void setUserName(
		UnsafeSupplier<String, Exception> userNameUnsafeSupplier) {

		try {
			userName = userNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The name of the user who created the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String userName;

	@Schema(description = "The uuid of the user who created the license key.")
	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	@JsonIgnore
	public void setUserUuid(
		UnsafeSupplier<String, Exception> userUuidUnsafeSupplier) {

		try {
			userUuid = userUuidUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The uuid of the user who created the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String userUuid;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AppLicenseKey)) {
			return false;
		}

		AppLicenseKey appLicenseKey = (AppLicenseKey)object;

		return Objects.equals(toString(), appLicenseKey.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (active != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(active);
		}

		if (complimentary != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"complimentary\": ");

			sb.append(complimentary);
		}

		if (createDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(createDate));

			sb.append("\"");
		}

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
		}

		if (expirationDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"expirationDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(expirationDate));

			sb.append("\"");
		}

		if (hostName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hostName\": ");

			sb.append("\"");

			sb.append(_escape(hostName));

			sb.append("\"");
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (ipAddresses != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ipAddresses\": ");

			sb.append("\"");

			sb.append(_escape(ipAddresses));

			sb.append("\"");
		}

		if (key != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(key));

			sb.append("\"");
		}

		if (licenseType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseType\": ");

			sb.append("\"");

			sb.append(licenseType);

			sb.append("\"");
		}

		if (macAddresses != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"macAddresses\": ");

			sb.append("\"");

			sb.append(_escape(macAddresses));

			sb.append("\"");
		}

		if (modifiedDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(modifiedDate));

			sb.append("\"");
		}

		if (modifiedUserName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserName\": ");

			sb.append("\"");

			sb.append(_escape(modifiedUserName));

			sb.append("\"");
		}

		if (modifiedUserUuid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserUuid\": ");

			sb.append("\"");

			sb.append(_escape(modifiedUserUuid));

			sb.append("\"");
		}

		if (orderId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderId\": ");

			sb.append("\"");

			sb.append(_escape(orderId));

			sb.append("\"");
		}

		if (owner != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"owner\": ");

			sb.append("\"");

			sb.append(_escape(owner));

			sb.append("\"");
		}

		if (productId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append("\"");

			sb.append(_escape(productId));

			sb.append("\"");
		}

		if (productName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productName\": ");

			sb.append("\"");

			sb.append(_escape(productName));

			sb.append("\"");
		}

		if (productVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productVersion\": ");

			sb.append("\"");

			sb.append(_escape(productVersion));

			sb.append("\"");
		}

		if (startDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(startDate));

			sb.append("\"");
		}

		if (userName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userName\": ");

			sb.append("\"");

			sb.append(_escape(userName));

			sb.append("\"");
		}

		if (userUuid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userUuid\": ");

			sb.append("\"");

			sb.append(_escape(userUuid));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.provisioning.rest.dto.v1_0.AppLicenseKey",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("LicenseType")
	public static enum LicenseType {

		DEVELOPER("developer"), ENTERPRISE("enterprise"), OEM("oem"),
		PRODUCTION("production"), VIRTUAL_CLUSTER("virtual-cluster");

		@JsonCreator
		public static LicenseType create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (LicenseType licenseType : values()) {
				if (Objects.equals(licenseType.getValue(), value)) {
					return licenseType;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private LicenseType(String value) {
			_value = value;
		}

		private final String _value;

	}

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}