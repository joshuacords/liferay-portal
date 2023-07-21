/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.dto.v1_0;

import com.liferay.osb.provisioning.rest.client.function.UnsafeSupplier;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.SubscriptionTermSerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class SubscriptionTerm implements Cloneable, Serializable {

	public static SubscriptionTerm toDTO(String json) {
		return SubscriptionTermSerDes.toDTO(json);
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

	public Integer getInstanceSize() {
		return instanceSize;
	}

	public void setInstanceSize(Integer instanceSize) {
		this.instanceSize = instanceSize;
	}

	public void setInstanceSize(
		UnsafeSupplier<Integer, Exception> instanceSizeUnsafeSupplier) {

		try {
			instanceSize = instanceSizeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer instanceSize;

	public Date getLicenseKeyEndDate() {
		return licenseKeyEndDate;
	}

	public void setLicenseKeyEndDate(Date licenseKeyEndDate) {
		this.licenseKeyEndDate = licenseKeyEndDate;
	}

	public void setLicenseKeyEndDate(
		UnsafeSupplier<Date, Exception> licenseKeyEndDateUnsafeSupplier) {

		try {
			licenseKeyEndDate = licenseKeyEndDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date licenseKeyEndDate;

	public Boolean getPerpetual() {
		return perpetual;
	}

	public void setPerpetual(Boolean perpetual) {
		this.perpetual = perpetual;
	}

	public void setPerpetual(
		UnsafeSupplier<Boolean, Exception> perpetualUnsafeSupplier) {

		try {
			perpetual = perpetualUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean perpetual;

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

	public String getProductPurchaseKey() {
		return productPurchaseKey;
	}

	public void setProductPurchaseKey(String productPurchaseKey) {
		this.productPurchaseKey = productPurchaseKey;
	}

	public void setProductPurchaseKey(
		UnsafeSupplier<String, Exception> productPurchaseKeyUnsafeSupplier) {

		try {
			productPurchaseKey = productPurchaseKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productPurchaseKey;

	public Integer getProvisionedCount() {
		return provisionedCount;
	}

	public void setProvisionedCount(Integer provisionedCount) {
		this.provisionedCount = provisionedCount;
	}

	public void setProvisionedCount(
		UnsafeSupplier<Integer, Exception> provisionedCountUnsafeSupplier) {

		try {
			provisionedCount = provisionedCountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer provisionedCount;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setQuantity(
		UnsafeSupplier<Integer, Exception> quantityUnsafeSupplier) {

		try {
			quantity = quantityUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer quantity;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStartDate(
		UnsafeSupplier<Date, Exception> startDateUnsafeSupplier) {

		try {
			startDate = startDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date startDate;

	@Override
	public SubscriptionTerm clone() throws CloneNotSupportedException {
		return (SubscriptionTerm)super.clone();
	}

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
		return SubscriptionTermSerDes.toJSON(this);
	}

}