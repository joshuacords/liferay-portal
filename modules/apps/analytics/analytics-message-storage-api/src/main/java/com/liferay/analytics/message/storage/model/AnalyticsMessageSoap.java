/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.analytics.message.storage.model;

import java.io.Serializable;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AnalyticsMessageSoap implements Serializable {

	public static AnalyticsMessageSoap toSoapModel(AnalyticsMessage model) {
		AnalyticsMessageSoap soapModel = new AnalyticsMessageSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setAnalyticsMessageId(model.getAnalyticsMessageId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setBody(model.getBody());

		return soapModel;
	}

	public static AnalyticsMessageSoap[] toSoapModels(
		AnalyticsMessage[] models) {

		AnalyticsMessageSoap[] soapModels =
			new AnalyticsMessageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AnalyticsMessageSoap[][] toSoapModels(
		AnalyticsMessage[][] models) {

		AnalyticsMessageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AnalyticsMessageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AnalyticsMessageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AnalyticsMessageSoap[] toSoapModels(
		List<AnalyticsMessage> models) {

		List<AnalyticsMessageSoap> soapModels =
			new ArrayList<AnalyticsMessageSoap>(models.size());

		for (AnalyticsMessage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AnalyticsMessageSoap[soapModels.size()]);
	}

	public AnalyticsMessageSoap() {
	}

	public long getPrimaryKey() {
		return _analyticsMessageId;
	}

	public void setPrimaryKey(long pk) {
		setAnalyticsMessageId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getAnalyticsMessageId() {
		return _analyticsMessageId;
	}

	public void setAnalyticsMessageId(long analyticsMessageId) {
		_analyticsMessageId = analyticsMessageId;
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

	public Blob getBody() {
		return _body;
	}

	public void setBody(Blob body) {
		_body = body;
	}

	private long _mvccVersion;
	private long _analyticsMessageId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Blob _body;

}