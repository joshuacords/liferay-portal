/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.kernel.model;

import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author Jorge Ferrer
 */
public interface CustomAttributesDisplay {

	public String getClassName();

	public String getIconCssClass();

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public String getIconPath(ThemeDisplay themeDisplay);

	public String getPortletId();

	public void setClassNameId(long classNameId);

	public void setPortletId(String portletId);

}