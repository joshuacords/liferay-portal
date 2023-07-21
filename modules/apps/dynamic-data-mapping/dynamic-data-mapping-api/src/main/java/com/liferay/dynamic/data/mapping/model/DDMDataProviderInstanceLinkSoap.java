/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DDMDataProviderInstanceLinkSoap implements Serializable {

	public static DDMDataProviderInstanceLinkSoap toSoapModel(
		DDMDataProviderInstanceLink model) {

		DDMDataProviderInstanceLinkSoap soapModel =
			new DDMDataProviderInstanceLinkSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setDataProviderInstanceLinkId(
			model.getDataProviderInstanceLinkId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setDataProviderInstanceId(model.getDataProviderInstanceId());
		soapModel.setStructureId(model.getStructureId());

		return soapModel;
	}

	public static DDMDataProviderInstanceLinkSoap[] toSoapModels(
		DDMDataProviderInstanceLink[] models) {

		DDMDataProviderInstanceLinkSoap[] soapModels =
			new DDMDataProviderInstanceLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DDMDataProviderInstanceLinkSoap[][] toSoapModels(
		DDMDataProviderInstanceLink[][] models) {

		DDMDataProviderInstanceLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DDMDataProviderInstanceLinkSoap
				[models.length][models[0].length];
		}
		else {
			soapModels = new DDMDataProviderInstanceLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DDMDataProviderInstanceLinkSoap[] toSoapModels(
		List<DDMDataProviderInstanceLink> models) {

		List<DDMDataProviderInstanceLinkSoap> soapModels =
			new ArrayList<DDMDataProviderInstanceLinkSoap>(models.size());

		for (DDMDataProviderInstanceLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new DDMDataProviderInstanceLinkSoap[soapModels.size()]);
	}

	public DDMDataProviderInstanceLinkSoap() {
	}

	public long getPrimaryKey() {
		return _dataProviderInstanceLinkId;
	}

	public void setPrimaryKey(long pk) {
		setDataProviderInstanceLinkId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getDataProviderInstanceLinkId() {
		return _dataProviderInstanceLinkId;
	}

	public void setDataProviderInstanceLinkId(long dataProviderInstanceLinkId) {
		_dataProviderInstanceLinkId = dataProviderInstanceLinkId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getDataProviderInstanceId() {
		return _dataProviderInstanceId;
	}

	public void setDataProviderInstanceId(long dataProviderInstanceId) {
		_dataProviderInstanceId = dataProviderInstanceId;
	}

	public long getStructureId() {
		return _structureId;
	}

	public void setStructureId(long structureId) {
		_structureId = structureId;
	}

	private long _mvccVersion;
	private long _dataProviderInstanceLinkId;
	private long _companyId;
	private long _dataProviderInstanceId;
	private long _structureId;

}