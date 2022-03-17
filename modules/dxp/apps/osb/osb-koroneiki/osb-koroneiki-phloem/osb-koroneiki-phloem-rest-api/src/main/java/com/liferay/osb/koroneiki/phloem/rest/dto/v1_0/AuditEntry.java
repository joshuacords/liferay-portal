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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "An audit log that records changes to objects.",
	value = "AuditEntry"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "AuditEntry")
public class AuditEntry implements Serializable {

	public static AuditEntry toDTO(String json) {
		return ObjectMapperUtil.readValue(AuditEntry.class, json);
	}

	public static AuditEntry unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(AuditEntry.class, json);
	}

	@Schema(description = "The action performed on the object.")
	@Valid
	public Action getAction() {
		return action;
	}

	@JsonIgnore
	public String getActionAsString() {
		if (action == null) {
			return null;
		}

		return action.toString();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@JsonIgnore
	public void setAction(
		UnsafeSupplier<Action, Exception> actionUnsafeSupplier) {

		try {
			action = actionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The action performed on the object.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Action action;

	@Schema(
		description = "The full name of the user performing the audited action."
	)
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@JsonIgnore
	public void setAgentName(
		UnsafeSupplier<String, Exception> agentNameUnsafeSupplier) {

		try {
			agentName = agentNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The full name of the user performing the audited action."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String agentName;

	@Schema(description = "The UUID of the user performing the audited action.")
	public String getAgentUID() {
		return agentUID;
	}

	public void setAgentUID(String agentUID) {
		this.agentUID = agentUID;
	}

	@JsonIgnore
	public void setAgentUID(
		UnsafeSupplier<String, Exception> agentUIDUnsafeSupplier) {

		try {
			agentUID = agentUIDUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The UUID of the user performing the audited action."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String agentUID;

	@Schema(description = "The id of related audit entries.")
	public Long getAuditSetId() {
		return auditSetId;
	}

	public void setAuditSetId(Long auditSetId) {
		this.auditSetId = auditSetId;
	}

	@JsonIgnore
	public void setAuditSetId(
		UnsafeSupplier<Long, Exception> auditSetIdUnsafeSupplier) {

		try {
			auditSetId = auditSetIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The id of related audit entries.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long auditSetId;

	@Schema(description = "The audit entry's creation date.")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonIgnore
	public void setDateCreated(
		UnsafeSupplier<Date, Exception> dateCreatedUnsafeSupplier) {

		try {
			dateCreated = dateCreatedUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The audit entry's creation date.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date dateCreated;

	@Schema(description = "Additional information describing what occurred.")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Additional information describing what occurred."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@Schema(description = "The field of the audited object.")
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@JsonIgnore
	public void setField(
		UnsafeSupplier<String, Exception> fieldUnsafeSupplier) {

		try {
			field = fieldUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The field of the audited object.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String field;

	@Schema(description = "The class name of the audited object.")
	public String getFieldClassLabel() {
		return fieldClassLabel;
	}

	public void setFieldClassLabel(String fieldClassLabel) {
		this.fieldClassLabel = fieldClassLabel;
	}

	@JsonIgnore
	public void setFieldClassLabel(
		UnsafeSupplier<String, Exception> fieldClassLabelUnsafeSupplier) {

		try {
			fieldClassLabel = fieldClassLabelUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The class name of the audited object.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String fieldClassLabel;

	@Schema(description = "The primary key of the audited object.")
	public Long getFieldClassPK() {
		return fieldClassPK;
	}

	public void setFieldClassPK(Long fieldClassPK) {
		this.fieldClassPK = fieldClassPK;
	}

	@JsonIgnore
	public void setFieldClassPK(
		UnsafeSupplier<Long, Exception> fieldClassPKUnsafeSupplier) {

		try {
			fieldClassPK = fieldClassPKUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The primary key of the audited object.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long fieldClassPK;

	@Schema(description = "The audit entry's key.")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@JsonIgnore
	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The audit entry's key.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String key;

	@Schema(description = "The new value of the field on the audited object.")
	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	@JsonIgnore
	public void setNewValue(
		UnsafeSupplier<String, Exception> newValueUnsafeSupplier) {

		try {
			newValue = newValueUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The new value of the field on the audited object."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String newValue;

	@Schema(description = "The old value of the field on the audited object.")
	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	@JsonIgnore
	public void setOldValue(
		UnsafeSupplier<String, Exception> oldValueUnsafeSupplier) {

		try {
			oldValue = oldValueUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The old value of the field on the audited object."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String oldValue;

	@Schema(description = "A summary of the what occurred.")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@JsonIgnore
	public void setSummary(
		UnsafeSupplier<String, Exception> summaryUnsafeSupplier) {

		try {
			summary = summaryUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "A summary of the what occurred.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String summary;

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
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (action != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"action\": ");

			sb.append("\"");

			sb.append(action);

			sb.append("\"");
		}

		if (agentName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"agentName\": ");

			sb.append("\"");

			sb.append(_escape(agentName));

			sb.append("\"");
		}

		if (agentUID != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"agentUID\": ");

			sb.append("\"");

			sb.append(_escape(agentUID));

			sb.append("\"");
		}

		if (auditSetId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"auditSetId\": ");

			sb.append(auditSetId);
		}

		if (dateCreated != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(dateCreated));

			sb.append("\"");
		}

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
		}

		if (field != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"field\": ");

			sb.append("\"");

			sb.append(_escape(field));

			sb.append("\"");
		}

		if (fieldClassLabel != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldClassLabel\": ");

			sb.append("\"");

			sb.append(_escape(fieldClassLabel));

			sb.append("\"");
		}

		if (fieldClassPK != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldClassPK\": ");

			sb.append(fieldClassPK);
		}

		if (key != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(key));

			sb.append("\"");
		}

		if (newValue != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"newValue\": ");

			sb.append("\"");

			sb.append(_escape(newValue));

			sb.append("\"");
		}

		if (oldValue != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"oldValue\": ");

			sb.append("\"");

			sb.append(_escape(oldValue));

			sb.append("\"");
		}

		if (summary != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"summary\": ");

			sb.append("\"");

			sb.append(_escape(summary));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry",
		name = "x-class-name"
	)
	public String xClassName;

	@GraphQLName("Action")
	public static enum Action {

		ADD("Add"), ASSIGN("Assign"), DELETE("Delete"), RENEW("Renew"),
		UNASSIGN("Unassign"), UPDATE("Update");

		@JsonCreator
		public static Action create(String value) {
			if ((value == null) || value.equals("")) {
				return null;
			}

			for (Action action : values()) {
				if (Objects.equals(action.getValue(), value)) {
					return action;
				}
			}

			throw new IllegalArgumentException("Invalid enum value: " + value);
		}

		@JsonValue
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

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

}