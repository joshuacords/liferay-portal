/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.events;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Raymond Augé
 */
public class LifecycleEvent {

	public LifecycleEvent() {
		this(null, null, null, null);
	}

	public LifecycleEvent(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		this(null, httpServletRequest, httpServletResponse, null);
	}

	public LifecycleEvent(HttpSession session) {
		this(null, null, null, session);
	}

	public LifecycleEvent(String[] ids) {
		this(ids, null, null, null);
	}

	public LifecycleEvent(
		String[] ids, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, HttpSession session) {

		_ids = ids;
		_httpServletRequest = httpServletRequest;
		_httpServletResponse = httpServletResponse;
		_session = session;
	}

	public String[] getIds() {
		return _ids;
	}

	public HttpServletRequest getRequest() {
		return _httpServletRequest;
	}

	public HttpServletResponse getResponse() {
		return _httpServletResponse;
	}

	public HttpSession getSession() {
		return _session;
	}

	private final HttpServletRequest _httpServletRequest;
	private final HttpServletResponse _httpServletResponse;
	private final String[] _ids;
	private final HttpSession _session;

}