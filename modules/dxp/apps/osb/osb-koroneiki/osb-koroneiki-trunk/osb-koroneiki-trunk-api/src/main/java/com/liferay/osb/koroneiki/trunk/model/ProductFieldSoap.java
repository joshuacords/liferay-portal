/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.trunk.service.http.ProductFieldServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductFieldSoap implements Serializable {

	public static ProductFieldSoap toSoapModel(ProductField model) {
		ProductFieldSoap soapModel = new ProductFieldSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setProductFieldId(model.getProductFieldId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setName(model.getName());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static ProductFieldSoap[] toSoapModels(ProductField[] models) {
		ProductFieldSoap[] soapModels = new ProductFieldSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProductFieldSoap[][] toSoapModels(ProductField[][] models) {
		ProductFieldSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProductFieldSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProductFieldSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProductFieldSoap[] toSoapModels(List<ProductField> models) {
		List<ProductFieldSoap> soapModels = new ArrayList<ProductFieldSoap>(
			models.size());

		for (ProductField model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProductFieldSoap[soapModels.size()]);
	}

	public ProductFieldSoap() {
	}

	public long getPrimaryKey() {
		return _productFieldId;
	}

	public void setPrimaryKey(long pk) {
		setProductFieldId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getProductFieldId() {
		return _productFieldId;
	}

	public void setProductFieldId(long productFieldId) {
		_productFieldId = productFieldId;
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	private long _mvccVersion;
	private long _productFieldId;
	private long _companyId;
	private long _userId;
	private long _classNameId;
	private long _classPK;
	private String _name;
	private String _value;

}