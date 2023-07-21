/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.counter.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CounterSoap implements Serializable {

	public static CounterSoap toSoapModel(Counter model) {
		CounterSoap soapModel = new CounterSoap();

		soapModel.setName(model.getName());
		soapModel.setCurrentId(model.getCurrentId());

		return soapModel;
	}

	public static CounterSoap[] toSoapModels(Counter[] models) {
		CounterSoap[] soapModels = new CounterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CounterSoap[][] toSoapModels(Counter[][] models) {
		CounterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CounterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CounterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CounterSoap[] toSoapModels(List<Counter> models) {
		List<CounterSoap> soapModels = new ArrayList<CounterSoap>(
			models.size());

		for (Counter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CounterSoap[soapModels.size()]);
	}

	public CounterSoap() {
	}

	public String getPrimaryKey() {
		return _name;
	}

	public void setPrimaryKey(String pk) {
		setName(pk);
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getCurrentId() {
		return _currentId;
	}

	public void setCurrentId(long currentId) {
		_currentId = currentId;
	}

	private String _name;
	private long _currentId;

}