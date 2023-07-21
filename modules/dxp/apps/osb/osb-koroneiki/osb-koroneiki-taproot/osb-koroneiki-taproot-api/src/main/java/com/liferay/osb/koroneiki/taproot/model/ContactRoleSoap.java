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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.taproot.service.http.ContactRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactRoleSoap implements Serializable {

	public static ContactRoleSoap toSoapModel(ContactRole model) {
		ContactRoleSoap soapModel = new ContactRoleSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setContactRoleId(model.getContactRoleId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setContactRoleKey(model.getContactRoleKey());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());
		soapModel.setSystem(model.isSystem());

		return soapModel;
	}

	public static ContactRoleSoap[] toSoapModels(ContactRole[] models) {
		ContactRoleSoap[] soapModels = new ContactRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContactRoleSoap[][] toSoapModels(ContactRole[][] models) {
		ContactRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContactRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContactRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContactRoleSoap[] toSoapModels(List<ContactRole> models) {
		List<ContactRoleSoap> soapModels = new ArrayList<ContactRoleSoap>(
			models.size());

		for (ContactRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContactRoleSoap[soapModels.size()]);
	}

	public ContactRoleSoap() {
	}

	public long getPrimaryKey() {
		return _contactRoleId;
	}

	public void setPrimaryKey(long pk) {
		setContactRoleId(pk);
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

	public long getContactRoleId() {
		return _contactRoleId;
	}

	public void setContactRoleId(long contactRoleId) {
		_contactRoleId = contactRoleId;
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

	public String getContactRoleKey() {
		return _contactRoleKey;
	}

	public void setContactRoleKey(String contactRoleKey) {
		_contactRoleKey = contactRoleKey;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public boolean getSystem() {
		return _system;
	}

	public boolean isSystem() {
		return _system;
	}

	public void setSystem(boolean system) {
		_system = system;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _contactRoleId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _contactRoleKey;
	private String _name;
	private String _description;
	private String _type;
	private boolean _system;

}