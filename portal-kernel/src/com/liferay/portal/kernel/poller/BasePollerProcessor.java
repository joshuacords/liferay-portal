/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BasePollerProcessor implements PollerProcessor {

	@Override
	public PollerResponse receive(PollerRequest pollerRequest)
		throws PollerException {

		try {
			return doReceive(pollerRequest);
		}
		catch (Exception exception) {
			throw new PollerException(exception);
		}
	}

	@Override
	public void send(PollerRequest pollerRequest) throws PollerException {
		try {
			doSend(pollerRequest);
		}
		catch (Exception exception) {
			throw new PollerException(exception);
		}
	}

	protected abstract PollerResponse doReceive(PollerRequest pollerRequest)
		throws Exception;

	protected abstract void doSend(PollerRequest pollerRequest)
		throws Exception;

	protected boolean getBoolean(PollerRequest pollerRequest, String name) {
		return getBoolean(pollerRequest, name, GetterUtil.DEFAULT_BOOLEAN);
	}

	protected boolean getBoolean(
		PollerRequest pollerRequest, String name, boolean defaultValue) {

		Map<String, String> parameterMap = pollerRequest.getParameterMap();

		return GetterUtil.getBoolean(parameterMap.get(name), defaultValue);
	}

	protected double getDouble(PollerRequest pollerRequest, String name) {
		return getDouble(pollerRequest, name, -1);
	}

	protected double getDouble(
		PollerRequest pollerRequest, String name, double defaultValue) {

		Map<String, String> parameterMap = pollerRequest.getParameterMap();

		return GetterUtil.getDouble(parameterMap.get(name), defaultValue);
	}

	protected int getInteger(PollerRequest pollerRequest, String name) {
		return getInteger(pollerRequest, name, -1);
	}

	protected int getInteger(
		PollerRequest pollerRequest, String name, int defaultValue) {

		Map<String, String> parameterMap = pollerRequest.getParameterMap();

		return GetterUtil.getInteger(parameterMap.get(name), defaultValue);
	}

	protected long getLong(PollerRequest pollerRequest, String name) {
		return getLong(pollerRequest, name, -1);
	}

	protected long getLong(
		PollerRequest pollerRequest, String name, long defaultValue) {

		Map<String, String> parameterMap = pollerRequest.getParameterMap();

		return GetterUtil.getLong(parameterMap.get(name), defaultValue);
	}

	protected String getString(PollerRequest pollerRequest, String name) {
		return getString(pollerRequest, name, null);
	}

	protected String getString(
		PollerRequest pollerRequest, String name, String defaultValue) {

		Map<String, String> parameterMap = pollerRequest.getParameterMap();

		return GetterUtil.getString(parameterMap.get(name), defaultValue);
	}

}