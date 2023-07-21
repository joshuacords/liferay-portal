/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.list.renderer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jorge Ferrer
 */
public interface InfoListRenderer<T> {

	public default String getKey() {
		Class<?> clazz = getClass();

		return clazz.getName();
	}

	public void render(
		List<T> list, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

}