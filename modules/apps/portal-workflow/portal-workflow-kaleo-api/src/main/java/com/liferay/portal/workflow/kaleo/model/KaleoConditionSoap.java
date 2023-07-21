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
public class KaleoConditionSoap implements Serializable {

	public static KaleoConditionSoap toSoapModel(KaleoCondition model) {
		KaleoConditionSoap soapModel = new KaleoConditionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setKaleoConditionId(model.getKaleoConditionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionVersionId(
			model.getKaleoDefinitionVersionId());
		soapModel.setKaleoNodeId(model.getKaleoNodeId());
		soapModel.setScript(model.getScript());
		soapModel.setScriptLanguage(model.getScriptLanguage());
		soapModel.setScriptRequiredContexts(model.getScriptRequiredContexts());

		return soapModel;
	}

	public static KaleoConditionSoap[] toSoapModels(KaleoCondition[] models) {
		KaleoConditionSoap[] soapModels = new KaleoConditionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoConditionSoap[][] toSoapModels(
		KaleoCondition[][] models) {

		KaleoConditionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new KaleoConditionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoConditionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoConditionSoap[] toSoapModels(
		List<KaleoCondition> models) {

		List<KaleoConditionSoap> soapModels = new ArrayList<KaleoConditionSoap>(
			models.size());

		for (KaleoCondition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoConditionSoap[soapModels.size()]);
	}

	public KaleoConditionSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoConditionId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoConditionId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getKaleoConditionId() {
		return _kaleoConditionId;
	}

	public void setKaleoConditionId(long kaleoConditionId) {
		_kaleoConditionId = kaleoConditionId;
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

	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
	}

	public String getScript() {
		return _script;
	}

	public void setScript(String script) {
		_script = script;
	}

	public String getScriptLanguage() {
		return _scriptLanguage;
	}

	public void setScriptLanguage(String scriptLanguage) {
		_scriptLanguage = scriptLanguage;
	}

	public String getScriptRequiredContexts() {
		return _scriptRequiredContexts;
	}

	public void setScriptRequiredContexts(String scriptRequiredContexts) {
		_scriptRequiredContexts = scriptRequiredContexts;
	}

	private long _mvccVersion;
	private long _kaleoConditionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionVersionId;
	private long _kaleoNodeId;
	private String _script;
	private String _scriptLanguage;
	private String _scriptRequiredContexts;

}