/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BigDecimalEntrySoap implements Serializable {

	public static BigDecimalEntrySoap toSoapModel(BigDecimalEntry model) {
		BigDecimalEntrySoap soapModel = new BigDecimalEntrySoap();

		soapModel.setBigDecimalEntryId(model.getBigDecimalEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setBigDecimalValue(model.getBigDecimalValue());

		return soapModel;
	}

	public static BigDecimalEntrySoap[] toSoapModels(BigDecimalEntry[] models) {
		BigDecimalEntrySoap[] soapModels =
			new BigDecimalEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BigDecimalEntrySoap[][] toSoapModels(
		BigDecimalEntry[][] models) {

		BigDecimalEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new BigDecimalEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new BigDecimalEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BigDecimalEntrySoap[] toSoapModels(
		List<BigDecimalEntry> models) {

		List<BigDecimalEntrySoap> soapModels =
			new ArrayList<BigDecimalEntrySoap>(models.size());

		for (BigDecimalEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BigDecimalEntrySoap[soapModels.size()]);
	}

	public BigDecimalEntrySoap() {
	}

	public long getPrimaryKey() {
		return _bigDecimalEntryId;
	}

	public void setPrimaryKey(long pk) {
		setBigDecimalEntryId(pk);
	}

	public long getBigDecimalEntryId() {
		return _bigDecimalEntryId;
	}

	public void setBigDecimalEntryId(long bigDecimalEntryId) {
		_bigDecimalEntryId = bigDecimalEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public BigDecimal getBigDecimalValue() {
		return _bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {
		_bigDecimalValue = bigDecimalValue;
	}

	private long _bigDecimalEntryId;
	private long _companyId;
	private BigDecimal _bigDecimalValue;

}