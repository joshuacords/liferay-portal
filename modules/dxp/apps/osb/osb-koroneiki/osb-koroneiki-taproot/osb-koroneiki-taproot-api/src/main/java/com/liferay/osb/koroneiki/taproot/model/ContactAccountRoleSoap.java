/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.taproot.service.http.ContactAccountRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactAccountRoleSoap implements Serializable {

	public static ContactAccountRoleSoap toSoapModel(ContactAccountRole model) {
		ContactAccountRoleSoap soapModel = new ContactAccountRoleSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setContactId(model.getContactId());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setContactRoleId(model.getContactRoleId());

		return soapModel;
	}

	public static ContactAccountRoleSoap[] toSoapModels(
		ContactAccountRole[] models) {

		ContactAccountRoleSoap[] soapModels =
			new ContactAccountRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContactAccountRoleSoap[][] toSoapModels(
		ContactAccountRole[][] models) {

		ContactAccountRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ContactAccountRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContactAccountRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContactAccountRoleSoap[] toSoapModels(
		List<ContactAccountRole> models) {

		List<ContactAccountRoleSoap> soapModels =
			new ArrayList<ContactAccountRoleSoap>(models.size());

		for (ContactAccountRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new ContactAccountRoleSoap[soapModels.size()]);
	}

	public ContactAccountRoleSoap() {
	}

	public ContactAccountRolePK getPrimaryKey() {
		return new ContactAccountRolePK(_contactId, _accountId, _contactRoleId);
	}

	public void setPrimaryKey(ContactAccountRolePK pk) {
		setContactId(pk.contactId);
		setAccountId(pk.accountId);
		setContactRoleId(pk.contactRoleId);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getContactId() {
		return _contactId;
	}

	public void setContactId(long contactId) {
		_contactId = contactId;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public long getContactRoleId() {
		return _contactRoleId;
	}

	public void setContactRoleId(long contactRoleId) {
		_contactRoleId = contactRoleId;
	}

	private long _mvccVersion;
	private long _contactId;
	private long _accountId;
	private long _contactRoleId;

}