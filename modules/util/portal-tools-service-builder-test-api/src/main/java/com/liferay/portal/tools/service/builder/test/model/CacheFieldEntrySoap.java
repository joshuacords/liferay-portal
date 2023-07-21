/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CacheFieldEntrySoap implements Serializable {

	public static CacheFieldEntrySoap toSoapModel(CacheFieldEntry model) {
		CacheFieldEntrySoap soapModel = new CacheFieldEntrySoap();

		soapModel.setCacheFieldEntryId(model.getCacheFieldEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static CacheFieldEntrySoap[] toSoapModels(CacheFieldEntry[] models) {
		CacheFieldEntrySoap[] soapModels =
			new CacheFieldEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CacheFieldEntrySoap[][] toSoapModels(
		CacheFieldEntry[][] models) {

		CacheFieldEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new CacheFieldEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CacheFieldEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CacheFieldEntrySoap[] toSoapModels(
		List<CacheFieldEntry> models) {

		List<CacheFieldEntrySoap> soapModels =
			new ArrayList<CacheFieldEntrySoap>(models.size());

		for (CacheFieldEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CacheFieldEntrySoap[soapModels.size()]);
	}

	public CacheFieldEntrySoap() {
	}

	public long getPrimaryKey() {
		return _cacheFieldEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCacheFieldEntryId(pk);
	}

	public long getCacheFieldEntryId() {
		return _cacheFieldEntryId;
	}

	public void setCacheFieldEntryId(long cacheFieldEntryId) {
		_cacheFieldEntryId = cacheFieldEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _cacheFieldEntryId;
	private long _groupId;
	private String _name;

}