/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRolePK;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContactAccountRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactAccountRoleCacheModel
	implements CacheModel<ContactAccountRole>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactAccountRoleCacheModel)) {
			return false;
		}

		ContactAccountRoleCacheModel contactAccountRoleCacheModel =
			(ContactAccountRoleCacheModel)object;

		if (contactAccountRolePK.equals(
				contactAccountRoleCacheModel.contactAccountRolePK) &&
			(mvccVersion == contactAccountRoleCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, contactAccountRolePK);

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
		StringBundler sb = new StringBundler(9);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", contactId=");
		sb.append(contactId);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", contactRoleId=");
		sb.append(contactRoleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContactAccountRole toEntityModel() {
		ContactAccountRoleImpl contactAccountRoleImpl =
			new ContactAccountRoleImpl();

		contactAccountRoleImpl.setMvccVersion(mvccVersion);
		contactAccountRoleImpl.setContactId(contactId);
		contactAccountRoleImpl.setAccountId(accountId);
		contactAccountRoleImpl.setContactRoleId(contactRoleId);

		contactAccountRoleImpl.resetOriginalValues();

		return contactAccountRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		contactId = objectInput.readLong();

		accountId = objectInput.readLong();

		contactRoleId = objectInput.readLong();

		contactAccountRolePK = new ContactAccountRolePK(
			contactId, accountId, contactRoleId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(contactId);

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(contactRoleId);
	}

	public long mvccVersion;
	public long contactId;
	public long accountId;
	public long contactRoleId;
	public transient ContactAccountRolePK contactAccountRolePK;

}