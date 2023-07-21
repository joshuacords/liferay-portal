/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model;

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
public class KaleoNotificationSoap implements Serializable {

	public static KaleoNotificationSoap toSoapModel(KaleoNotification model) {
		KaleoNotificationSoap soapModel = new KaleoNotificationSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setKaleoNotificationId(model.getKaleoNotificationId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoClassName(model.getKaleoClassName());
		soapModel.setKaleoClassPK(model.getKaleoClassPK());
		soapModel.setKaleoDefinitionVersionId(
			model.getKaleoDefinitionVersionId());
		soapModel.setKaleoNodeName(model.getKaleoNodeName());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setExecutionType(model.getExecutionType());
		soapModel.setTemplate(model.getTemplate());
		soapModel.setTemplateLanguage(model.getTemplateLanguage());
		soapModel.setNotificationTypes(model.getNotificationTypes());

		return soapModel;
	}

	public static KaleoNotificationSoap[] toSoapModels(
		KaleoNotification[] models) {

		KaleoNotificationSoap[] soapModels =
			new KaleoNotificationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoNotificationSoap[][] toSoapModels(
		KaleoNotification[][] models) {

		KaleoNotificationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new KaleoNotificationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoNotificationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoNotificationSoap[] toSoapModels(
		List<KaleoNotification> models) {

		List<KaleoNotificationSoap> soapModels =
			new ArrayList<KaleoNotificationSoap>(models.size());

		for (KaleoNotification model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoNotificationSoap[soapModels.size()]);
	}

	public KaleoNotificationSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoNotificationId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoNotificationId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationId = kaleoNotificationId;
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

	public String getKaleoClassName() {
		return _kaleoClassName;
	}

	public void setKaleoClassName(String kaleoClassName) {
		_kaleoClassName = kaleoClassName;
	}

	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoClassPK = kaleoClassPK;
	}

	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	public String getKaleoNodeName() {
		return _kaleoNodeName;
	}

	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getExecutionType() {
		return _executionType;
	}

	public void setExecutionType(String executionType) {
		_executionType = executionType;
	}

	public String getTemplate() {
		return _template;
	}

	public void setTemplate(String template) {
		_template = template;
	}

	public String getTemplateLanguage() {
		return _templateLanguage;
	}

	public void setTemplateLanguage(String templateLanguage) {
		_templateLanguage = templateLanguage;
	}

	public String getNotificationTypes() {
		return _notificationTypes;
	}

	public void setNotificationTypes(String notificationTypes) {
		_notificationTypes = notificationTypes;
	}

	private long _mvccVersion;
	private long _kaleoNotificationId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionVersionId;
	private String _kaleoNodeName;
	private String _name;
	private String _description;
	private String _executionType;
	private String _template;
	private String _templateLanguage;
	private String _notificationTypes;

}