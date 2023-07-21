/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.powwow.model.PowwowMeeting;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PowwowMeeting in entity cache.
 *
 * @author Shinn Lok
 * @generated
 */
public class PowwowMeetingCacheModel
	implements CacheModel<PowwowMeeting>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PowwowMeetingCacheModel)) {
			return false;
		}

		PowwowMeetingCacheModel powwowMeetingCacheModel =
			(PowwowMeetingCacheModel)object;

		if (powwowMeetingId == powwowMeetingCacheModel.powwowMeetingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, powwowMeetingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{powwowMeetingId=");
		sb.append(powwowMeetingId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", powwowServerId=");
		sb.append(powwowServerId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", providerType=");
		sb.append(providerType);
		sb.append(", providerTypeMetadata=");
		sb.append(providerTypeMetadata);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PowwowMeeting toEntityModel() {
		PowwowMeetingImpl powwowMeetingImpl = new PowwowMeetingImpl();

		powwowMeetingImpl.setPowwowMeetingId(powwowMeetingId);
		powwowMeetingImpl.setGroupId(groupId);
		powwowMeetingImpl.setCompanyId(companyId);
		powwowMeetingImpl.setUserId(userId);

		if (userName == null) {
			powwowMeetingImpl.setUserName("");
		}
		else {
			powwowMeetingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			powwowMeetingImpl.setCreateDate(null);
		}
		else {
			powwowMeetingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			powwowMeetingImpl.setModifiedDate(null);
		}
		else {
			powwowMeetingImpl.setModifiedDate(new Date(modifiedDate));
		}

		powwowMeetingImpl.setPowwowServerId(powwowServerId);

		if (name == null) {
			powwowMeetingImpl.setName("");
		}
		else {
			powwowMeetingImpl.setName(name);
		}

		if (description == null) {
			powwowMeetingImpl.setDescription("");
		}
		else {
			powwowMeetingImpl.setDescription(description);
		}

		if (providerType == null) {
			powwowMeetingImpl.setProviderType("");
		}
		else {
			powwowMeetingImpl.setProviderType(providerType);
		}

		if (providerTypeMetadata == null) {
			powwowMeetingImpl.setProviderTypeMetadata("");
		}
		else {
			powwowMeetingImpl.setProviderTypeMetadata(providerTypeMetadata);
		}

		if (languageId == null) {
			powwowMeetingImpl.setLanguageId("");
		}
		else {
			powwowMeetingImpl.setLanguageId(languageId);
		}

		powwowMeetingImpl.setCalendarBookingId(calendarBookingId);
		powwowMeetingImpl.setStatus(status);

		powwowMeetingImpl.resetOriginalValues();

		return powwowMeetingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		powwowMeetingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		powwowServerId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		providerType = objectInput.readUTF();
		providerTypeMetadata = objectInput.readUTF();
		languageId = objectInput.readUTF();

		calendarBookingId = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(powwowMeetingId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(powwowServerId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (providerType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(providerType);
		}

		if (providerTypeMetadata == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(providerTypeMetadata);
		}

		if (languageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(languageId);
		}

		objectOutput.writeLong(calendarBookingId);

		objectOutput.writeInt(status);
	}

	public long powwowMeetingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long powwowServerId;
	public String name;
	public String description;
	public String providerType;
	public String providerTypeMetadata;
	public String languageId;
	public long calendarBookingId;
	public int status;

}