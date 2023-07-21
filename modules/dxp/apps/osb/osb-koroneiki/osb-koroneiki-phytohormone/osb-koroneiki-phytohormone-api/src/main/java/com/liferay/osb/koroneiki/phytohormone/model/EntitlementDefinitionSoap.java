/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.phytohormone.service.http.EntitlementDefinitionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntitlementDefinitionSoap implements Serializable {

	public static EntitlementDefinitionSoap toSoapModel(
		EntitlementDefinition model) {

		EntitlementDefinitionSoap soapModel = new EntitlementDefinitionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setEntitlementDefinitionId(
			model.getEntitlementDefinitionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEntitlementDefinitionKey(
			model.getEntitlementDefinitionKey());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setDefinition(model.getDefinition());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static EntitlementDefinitionSoap[] toSoapModels(
		EntitlementDefinition[] models) {

		EntitlementDefinitionSoap[] soapModels =
			new EntitlementDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntitlementDefinitionSoap[][] toSoapModels(
		EntitlementDefinition[][] models) {

		EntitlementDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new EntitlementDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntitlementDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntitlementDefinitionSoap[] toSoapModels(
		List<EntitlementDefinition> models) {

		List<EntitlementDefinitionSoap> soapModels =
			new ArrayList<EntitlementDefinitionSoap>(models.size());

		for (EntitlementDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new EntitlementDefinitionSoap[soapModels.size()]);
	}

	public EntitlementDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _entitlementDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setEntitlementDefinitionId(pk);
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

	public long getEntitlementDefinitionId() {
		return _entitlementDefinitionId;
	}

	public void setEntitlementDefinitionId(long entitlementDefinitionId) {
		_entitlementDefinitionId = entitlementDefinitionId;
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

	public String getEntitlementDefinitionKey() {
		return _entitlementDefinitionKey;
	}

	public void setEntitlementDefinitionKey(String entitlementDefinitionKey) {
		_entitlementDefinitionKey = entitlementDefinitionKey;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
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

	public String getDefinition() {
		return _definition;
	}

	public void setDefinition(String definition) {
		_definition = definition;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _entitlementDefinitionId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _entitlementDefinitionKey;
	private long _classNameId;
	private String _name;
	private String _description;
	private String _definition;
	private int _status;

}