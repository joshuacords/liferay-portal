/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "The license types available to generate the license key for.",
	value = "Type"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Type")
public class Type implements Serializable {

	public static Type toDTO(String json) {
		return ObjectMapperUtil.readValue(Type.class, json);
	}

	public static Type unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Type.class, json);
	}

	@Schema(
		description = "The display name of the license entry shown on the UI."
	)
	public String getLicenseEntryDisplayName() {
		return licenseEntryDisplayName;
	}

	public void setLicenseEntryDisplayName(String licenseEntryDisplayName) {
		this.licenseEntryDisplayName = licenseEntryDisplayName;
	}

	@JsonIgnore
	public void setLicenseEntryDisplayName(
		UnsafeSupplier<String, Exception>
			licenseEntryDisplayNameUnsafeSupplier) {

		try {
			licenseEntryDisplayName =
				licenseEntryDisplayNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The display name of the license entry shown on the UI."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String licenseEntryDisplayName;

	@Schema(description = "The name of the license entry of the license key.")
	public String getLicenseEntryName() {
		return licenseEntryName;
	}

	public void setLicenseEntryName(String licenseEntryName) {
		this.licenseEntryName = licenseEntryName;
	}

	@JsonIgnore
	public void setLicenseEntryName(
		UnsafeSupplier<String, Exception> licenseEntryNameUnsafeSupplier) {

		try {
			licenseEntryName = licenseEntryNameUnsafeSupplier.get();
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
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String licenseEntryName;

	@Schema(description = "The type of the license entry of the license key.")
	public String getLicenseEntryType() {
		return licenseEntryType;
	}

	public void setLicenseEntryType(String licenseEntryType) {
		this.licenseEntryType = licenseEntryType;
	}

	@JsonIgnore
	public void setLicenseEntryType(
		UnsafeSupplier<String, Exception> licenseEntryTypeUnsafeSupplier) {

		try {
			licenseEntryType = licenseEntryTypeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The type of the license entry of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String licenseEntryType;

	@Schema(description = "The key of the license entry product.")
	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	@JsonIgnore
	public void setProductKey(
		UnsafeSupplier<String, Exception> productKeyUnsafeSupplier) {

		try {
			productKey = productKeyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The key of the license entry product.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String productKey;

	@Schema(
		description = "The necessary details from the user to generate this type of license key. Possible values are \"None\", \"Server Id\", \"Virtual Cluster\"."
	)
	public String getRequiredDetails() {
		return requiredDetails;
	}

	public void setRequiredDetails(String requiredDetails) {
		this.requiredDetails = requiredDetails;
	}

	@JsonIgnore
	public void setRequiredDetails(
		UnsafeSupplier<String, Exception> requiredDetailsUnsafeSupplier) {

		try {
			requiredDetails = requiredDetailsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The necessary details from the user to generate this type of license key. Possible values are \"None\", \"Server Id\", \"Virtual Cluster\"."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String requiredDetails;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Type)) {
			return false;
		}

		Type type = (Type)object;

		return Objects.equals(toString(), type.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (licenseEntryDisplayName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryDisplayName\": ");

			sb.append("\"");

			sb.append(_escape(licenseEntryDisplayName));

			sb.append("\"");
		}

		if (licenseEntryName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryName\": ");

			sb.append("\"");

			sb.append(_escape(licenseEntryName));

			sb.append("\"");
		}

		if (licenseEntryType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryType\": ");

			sb.append("\"");

			sb.append(_escape(licenseEntryType));

			sb.append("\"");
		}

		if (productKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productKey\": ");

			sb.append("\"");

			sb.append(_escape(productKey));

			sb.append("\"");
		}

		if (requiredDetails != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"requiredDetails\": ");

			sb.append("\"");

			sb.append(_escape(requiredDetails));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.provisioning.rest.dto.v1_0.Type",
		name = "x-class-name"
	)
	public String xClassName;

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