/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProductBundle}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundle
 * @generated
 */
public class ProductBundleWrapper
	extends BaseModelWrapper<ProductBundle>
	implements ModelWrapper<ProductBundle>, ProductBundle {

	public ProductBundleWrapper(ProductBundle productBundle) {
		super(productBundle);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("productBundleId", getProductBundleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long productBundleId = (Long)attributes.get("productBundleId");

		if (productBundleId != null) {
			setProductBundleId(productBundleId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	 * Returns the company ID of this product bundle.
	 *
	 * @return the company ID of this product bundle
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this product bundle.
	 *
	 * @return the create date of this product bundle
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the modified date of this product bundle.
	 *
	 * @return the modified date of this product bundle
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this product bundle.
	 *
	 * @return the mvcc version of this product bundle
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this product bundle.
	 *
	 * @return the name of this product bundle
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this product bundle.
	 *
	 * @return the primary key of this product bundle
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the product bundle ID of this product bundle.
	 *
	 * @return the product bundle ID of this product bundle
	 */
	@Override
	public long getProductBundleId() {
		return model.getProductBundleId();
	}

	/**
	 * Returns the user ID of this product bundle.
	 *
	 * @return the user ID of this product bundle
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this product bundle.
	 *
	 * @return the user uuid of this product bundle
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this product bundle.
	 *
	 * @return the uuid of this product bundle
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this product bundle.
	 *
	 * @param companyId the company ID of this product bundle
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this product bundle.
	 *
	 * @param createDate the create date of this product bundle
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this product bundle.
	 *
	 * @param modifiedDate the modified date of this product bundle
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this product bundle.
	 *
	 * @param mvccVersion the mvcc version of this product bundle
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this product bundle.
	 *
	 * @param name the name of this product bundle
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this product bundle.
	 *
	 * @param primaryKey the primary key of this product bundle
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product bundle ID of this product bundle.
	 *
	 * @param productBundleId the product bundle ID of this product bundle
	 */
	@Override
	public void setProductBundleId(long productBundleId) {
		model.setProductBundleId(productBundleId);
	}

	/**
	 * Sets the user ID of this product bundle.
	 *
	 * @param userId the user ID of this product bundle
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this product bundle.
	 *
	 * @param userUuid the user uuid of this product bundle
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this product bundle.
	 *
	 * @param uuid the uuid of this product bundle
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected ProductBundleWrapper wrap(ProductBundle productBundle) {
		return new ProductBundleWrapper(productBundle);
	}

}