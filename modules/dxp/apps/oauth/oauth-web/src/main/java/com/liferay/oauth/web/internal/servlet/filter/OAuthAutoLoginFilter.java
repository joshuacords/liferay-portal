/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.web.internal.servlet.filter;

import com.liferay.portal.servlet.filters.autologin.AutoLoginFilter;

import javax.servlet.Filter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.filter.name=com.liferay.oauth.web.internal.servlet.filter.OAuthAutoLoginFilter",
		"osgi.http.whiteboard.filter.pattern=/c/portal/oauth/*"
	},
	service = Filter.class
)
public class OAuthAutoLoginFilter extends AutoLoginFilter {
}