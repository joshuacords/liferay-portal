/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.scion.service.http.AuthenticationTokenServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuthenticationTokenSoap implements Serializable {

	public static AuthenticationTokenSoap toSoapModel(
		AuthenticationToken model) {

		AuthenticationTokenSoap soapModel = new AuthenticationTokenSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setAuthenticationTokenId(model.getAuthenticationTokenId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setServiceProducerId(model.getServiceProducerId());
		soapModel.setName(model.getName());
		soapModel.setPrefix(model.getPrefix());
		soapModel.setDigest(model.getDigest());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static AuthenticationTokenSoap[] toSoapModels(
		AuthenticationToken[] models) {

		AuthenticationTokenSoap[] soapModels =
			new AuthenticationTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuthenticationTokenSoap[][] toSoapModels(
		AuthenticationToken[][] models) {

		AuthenticationTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AuthenticationTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuthenticationTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuthenticationTokenSoap[] toSoapModels(
		List<AuthenticationToken> models) {

		List<AuthenticationTokenSoap> soapModels =
			new ArrayList<AuthenticationTokenSoap>(models.size());

		for (AuthenticationToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new AuthenticationTokenSoap[soapModels.size()]);
	}

	public AuthenticationTokenSoap() {
	}

	public long getPrimaryKey() {
		return _authenticationTokenId;
	}

	public void setPrimaryKey(long pk) {
		setAuthenticationTokenId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getAuthenticationTokenId() {
		return _authenticationTokenId;
	}

	public void setAuthenticationTokenId(long authenticationTokenId) {
		_authenticationTokenId = authenticationTokenId;
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

	public long getServiceProducerId() {
		return _serviceProducerId;
	}

	public void setServiceProducerId(long serviceProducerId) {
		_serviceProducerId = serviceProducerId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPrefix() {
		return _prefix;
	}

	public void setPrefix(String prefix) {
		_prefix = prefix;
	}

	public String getDigest() {
		return _digest;
	}

	public void setDigest(String digest) {
		_digest = digest;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _mvccVersion;
	private long _authenticationTokenId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _serviceProducerId;
	private String _name;
	private String _prefix;
	private String _digest;
	private int _status;

}