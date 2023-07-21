/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.util.ObjectValuePair;

import java.io.Closeable;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 */
public class ThreadLocalFacadeServletRequestWrapperUtil {

	public static <T extends ServletRequest> ObjectValuePair<T, Closeable>
		inject(T servletRequest) {

		ServletRequestWrapper previousServletRequestWrapper = null;
		ServletRequest currentServletRequest = servletRequest;

		while (currentServletRequest != null) {
			if (!(currentServletRequest instanceof ServletRequestWrapper)) {
				break;
			}

			Class<?> clazz = currentServletRequest.getClass();

			if (_stopperClassNames.contains(clazz.getName())) {
				break;
			}

			previousServletRequestWrapper =
				(ServletRequestWrapper)currentServletRequest;

			ServletRequestWrapper servletRequestWrapper =
				(ServletRequestWrapper)currentServletRequest;

			currentServletRequest = servletRequestWrapper.getRequest();
		}

		ServletRequestWrapper servletRequestWrapper = null;

		if (currentServletRequest instanceof HttpServletRequest) {
			servletRequestWrapper =
				new ThreadLocalFacadeHttpServletRequestWrapper(
					previousServletRequestWrapper,
					(HttpServletRequest)currentServletRequest);
		}
		else {
			servletRequestWrapper = new ThreadLocalFacadeServletRequestWrapper(
				previousServletRequestWrapper, currentServletRequest);
		}

		if (previousServletRequestWrapper != null) {
			previousServletRequestWrapper.setRequest(servletRequestWrapper);
		}
		else {
			servletRequest = (T)servletRequestWrapper;
		}

		Closeable closeable = (Closeable)servletRequestWrapper;

		return new ObjectValuePair<>(servletRequest, closeable);
	}

	public void setStopperClassNames(Set<String> stopperClassNames) {
		_stopperClassNames.clear();

		_stopperClassNames.addAll(stopperClassNames);
	}

	private static final Set<String> _stopperClassNames = new HashSet<>();

}