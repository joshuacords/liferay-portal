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
public class LVEntryLocalizationSoap implements Serializable {

	public static LVEntryLocalizationSoap toSoapModel(
		LVEntryLocalization model) {

		LVEntryLocalizationSoap soapModel = new LVEntryLocalizationSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setHeadId(model.getHeadId());
		soapModel.setLvEntryLocalizationId(model.getLvEntryLocalizationId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setLvEntryId(model.getLvEntryId());
		soapModel.setLanguageId(model.getLanguageId());
		soapModel.setTitle(model.getTitle());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static LVEntryLocalizationSoap[] toSoapModels(
		LVEntryLocalization[] models) {

		LVEntryLocalizationSoap[] soapModels =
			new LVEntryLocalizationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LVEntryLocalizationSoap[][] toSoapModels(
		LVEntryLocalization[][] models) {

		LVEntryLocalizationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LVEntryLocalizationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LVEntryLocalizationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LVEntryLocalizationSoap[] toSoapModels(
		List<LVEntryLocalization> models) {

		List<LVEntryLocalizationSoap> soapModels =
			new ArrayList<LVEntryLocalizationSoap>(models.size());

		for (LVEntryLocalization model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new LVEntryLocalizationSoap[soapModels.size()]);
	}

	public LVEntryLocalizationSoap() {
	}

	public long getPrimaryKey() {
		return _lvEntryLocalizationId;
	}

	public void setPrimaryKey(long pk) {
		setLvEntryLocalizationId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getHeadId() {
		return _headId;
	}

	public void setHeadId(long headId) {
		_headId = headId;
	}

	public long getLvEntryLocalizationId() {
		return _lvEntryLocalizationId;
	}

	public void setLvEntryLocalizationId(long lvEntryLocalizationId) {
		_lvEntryLocalizationId = lvEntryLocalizationId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getLvEntryId() {
		return _lvEntryId;
	}

	public void setLvEntryId(long lvEntryId) {
		_lvEntryId = lvEntryId;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private long _mvccVersion;
	private long _headId;
	private long _lvEntryLocalizationId;
	private long _companyId;
	private long _lvEntryId;
	private String _languageId;
	private String _title;
	private String _content;

}