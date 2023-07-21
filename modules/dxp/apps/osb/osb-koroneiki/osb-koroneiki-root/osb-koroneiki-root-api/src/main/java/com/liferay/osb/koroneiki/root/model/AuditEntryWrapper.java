/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AuditEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntry
 * @generated
 */
public class AuditEntryWrapper
	extends BaseModelWrapper<AuditEntry>
	implements AuditEntry, ModelWrapper<AuditEntry> {

	public AuditEntryWrapper(AuditEntry auditEntry) {
		super(auditEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("auditEntryId", getAuditEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("auditEntryKey", getAuditEntryKey());
		attributes.put("agentName", getAgentName());
		attributes.put("agentUID", getAgentUID());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("auditSetId", getAuditSetId());
		attributes.put("fieldClassNameId", getFieldClassNameId());
		attributes.put("fieldClassPK", getFieldClassPK());
		attributes.put("action", getAction());
		attributes.put("field", getField());
		attributes.put("oldLabel", getOldLabel());
		attributes.put("oldValue", getOldValue());
		attributes.put("newLabel", getNewLabel());
		attributes.put("newValue", getNewValue());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long auditEntryId = (Long)attributes.get("auditEntryId");

		if (auditEntryId != null) {
			setAuditEntryId(auditEntryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String auditEntryKey = (String)attributes.get("auditEntryKey");

		if (auditEntryKey != null) {
			setAuditEntryKey(auditEntryKey);
		}

		String agentName = (String)attributes.get("agentName");

		if (agentName != null) {
			setAgentName(agentName);
		}

		String agentUID = (String)attributes.get("agentUID");

		if (agentUID != null) {
			setAgentUID(agentUID);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long auditSetId = (Long)attributes.get("auditSetId");

		if (auditSetId != null) {
			setAuditSetId(auditSetId);
		}

		Long fieldClassNameId = (Long)attributes.get("fieldClassNameId");

		if (fieldClassNameId != null) {
			setFieldClassNameId(fieldClassNameId);
		}

		Long fieldClassPK = (Long)attributes.get("fieldClassPK");

		if (fieldClassPK != null) {
			setFieldClassPK(fieldClassPK);
		}

		String action = (String)attributes.get("action");

		if (action != null) {
			setAction(action);
		}

		String field = (String)attributes.get("field");

		if (field != null) {
			setField(field);
		}

		String oldLabel = (String)attributes.get("oldLabel");

		if (oldLabel != null) {
			setOldLabel(oldLabel);
		}

		String oldValue = (String)attributes.get("oldValue");

		if (oldValue != null) {
			setOldValue(oldValue);
		}

		String newLabel = (String)attributes.get("newLabel");

		if (newLabel != null) {
			setNewLabel(newLabel);
		}

		String newValue = (String)attributes.get("newValue");

		if (newValue != null) {
			setNewValue(newValue);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	 * Returns the action of this audit entry.
	 *
	 * @return the action of this audit entry
	 */
	@Override
	public String getAction() {
		return model.getAction();
	}

	/**
	 * Returns the agent name of this audit entry.
	 *
	 * @return the agent name of this audit entry
	 */
	@Override
	public String getAgentName() {
		return model.getAgentName();
	}

	/**
	 * Returns the agent uid of this audit entry.
	 *
	 * @return the agent uid of this audit entry
	 */
	@Override
	public String getAgentUID() {
		return model.getAgentUID();
	}

	/**
	 * Returns the audit entry ID of this audit entry.
	 *
	 * @return the audit entry ID of this audit entry
	 */
	@Override
	public long getAuditEntryId() {
		return model.getAuditEntryId();
	}

	/**
	 * Returns the audit entry key of this audit entry.
	 *
	 * @return the audit entry key of this audit entry
	 */
	@Override
	public String getAuditEntryKey() {
		return model.getAuditEntryKey();
	}

	/**
	 * Returns the audit set ID of this audit entry.
	 *
	 * @return the audit set ID of this audit entry
	 */
	@Override
	public long getAuditSetId() {
		return model.getAuditSetId();
	}

	/**
	 * Returns the fully qualified class name of this audit entry.
	 *
	 * @return the fully qualified class name of this audit entry
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this audit entry.
	 *
	 * @return the class name ID of this audit entry
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class pk of this audit entry.
	 *
	 * @return the class pk of this audit entry
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the company ID of this audit entry.
	 *
	 * @return the company ID of this audit entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this audit entry.
	 *
	 * @return the create date of this audit entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this audit entry.
	 *
	 * @return the description of this audit entry
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the field of this audit entry.
	 *
	 * @return the field of this audit entry
	 */
	@Override
	public String getField() {
		return model.getField();
	}

	/**
	 * Returns the field class name ID of this audit entry.
	 *
	 * @return the field class name ID of this audit entry
	 */
	@Override
	public long getFieldClassNameId() {
		return model.getFieldClassNameId();
	}

	/**
	 * Returns the field class pk of this audit entry.
	 *
	 * @return the field class pk of this audit entry
	 */
	@Override
	public long getFieldClassPK() {
		return model.getFieldClassPK();
	}

	/**
	 * Returns the modified date of this audit entry.
	 *
	 * @return the modified date of this audit entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this audit entry.
	 *
	 * @return the mvcc version of this audit entry
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the new label of this audit entry.
	 *
	 * @return the new label of this audit entry
	 */
	@Override
	public String getNewLabel() {
		return model.getNewLabel();
	}

	/**
	 * Returns the new value of this audit entry.
	 *
	 * @return the new value of this audit entry
	 */
	@Override
	public String getNewValue() {
		return model.getNewValue();
	}

	/**
	 * Returns the old label of this audit entry.
	 *
	 * @return the old label of this audit entry
	 */
	@Override
	public String getOldLabel() {
		return model.getOldLabel();
	}

	/**
	 * Returns the old value of this audit entry.
	 *
	 * @return the old value of this audit entry
	 */
	@Override
	public String getOldValue() {
		return model.getOldValue();
	}

	/**
	 * Returns the primary key of this audit entry.
	 *
	 * @return the primary key of this audit entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this audit entry.
	 *
	 * @return the user ID of this audit entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this audit entry.
	 *
	 * @return the user uuid of this audit entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action of this audit entry.
	 *
	 * @param action the action of this audit entry
	 */
	@Override
	public void setAction(String action) {
		model.setAction(action);
	}

	/**
	 * Sets the agent name of this audit entry.
	 *
	 * @param agentName the agent name of this audit entry
	 */
	@Override
	public void setAgentName(String agentName) {
		model.setAgentName(agentName);
	}

	/**
	 * Sets the agent uid of this audit entry.
	 *
	 * @param agentUID the agent uid of this audit entry
	 */
	@Override
	public void setAgentUID(String agentUID) {
		model.setAgentUID(agentUID);
	}

	/**
	 * Sets the audit entry ID of this audit entry.
	 *
	 * @param auditEntryId the audit entry ID of this audit entry
	 */
	@Override
	public void setAuditEntryId(long auditEntryId) {
		model.setAuditEntryId(auditEntryId);
	}

	/**
	 * Sets the audit entry key of this audit entry.
	 *
	 * @param auditEntryKey the audit entry key of this audit entry
	 */
	@Override
	public void setAuditEntryKey(String auditEntryKey) {
		model.setAuditEntryKey(auditEntryKey);
	}

	/**
	 * Sets the audit set ID of this audit entry.
	 *
	 * @param auditSetId the audit set ID of this audit entry
	 */
	@Override
	public void setAuditSetId(long auditSetId) {
		model.setAuditSetId(auditSetId);
	}

	@Override
	public void setClassName(String className) {
		model.setClassName(className);
	}

	/**
	 * Sets the class name ID of this audit entry.
	 *
	 * @param classNameId the class name ID of this audit entry
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this audit entry.
	 *
	 * @param classPK the class pk of this audit entry
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this audit entry.
	 *
	 * @param companyId the company ID of this audit entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this audit entry.
	 *
	 * @param createDate the create date of this audit entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this audit entry.
	 *
	 * @param description the description of this audit entry
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the field of this audit entry.
	 *
	 * @param field the field of this audit entry
	 */
	@Override
	public void setField(String field) {
		model.setField(field);
	}

	/**
	 * Sets the field class name ID of this audit entry.
	 *
	 * @param fieldClassNameId the field class name ID of this audit entry
	 */
	@Override
	public void setFieldClassNameId(long fieldClassNameId) {
		model.setFieldClassNameId(fieldClassNameId);
	}

	/**
	 * Sets the field class pk of this audit entry.
	 *
	 * @param fieldClassPK the field class pk of this audit entry
	 */
	@Override
	public void setFieldClassPK(long fieldClassPK) {
		model.setFieldClassPK(fieldClassPK);
	}

	/**
	 * Sets the modified date of this audit entry.
	 *
	 * @param modifiedDate the modified date of this audit entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this audit entry.
	 *
	 * @param mvccVersion the mvcc version of this audit entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the new label of this audit entry.
	 *
	 * @param newLabel the new label of this audit entry
	 */
	@Override
	public void setNewLabel(String newLabel) {
		model.setNewLabel(newLabel);
	}

	/**
	 * Sets the new value of this audit entry.
	 *
	 * @param newValue the new value of this audit entry
	 */
	@Override
	public void setNewValue(String newValue) {
		model.setNewValue(newValue);
	}

	/**
	 * Sets the old label of this audit entry.
	 *
	 * @param oldLabel the old label of this audit entry
	 */
	@Override
	public void setOldLabel(String oldLabel) {
		model.setOldLabel(oldLabel);
	}

	/**
	 * Sets the old value of this audit entry.
	 *
	 * @param oldValue the old value of this audit entry
	 */
	@Override
	public void setOldValue(String oldValue) {
		model.setOldValue(oldValue);
	}

	/**
	 * Sets the primary key of this audit entry.
	 *
	 * @param primaryKey the primary key of this audit entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this audit entry.
	 *
	 * @param userId the user ID of this audit entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this audit entry.
	 *
	 * @param userUuid the user uuid of this audit entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected AuditEntryWrapper wrap(AuditEntry auditEntry) {
		return new AuditEntryWrapper(auditEntry);
	}

}