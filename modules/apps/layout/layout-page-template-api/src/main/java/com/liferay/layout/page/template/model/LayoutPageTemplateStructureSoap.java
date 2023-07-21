/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.layout.page.template.service.http.LayoutPageTemplateStructureServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LayoutPageTemplateStructureSoap implements Serializable {

	public static LayoutPageTemplateStructureSoap toSoapModel(
		LayoutPageTemplateStructure model) {

		LayoutPageTemplateStructureSoap soapModel =
			new LayoutPageTemplateStructureSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setLayoutPageTemplateStructureId(
			model.getLayoutPageTemplateStructureId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static LayoutPageTemplateStructureSoap[] toSoapModels(
		LayoutPageTemplateStructure[] models) {

		LayoutPageTemplateStructureSoap[] soapModels =
			new LayoutPageTemplateStructureSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LayoutPageTemplateStructureSoap[][] toSoapModels(
		LayoutPageTemplateStructure[][] models) {

		LayoutPageTemplateStructureSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LayoutPageTemplateStructureSoap
				[models.length][models[0].length];
		}
		else {
			soapModels = new LayoutPageTemplateStructureSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LayoutPageTemplateStructureSoap[] toSoapModels(
		List<LayoutPageTemplateStructure> models) {

		List<LayoutPageTemplateStructureSoap> soapModels =
			new ArrayList<LayoutPageTemplateStructureSoap>(models.size());

		for (LayoutPageTemplateStructure model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new LayoutPageTemplateStructureSoap[soapModels.size()]);
	}

	public LayoutPageTemplateStructureSoap() {
	}

	public long getPrimaryKey() {
		return _layoutPageTemplateStructureId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutPageTemplateStructureId(pk);
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

	public long getLayoutPageTemplateStructureId() {
		return _layoutPageTemplateStructureId;
	}

	public void setLayoutPageTemplateStructureId(
		long layoutPageTemplateStructureId) {

		_layoutPageTemplateStructureId = layoutPageTemplateStructureId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	private long _mvccVersion;
	private String _uuid;
	private long _layoutPageTemplateStructureId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;

}