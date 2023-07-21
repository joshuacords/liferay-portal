/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactAccountRolePK
	implements Comparable<ContactAccountRolePK>, Serializable {

	public long contactId;
	public long accountId;
	public long contactRoleId;

	public ContactAccountRolePK() {
	}

	public ContactAccountRolePK(
		long contactId, long accountId, long contactRoleId) {

		this.contactId = contactId;
		this.accountId = accountId;
		this.contactRoleId = contactRoleId;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getContactRoleId() {
		return contactRoleId;
	}

	public void setContactRoleId(long contactRoleId) {
		this.contactRoleId = contactRoleId;
	}

	@Override
	public int compareTo(ContactAccountRolePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (contactId < pk.contactId) {
			value = -1;
		}
		else if (contactId > pk.contactId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (accountId < pk.accountId) {
			value = -1;
		}
		else if (accountId > pk.accountId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (contactRoleId < pk.contactRoleId) {
			value = -1;
		}
		else if (contactRoleId > pk.contactRoleId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactAccountRolePK)) {
			return false;
		}

		ContactAccountRolePK pk = (ContactAccountRolePK)object;

		if ((contactId == pk.contactId) && (accountId == pk.accountId) &&
			(contactRoleId == pk.contactRoleId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, contactId);
		hashCode = HashUtil.hash(hashCode, accountId);
		hashCode = HashUtil.hash(hashCode, contactRoleId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(8);

		sb.append("{");

		sb.append("contactId=");

		sb.append(contactId);
		sb.append(", accountId=");

		sb.append(accountId);
		sb.append(", contactRoleId=");

		sb.append(contactRoleId);

		sb.append("}");

		return sb.toString();
	}

}