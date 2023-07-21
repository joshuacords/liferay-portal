/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.taproot.service.http.ContactTeamRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactTeamRoleSoap implements Serializable {

	public static ContactTeamRoleSoap toSoapModel(ContactTeamRole model) {
		ContactTeamRoleSoap soapModel = new ContactTeamRoleSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setContactId(model.getContactId());
		soapModel.setTeamId(model.getTeamId());
		soapModel.setContactRoleId(model.getContactRoleId());

		return soapModel;
	}

	public static ContactTeamRoleSoap[] toSoapModels(ContactTeamRole[] models) {
		ContactTeamRoleSoap[] soapModels =
			new ContactTeamRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContactTeamRoleSoap[][] toSoapModels(
		ContactTeamRole[][] models) {

		ContactTeamRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ContactTeamRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContactTeamRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContactTeamRoleSoap[] toSoapModels(
		List<ContactTeamRole> models) {

		List<ContactTeamRoleSoap> soapModels =
			new ArrayList<ContactTeamRoleSoap>(models.size());

		for (ContactTeamRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContactTeamRoleSoap[soapModels.size()]);
	}

	public ContactTeamRoleSoap() {
	}

	public ContactTeamRolePK getPrimaryKey() {
		return new ContactTeamRolePK(_contactId, _teamId, _contactRoleId);
	}

	public void setPrimaryKey(ContactTeamRolePK pk) {
		setContactId(pk.contactId);
		setTeamId(pk.teamId);
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

	public long getTeamId() {
		return _teamId;
	}

	public void setTeamId(long teamId) {
		_teamId = teamId;
	}

	public long getContactRoleId() {
		return _contactRoleId;
	}

	public void setContactRoleId(long contactRoleId) {
		_contactRoleId = contactRoleId;
	}

	private long _mvccVersion;
	private long _contactId;
	private long _teamId;
	private long _contactRoleId;

}