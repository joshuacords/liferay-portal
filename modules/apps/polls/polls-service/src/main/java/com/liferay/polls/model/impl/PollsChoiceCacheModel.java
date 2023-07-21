/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.polls.model.PollsChoice;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PollsChoice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PollsChoiceCacheModel
	implements CacheModel<PollsChoice>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PollsChoiceCacheModel)) {
			return false;
		}

		PollsChoiceCacheModel pollsChoiceCacheModel =
			(PollsChoiceCacheModel)object;

		if ((choiceId == pollsChoiceCacheModel.choiceId) &&
			(mvccVersion == pollsChoiceCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, choiceId);

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
		StringBundler sb = new StringBundler(27);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", choiceId=");
		sb.append(choiceId);
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
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PollsChoice toEntityModel() {
		PollsChoiceImpl pollsChoiceImpl = new PollsChoiceImpl();

		pollsChoiceImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			pollsChoiceImpl.setUuid("");
		}
		else {
			pollsChoiceImpl.setUuid(uuid);
		}

		pollsChoiceImpl.setChoiceId(choiceId);
		pollsChoiceImpl.setGroupId(groupId);
		pollsChoiceImpl.setCompanyId(companyId);
		pollsChoiceImpl.setUserId(userId);

		if (userName == null) {
			pollsChoiceImpl.setUserName("");
		}
		else {
			pollsChoiceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pollsChoiceImpl.setCreateDate(null);
		}
		else {
			pollsChoiceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pollsChoiceImpl.setModifiedDate(null);
		}
		else {
			pollsChoiceImpl.setModifiedDate(new Date(modifiedDate));
		}

		pollsChoiceImpl.setQuestionId(questionId);

		if (name == null) {
			pollsChoiceImpl.setName("");
		}
		else {
			pollsChoiceImpl.setName(name);
		}

		if (description == null) {
			pollsChoiceImpl.setDescription("");
		}
		else {
			pollsChoiceImpl.setDescription(description);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			pollsChoiceImpl.setLastPublishDate(null);
		}
		else {
			pollsChoiceImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		pollsChoiceImpl.resetOriginalValues();

		return pollsChoiceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		choiceId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		questionId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(choiceId);

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

		objectOutput.writeLong(questionId);

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

		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public long choiceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long questionId;
	public String name;
	public String description;
	public long lastPublishDate;

}