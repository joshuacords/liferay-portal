/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Wilberforce (7.0.x), , replaced by {@link
 *             com.liferay.portal.kernel.search.generic.TermQueryImpl}
 */
@Deprecated
public interface TermQueryFactory {

	public TermQuery create(String field, long value);

	public TermQuery create(String field, String value);

}