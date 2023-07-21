/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.spring.sample.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SpringEntrySoap implements Serializable {

	public static SpringEntrySoap toSoapModel(SpringEntry model) {
		SpringEntrySoap soapModel = new SpringEntrySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setSpringEntryId(model.getSpringEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static SpringEntrySoap[] toSoapModels(SpringEntry[] models) {
		SpringEntrySoap[] soapModels = new SpringEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SpringEntrySoap[][] toSoapModels(SpringEntry[][] models) {
		SpringEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SpringEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SpringEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SpringEntrySoap[] toSoapModels(List<SpringEntry> models) {
		List<SpringEntrySoap> soapModels = new ArrayList<SpringEntrySoap>(
			models.size());

		for (SpringEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SpringEntrySoap[soapModels.size()]);
	}

	public SpringEntrySoap() {
	}

	public long getPrimaryKey() {
		return _springEntryId;
	}

	public void setPrimaryKey(long pk) {
		setSpringEntryId(pk);
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

	public long getSpringEntryId() {
		return _springEntryId;
	}

	public void setSpringEntryId(long springEntryId) {
		_springEntryId = springEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _springEntryId;
	private long _companyId;
	private Date _createDate;

}