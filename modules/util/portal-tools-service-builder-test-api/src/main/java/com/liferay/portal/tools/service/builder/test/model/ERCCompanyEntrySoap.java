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
public class ERCCompanyEntrySoap implements Serializable {

	public static ERCCompanyEntrySoap toSoapModel(ERCCompanyEntry model) {
		ERCCompanyEntrySoap soapModel = new ERCCompanyEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setExternalReferenceCode(model.getExternalReferenceCode());
		soapModel.setErcCompanyEntryId(model.getErcCompanyEntryId());
		soapModel.setCompanyId(model.getCompanyId());

		return soapModel;
	}

	public static ERCCompanyEntrySoap[] toSoapModels(ERCCompanyEntry[] models) {
		ERCCompanyEntrySoap[] soapModels =
			new ERCCompanyEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ERCCompanyEntrySoap[][] toSoapModels(
		ERCCompanyEntry[][] models) {

		ERCCompanyEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ERCCompanyEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ERCCompanyEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ERCCompanyEntrySoap[] toSoapModels(
		List<ERCCompanyEntry> models) {

		List<ERCCompanyEntrySoap> soapModels =
			new ArrayList<ERCCompanyEntrySoap>(models.size());

		for (ERCCompanyEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ERCCompanyEntrySoap[soapModels.size()]);
	}

	public ERCCompanyEntrySoap() {
	}

	public long getPrimaryKey() {
		return _ercCompanyEntryId;
	}

	public void setPrimaryKey(long pk) {
		setErcCompanyEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public long getErcCompanyEntryId() {
		return _ercCompanyEntryId;
	}

	public void setErcCompanyEntryId(long ercCompanyEntryId) {
		_ercCompanyEntryId = ercCompanyEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	private String _uuid;
	private String _externalReferenceCode;
	private long _ercCompanyEntryId;
	private long _companyId;

}