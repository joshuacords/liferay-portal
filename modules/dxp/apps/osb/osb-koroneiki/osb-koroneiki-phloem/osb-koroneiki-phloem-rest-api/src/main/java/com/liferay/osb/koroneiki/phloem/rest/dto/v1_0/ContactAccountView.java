/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
@GraphQLName(
	description = "A contact's perspective of his/her relationship to an account.",
	value = "ContactAccountView"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ContactAccountView")
public class ContactAccountView implements Serializable {

	public static ContactAccountView toDTO(String json) {
		return ObjectMapperUtil.readValue(ContactAccountView.class, json);
	}

	public static ContactAccountView unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(ContactAccountView.class, json);
	}

	@Schema
	@Valid
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@JsonIgnore
	public void setAccount(
		UnsafeSupplier<Account, Exception> accountUnsafeSupplier) {

		try {
			account = accountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Account account;

	@Schema
	@Valid
	public ContactRole[] getCustomerContactRoles() {
		return customerContactRoles;
	}

	public void setCustomerContactRoles(ContactRole[] customerContactRoles) {
		this.customerContactRoles = customerContactRoles;
	}

	@JsonIgnore
	public void setCustomerContactRoles(
		UnsafeSupplier<ContactRole[], Exception>
			customerContactRolesUnsafeSupplier) {

		try {
			customerContactRoles = customerContactRolesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected ContactRole[] customerContactRoles;

	@Schema
	@Valid
	public ContactRole[] getWorkerContactRoles() {
		return workerContactRoles;
	}

	public void setWorkerContactRoles(ContactRole[] workerContactRoles) {
		this.workerContactRoles = workerContactRoles;
	}

	@JsonIgnore
	public void setWorkerContactRoles(
		UnsafeSupplier<ContactRole[], Exception>
			workerContactRolesUnsafeSupplier) {

		try {
			workerContactRoles = workerContactRolesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected ContactRole[] workerContactRoles;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactAccountView)) {
			return false;
		}

		ContactAccountView contactAccountView = (ContactAccountView)object;

		return Objects.equals(toString(), contactAccountView.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (account != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"account\": ");

			sb.append(String.valueOf(account));
		}

		if (customerContactRoles != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customerContactRoles\": ");

			sb.append("[");

			for (int i = 0; i < customerContactRoles.length; i++) {
				sb.append(String.valueOf(customerContactRoles[i]));

				if ((i + 1) < customerContactRoles.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (workerContactRoles != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workerContactRoles\": ");

			sb.append("[");

			for (int i = 0; i < workerContactRoles.length; i++) {
				sb.append(String.valueOf(workerContactRoles[i]));

				if ((i + 1) < workerContactRoles.length) {
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
		defaultValue = "com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountView",
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