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
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.LicenseKeyGenerateFormSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class LicenseKeyGenerateForm implements Cloneable, Serializable {

	public static LicenseKeyGenerateForm toDTO(String json) {
		return LicenseKeyGenerateFormSerDes.toDTO(json);
	}

	public Boolean getAllowComplimentary() {
		return allowComplimentary;
	}

	public void setAllowComplimentary(Boolean allowComplimentary) {
		this.allowComplimentary = allowComplimentary;
	}

	public void setAllowComplimentary(
		UnsafeSupplier<Boolean, Exception> allowComplimentaryUnsafeSupplier) {

		try {
			allowComplimentary = allowComplimentaryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean allowComplimentary;

	public Boolean getAllowPermanentLicenses() {
		return allowPermanentLicenses;
	}

	public void setAllowPermanentLicenses(Boolean allowPermanentLicenses) {
		this.allowPermanentLicenses = allowPermanentLicenses;
	}

	public void setAllowPermanentLicenses(
		UnsafeSupplier<Boolean, Exception>
			allowPermanentLicensesUnsafeSupplier) {

		try {
			allowPermanentLicenses = allowPermanentLicensesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean allowPermanentLicenses;

	public SubscriptionTerm[] getSubscriptionTerms() {
		return subscriptionTerms;
	}

	public void setSubscriptionTerms(SubscriptionTerm[] subscriptionTerms) {
		this.subscriptionTerms = subscriptionTerms;
	}

	public void setSubscriptionTerms(
		UnsafeSupplier<SubscriptionTerm[], Exception>
			subscriptionTermsUnsafeSupplier) {

		try {
			subscriptionTerms = subscriptionTermsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected SubscriptionTerm[] subscriptionTerms;

	public Version[] getVersions() {
		return versions;
	}

	public void setVersions(Version[] versions) {
		this.versions = versions;
	}

	public void setVersions(
		UnsafeSupplier<Version[], Exception> versionsUnsafeSupplier) {

		try {
			versions = versionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Version[] versions;

	@Override
	public LicenseKeyGenerateForm clone() throws CloneNotSupportedException {
		return (LicenseKeyGenerateForm)super.clone();
	}

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
		return LicenseKeyGenerateFormSerDes.toJSON(this);
	}

}