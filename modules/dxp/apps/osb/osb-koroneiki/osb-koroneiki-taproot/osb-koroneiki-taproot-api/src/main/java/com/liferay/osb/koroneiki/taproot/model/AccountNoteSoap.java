/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.taproot.service.http.AccountNoteServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountNoteSoap implements Serializable {

	public static AccountNoteSoap toSoapModel(AccountNote model) {
		AccountNoteSoap soapModel = new AccountNoteSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setAccountNoteId(model.getAccountNoteId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setCreatorUID(model.getCreatorUID());
		soapModel.setCreatorName(model.getCreatorName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setModifierUID(model.getModifierUID());
		soapModel.setModifierName(model.getModifierName());
		soapModel.setAccountNoteKey(model.getAccountNoteKey());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setType(model.getType());
		soapModel.setPriority(model.getPriority());
		soapModel.setContent(model.getContent());
		soapModel.setFormat(model.getFormat());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static AccountNoteSoap[] toSoapModels(AccountNote[] models) {
		AccountNoteSoap[] soapModels = new AccountNoteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountNoteSoap[][] toSoapModels(AccountNote[][] models) {
		AccountNoteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountNoteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountNoteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountNoteSoap[] toSoapModels(List<AccountNote> models) {
		List<AccountNoteSoap> soapModels = new ArrayList<AccountNoteSoap>(
			models.size());

		for (AccountNote model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountNoteSoap[soapModels.size()]);
	}

	public AccountNoteSoap() {
	}

	public long getPrimaryKey() {
		return _accountNoteId;
	}

	public void setPrimaryKey(long pk) {
		setAccountNoteId(pk);
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

	public long getAccountNoteId() {
		return _accountNoteId;
	}

	public void setAccountNoteId(long accountNoteId) {
		_accountNoteId = accountNoteId;
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

	public String getCreatorUID() {
		return _creatorUID;
	}

	public void setCreatorUID(String creatorUID) {
		_creatorUID = creatorUID;
	}

	public String getCreatorName() {
		return _creatorName;
	}

	public void setCreatorName(String creatorName) {
		_creatorName = creatorName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getModifierUID() {
		return _modifierUID;
	}

	public void setModifierUID(String modifierUID) {
		_modifierUID = modifierUID;
	}

	public String getModifierName() {
		return _modifierName;
	}

	public void setModifierName(String modifierName) {
		_modifierName = modifierName;
	}

	public String getAccountNoteKey() {
		return _accountNoteKey;
	}

	public void setAccountNoteKey(String accountNoteKey) {
		_accountNoteKey = accountNoteKey;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getFormat() {
		return _format;
	}

	public void setFormat(String format) {
		_format = format;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _accountNoteId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private String _creatorUID;
	private String _creatorName;
	private Date _modifiedDate;
	private String _modifierUID;
	private String _modifierName;
	private String _accountNoteKey;
	private long _accountId;
	private String _type;
	private int _priority;
	private String _content;
	private String _format;
	private String _status;

}