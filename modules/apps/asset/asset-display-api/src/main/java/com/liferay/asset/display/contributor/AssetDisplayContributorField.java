/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.contributor;

import java.util.Locale;

/**
 * @author     Jürgen Kappler
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             InfoDisplayContributorField}
 */
@Deprecated
public interface AssetDisplayContributorField<T> {

	public String getKey();

	public String getLabel(Locale locale);

	public String getType();

	public Object getValue(T model, Locale locale);

}