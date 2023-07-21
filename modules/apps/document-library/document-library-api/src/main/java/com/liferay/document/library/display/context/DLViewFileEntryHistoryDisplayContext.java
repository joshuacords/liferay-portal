/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;

/**
 * @author Mauro Mariuzzo
 */
public interface DLViewFileEntryHistoryDisplayContext extends DLDisplayContext {

	public Menu getMenu() throws PortalException;

}