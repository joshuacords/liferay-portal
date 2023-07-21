/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.ClassNameServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ClassNameSoap implements Serializable {

	public static ClassNameSoap toSoapModel(ClassName model) {
		ClassNameSoap soapModel = new ClassNameSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static ClassNameSoap[] toSoapModels(ClassName[] models) {
		ClassNameSoap[] soapModels = new ClassNameSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ClassNameSoap[][] toSoapModels(ClassName[][] models) {
		ClassNameSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ClassNameSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ClassNameSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ClassNameSoap[] toSoapModels(List<ClassName> models) {
		List<ClassNameSoap> soapModels = new ArrayList<ClassNameSoap>(
			models.size());

		for (ClassName model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ClassNameSoap[soapModels.size()]);
	}

	public ClassNameSoap() {
	}

	public long getPrimaryKey() {
		return _classNameId;
	}

	public void setPrimaryKey(long pk) {
		setClassNameId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	private long _mvccVersion;
	private long _classNameId;
	private String _value;

}