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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.root.service.http.AuditEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuditEntrySoap implements Serializable {

	public static AuditEntrySoap toSoapModel(AuditEntry model) {
		AuditEntrySoap soapModel = new AuditEntrySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setAuditEntryId(model.getAuditEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAuditEntryKey(model.getAuditEntryKey());
		soapModel.setAgentName(model.getAgentName());
		soapModel.setAgentUID(model.getAgentUID());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setAuditSetId(model.getAuditSetId());
		soapModel.setFieldClassNameId(model.getFieldClassNameId());
		soapModel.setFieldClassPK(model.getFieldClassPK());
		soapModel.setAction(model.getAction());
		soapModel.setField(model.getField());
		soapModel.setOldLabel(model.getOldLabel());
		soapModel.setOldValue(model.getOldValue());
		soapModel.setNewLabel(model.getNewLabel());
		soapModel.setNewValue(model.getNewValue());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static AuditEntrySoap[] toSoapModels(AuditEntry[] models) {
		AuditEntrySoap[] soapModels = new AuditEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditEntrySoap[][] toSoapModels(AuditEntry[][] models) {
		AuditEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditEntrySoap[] toSoapModels(List<AuditEntry> models) {
		List<AuditEntrySoap> soapModels = new ArrayList<AuditEntrySoap>(
			models.size());

		for (AuditEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditEntrySoap[soapModels.size()]);
	}

	public AuditEntrySoap() {
	}

	public long getPrimaryKey() {
		return _auditEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAuditEntryId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getAuditEntryId() {
		return _auditEntryId;
	}

	public void setAuditEntryId(long auditEntryId) {
		_auditEntryId = auditEntryId;
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

	public String getAuditEntryKey() {
		return _auditEntryKey;
	}

	public void setAuditEntryKey(String auditEntryKey) {
		_auditEntryKey = auditEntryKey;
	}

	public String getAgentName() {
		return _agentName;
	}

	public void setAgentName(String agentName) {
		_agentName = agentName;
	}

	public String getAgentUID() {
		return _agentUID;
	}

	public void setAgentUID(String agentUID) {
		_agentUID = agentUID;
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

	public long getAuditSetId() {
		return _auditSetId;
	}

	public void setAuditSetId(long auditSetId) {
		_auditSetId = auditSetId;
	}

	public long getFieldClassNameId() {
		return _fieldClassNameId;
	}

	public void setFieldClassNameId(long fieldClassNameId) {
		_fieldClassNameId = fieldClassNameId;
	}

	public long getFieldClassPK() {
		return _fieldClassPK;
	}

	public void setFieldClassPK(long fieldClassPK) {
		_fieldClassPK = fieldClassPK;
	}

	public String getAction() {
		return _action;
	}

	public void setAction(String action) {
		_action = action;
	}

	public String getField() {
		return _field;
	}

	public void setField(String field) {
		_field = field;
	}

	public String getOldLabel() {
		return _oldLabel;
	}

	public void setOldLabel(String oldLabel) {
		_oldLabel = oldLabel;
	}

	public String getOldValue() {
		return _oldValue;
	}

	public void setOldValue(String oldValue) {
		_oldValue = oldValue;
	}

	public String getNewLabel() {
		return _newLabel;
	}

	public void setNewLabel(String newLabel) {
		_newLabel = newLabel;
	}

	public String getNewValue() {
		return _newValue;
	}

	public void setNewValue(String newValue) {
		_newValue = newValue;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _mvccVersion;
	private long _auditEntryId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _auditEntryKey;
	private String _agentName;
	private String _agentUID;
	private long _classNameId;
	private long _classPK;
	private long _auditSetId;
	private long _fieldClassNameId;
	private long _fieldClassPK;
	private String _action;
	private String _field;
	private String _oldLabel;
	private String _oldValue;
	private String _newLabel;
	private String _newValue;
	private String _description;

}