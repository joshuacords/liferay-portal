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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.provisioning.license.service.http.CommonLicenseKeyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CommonLicenseKeySoap implements Serializable {

	public static CommonLicenseKeySoap toSoapModel(CommonLicenseKey model) {
		CommonLicenseKeySoap soapModel = new CommonLicenseKeySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setCommonLicenseKeyId(model.getCommonLicenseKeyId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setProductGroup(model.getProductGroup());
		soapModel.setProductEnvironment(model.getProductEnvironment());
		soapModel.setProductVersion(model.getProductVersion());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setFileName(model.getFileName());
		soapModel.setFileSize(model.getFileSize());

		return soapModel;
	}

	public static CommonLicenseKeySoap[] toSoapModels(
		CommonLicenseKey[] models) {

		CommonLicenseKeySoap[] soapModels =
			new CommonLicenseKeySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommonLicenseKeySoap[][] toSoapModels(
		CommonLicenseKey[][] models) {

		CommonLicenseKeySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CommonLicenseKeySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommonLicenseKeySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommonLicenseKeySoap[] toSoapModels(
		List<CommonLicenseKey> models) {

		List<CommonLicenseKeySoap> soapModels =
			new ArrayList<CommonLicenseKeySoap>(models.size());

		for (CommonLicenseKey model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommonLicenseKeySoap[soapModels.size()]);
	}

	public CommonLicenseKeySoap() {
	}

	public long getPrimaryKey() {
		return _commonLicenseKeyId;
	}

	public void setPrimaryKey(long pk) {
		setCommonLicenseKeyId(pk);
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

	public long getCommonLicenseKeyId() {
		return _commonLicenseKeyId;
	}

	public void setCommonLicenseKeyId(long commonLicenseKeyId) {
		_commonLicenseKeyId = commonLicenseKeyId;
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

	public String getProductGroup() {
		return _productGroup;
	}

	public void setProductGroup(String productGroup) {
		_productGroup = productGroup;
	}

	public String getProductEnvironment() {
		return _productEnvironment;
	}

	public void setProductEnvironment(String productEnvironment) {
		_productEnvironment = productEnvironment;
	}

	public String getProductVersion() {
		return _productVersion;
	}

	public void setProductVersion(String productVersion) {
		_productVersion = productVersion;
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

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public long getFileSize() {
		return _fileSize;
	}

	public void setFileSize(long fileSize) {
		_fileSize = fileSize;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _commonLicenseKeyId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private String _productGroup;
	private String _productEnvironment;
	private String _productVersion;
	private Date _startDate;
	private Date _endDate;
	private String _fileName;
	private long _fileSize;

}