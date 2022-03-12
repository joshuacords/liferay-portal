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

package com.liferay.osb.provisioning.rest.client.dto.v1_0;

import com.liferay.osb.provisioning.rest.client.function.UnsafeSupplier;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.TypeSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class Type implements Cloneable, Serializable {

	public static Type toDTO(String json) {
		return TypeSerDes.toDTO(json);
	}

	public String getLicenseEntryDisplayName() {
		return licenseEntryDisplayName;
	}

	public void setLicenseEntryDisplayName(String licenseEntryDisplayName) {
		this.licenseEntryDisplayName = licenseEntryDisplayName;
	}

	public void setLicenseEntryDisplayName(
		UnsafeSupplier<String, Exception>
			licenseEntryDisplayNameUnsafeSupplier) {

		try {
			licenseEntryDisplayName =
				licenseEntryDisplayNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String licenseEntryDisplayName;

	public String getLicenseEntryName() {
		return licenseEntryName;
	}

	public void setLicenseEntryName(String licenseEntryName) {
		this.licenseEntryName = licenseEntryName;
	}

	public void setLicenseEntryName(
		UnsafeSupplier<String, Exception> licenseEntryNameUnsafeSupplier) {

		try {
			licenseEntryName = licenseEntryNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String licenseEntryName;

	public String getLicenseEntryType() {
		return licenseEntryType;
	}

	public void setLicenseEntryType(String licenseEntryType) {
		this.licenseEntryType = licenseEntryType;
	}

	public void setLicenseEntryType(
		UnsafeSupplier<String, Exception> licenseEntryTypeUnsafeSupplier) {

		try {
			licenseEntryType = licenseEntryTypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String licenseEntryType;

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

	public String getRequiredDetails() {
		return requiredDetails;
	}

	public void setRequiredDetails(String requiredDetails) {
		this.requiredDetails = requiredDetails;
	}

	public void setRequiredDetails(
		UnsafeSupplier<String, Exception> requiredDetailsUnsafeSupplier) {

		try {
			requiredDetails = requiredDetailsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String requiredDetails;

	@Override
	public Type clone() throws CloneNotSupportedException {
		return (Type)super.clone();
	}

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
		return TypeSerDes.toJSON(this);
	}

}