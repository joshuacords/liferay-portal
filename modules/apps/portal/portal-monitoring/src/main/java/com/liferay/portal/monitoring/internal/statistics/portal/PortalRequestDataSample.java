/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.monitoring.internal.statistics.portal;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.monitoring.internal.BaseDataSample;
import com.liferay.portal.monitoring.internal.MonitorNames;

/**
 * @author Rajesh Thiagarajan
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class PortalRequestDataSample extends BaseDataSample {

	public PortalRequestDataSample(
		long companyId, long groupId, String referer, String remoteAddr,
		String user, String requestURI, String requestURL, String userAgent) {

		_referer = referer;
		_remoteAddr = remoteAddr;
		_requestURL = requestURL;
		_userAgent = userAgent;

		setCompanyId(companyId);
		setGroupId(groupId);
		setName(requestURI);
		setNamespace(MonitorNames.PORTAL);
		setUser(user);
	}

	public String getReferer() {
		return _referer;
	}

	public String getRemoteAddr() {
		return _remoteAddr;
	}

	public String getRequestURL() {
		return _requestURL;
	}

	public int getStatusCode() {
		return _statusCode;
	}

	public String getUserAgent() {
		return _userAgent;
	}

	public void setStatusCode(int statusCode) {
		_statusCode = statusCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{referer=");
		sb.append(_referer);
		sb.append(", remoteAddr=");
		sb.append(_remoteAddr);
		sb.append(", requestURL=");
		sb.append(_requestURL);
		sb.append(", statusCode=");
		sb.append(_statusCode);
		sb.append(", userAgent=");
		sb.append(_userAgent);
		sb.append(", ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private final String _referer;
	private final String _remoteAddr;
	private final String _requestURL;
	private int _statusCode;
	private final String _userAgent;

}