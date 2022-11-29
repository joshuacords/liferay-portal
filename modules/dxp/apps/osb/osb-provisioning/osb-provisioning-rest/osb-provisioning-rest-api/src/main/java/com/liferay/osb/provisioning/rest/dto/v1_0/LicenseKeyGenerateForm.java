/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Represents the options available during license generation based on account and product.",
	value = "LicenseKeyGenerateForm"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "LicenseKeyGenerateForm")
public class LicenseKeyGenerateForm implements Serializable {

	public static LicenseKeyGenerateForm toDTO(String json) {
		return ObjectMapperUtil.readValue(LicenseKeyGenerateForm.class, json);
	}

	public static LicenseKeyGenerateForm unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(
			LicenseKeyGenerateForm.class, json);
	}

	@Schema(
		description = "If the account can generate complimentary license keys."
	)
	public Boolean getAllowComplimentary() {
		return allowComplimentary;
	}

	public void setAllowComplimentary(Boolean allowComplimentary) {
		this.allowComplimentary = allowComplimentary;
	}

	@JsonIgnore
	public void setAllowComplimentary(
		UnsafeSupplier<Boolean, Exception> allowComplimentaryUnsafeSupplier) {

		try {
			allowComplimentary = allowComplimentaryUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "If the account can generate complimentary license keys."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean allowComplimentary;

	@Schema(description = "If the account can generate permanent license keys.")
	public Boolean getAllowPermanentLicenses() {
		return allowPermanentLicenses;
	}

	public void setAllowPermanentLicenses(Boolean allowPermanentLicenses) {
		this.allowPermanentLicenses = allowPermanentLicenses;
	}

	@JsonIgnore
	public void setAllowPermanentLicenses(
		UnsafeSupplier<Boolean, Exception>
			allowPermanentLicensesUnsafeSupplier) {

		try {
			allowPermanentLicenses = allowPermanentLicensesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "If the account can generate permanent license keys."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean allowPermanentLicenses;

	@Schema(
		description = "The subscription terms of the account available to generate the license key on."
	)
	@Valid
	public SubscriptionTerm[] getSubscriptionTerms() {
		return subscriptionTerms;
	}

	public void setSubscriptionTerms(SubscriptionTerm[] subscriptionTerms) {
		this.subscriptionTerms = subscriptionTerms;
	}

	@JsonIgnore
	public void setSubscriptionTerms(
		UnsafeSupplier<SubscriptionTerm[], Exception>
			subscriptionTermsUnsafeSupplier) {

		try {
			subscriptionTerms = subscriptionTermsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The subscription terms of the account available to generate the license key on."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected SubscriptionTerm[] subscriptionTerms;

	@Schema(
		description = "The versions available to generate the license key for."
	)
	@Valid
	public Version[] getVersions() {
		return versions;
	}

	public void setVersions(Version[] versions) {
		this.versions = versions;
	}

	@JsonIgnore
	public void setVersions(
		UnsafeSupplier<Version[], Exception> versionsUnsafeSupplier) {

		try {
			versions = versionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The versions available to generate the license key for."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Version[] versions;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKeyGenerateForm)) {
			return false;
		}

		LicenseKeyGenerateForm licenseKeyGenerateForm =
			(LicenseKeyGenerateForm)object;

		return Objects.equals(toString(), licenseKeyGenerateForm.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (allowComplimentary != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"allowComplimentary\": ");

			sb.append(allowComplimentary);
		}

		if (allowPermanentLicenses != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"allowPermanentLicenses\": ");

			sb.append(allowPermanentLicenses);
		}

		if (subscriptionTerms != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subscriptionTerms\": ");

			sb.append("[");

			for (int i = 0; i < subscriptionTerms.length; i++) {
				sb.append(String.valueOf(subscriptionTerms[i]));

				if ((i + 1) < subscriptionTerms.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (versions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"versions\": ");

			sb.append("[");

			for (int i = 0; i < versions.length; i++) {
				sb.append(String.valueOf(versions[i]));

				if ((i + 1) < versions.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKeyGenerateForm",
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