/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.taproot.service.http.AccountFieldServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountFieldSoap implements Serializable {

	public static AccountFieldSoap toSoapModel(AccountField model) {
		AccountFieldSoap soapModel = new AccountFieldSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setAccountFieldId(model.getAccountFieldId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setName(model.getName());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static AccountFieldSoap[] toSoapModels(AccountField[] models) {
		AccountFieldSoap[] soapModels = new AccountFieldSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountFieldSoap[][] toSoapModels(AccountField[][] models) {
		AccountFieldSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountFieldSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountFieldSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountFieldSoap[] toSoapModels(List<AccountField> models) {
		List<AccountFieldSoap> soapModels = new ArrayList<AccountFieldSoap>(
			models.size());

		for (AccountField model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountFieldSoap[soapModels.size()]);
	}

	public AccountFieldSoap() {
	}

	public long getPrimaryKey() {
		return _accountFieldId;
	}

	public void setPrimaryKey(long pk) {
		setAccountFieldId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getAccountFieldId() {
		return _accountFieldId;
	}

	public void setAccountFieldId(long accountFieldId) {
		_accountFieldId = accountFieldId;
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

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
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
	private long _accountFieldId;
	private long _companyId;
	private long _userId;
	private long _accountId;
	private String _name;
	private String _value;

}