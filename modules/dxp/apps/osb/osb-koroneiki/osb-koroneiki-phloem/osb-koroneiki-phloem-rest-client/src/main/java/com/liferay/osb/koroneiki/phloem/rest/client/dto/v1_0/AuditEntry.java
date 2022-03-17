/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.function.UnsafeSupplier;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AuditEntrySerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class AuditEntry implements Cloneable, Serializable {

	public static AuditEntry toDTO(String json) {
		return AuditEntrySerDes.toDTO(json);
	}

	public Action getAction() {
		return action;
	}

	public String getActionAsString() {
		if (action == null) {
			return null;
		}

		return action.toString();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setAction(
		UnsafeSupplier<Action, Exception> actionUnsafeSupplier) {

		try {
			action = actionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Action action;

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public void setAgentName(
		UnsafeSupplier<String, Exception> agentNameUnsafeSupplier) {

		try {
			agentName = agentNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String agentName;

	public String getAgentUID() {
		return agentUID;
	}

	public void setAgentUID(String agentUID) {
		this.agentUID = agentUID;
	}

	public void setAgentUID(
		UnsafeSupplier<String, Exception> agentUIDUnsafeSupplier) {

		try {
			agentUID = agentUIDUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String agentUID;

	public Long getAuditSetId() {
		return auditSetId;
	}

	public void setAuditSetId(Long auditSetId) {
		this.auditSetId = auditSetId;
	}

	public void setAuditSetId(
		UnsafeSupplier<Long, Exception> auditSetIdUnsafeSupplier) {

		try {
			auditSetId = auditSetIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long auditSetId;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date dateCreated;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setField(
		UnsafeSupplier<String, Exception> fieldUnsafeSupplier) {

		try {
			field = fieldUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String field;

	public String getFieldClassLabel() {
		return fieldClassLabel;
	}

	public void setFieldClassLabel(String fieldClassLabel) {
		this.fieldClassLabel = fieldClassLabel;
	}

	public void setFieldClassLabel(
		UnsafeSupplier<String, Exception> fieldClassLabelUnsafeSupplier) {

		try {
			fieldClassLabel = fieldClassLabelUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String fieldClassLabel;

	public Long getFieldClassPK() {
		return fieldClassPK;
	}

	public void setFieldClassPK(Long fieldClassPK) {
		this.fieldClassPK = fieldClassPK;
	}

	public void setFieldClassPK(
		UnsafeSupplier<Long, Exception> fieldClassPKUnsafeSupplier) {

		try {
			fieldClassPK = fieldClassPKUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long fieldClassPK;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String key;

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public void setNewValue(
		UnsafeSupplier<String, Exception> newValueUnsafeSupplier) {

		try {
			newValue = newValueUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String newValue;

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public void setOldValue(
		UnsafeSupplier<String, Exception> oldValueUnsafeSupplier) {

		try {
			oldValue = oldValueUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String oldValue;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setSummary(
		UnsafeSupplier<String, Exception> summaryUnsafeSupplier) {

		try {
			summary = summaryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String summary;

	@Override
	public AuditEntry clone() throws CloneNotSupportedException {
		return (AuditEntry)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AuditEntry)) {
			return false;
		}

		AuditEntry auditEntry = (AuditEntry)object;

		return Objects.equals(toString(), auditEntry.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AuditEntrySerDes.toJSON(this);
	}

	public static enum Action {

		ADD("Add"), ASSIGN("Assign"), DELETE("Delete"), RENEW("Renew"),
		UNASSIGN("Unassign"), UPDATE("Update");

		public static Action create(String value) {
			for (Action action : values()) {
				if (Objects.equals(action.getValue(), value) ||
					Objects.equals(action.name(), value)) {

					return action;
				}
			}

			return null;
		}

		public String getValue() {
			return _value;
		}

		@Override
		public String toString() {
			return _value;
		}

		private Action(String value) {
			_value = value;
		}

		private final String _value;

	}

}