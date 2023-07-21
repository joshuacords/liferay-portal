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
public class LVEntrySoap implements Serializable {

	public static LVEntrySoap toSoapModel(LVEntry model) {
		LVEntrySoap soapModel = new LVEntrySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setHeadId(model.getHeadId());
		soapModel.setDefaultLanguageId(model.getDefaultLanguageId());
		soapModel.setLvEntryId(model.getLvEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUniqueGroupKey(model.getUniqueGroupKey());

		return soapModel;
	}

	public static LVEntrySoap[] toSoapModels(LVEntry[] models) {
		LVEntrySoap[] soapModels = new LVEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LVEntrySoap[][] toSoapModels(LVEntry[][] models) {
		LVEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LVEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LVEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LVEntrySoap[] toSoapModels(List<LVEntry> models) {
		List<LVEntrySoap> soapModels = new ArrayList<LVEntrySoap>(
			models.size());

		for (LVEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LVEntrySoap[soapModels.size()]);
	}

	public LVEntrySoap() {
	}

	public long getPrimaryKey() {
		return _lvEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLvEntryId(pk);
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

	public long getHeadId() {
		return _headId;
	}

	public void setHeadId(long headId) {
		_headId = headId;
	}

	public String getDefaultLanguageId() {
		return _defaultLanguageId;
	}

	public void setDefaultLanguageId(String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;
	}

	public long getLvEntryId() {
		return _lvEntryId;
	}

	public void setLvEntryId(long lvEntryId) {
		_lvEntryId = lvEntryId;
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

	public String getUniqueGroupKey() {
		return _uniqueGroupKey;
	}

	public void setUniqueGroupKey(String uniqueGroupKey) {
		_uniqueGroupKey = uniqueGroupKey;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _headId;
	private String _defaultLanguageId;
	private long _lvEntryId;
	private long _groupId;
	private long _companyId;
	private String _uniqueGroupKey;

}