/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller.comet;

import com.liferay.portal.kernel.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseCometRequest implements CometRequest {

	public BaseCometRequest(HttpServletRequest httpServletRequest) {
		_httpServletRequest = httpServletRequest;

		setRequest(httpServletRequest);
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getPathInfo() {
		return _pathInfo;
	}

	@Override
	public HttpServletRequest getRequest() {
		return _httpServletRequest;
	}

	@Override
	public long getTimestamp() {
		return _timestamp;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setPathInfo(String pathInfo) {
		_pathInfo = pathInfo;
	}

	public void setRequest(HttpServletRequest httpServletRequest) {
		setCompanyId(PortalUtil.getCompanyId(httpServletRequest));
		setPathInfo(httpServletRequest.getPathInfo());
		setUserId(PortalUtil.getUserId(httpServletRequest));
	}

	@Override
	public void setTimestamp(long timestamp) {
		_timestamp = timestamp;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _companyId;
	private final HttpServletRequest _httpServletRequest;
	private String _pathInfo;
	private long _timestamp = System.currentTimeMillis();
	private long _userId;

}