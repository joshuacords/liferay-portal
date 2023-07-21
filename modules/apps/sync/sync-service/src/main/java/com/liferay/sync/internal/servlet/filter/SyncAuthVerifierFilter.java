/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.servlet.filter;

import com.liferay.portal.servlet.filters.authverifier.AuthVerifierFilter;

import javax.servlet.Filter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"filter.init.auth.verifier.OAuthVerifier.urls.includes=/sync/download/*",
		"filter.init.auth.verifier.SyncAuthVerifier.urls.includes=/sync/download/*",
		"osgi.http.whiteboard.filter.name=com.liferay.sync.internal.servlet.filter.SyncAuthVerifierFilter",
		"osgi.http.whiteboard.filter.pattern=/sync/download/*"
	},
	service = Filter.class
)
public class SyncAuthVerifierFilter extends AuthVerifierFilter {
}