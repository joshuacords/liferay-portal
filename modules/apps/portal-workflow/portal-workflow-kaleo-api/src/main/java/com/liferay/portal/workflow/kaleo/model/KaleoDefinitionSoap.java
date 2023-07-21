/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.workflow.kaleo.service.http.KaleoDefinitionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoDefinitionSoap implements Serializable {

	public static KaleoDefinitionSoap toSoapModel(KaleoDefinition model) {
		KaleoDefinitionSoap soapModel = new KaleoDefinitionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setContent(model.getContent());
		soapModel.setVersion(model.getVersion());
		soapModel.setActive(model.isActive());

		return soapModel;
	}

	public static KaleoDefinitionSoap[] toSoapModels(KaleoDefinition[] models) {
		KaleoDefinitionSoap[] soapModels =
			new KaleoDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoDefinitionSoap[][] toSoapModels(
		KaleoDefinition[][] models) {

		KaleoDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new KaleoDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoDefinitionSoap[] toSoapModels(
		List<KaleoDefinition> models) {

		List<KaleoDefinitionSoap> soapModels =
			new ArrayList<KaleoDefinitionSoap>(models.size());

		for (KaleoDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoDefinitionSoap[soapModels.size()]);
	}

	public KaleoDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoDefinitionId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _mvccVersion;
	private long _kaleoDefinitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _title;
	private String _description;
	private String _content;
	private int _version;
	private boolean _active;

}