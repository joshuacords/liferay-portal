/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.dto.v1_0;

import com.liferay.osb.provisioning.rest.client.function.UnsafeSupplier;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.ProductGroupSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class ProductGroup implements Cloneable, Serializable {

	public static ProductGroup toDTO(String json) {
		return ProductGroupSerDes.toDTO(json);
	}

	public Name getName() {
		return name;
	}

	public String getNameAsString() {
		if (name == null) {
			return null;
		}

		return name.toString();
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<Name, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Name name;

	@Override
	public ProductGroup clone() throws CloneNotSupportedException {
		return (ProductGroup)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductGroup)) {
			return false;
		}

		ProductGroup productGroup = (ProductGroup)object;

		return Objects.equals(toString(), productGroup.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ProductGroupSerDes.toJSON(this);
	}

	public static enum Name {

		COMMERCE("Commerce"), DXP("DXP"),
		ENTERPRISE_SEARCH("Enterprise Search"), PORTAL("Portal");

		public static Name create(String value) {
			for (Name name : values()) {
				if (Objects.equals(name.getValue(), value) ||
					Objects.equals(name.name(), value)) {

					return name;
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

		private Name(String value) {
			_value = value;
		}

		private final String _value;

	}

}