/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.product.service.http.CPOptionValueServiceSoap}.
 *
 * @author Marco Leo
 * @generated
 */
public class CPOptionValueSoap implements Serializable {

	public static CPOptionValueSoap toSoapModel(CPOptionValue model) {
		CPOptionValueSoap soapModel = new CPOptionValueSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setExternalReferenceCode(model.getExternalReferenceCode());
		soapModel.setCPOptionValueId(model.getCPOptionValueId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCPOptionId(model.getCPOptionId());
		soapModel.setName(model.getName());
		soapModel.setPriority(model.getPriority());
		soapModel.setKey(model.getKey());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static CPOptionValueSoap[] toSoapModels(CPOptionValue[] models) {
		CPOptionValueSoap[] soapModels = new CPOptionValueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CPOptionValueSoap[][] toSoapModels(CPOptionValue[][] models) {
		CPOptionValueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CPOptionValueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CPOptionValueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CPOptionValueSoap[] toSoapModels(List<CPOptionValue> models) {
		List<CPOptionValueSoap> soapModels = new ArrayList<CPOptionValueSoap>(
			models.size());

		for (CPOptionValue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CPOptionValueSoap[soapModels.size()]);
	}

	public CPOptionValueSoap() {
	}

	public long getPrimaryKey() {
		return _CPOptionValueId;
	}

	public void setPrimaryKey(long pk) {
		setCPOptionValueId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public long getCPOptionValueId() {
		return _CPOptionValueId;
	}

	public void setCPOptionValueId(long CPOptionValueId) {
		_CPOptionValueId = CPOptionValueId;
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public long getCPOptionId() {
		return _CPOptionId;
	}

	public void setCPOptionId(long CPOptionId) {
		_CPOptionId = CPOptionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public double getPriority() {
		return _priority;
	}

	public void setPriority(double priority) {
		_priority = priority;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private String _uuid;
	private String _externalReferenceCode;
	private long _CPOptionValueId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _CPOptionId;
	private String _name;
	private double _priority;
	private String _key;
	private Date _lastPublishDate;

}