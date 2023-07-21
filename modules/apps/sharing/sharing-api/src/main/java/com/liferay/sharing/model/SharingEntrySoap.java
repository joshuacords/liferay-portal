/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sharing.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.sharing.service.http.SharingEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SharingEntrySoap implements Serializable {

	public static SharingEntrySoap toSoapModel(SharingEntry model) {
		SharingEntrySoap soapModel = new SharingEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSharingEntryId(model.getSharingEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setToUserId(model.getToUserId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setShareable(model.isShareable());
		soapModel.setActionIds(model.getActionIds());
		soapModel.setExpirationDate(model.getExpirationDate());

		return soapModel;
	}

	public static SharingEntrySoap[] toSoapModels(SharingEntry[] models) {
		SharingEntrySoap[] soapModels = new SharingEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SharingEntrySoap[][] toSoapModels(SharingEntry[][] models) {
		SharingEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SharingEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SharingEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SharingEntrySoap[] toSoapModels(List<SharingEntry> models) {
		List<SharingEntrySoap> soapModels = new ArrayList<SharingEntrySoap>(
			models.size());

		for (SharingEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SharingEntrySoap[soapModels.size()]);
	}

	public SharingEntrySoap() {
	}

	public long getPrimaryKey() {
		return _sharingEntryId;
	}

	public void setPrimaryKey(long pk) {
		setSharingEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSharingEntryId() {
		return _sharingEntryId;
	}

	public void setSharingEntryId(long sharingEntryId) {
		_sharingEntryId = sharingEntryId;
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

	public long getToUserId() {
		return _toUserId;
	}

	public void setToUserId(long toUserId) {
		_toUserId = toUserId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public boolean getShareable() {
		return _shareable;
	}

	public boolean isShareable() {
		return _shareable;
	}

	public void setShareable(boolean shareable) {
		_shareable = shareable;
	}

	public long getActionIds() {
		return _actionIds;
	}

	public void setActionIds(long actionIds) {
		_actionIds = actionIds;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	private String _uuid;
	private long _sharingEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _toUserId;
	private long _classNameId;
	private long _classPK;
	private boolean _shareable;
	private long _actionIds;
	private Date _expirationDate;

}