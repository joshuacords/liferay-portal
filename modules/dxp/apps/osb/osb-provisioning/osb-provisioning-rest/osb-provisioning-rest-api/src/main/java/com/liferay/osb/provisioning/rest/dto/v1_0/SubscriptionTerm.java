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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
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
	description = "The subscription terms of the account available to generate the license key on.",
	value = "SubscriptionTerm"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "SubscriptionTerm")
public class SubscriptionTerm implements Serializable {

	public static SubscriptionTerm toDTO(String json) {
		return ObjectMapperUtil.readValue(SubscriptionTerm.class, json);
	}

	public static SubscriptionTerm unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(SubscriptionTerm.class, json);
	}

	@Schema(description = "The date the subscription term ends.")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JsonIgnore
	public void setEndDate(
		UnsafeSupplier<Date, Exception> endDateUnsafeSupplier) {

		try {
			endDate = endDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The date the subscription term ends.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date endDate;

	@Schema(description = "The size of the instance.")
	public Integer getInstanceSize() {
		return instanceSize;
	}

	public void setInstanceSize(Integer instanceSize) {
		this.instanceSize = instanceSize;
	}

	@JsonIgnore
	public void setInstanceSize(
		UnsafeSupplier<Integer, Exception> instanceSizeUnsafeSupplier) {

		try {
			instanceSize = instanceSizeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The size of the instance.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer instanceSize;

	@Schema(
		description = "The date a license key attached to this subscription term ends."
	)
	public Date getLicenseKeyEndDate() {
		return licenseKeyEndDate;
	}

	public void setLicenseKeyEndDate(Date licenseKeyEndDate) {
		this.licenseKeyEndDate = licenseKeyEndDate;
	}

	@JsonIgnore
	public void setLicenseKeyEndDate(
		UnsafeSupplier<Date, Exception> licenseKeyEndDateUnsafeSupplier) {

		try {
			licenseKeyEndDate = licenseKeyEndDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The date a license key attached to this subscription term ends."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date licenseKeyEndDate;

	@Schema(description = "If the subscription is perpetual.")
	public Boolean getPerpetual() {
		return perpetual;
	}

	public void setPerpetual(Boolean perpetual) {
		this.perpetual = perpetual;
	}

	@JsonIgnore
	public void setPerpetual(
		UnsafeSupplier<Boolean, Exception> perpetualUnsafeSupplier) {

		try {
			perpetual = perpetualUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "If the subscription is perpetual.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean perpetual;

	@Schema(description = "The key of the subscription's product.")
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

	@GraphQLField(description = "The key of the subscription's product.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String productKey;

	@Schema(description = "The key of the subscription's product purchase.")
	public String getProductPurchaseKey() {
		return productPurchaseKey;
	}

	public void setProductPurchaseKey(String productPurchaseKey) {
		this.productPurchaseKey = productPurchaseKey;
	}

	@JsonIgnore
	public void setProductPurchaseKey(
		UnsafeSupplier<String, Exception> productPurchaseKeyUnsafeSupplier) {

		try {
			productPurchaseKey = productPurchaseKeyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The key of the subscription's product purchase."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String productPurchaseKey;

	@Schema(description = "The amount of currently provisioned license keys.")
	public Integer getProvisionedCount() {
		return provisionedCount;
	}

	public void setProvisionedCount(Integer provisionedCount) {
		this.provisionedCount = provisionedCount;
	}

	@JsonIgnore
	public void setProvisionedCount(
		UnsafeSupplier<Integer, Exception> provisionedCountUnsafeSupplier) {

		try {
			provisionedCount = provisionedCountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The amount of currently provisioned license keys."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer provisionedCount;

	@Schema(
		description = "The quantity of the subscription's purchased product."
	)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@JsonIgnore
	public void setQuantity(
		UnsafeSupplier<Integer, Exception> quantityUnsafeSupplier) {

		try {
			quantity = quantityUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The quantity of the subscription's purchased product."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Integer quantity;

	@Schema(description = "The date the subscription term starts.")
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

	@GraphQLField(description = "The date the subscription term starts.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date startDate;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SubscriptionTerm)) {
			return false;
		}

		SubscriptionTerm subscriptionTerm = (SubscriptionTerm)object;

		return Objects.equals(toString(), subscriptionTerm.toString());
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

		if (endDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(endDate));

			sb.append("\"");
		}

		if (instanceSize != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"instanceSize\": ");

			sb.append(instanceSize);
		}

		if (licenseKeyEndDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseKeyEndDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(licenseKeyEndDate));

			sb.append("\"");
		}

		if (perpetual != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"perpetual\": ");

			sb.append(perpetual);
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

		if (productPurchaseKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productPurchaseKey\": ");

			sb.append("\"");

			sb.append(_escape(productPurchaseKey));

			sb.append("\"");
		}

		if (provisionedCount != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"provisionedCount\": ");

			sb.append(provisionedCount);
		}

		if (quantity != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"quantity\": ");

			sb.append(quantity);
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

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.provisioning.rest.dto.v1_0.SubscriptionTerm",
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