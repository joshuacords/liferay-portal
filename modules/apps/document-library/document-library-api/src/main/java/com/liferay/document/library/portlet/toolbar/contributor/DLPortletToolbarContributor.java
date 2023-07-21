/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.portlet.toolbar.contributor;

import com.liferay.portal.kernel.portlet.toolbar.contributor.PortletToolbarContributor;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Adolfo Pérez
 */
public interface DLPortletToolbarContributor extends PortletToolbarContributor {

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public default List<MenuItem> getPortletTitleAddDocumentMenuItems(
		Folder folder, ThemeDisplay themeDisplay,
		PortletRequest portletRequest) {

		return Collections.emptyList();
	}

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public default MenuItem getPortletTitleAddFolderMenuItem(
		ThemeDisplay themeDisplay, PortletRequest portletRequest,
		Folder folder) {

		return null;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public default MenuItem getPortletTitleAddMultipleDocumentsMenuItem(
		ThemeDisplay themeDisplay, PortletRequest portletRequest,
		Folder folder) {

		return null;
	}

}