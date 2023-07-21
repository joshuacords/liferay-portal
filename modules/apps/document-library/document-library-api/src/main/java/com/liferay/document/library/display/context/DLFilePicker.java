/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.display.context;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Iván Zaera
 */
public interface DLFilePicker {

	public String getDescriptionFieldName();

	public String getIconFieldName();

	public String getJavaScript() throws PortalException;

	public String getJavaScriptModuleName();

	public String getOnClickCallback();

	public String getTitleFieldName();

}