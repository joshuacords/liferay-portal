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
public class LVEntryLocalizationVersionSoap implements Serializable {

	public static LVEntryLocalizationVersionSoap toSoapModel(
		LVEntryLocalizationVersion model) {

		LVEntryLocalizationVersionSoap soapModel =
			new LVEntryLocalizationVersionSoap();

		soapModel.setLvEntryLocalizationVersionId(
			model.getLvEntryLocalizationVersionId());
		soapModel.setVersion(model.getVersion());
		soapModel.setLvEntryLocalizationId(model.getLvEntryLocalizationId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setLvEntryId(model.getLvEntryId());
		soapModel.setLanguageId(model.getLanguageId());
		soapModel.setTitle(model.getTitle());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static LVEntryLocalizationVersionSoap[] toSoapModels(
		LVEntryLocalizationVersion[] models) {

		LVEntryLocalizationVersionSoap[] soapModels =
			new LVEntryLocalizationVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LVEntryLocalizationVersionSoap[][] toSoapModels(
		LVEntryLocalizationVersion[][] models) {

		LVEntryLocalizationVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LVEntryLocalizationVersionSoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new LVEntryLocalizationVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LVEntryLocalizationVersionSoap[] toSoapModels(
		List<LVEntryLocalizationVersion> models) {

		List<LVEntryLocalizationVersionSoap> soapModels =
			new ArrayList<LVEntryLocalizationVersionSoap>(models.size());

		for (LVEntryLocalizationVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new LVEntryLocalizationVersionSoap[soapModels.size()]);
	}

	public LVEntryLocalizationVersionSoap() {
	}

	public long getPrimaryKey() {
		return _lvEntryLocalizationVersionId;
	}

	public void setPrimaryKey(long pk) {
		setLvEntryLocalizationVersionId(pk);
	}

	public long getLvEntryLocalizationVersionId() {
		return _lvEntryLocalizationVersionId;
	}

	public void setLvEntryLocalizationVersionId(
		long lvEntryLocalizationVersionId) {

		_lvEntryLocalizationVersionId = lvEntryLocalizationVersionId;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
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

	private long _lvEntryLocalizationVersionId;
	private int _version;
	private long _lvEntryLocalizationId;
	private long _companyId;
	private long _lvEntryId;
	private String _languageId;
	private String _title;
	private String _content;

}