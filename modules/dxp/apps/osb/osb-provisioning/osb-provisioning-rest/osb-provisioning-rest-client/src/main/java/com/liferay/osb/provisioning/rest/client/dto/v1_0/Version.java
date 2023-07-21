/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.dto.v1_0;

import com.liferay.osb.provisioning.rest.client.function.UnsafeSupplier;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.VersionSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class Version implements Cloneable, Serializable {

	public static Version toDTO(String json) {
		return VersionSerDes.toDTO(json);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLabel(
		UnsafeSupplier<String, Exception> labelUnsafeSupplier) {

		try {
			label = labelUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String label;

	public Type[] getTypes() {
		return types;
	}

	public void setTypes(Type[] types) {
		this.types = types;
	}

	public void setTypes(
		UnsafeSupplier<Type[], Exception> typesUnsafeSupplier) {

		try {
			types = typesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Type[] types;

	@Override
	public Version clone() throws CloneNotSupportedException {
		return (Version)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Version)) {
			return false;
		}

		Version version = (Version)object;

		return Objects.equals(toString(), version.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return VersionSerDes.toJSON(this);
	}

}