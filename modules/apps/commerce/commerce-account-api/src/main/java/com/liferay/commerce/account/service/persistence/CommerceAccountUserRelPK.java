/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service.persistence;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Marco Leo
 * @generated
 */
public class CommerceAccountUserRelPK
	implements Comparable<CommerceAccountUserRelPK>, Serializable {

	public long commerceAccountId;
	public long commerceAccountUserId;

	public CommerceAccountUserRelPK() {
	}

	public CommerceAccountUserRelPK(
		long commerceAccountId, long commerceAccountUserId) {

		this.commerceAccountId = commerceAccountId;
		this.commerceAccountUserId = commerceAccountUserId;
	}

	public long getCommerceAccountId() {
		return commerceAccountId;
	}

	public void setCommerceAccountId(long commerceAccountId) {
		this.commerceAccountId = commerceAccountId;
	}

	public long getCommerceAccountUserId() {
		return commerceAccountUserId;
	}

	public void setCommerceAccountUserId(long commerceAccountUserId) {
		this.commerceAccountUserId = commerceAccountUserId;
	}

	@Override
	public int compareTo(CommerceAccountUserRelPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (commerceAccountId < pk.commerceAccountId) {
			value = -1;
		}
		else if (commerceAccountId > pk.commerceAccountId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (commerceAccountUserId < pk.commerceAccountUserId) {
			value = -1;
		}
		else if (commerceAccountUserId > pk.commerceAccountUserId) {
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

		if (!(object instanceof CommerceAccountUserRelPK)) {
			return false;
		}

		CommerceAccountUserRelPK pk = (CommerceAccountUserRelPK)object;

		if ((commerceAccountId == pk.commerceAccountId) &&
			(commerceAccountUserId == pk.commerceAccountUserId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, commerceAccountId);
		hashCode = HashUtil.hash(hashCode, commerceAccountUserId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("commerceAccountId=");

		sb.append(commerceAccountId);
		sb.append(", commerceAccountUserId=");

		sb.append(commerceAccountUserId);

		sb.append("}");

		return sb.toString();
	}

}