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
public class LVEntryVersionSoap implements Serializable {

	public static LVEntryVersionSoap toSoapModel(LVEntryVersion model) {
		LVEntryVersionSoap soapModel = new LVEntryVersionSoap();

		soapModel.setLvEntryVersionId(model.getLvEntryVersionId());
		soapModel.setVersion(model.getVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setDefaultLanguageId(model.getDefaultLanguageId());
		soapModel.setLvEntryId(model.getLvEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUniqueGroupKey(model.getUniqueGroupKey());

		return soapModel;
	}

	public static LVEntryVersionSoap[] toSoapModels(LVEntryVersion[] models) {
		LVEntryVersionSoap[] soapModels = new LVEntryVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LVEntryVersionSoap[][] toSoapModels(
		LVEntryVersion[][] models) {

		LVEntryVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LVEntryVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LVEntryVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LVEntryVersionSoap[] toSoapModels(
		List<LVEntryVersion> models) {

		List<LVEntryVersionSoap> soapModels = new ArrayList<LVEntryVersionSoap>(
			models.size());

		for (LVEntryVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LVEntryVersionSoap[soapModels.size()]);
	}

	public LVEntryVersionSoap() {
	}

	public long getPrimaryKey() {
		return _lvEntryVersionId;
	}

	public void setPrimaryKey(long pk) {
		setLvEntryVersionId(pk);
	}

	public long getLvEntryVersionId() {
		return _lvEntryVersionId;
	}

	public void setLvEntryVersionId(long lvEntryVersionId) {
		_lvEntryVersionId = lvEntryVersionId;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
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

	private long _lvEntryVersionId;
	private int _version;
	private String _uuid;
	private String _defaultLanguageId;
	private long _lvEntryId;
	private long _groupId;
	private long _companyId;
	private String _uniqueGroupKey;

}