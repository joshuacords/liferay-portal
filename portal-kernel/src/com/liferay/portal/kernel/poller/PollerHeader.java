/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller;

import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerHeader implements Serializable {

	public PollerHeader(
		long companyId, long userId, long browserKey,
		Map<String, Boolean> portletIdsMap, boolean startPolling) {

		_companyId = companyId;
		_userId = userId;
		_browserKey = browserKey;
		_portletIdsMap = portletIdsMap;
		_startPolling = startPolling;

		_timestamp = System.currentTimeMillis();
	}

	public long getBrowserKey() {
		return _browserKey;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Map<String, Boolean> getPortletIdsMap() {
		return _portletIdsMap;
	}

	public long getTimestamp() {
		return _timestamp;
	}

	public long getUserId() {
		return _userId;
	}

	public boolean isStartPolling() {
		return _startPolling;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{_browserKey=");
		sb.append(_browserKey);
		sb.append(", companyId=");
		sb.append(_companyId);
		sb.append(", portletIdsMap=");
		sb.append(_portletIdsMap);
		sb.append(", startPolling=");
		sb.append(_startPolling);
		sb.append(", timestamp=");
		sb.append(_timestamp);
		sb.append(", userId=");
		sb.append(_userId);
		sb.append("}");

		return sb.toString();
	}

	private static final long serialVersionUID = 1L;

	private final long _browserKey;
	private final long _companyId;
	private final Map<String, Boolean> _portletIdsMap;
	private final boolean _startPolling;
	private final long _timestamp;
	private final long _userId;

}