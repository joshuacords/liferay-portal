/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

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
public class PasswordTrackerSoap implements Serializable {

	public static PasswordTrackerSoap toSoapModel(PasswordTracker model) {
		PasswordTrackerSoap soapModel = new PasswordTrackerSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setPasswordTrackerId(model.getPasswordTrackerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setPassword(model.getPassword());

		return soapModel;
	}

	public static PasswordTrackerSoap[] toSoapModels(PasswordTracker[] models) {
		PasswordTrackerSoap[] soapModels =
			new PasswordTrackerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PasswordTrackerSoap[][] toSoapModels(
		PasswordTracker[][] models) {

		PasswordTrackerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new PasswordTrackerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PasswordTrackerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PasswordTrackerSoap[] toSoapModels(
		List<PasswordTracker> models) {

		List<PasswordTrackerSoap> soapModels =
			new ArrayList<PasswordTrackerSoap>(models.size());

		for (PasswordTracker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PasswordTrackerSoap[soapModels.size()]);
	}

	public PasswordTrackerSoap() {
	}

	public long getPrimaryKey() {
		return _passwordTrackerId;
	}

	public void setPrimaryKey(long pk) {
		setPasswordTrackerId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getPasswordTrackerId() {
		return _passwordTrackerId;
	}

	public void setPasswordTrackerId(long passwordTrackerId) {
		_passwordTrackerId = passwordTrackerId;
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

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	private long _mvccVersion;
	private long _passwordTrackerId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private String _password;

}