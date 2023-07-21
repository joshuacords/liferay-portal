/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.web.internal.auth.publicpath;

import org.osgi.service.component.annotations.Component;

/**
 * @author Miguel Pastor
 */
@Component(
	immediate = true, property = "auth.public.path=/polls/view_chart",
	service = Object.class
)
public class AuthPublicPath {
}