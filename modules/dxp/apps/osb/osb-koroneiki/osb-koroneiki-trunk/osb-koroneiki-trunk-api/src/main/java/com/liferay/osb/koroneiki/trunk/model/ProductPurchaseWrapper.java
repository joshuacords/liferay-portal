/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProductPurchase}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchase
 * @generated
 */
public class ProductPurchaseWrapper
	extends BaseModelWrapper<ProductPurchase>
	implements ModelWrapper<ProductPurchase>, ProductPurchase {

	public ProductPurchaseWrapper(ProductPurchase productPurchase) {
		super(productPurchase);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("productPurchaseId", getProductPurchaseId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productPurchaseKey", getProductPurchaseKey());
		attributes.put("accountId", getAccountId());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("originalEndDate", getOriginalEndDate());
		attributes.put("quantity", getQuantity());
		attributes.put("status", getStatus());

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

		Long productPurchaseId = (Long)attributes.get("productPurchaseId");

		if (productPurchaseId != null) {
			setProductPurchaseId(productPurchaseId);
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

		String productPurchaseKey = (String)attributes.get(
			"productPurchaseKey");

		if (productPurchaseKey != null) {
			setProductPurchaseKey(productPurchaseKey);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date originalEndDate = (Date)attributes.get("originalEndDate");

		if (originalEndDate != null) {
			setOriginalEndDate(originalEndDate);
		}

		Integer quantity = (Integer)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account getAccount()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getAccount();
	}

	/**
	 * Returns the account ID of this product purchase.
	 *
	 * @return the account ID of this product purchase
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	@Override
	public String getAccountKey()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getAccountKey();
	}

	/**
	 * Returns the company ID of this product purchase.
	 *
	 * @return the company ID of this product purchase
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this product purchase.
	 *
	 * @return the create date of this product purchase
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the end date of this product purchase.
	 *
	 * @return the end date of this product purchase
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks() {

		return model.getExternalLinks();
	}

	/**
	 * Returns the modified date of this product purchase.
	 *
	 * @return the modified date of this product purchase
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this product purchase.
	 *
	 * @return the mvcc version of this product purchase
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the original end date of this product purchase.
	 *
	 * @return the original end date of this product purchase
	 */
	@Override
	public Date getOriginalEndDate() {
		return model.getOriginalEndDate();
	}

	/**
	 * Returns the primary key of this product purchase.
	 *
	 * @return the primary key of this product purchase
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public ProductEntry getProductEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getProductEntry();
	}

	/**
	 * Returns the product entry ID of this product purchase.
	 *
	 * @return the product entry ID of this product purchase
	 */
	@Override
	public long getProductEntryId() {
		return model.getProductEntryId();
	}

	@Override
	public String getProductEntryKey()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getProductEntryKey();
	}

	@Override
	public java.util.List<ProductField> getProductFields() {
		return model.getProductFields();
	}

	@Override
	public Map<String, String> getProductFieldsMap() {
		return model.getProductFieldsMap();
	}

	/**
	 * Returns the product purchase ID of this product purchase.
	 *
	 * @return the product purchase ID of this product purchase
	 */
	@Override
	public long getProductPurchaseId() {
		return model.getProductPurchaseId();
	}

	/**
	 * Returns the product purchase key of this product purchase.
	 *
	 * @return the product purchase key of this product purchase
	 */
	@Override
	public String getProductPurchaseKey() {
		return model.getProductPurchaseKey();
	}

	/**
	 * Returns the quantity of this product purchase.
	 *
	 * @return the quantity of this product purchase
	 */
	@Override
	public int getQuantity() {
		return model.getQuantity();
	}

	/**
	 * Returns the start date of this product purchase.
	 *
	 * @return the start date of this product purchase
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the status of this product purchase.
	 *
	 * @return the status of this product purchase
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	@Override
	public String getStatusLabel() {
		return model.getStatusLabel();
	}

	/**
	 * Returns the user ID of this product purchase.
	 *
	 * @return the user ID of this product purchase
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this product purchase.
	 *
	 * @return the user uuid of this product purchase
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this product purchase.
	 *
	 * @return the uuid of this product purchase
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public boolean isPerpetual() {
		return model.isPerpetual();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account ID of this product purchase.
	 *
	 * @param accountId the account ID of this product purchase
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	@Override
	public void setAccountKey(String accountKey) {
		model.setAccountKey(accountKey);
	}

	/**
	 * Sets the company ID of this product purchase.
	 *
	 * @param companyId the company ID of this product purchase
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this product purchase.
	 *
	 * @param createDate the create date of this product purchase
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the end date of this product purchase.
	 *
	 * @param endDate the end date of this product purchase
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the modified date of this product purchase.
	 *
	 * @param modifiedDate the modified date of this product purchase
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this product purchase.
	 *
	 * @param mvccVersion the mvcc version of this product purchase
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the original end date of this product purchase.
	 *
	 * @param originalEndDate the original end date of this product purchase
	 */
	@Override
	public void setOriginalEndDate(Date originalEndDate) {
		model.setOriginalEndDate(originalEndDate);
	}

	/**
	 * Sets the primary key of this product purchase.
	 *
	 * @param primaryKey the primary key of this product purchase
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product entry ID of this product purchase.
	 *
	 * @param productEntryId the product entry ID of this product purchase
	 */
	@Override
	public void setProductEntryId(long productEntryId) {
		model.setProductEntryId(productEntryId);
	}

	/**
	 * Sets the product purchase ID of this product purchase.
	 *
	 * @param productPurchaseId the product purchase ID of this product purchase
	 */
	@Override
	public void setProductPurchaseId(long productPurchaseId) {
		model.setProductPurchaseId(productPurchaseId);
	}

	/**
	 * Sets the product purchase key of this product purchase.
	 *
	 * @param productPurchaseKey the product purchase key of this product purchase
	 */
	@Override
	public void setProductPurchaseKey(String productPurchaseKey) {
		model.setProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Sets the quantity of this product purchase.
	 *
	 * @param quantity the quantity of this product purchase
	 */
	@Override
	public void setQuantity(int quantity) {
		model.setQuantity(quantity);
	}

	/**
	 * Sets the start date of this product purchase.
	 *
	 * @param startDate the start date of this product purchase
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the status of this product purchase.
	 *
	 * @param status the status of this product purchase
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this product purchase.
	 *
	 * @param userId the user ID of this product purchase
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this product purchase.
	 *
	 * @param userUuid the user uuid of this product purchase
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this product purchase.
	 *
	 * @param uuid the uuid of this product purchase
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
	protected ProductPurchaseWrapper wrap(ProductPurchase productPurchase) {
		return new ProductPurchaseWrapper(productPurchase);
	}

}