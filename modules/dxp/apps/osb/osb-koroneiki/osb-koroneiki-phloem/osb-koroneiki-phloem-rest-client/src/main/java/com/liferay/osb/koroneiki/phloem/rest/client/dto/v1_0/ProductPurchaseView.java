/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.function.UnsafeSupplier;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseViewSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class ProductPurchaseView implements Cloneable, Serializable {

	public static ProductPurchaseView toDTO(String json) {
		return ProductPurchaseViewSerDes.toDTO(json);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProduct(
		UnsafeSupplier<Product, Exception> productUnsafeSupplier) {

		try {
			product = productUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Product product;

	public ProductConsumption[] getProductConsumptions() {
		return productConsumptions;
	}

	public void setProductConsumptions(
		ProductConsumption[] productConsumptions) {

		this.productConsumptions = productConsumptions;
	}

	public void setProductConsumptions(
		UnsafeSupplier<ProductConsumption[], Exception>
			productConsumptionsUnsafeSupplier) {

		try {
			productConsumptions = productConsumptionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ProductConsumption[] productConsumptions;

	public ProductPurchase[] getProductPurchases() {
		return productPurchases;
	}

	public void setProductPurchases(ProductPurchase[] productPurchases) {
		this.productPurchases = productPurchases;
	}

	public void setProductPurchases(
		UnsafeSupplier<ProductPurchase[], Exception>
			productPurchasesUnsafeSupplier) {

		try {
			productPurchases = productPurchasesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ProductPurchase[] productPurchases;

	@Override
	public ProductPurchaseView clone() throws CloneNotSupportedException {
		return (ProductPurchaseView)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductPurchaseView)) {
			return false;
		}

		ProductPurchaseView productPurchaseView = (ProductPurchaseView)object;

		return Objects.equals(toString(), productPurchaseView.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ProductPurchaseViewSerDes.toJSON(this);
	}

}