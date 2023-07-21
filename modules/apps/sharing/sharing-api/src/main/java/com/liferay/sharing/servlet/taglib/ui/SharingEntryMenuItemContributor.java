/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sharing.servlet.taglib.ui;

import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.sharing.model.SharingEntry;

import java.util.Collection;

/**
 * @author Adolfo Pérez
 */
public interface SharingEntryMenuItemContributor {

	public Collection<MenuItem> getSharingEntryMenuItems(
		SharingEntry sharingEntry, ThemeDisplay themeDisplay);

}