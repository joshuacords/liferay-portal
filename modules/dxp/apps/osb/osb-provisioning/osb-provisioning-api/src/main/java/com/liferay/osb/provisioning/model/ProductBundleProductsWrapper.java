/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProductBundleProducts}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProducts
 * @generated
 */
public class ProductBundleProductsWrapper
	extends BaseModelWrapper<ProductBundleProducts>
	implements ModelWrapper<ProductBundleProducts>, ProductBundleProducts {

	public ProductBundleProductsWrapper(
		ProductBundleProducts productBundleProducts) {

		super(productBundleProducts);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("productBundleId", getProductBundleId());
		attributes.put("productKey", getProductKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long productBundleId = (Long)attributes.get("productBundleId");

		if (productBundleId != null) {
			setProductBundleId(productBundleId);
		}

		String productKey = (String)attributes.get("productKey");

		if (productKey != null) {
			setProductKey(productKey);
		}
	}

	/**
	 * Returns the mvcc version of this product bundle products.
	 *
	 * @return the mvcc version of this product bundle products
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this product bundle products.
	 *
	 * @return the primary key of this product bundle products
	 */
	@Override
	public
		com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK
			getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the product bundle ID of this product bundle products.
	 *
	 * @return the product bundle ID of this product bundle products
	 */
	@Override
	public long getProductBundleId() {
		return model.getProductBundleId();
	}

	/**
	 * Returns the product key of this product bundle products.
	 *
	 * @return the product key of this product bundle products
	 */
	@Override
	public String getProductKey() {
		return model.getProductKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the mvcc version of this product bundle products.
	 *
	 * @param mvccVersion the mvcc version of this product bundle products
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this product bundle products.
	 *
	 * @param primaryKey the primary key of this product bundle products
	 */
	@Override
	public void setPrimaryKey(
		com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product bundle ID of this product bundle products.
	 *
	 * @param productBundleId the product bundle ID of this product bundle products
	 */
	@Override
	public void setProductBundleId(long productBundleId) {
		model.setProductBundleId(productBundleId);
	}

	/**
	 * Sets the product key of this product bundle products.
	 *
	 * @param productKey the product key of this product bundle products
	 */
	@Override
	public void setProductKey(String productKey) {
		model.setProductKey(productKey);
	}

	@Override
	protected ProductBundleProductsWrapper wrap(
		ProductBundleProducts productBundleProducts) {

		return new ProductBundleProductsWrapper(productBundleProducts);
	}

}