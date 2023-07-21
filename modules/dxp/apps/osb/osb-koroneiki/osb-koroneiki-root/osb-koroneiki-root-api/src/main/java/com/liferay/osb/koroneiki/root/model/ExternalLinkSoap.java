/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.root.service.http.ExternalLinkServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ExternalLinkSoap implements Serializable {

	public static ExternalLinkSoap toSoapModel(ExternalLink model) {
		ExternalLinkSoap soapModel = new ExternalLinkSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setExternalLinkId(model.getExternalLinkId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setExternalLinkKey(model.getExternalLinkKey());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setDomain(model.getDomain());
		soapModel.setEntityName(model.getEntityName());
		soapModel.setEntityId(model.getEntityId());

		return soapModel;
	}

	public static ExternalLinkSoap[] toSoapModels(ExternalLink[] models) {
		ExternalLinkSoap[] soapModels = new ExternalLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ExternalLinkSoap[][] toSoapModels(ExternalLink[][] models) {
		ExternalLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ExternalLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ExternalLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ExternalLinkSoap[] toSoapModels(List<ExternalLink> models) {
		List<ExternalLinkSoap> soapModels = new ArrayList<ExternalLinkSoap>(
			models.size());

		for (ExternalLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ExternalLinkSoap[soapModels.size()]);
	}

	public ExternalLinkSoap() {
	}

	public long getPrimaryKey() {
		return _externalLinkId;
	}

	public void setPrimaryKey(long pk) {
		setExternalLinkId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getExternalLinkId() {
		return _externalLinkId;
	}

	public void setExternalLinkId(long externalLinkId) {
		_externalLinkId = externalLinkId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getExternalLinkKey() {
		return _externalLinkKey;
	}

	public void setExternalLinkKey(String externalLinkKey) {
		_externalLinkKey = externalLinkKey;
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

	public String getDomain() {
		return _domain;
	}

	public void setDomain(String domain) {
		_domain = domain;
	}

	public String getEntityName() {
		return _entityName;
	}

	public void setEntityName(String entityName) {
		_entityName = entityName;
	}

	public String getEntityId() {
		return _entityId;
	}

	public void setEntityId(String entityId) {
		_entityId = entityId;
	}

	private long _mvccVersion;
	private long _externalLinkId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _externalLinkKey;
	private long _classNameId;
	private long _classPK;
	private String _domain;
	private String _entityName;
	private String _entityId;

}