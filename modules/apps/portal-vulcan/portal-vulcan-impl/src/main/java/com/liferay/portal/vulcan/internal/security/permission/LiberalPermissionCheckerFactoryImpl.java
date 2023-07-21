/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.security.permission;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matthew Tambara
 */
@Component(
	property = {
		"permission.checker.type=liberal", "service.ranking:Integer=-100"
	},
	service = PermissionCheckerFactory.class
)
public class LiberalPermissionCheckerFactoryImpl
	implements PermissionCheckerFactory {

	@Override
	public PermissionChecker create(User user) {
		PermissionChecker permissionChecker = new LiberalPermissionChecker();

		permissionChecker.init(user);

		return permissionChecker;
	}

}