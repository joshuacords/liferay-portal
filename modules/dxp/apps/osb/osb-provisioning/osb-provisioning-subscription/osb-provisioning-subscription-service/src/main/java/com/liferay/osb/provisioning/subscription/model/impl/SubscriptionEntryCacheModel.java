/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.model.impl;

import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
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
 * The cache model class for representing SubscriptionEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SubscriptionEntryCacheModel
	implements CacheModel<SubscriptionEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SubscriptionEntryCacheModel)) {
			return false;
		}

		SubscriptionEntryCacheModel subscriptionEntryCacheModel =
			(SubscriptionEntryCacheModel)object;

		if ((subscriptionEntryId ==
				subscriptionEntryCacheModel.subscriptionEntryId) &&
			(mvccVersion == subscriptionEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, subscriptionEntryId);

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
		StringBundler sb = new StringBundler(13);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", subscriptionEntryId=");
		sb.append(subscriptionEntryId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", contactUuid=");
		sb.append(contactUuid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SubscriptionEntry toEntityModel() {
		SubscriptionEntryImpl subscriptionEntryImpl =
			new SubscriptionEntryImpl();

		subscriptionEntryImpl.setMvccVersion(mvccVersion);
		subscriptionEntryImpl.setSubscriptionEntryId(subscriptionEntryId);

		if (createDate == Long.MIN_VALUE) {
			subscriptionEntryImpl.setCreateDate(null);
		}
		else {
			subscriptionEntryImpl.setCreateDate(new Date(createDate));
		}

		subscriptionEntryImpl.setClassNameId(classNameId);
		subscriptionEntryImpl.setClassPK(classPK);

		if (contactUuid == null) {
			subscriptionEntryImpl.setContactUuid("");
		}
		else {
			subscriptionEntryImpl.setContactUuid(contactUuid);
		}

		subscriptionEntryImpl.resetOriginalValues();

		return subscriptionEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		subscriptionEntryId = objectInput.readLong();
		createDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		contactUuid = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(subscriptionEntryId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (contactUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactUuid);
		}
	}

	public long mvccVersion;
	public long subscriptionEntryId;
	public long createDate;
	public long classNameId;
	public long classPK;
	public String contactUuid;

}