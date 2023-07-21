/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserIdMapperSoap implements Serializable {

	public static UserIdMapperSoap toSoapModel(UserIdMapper model) {
		UserIdMapperSoap soapModel = new UserIdMapperSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUserIdMapperId(model.getUserIdMapperId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setType(model.getType());
		soapModel.setDescription(model.getDescription());
		soapModel.setExternalUserId(model.getExternalUserId());

		return soapModel;
	}

	public static UserIdMapperSoap[] toSoapModels(UserIdMapper[] models) {
		UserIdMapperSoap[] soapModels = new UserIdMapperSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserIdMapperSoap[][] toSoapModels(UserIdMapper[][] models) {
		UserIdMapperSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserIdMapperSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserIdMapperSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserIdMapperSoap[] toSoapModels(List<UserIdMapper> models) {
		List<UserIdMapperSoap> soapModels = new ArrayList<UserIdMapperSoap>(
			models.size());

		for (UserIdMapper model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserIdMapperSoap[soapModels.size()]);
	}

	public UserIdMapperSoap() {
	}

	public long getPrimaryKey() {
		return _userIdMapperId;
	}

	public void setPrimaryKey(long pk) {
		setUserIdMapperId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getUserIdMapperId() {
		return _userIdMapperId;
	}

	public void setUserIdMapperId(long userIdMapperId) {
		_userIdMapperId = userIdMapperId;
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

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getExternalUserId() {
		return _externalUserId;
	}

	public void setExternalUserId(String externalUserId) {
		_externalUserId = externalUserId;
	}

	private long _mvccVersion;
	private long _userIdMapperId;
	private long _companyId;
	private long _userId;
	private String _type;
	private String _description;
	private String _externalUserId;

}