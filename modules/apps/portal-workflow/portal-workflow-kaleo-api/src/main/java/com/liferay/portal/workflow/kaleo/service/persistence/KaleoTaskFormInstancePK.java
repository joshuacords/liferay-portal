/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoTaskFormInstancePK implements Comparable<KaleoTaskFormInstancePK>,
	Serializable {
	public long kaleoTaskFormInstanceId;
	public long kaleoTaskFormId;

	public KaleoTaskFormInstancePK() {
	}

	public KaleoTaskFormInstancePK(long kaleoTaskFormInstanceId,
		long kaleoTaskFormId) {
		this.kaleoTaskFormInstanceId = kaleoTaskFormInstanceId;
		this.kaleoTaskFormId = kaleoTaskFormId;
	}

	public long getKaleoTaskFormInstanceId() {
		return kaleoTaskFormInstanceId;
	}

	public void setKaleoTaskFormInstanceId(long kaleoTaskFormInstanceId) {
		this.kaleoTaskFormInstanceId = kaleoTaskFormInstanceId;
	}

	public long getKaleoTaskFormId() {
		return kaleoTaskFormId;
	}

	public void setKaleoTaskFormId(long kaleoTaskFormId) {
		this.kaleoTaskFormId = kaleoTaskFormId;
	}

	@Override
	public int compareTo(KaleoTaskFormInstancePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (kaleoTaskFormInstanceId < pk.kaleoTaskFormInstanceId) {
			value = -1;
		}
		else if (kaleoTaskFormInstanceId > pk.kaleoTaskFormInstanceId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (kaleoTaskFormId < pk.kaleoTaskFormId) {
			value = -1;
		}
		else if (kaleoTaskFormId > pk.kaleoTaskFormId) {
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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskFormInstancePK)) {
			return false;
		}

		KaleoTaskFormInstancePK pk = (KaleoTaskFormInstancePK)obj;

		if ((kaleoTaskFormInstanceId == pk.kaleoTaskFormInstanceId) &&
				(kaleoTaskFormId == pk.kaleoTaskFormId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, kaleoTaskFormInstanceId);
		hashCode = HashUtil.hash(hashCode, kaleoTaskFormId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("kaleoTaskFormInstanceId");
		sb.append(StringPool.EQUAL);
		sb.append(kaleoTaskFormInstanceId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("kaleoTaskFormId");
		sb.append(StringPool.EQUAL);
		sb.append(kaleoTaskFormId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}