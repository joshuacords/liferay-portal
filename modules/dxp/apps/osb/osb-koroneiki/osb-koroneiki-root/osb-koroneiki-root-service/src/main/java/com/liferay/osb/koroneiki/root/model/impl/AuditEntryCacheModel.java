/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.model.impl;

import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AuditEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AuditEntryCacheModel
	implements CacheModel<AuditEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AuditEntryCacheModel)) {
			return false;
		}

		AuditEntryCacheModel auditEntryCacheModel =
			(AuditEntryCacheModel)object;

		if ((auditEntryId == auditEntryCacheModel.auditEntryId) &&
			(mvccVersion == auditEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, auditEntryId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", auditEntryId=");
		sb.append(auditEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", auditEntryKey=");
		sb.append(auditEntryKey);
		sb.append(", agentName=");
		sb.append(agentName);
		sb.append(", agentUID=");
		sb.append(agentUID);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", auditSetId=");
		sb.append(auditSetId);
		sb.append(", fieldClassNameId=");
		sb.append(fieldClassNameId);
		sb.append(", fieldClassPK=");
		sb.append(fieldClassPK);
		sb.append(", action=");
		sb.append(action);
		sb.append(", field=");
		sb.append(field);
		sb.append(", oldLabel=");
		sb.append(oldLabel);
		sb.append(", oldValue=");
		sb.append(oldValue);
		sb.append(", newLabel=");
		sb.append(newLabel);
		sb.append(", newValue=");
		sb.append(newValue);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuditEntry toEntityModel() {
		AuditEntryImpl auditEntryImpl = new AuditEntryImpl();

		auditEntryImpl.setMvccVersion(mvccVersion);
		auditEntryImpl.setAuditEntryId(auditEntryId);
		auditEntryImpl.setCompanyId(companyId);
		auditEntryImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			auditEntryImpl.setCreateDate(null);
		}
		else {
			auditEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			auditEntryImpl.setModifiedDate(null);
		}
		else {
			auditEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (auditEntryKey == null) {
			auditEntryImpl.setAuditEntryKey("");
		}
		else {
			auditEntryImpl.setAuditEntryKey(auditEntryKey);
		}

		if (agentName == null) {
			auditEntryImpl.setAgentName("");
		}
		else {
			auditEntryImpl.setAgentName(agentName);
		}

		if (agentUID == null) {
			auditEntryImpl.setAgentUID("");
		}
		else {
			auditEntryImpl.setAgentUID(agentUID);
		}

		auditEntryImpl.setClassNameId(classNameId);
		auditEntryImpl.setClassPK(classPK);
		auditEntryImpl.setAuditSetId(auditSetId);
		auditEntryImpl.setFieldClassNameId(fieldClassNameId);
		auditEntryImpl.setFieldClassPK(fieldClassPK);

		if (action == null) {
			auditEntryImpl.setAction("");
		}
		else {
			auditEntryImpl.setAction(action);
		}

		if (field == null) {
			auditEntryImpl.setField("");
		}
		else {
			auditEntryImpl.setField(field);
		}

		if (oldLabel == null) {
			auditEntryImpl.setOldLabel("");
		}
		else {
			auditEntryImpl.setOldLabel(oldLabel);
		}

		if (oldValue == null) {
			auditEntryImpl.setOldValue("");
		}
		else {
			auditEntryImpl.setOldValue(oldValue);
		}

		if (newLabel == null) {
			auditEntryImpl.setNewLabel("");
		}
		else {
			auditEntryImpl.setNewLabel(newLabel);
		}

		if (newValue == null) {
			auditEntryImpl.setNewValue("");
		}
		else {
			auditEntryImpl.setNewValue(newValue);
		}

		if (description == null) {
			auditEntryImpl.setDescription("");
		}
		else {
			auditEntryImpl.setDescription(description);
		}

		auditEntryImpl.resetOriginalValues();

		return auditEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		auditEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		auditEntryKey = objectInput.readUTF();
		agentName = objectInput.readUTF();
		agentUID = objectInput.readUTF();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		auditSetId = objectInput.readLong();

		fieldClassNameId = objectInput.readLong();

		fieldClassPK = objectInput.readLong();
		action = objectInput.readUTF();
		field = objectInput.readUTF();
		oldLabel = objectInput.readUTF();
		oldValue = objectInput.readUTF();
		newLabel = objectInput.readUTF();
		newValue = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(auditEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (auditEntryKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(auditEntryKey);
		}

		if (agentName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(agentName);
		}

		if (agentUID == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(agentUID);
		}

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(auditSetId);

		objectOutput.writeLong(fieldClassNameId);

		objectOutput.writeLong(fieldClassPK);

		if (action == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(action);
		}

		if (field == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(field);
		}

		if (oldLabel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(oldLabel);
		}

		if (oldValue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(oldValue);
		}

		if (newLabel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(newLabel);
		}

		if (newValue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(newValue);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long mvccVersion;
	public long auditEntryId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String auditEntryKey;
	public String agentName;
	public String agentUID;
	public long classNameId;
	public long classPK;
	public long auditSetId;
	public long fieldClassNameId;
	public long fieldClassPK;
	public String action;
	public String field;
	public String oldLabel;
	public String oldValue;
	public String newLabel;
	public String newValue;
	public String description;

}