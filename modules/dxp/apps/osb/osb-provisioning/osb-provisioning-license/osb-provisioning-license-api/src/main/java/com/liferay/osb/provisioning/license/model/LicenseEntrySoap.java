/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.provisioning.license.service.http.LicenseEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseEntrySoap implements Serializable {

	public static LicenseEntrySoap toSoapModel(LicenseEntry model) {
		LicenseEntrySoap soapModel = new LicenseEntrySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setLicenseEntryId(model.getLicenseEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProductKey(model.getProductKey());
		soapModel.setName(model.getName());
		soapModel.setType(model.getType());
		soapModel.setVersionMin(model.getVersionMin());
		soapModel.setVersionMax(model.getVersionMax());

		return soapModel;
	}

	public static LicenseEntrySoap[] toSoapModels(LicenseEntry[] models) {
		LicenseEntrySoap[] soapModels = new LicenseEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LicenseEntrySoap[][] toSoapModels(LicenseEntry[][] models) {
		LicenseEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LicenseEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LicenseEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LicenseEntrySoap[] toSoapModels(List<LicenseEntry> models) {
		List<LicenseEntrySoap> soapModels = new ArrayList<LicenseEntrySoap>(
			models.size());

		for (LicenseEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LicenseEntrySoap[soapModels.size()]);
	}

	public LicenseEntrySoap() {
	}

	public long getPrimaryKey() {
		return _licenseEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLicenseEntryId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getLicenseEntryId() {
		return _licenseEntryId;
	}

	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntryId = licenseEntryId;
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

	public String getProductKey() {
		return _productKey;
	}

	public void setProductKey(String productKey) {
		_productKey = productKey;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getVersionMin() {
		return _versionMin;
	}

	public void setVersionMin(String versionMin) {
		_versionMin = versionMin;
	}

	public String getVersionMax() {
		return _versionMax;
	}

	public void setVersionMax(String versionMax) {
		_versionMax = versionMax;
	}

	private long _mvccVersion;
	private long _licenseEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _productKey;
	private String _name;
	private String _type;
	private String _versionMin;
	private String _versionMax;

}