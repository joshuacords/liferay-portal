/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.machine.learning.internal.util.v1_0;

import com.liferay.commerce.account.permission.CommerceAccountPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(service = CommerceAccountPermissionHelper.class)
public class CommerceAccountPermissionHelper {

	public List<Long> filterCommerceAccountIds(List<Long> accountIds) {
		Stream<Long> accountIdsStream = accountIds.stream();

		return accountIdsStream.filter(
			this::_contains
		).collect(
			Collectors.toList()
		);
	}

	private boolean _contains(long commerceAccountId) {
		try {
			return _commerceAccountPermission.contains(
				_getPermissionChecker(), commerceAccountId, ActionKeys.VIEW);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return false;
		}
	}

	private PermissionChecker _getPermissionChecker()
		throws PrincipalException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			throw new PrincipalException("PermissionChecker not initialized");
		}

		return permissionChecker;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountPermissionHelper.class);

	@Reference
	private CommerceAccountPermission _commerceAccountPermission;

}