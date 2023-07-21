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
public class DDMTemplateLinkSoap implements Serializable {

	public static DDMTemplateLinkSoap toSoapModel(DDMTemplateLink model) {
		DDMTemplateLinkSoap soapModel = new DDMTemplateLinkSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setTemplateLinkId(model.getTemplateLinkId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setTemplateId(model.getTemplateId());

		return soapModel;
	}

	public static DDMTemplateLinkSoap[] toSoapModels(DDMTemplateLink[] models) {
		DDMTemplateLinkSoap[] soapModels =
			new DDMTemplateLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DDMTemplateLinkSoap[][] toSoapModels(
		DDMTemplateLink[][] models) {

		DDMTemplateLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new DDMTemplateLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DDMTemplateLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DDMTemplateLinkSoap[] toSoapModels(
		List<DDMTemplateLink> models) {

		List<DDMTemplateLinkSoap> soapModels =
			new ArrayList<DDMTemplateLinkSoap>(models.size());

		for (DDMTemplateLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DDMTemplateLinkSoap[soapModels.size()]);
	}

	public DDMTemplateLinkSoap() {
	}

	public long getPrimaryKey() {
		return _templateLinkId;
	}

	public void setPrimaryKey(long pk) {
		setTemplateLinkId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getTemplateLinkId() {
		return _templateLinkId;
	}

	public void setTemplateLinkId(long templateLinkId) {
		_templateLinkId = templateLinkId;
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

	public long getTemplateId() {
		return _templateId;
	}

	public void setTemplateId(long templateId) {
		_templateId = templateId;
	}

	private long _mvccVersion;
	private long _templateLinkId;
	private long _companyId;
	private long _classNameId;
	private long _classPK;
	private long _templateId;

}