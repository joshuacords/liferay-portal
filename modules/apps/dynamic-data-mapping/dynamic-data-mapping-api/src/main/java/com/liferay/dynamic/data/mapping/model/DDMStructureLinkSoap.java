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
public class DDMStructureLinkSoap implements Serializable {

	public static DDMStructureLinkSoap toSoapModel(DDMStructureLink model) {
		DDMStructureLinkSoap soapModel = new DDMStructureLinkSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setStructureLinkId(model.getStructureLinkId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setStructureId(model.getStructureId());

		return soapModel;
	}

	public static DDMStructureLinkSoap[] toSoapModels(
		DDMStructureLink[] models) {

		DDMStructureLinkSoap[] soapModels =
			new DDMStructureLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DDMStructureLinkSoap[][] toSoapModels(
		DDMStructureLink[][] models) {

		DDMStructureLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new DDMStructureLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DDMStructureLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DDMStructureLinkSoap[] toSoapModels(
		List<DDMStructureLink> models) {

		List<DDMStructureLinkSoap> soapModels =
			new ArrayList<DDMStructureLinkSoap>(models.size());

		for (DDMStructureLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DDMStructureLinkSoap[soapModels.size()]);
	}

	public DDMStructureLinkSoap() {
	}

	public long getPrimaryKey() {
		return _structureLinkId;
	}

	public void setPrimaryKey(long pk) {
		setStructureLinkId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getStructureLinkId() {
		return _structureLinkId;
	}

	public void setStructureLinkId(long structureLinkId) {
		_structureLinkId = structureLinkId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getStructureId() {
		return _structureId;
	}

	public void setStructureId(long structureId) {
		_structureId = structureId;
	}

	private long _mvccVersion;
	private long _structureLinkId;
	private long _companyId;
	private long _classNameId;
	private long _classPK;
	private long _structureId;

}