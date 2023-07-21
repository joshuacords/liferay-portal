/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UADPartialEntrySoap implements Serializable {

	public static UADPartialEntrySoap toSoapModel(UADPartialEntry model) {
		UADPartialEntrySoap soapModel = new UADPartialEntrySoap();

		soapModel.setUadPartialEntryId(model.getUadPartialEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setMessage(model.getMessage());

		return soapModel;
	}

	public static UADPartialEntrySoap[] toSoapModels(UADPartialEntry[] models) {
		UADPartialEntrySoap[] soapModels =
			new UADPartialEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UADPartialEntrySoap[][] toSoapModels(
		UADPartialEntry[][] models) {

		UADPartialEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UADPartialEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new UADPartialEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UADPartialEntrySoap[] toSoapModels(
		List<UADPartialEntry> models) {

		List<UADPartialEntrySoap> soapModels =
			new ArrayList<UADPartialEntrySoap>(models.size());

		for (UADPartialEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UADPartialEntrySoap[soapModels.size()]);
	}

	public UADPartialEntrySoap() {
	}

	public long getPrimaryKey() {
		return _uadPartialEntryId;
	}

	public void setPrimaryKey(long pk) {
		setUadPartialEntryId(pk);
	}

	public long getUadPartialEntryId() {
		return _uadPartialEntryId;
	}

	public void setUadPartialEntryId(long uadPartialEntryId) {
		_uadPartialEntryId = uadPartialEntryId;
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

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	private long _uadPartialEntryId;
	private long _userId;
	private String _userName;
	private String _message;

}