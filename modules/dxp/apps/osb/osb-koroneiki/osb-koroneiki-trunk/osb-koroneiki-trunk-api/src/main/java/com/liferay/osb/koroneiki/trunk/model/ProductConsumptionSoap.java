/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.trunk.service.http.ProductConsumptionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductConsumptionSoap implements Serializable {

	public static ProductConsumptionSoap toSoapModel(ProductConsumption model) {
		ProductConsumptionSoap soapModel = new ProductConsumptionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setProductConsumptionId(model.getProductConsumptionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProductConsumptionKey(model.getProductConsumptionKey());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setProductPurchaseId(model.getProductPurchaseId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());

		return soapModel;
	}

	public static ProductConsumptionSoap[] toSoapModels(
		ProductConsumption[] models) {

		ProductConsumptionSoap[] soapModels =
			new ProductConsumptionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProductConsumptionSoap[][] toSoapModels(
		ProductConsumption[][] models) {

		ProductConsumptionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ProductConsumptionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProductConsumptionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProductConsumptionSoap[] toSoapModels(
		List<ProductConsumption> models) {

		List<ProductConsumptionSoap> soapModels =
			new ArrayList<ProductConsumptionSoap>(models.size());

		for (ProductConsumption model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new ProductConsumptionSoap[soapModels.size()]);
	}

	public ProductConsumptionSoap() {
	}

	public long getPrimaryKey() {
		return _productConsumptionId;
	}

	public void setPrimaryKey(long pk) {
		setProductConsumptionId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getProductConsumptionId() {
		return _productConsumptionId;
	}

	public void setProductConsumptionId(long productConsumptionId) {
		_productConsumptionId = productConsumptionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getProductConsumptionKey() {
		return _productConsumptionKey;
	}

	public void setProductConsumptionKey(String productConsumptionKey) {
		_productConsumptionKey = productConsumptionKey;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	public long getProductPurchaseId() {
		return _productPurchaseId;
	}

	public void setProductPurchaseId(long productPurchaseId) {
		_productPurchaseId = productPurchaseId;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _productConsumptionId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _productConsumptionKey;
	private long _accountId;
	private long _productEntryId;
	private long _productPurchaseId;
	private Date _startDate;
	private Date _endDate;

}