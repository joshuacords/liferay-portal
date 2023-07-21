/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.EditPortletProvider;
import com.liferay.portal.kernel.portlet.ManagePortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"model.class.name=com.liferay.document.library.kernel.model.DLFileEntryType",
		"model.class.name=com.liferay.portal.kernel.repository.model.FileEntry",
		"model.class.name=com.liferay.portal.kernel.repository.model.FileShortcut",
		"model.class.name=com.liferay.portal.kernel.repository.model.Folder",
		"service.ranking:Integer=100"
	},
	service = {EditPortletProvider.class, ViewPortletProvider.class}
)
public class DLEditPortletProvider
	extends BasePortletProvider
	implements EditPortletProvider, ManagePortletProvider, ViewPortletProvider {

	@Override
	public String getPortletName() {
		return DLPortletKeys.DOCUMENT_LIBRARY;
	}

}