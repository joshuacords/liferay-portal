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
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.LicenseKeyEndDateSerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class LicenseKeyEndDate implements Cloneable, Serializable {

	public static LicenseKeyEndDate toDTO(String json) {
		return LicenseKeyEndDateSerDes.toDTO(json);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEndDate(
		UnsafeSupplier<Date, Exception> endDateUnsafeSupplier) {

		try {
			endDate = endDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date endDate;

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

	@Override
	public LicenseKeyEndDate clone() throws CloneNotSupportedException {
		return (LicenseKeyEndDate)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKeyEndDate)) {
			return false;
		}

		LicenseKeyEndDate licenseKeyEndDate = (LicenseKeyEndDate)object;

		return Objects.equals(toString(), licenseKeyEndDate.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return LicenseKeyEndDateSerDes.toJSON(this);
	}

}