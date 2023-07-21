/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.action;

import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public interface ActionHandler {

	public void applyAction(
			MDRAction mdrAction, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException;

	public String getEditorJSP();

	public Collection<String> getPropertyNames();

	public String getType();

}