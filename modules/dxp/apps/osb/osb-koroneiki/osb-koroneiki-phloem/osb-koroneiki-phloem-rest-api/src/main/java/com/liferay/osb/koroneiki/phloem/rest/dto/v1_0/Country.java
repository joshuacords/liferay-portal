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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0;

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
 * @author Amos Fong
 * @generated
 */
@Generated("")
@GraphQLName(description = "Represents a country.", value = "Country")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Country")
public class Country implements Serializable {

	public static Country toDTO(String json) {
		return ObjectMapperUtil.readValue(Country.class, json);
	}

	public static Country unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Country.class, json);
	}

	@Schema(description = "The country's alpha-2 code (e.g., US).")
	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	@JsonIgnore
	public void setA2(UnsafeSupplier<String, Exception> a2UnsafeSupplier) {
		try {
			a2 = a2UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The country's alpha-2 code (e.g., US).")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String a2;

	@Schema(description = "The country's alpha-3 code (e.g., USA).")
	public String getA3() {
		return a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	@JsonIgnore
	public void setA3(UnsafeSupplier<String, Exception> a3UnsafeSupplier) {
		try {
			a3 = a3UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The country's alpha-3 code (e.g., USA).")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String a3;

	@Schema(
		description = "A flag that identifies whether this country is active."
	)
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

	@GraphQLField(
		description = "A flag that identifies whether this country is active."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean active;

	@Schema(
		description = "The country's regions. Optional field that can retrieved with nestedFields."
	)
	@Valid
	public CountryRegion[] getCountryRegions() {
		return countryRegions;
	}

	public void setCountryRegions(CountryRegion[] countryRegions) {
		this.countryRegions = countryRegions;
	}

	@JsonIgnore
	public void setCountryRegions(
		UnsafeSupplier<CountryRegion[], Exception>
			countryRegionsUnsafeSupplier) {

		try {
			countryRegions = countryRegionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The country's regions. Optional field that can retrieved with nestedFields."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected CountryRegion[] countryRegions;

	@Schema(description = "The country's IDD.")
	public String getIdd() {
		return idd;
	}

	public void setIdd(String idd) {
		this.idd = idd;
	}

	@JsonIgnore
	public void setIdd(UnsafeSupplier<String, Exception> iddUnsafeSupplier) {
		try {
			idd = iddUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The country's IDD.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String idd;

	@Schema(description = "The name of the country.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The name of the country.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String name;

	@Schema(
		description = "A flag that identifies whether this country requires zip code."
	)
	public Boolean getZipRequired() {
		return zipRequired;
	}

	public void setZipRequired(Boolean zipRequired) {
		this.zipRequired = zipRequired;
	}

	@JsonIgnore
	public void setZipRequired(
		UnsafeSupplier<Boolean, Exception> zipRequiredUnsafeSupplier) {

		try {
			zipRequired = zipRequiredUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "A flag that identifies whether this country requires zip code."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean zipRequired;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Country)) {
			return false;
		}

		Country country = (Country)object;

		return Objects.equals(toString(), country.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (a2 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"a2\": ");

			sb.append("\"");

			sb.append(_escape(a2));

			sb.append("\"");
		}

		if (a3 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"a3\": ");

			sb.append("\"");

			sb.append(_escape(a3));

			sb.append("\"");
		}

		if (active != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(active);
		}

		if (countryRegions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryRegions\": ");

			sb.append("[");

			for (int i = 0; i < countryRegions.length; i++) {
				sb.append(String.valueOf(countryRegions[i]));

				if ((i + 1) < countryRegions.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (idd != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"idd\": ");

			sb.append("\"");

			sb.append(_escape(idd));

			sb.append("\"");
		}

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		if (zipRequired != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"zipRequired\": ");

			sb.append(zipRequired);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Country",
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