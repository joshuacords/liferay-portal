/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.alloy.mvc.json.web.service;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyPortlet;
import com.liferay.portal.kernel.json.JSONSerializable;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author Ethan Bustad
 */
public interface AlloyControllerInvoker {

	public JSONSerializable invokeAlloyController(
			String lifecycle, Object... parameters)
		throws Exception;

	public void setProperties(
		ThemeDisplay themeDisplay, AlloyPortlet alloyPortlet, Portlet portlet,
		String controller, Class<? extends AlloyController> controllerClass);

}