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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.trunk.service.http.ProductEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductEntrySoap implements Serializable {

	public static ProductEntrySoap toSoapModel(ProductEntry model) {
		ProductEntrySoap soapModel = new ProductEntrySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProductEntryKey(model.getProductEntryKey());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static ProductEntrySoap[] toSoapModels(ProductEntry[] models) {
		ProductEntrySoap[] soapModels = new ProductEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProductEntrySoap[][] toSoapModels(ProductEntry[][] models) {
		ProductEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProductEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProductEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProductEntrySoap[] toSoapModels(List<ProductEntry> models) {
		List<ProductEntrySoap> soapModels = new ArrayList<ProductEntrySoap>(
			models.size());

		for (ProductEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProductEntrySoap[soapModels.size()]);
	}

	public ProductEntrySoap() {
	}

	public long getPrimaryKey() {
		return _productEntryId;
	}

	public void setPrimaryKey(long pk) {
		setProductEntryId(pk);
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

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
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

	public String getProductEntryKey() {
		return _productEntryKey;
	}

	public void setProductEntryKey(String productEntryKey) {
		_productEntryKey = productEntryKey;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _productEntryId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _productEntryKey;
	private String _name;

}