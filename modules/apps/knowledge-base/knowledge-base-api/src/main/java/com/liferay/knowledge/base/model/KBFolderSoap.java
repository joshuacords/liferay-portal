/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.knowledge.base.service.http.KBFolderServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KBFolderSoap implements Serializable {

	public static KBFolderSoap toSoapModel(KBFolder model) {
		KBFolderSoap soapModel = new KBFolderSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setKbFolderId(model.getKbFolderId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentKBFolderId(model.getParentKBFolderId());
		soapModel.setName(model.getName());
		soapModel.setUrlTitle(model.getUrlTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static KBFolderSoap[] toSoapModels(KBFolder[] models) {
		KBFolderSoap[] soapModels = new KBFolderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KBFolderSoap[][] toSoapModels(KBFolder[][] models) {
		KBFolderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KBFolderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KBFolderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KBFolderSoap[] toSoapModels(List<KBFolder> models) {
		List<KBFolderSoap> soapModels = new ArrayList<KBFolderSoap>(
			models.size());

		for (KBFolder model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBFolderSoap[soapModels.size()]);
	}

	public KBFolderSoap() {
	}

	public long getPrimaryKey() {
		return _kbFolderId;
	}

	public void setPrimaryKey(long pk) {
		setKbFolderId(pk);
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

	public long getKbFolderId() {
		return _kbFolderId;
	}

	public void setKbFolderId(long kbFolderId) {
		_kbFolderId = kbFolderId;
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

	public long getParentKBFolderId() {
		return _parentKBFolderId;
	}

	public void setParentKBFolderId(long parentKBFolderId) {
		_parentKBFolderId = parentKBFolderId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		_urlTitle = urlTitle;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _kbFolderId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentKBFolderId;
	private String _name;
	private String _urlTitle;
	private String _description;
	private Date _lastPublishDate;

}