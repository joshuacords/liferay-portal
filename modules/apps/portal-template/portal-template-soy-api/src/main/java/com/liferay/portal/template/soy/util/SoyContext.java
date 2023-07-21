/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.util;

import com.liferay.petra.function.UnsafeSupplier;

import java.util.Map;

/**
 * @author Bruno Basto
 */
public interface SoyContext extends Map<String, Object> {

	public SoyContext clearInjectedData();

	public SoyContext put(String key, Object value);

	public SoyContext put(
		String key, UnsafeSupplier<?, Exception> unsafeSupplier);

	/**
	 * Put an HTML parameter in the SoyContext container. This is the same as
	 * calling {@link SoyContext#put(Object, Object)} with a {@link
	 * com.liferay.portal.template.soy.data.SoyHTMLData} value.
	 *
	 * @param  key
	 * @param  value
	 * @see    com.liferay.portal.template.soy.data.SoyHTMLData
	 * @review
	 */
	public SoyContext putHTML(String key, String value);

	public SoyContext putInjectedData(String key, Object value);

	public SoyContext removeInjectedData(String key);

}