/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.fragment.service.http.FragmentCollectionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FragmentCollectionSoap implements Serializable {

	public static FragmentCollectionSoap toSoapModel(FragmentCollection model) {
		FragmentCollectionSoap soapModel = new FragmentCollectionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setFragmentCollectionId(model.getFragmentCollectionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFragmentCollectionKey(model.getFragmentCollectionKey());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static FragmentCollectionSoap[] toSoapModels(
		FragmentCollection[] models) {

		FragmentCollectionSoap[] soapModels =
			new FragmentCollectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FragmentCollectionSoap[][] toSoapModels(
		FragmentCollection[][] models) {

		FragmentCollectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new FragmentCollectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FragmentCollectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FragmentCollectionSoap[] toSoapModels(
		List<FragmentCollection> models) {

		List<FragmentCollectionSoap> soapModels =
			new ArrayList<FragmentCollectionSoap>(models.size());

		for (FragmentCollection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new FragmentCollectionSoap[soapModels.size()]);
	}

	public FragmentCollectionSoap() {
	}

	public long getPrimaryKey() {
		return _fragmentCollectionId;
	}

	public void setPrimaryKey(long pk) {
		setFragmentCollectionId(pk);
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

	public long getFragmentCollectionId() {
		return _fragmentCollectionId;
	}

	public void setFragmentCollectionId(long fragmentCollectionId) {
		_fragmentCollectionId = fragmentCollectionId;
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

	public String getFragmentCollectionKey() {
		return _fragmentCollectionKey;
	}

	public void setFragmentCollectionKey(String fragmentCollectionKey) {
		_fragmentCollectionKey = fragmentCollectionKey;
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

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _fragmentCollectionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _fragmentCollectionKey;
	private String _name;
	private String _description;
	private Date _lastPublishDate;

}