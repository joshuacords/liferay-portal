/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.directory.web.internal.portlet;

import com.liferay.admin.kernel.util.PortalDirectoryApplicationType;
import com.liferay.directory.web.internal.constants.DirectoryPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(
	immediate = true,
	property = "model.class.name=" + PortalDirectoryApplicationType.PortalDirectory.CLASS_NAME,
	service = ViewPortletProvider.class
)
public class PortalDirectoryViewPortletProvider
	extends BasePortletProvider implements ViewPortletProvider {

	@Override
	public String getPortletName() {
		return DirectoryPortletKeys.DIRECTORY;
	}

}