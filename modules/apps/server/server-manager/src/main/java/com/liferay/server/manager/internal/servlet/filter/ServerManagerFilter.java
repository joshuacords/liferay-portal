/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.servlet.filter;

import com.liferay.portal.kernel.servlet.PortalClassLoaderFilter;

import javax.servlet.Filter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"filter.init.basic_auth=true",
		"filter.init.filter-class=com.liferay.portal.servlet.filters.secure.SecureFilter",
		"filter.init.portal_property_prefix=server.manager.servlet.",
		"osgi.http.whiteboard.filter.name=com.liferay.server.manager.internal.servlet.filter.ServerManagerFilter",
		"osgi.http.whiteboard.filter.pattern=/server-manager/*"
	},
	service = Filter.class
)
public class ServerManagerFilter extends PortalClassLoaderFilter {
}