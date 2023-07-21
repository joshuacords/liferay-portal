/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.util;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), use {@link
 *             com.liferay.portal.template.soy.data.SoyHTMLData} to wrap HTML
 *             values
 * @review
 */
@Deprecated
public interface SoyHTMLSanitizer {

	public Object sanitize(String value);

}