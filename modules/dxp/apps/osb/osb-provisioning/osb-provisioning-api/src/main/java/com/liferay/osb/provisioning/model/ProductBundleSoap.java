/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductBundleSoap implements Serializable {

	public static ProductBundleSoap toSoapModel(ProductBundle model) {
		ProductBundleSoap soapModel = new ProductBundleSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setProductBundleId(model.getProductBundleId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static ProductBundleSoap[] toSoapModels(ProductBundle[] models) {
		ProductBundleSoap[] soapModels = new ProductBundleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProductBundleSoap[][] toSoapModels(ProductBundle[][] models) {
		ProductBundleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProductBundleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProductBundleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProductBundleSoap[] toSoapModels(List<ProductBundle> models) {
		List<ProductBundleSoap> soapModels = new ArrayList<ProductBundleSoap>(
			models.size());

		for (ProductBundle model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProductBundleSoap[soapModels.size()]);
	}

	public ProductBundleSoap() {
	}

	public long getPrimaryKey() {
		return _productBundleId;
	}

	public void setPrimaryKey(long pk) {
		setProductBundleId(pk);
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

	public long getProductBundleId() {
		return _productBundleId;
	}

	public void setProductBundleId(long productBundleId) {
		_productBundleId = productBundleId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _productBundleId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;

}