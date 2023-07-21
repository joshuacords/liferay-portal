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
public class ContactTeamRolePK
	implements Comparable<ContactTeamRolePK>, Serializable {

	public long contactId;
	public long teamId;
	public long contactRoleId;

	public ContactTeamRolePK() {
	}

	public ContactTeamRolePK(long contactId, long teamId, long contactRoleId) {
		this.contactId = contactId;
		this.teamId = teamId;
		this.contactRoleId = contactRoleId;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public long getContactRoleId() {
		return contactRoleId;
	}

	public void setContactRoleId(long contactRoleId) {
		this.contactRoleId = contactRoleId;
	}

	@Override
	public int compareTo(ContactTeamRolePK pk) {
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

		if (teamId < pk.teamId) {
			value = -1;
		}
		else if (teamId > pk.teamId) {
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

		if (!(object instanceof ContactTeamRolePK)) {
			return false;
		}

		ContactTeamRolePK pk = (ContactTeamRolePK)object;

		if ((contactId == pk.contactId) && (teamId == pk.teamId) &&
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
		hashCode = HashUtil.hash(hashCode, teamId);
		hashCode = HashUtil.hash(hashCode, contactRoleId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(8);

		sb.append("{");

		sb.append("contactId=");

		sb.append(contactId);
		sb.append(", teamId=");

		sb.append(teamId);
		sb.append(", contactRoleId=");

		sb.append(contactRoleId);

		sb.append("}");

		return sb.toString();
	}

}