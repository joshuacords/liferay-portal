/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.permission;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.permission.CommerceCatalogPermission;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(immediate = true, service = CommerceCatalogPermission.class)
public class CommerceCatalogPermissionImpl
	implements CommerceCatalogPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceCatalog commerceCatalog, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceCatalog, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceCatalog.class.getName(),
				commerceCatalog.getCommerceCatalogId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceCatalogId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceCatalogId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceCatalog.class.getName(),
				commerceCatalogId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceCatalog commerceCatalog, String actionId)
		throws PortalException {

		if (contains(
				permissionChecker, commerceCatalog.getCommerceCatalogId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceCatalogId,
			String actionId)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			_commerceCatalogLocalService.fetchCommerceCatalog(
				commerceCatalogId);

		if (commerceCatalog == null) {
			return false;
		}

		return _contains(permissionChecker, commerceCatalog, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceCatalogIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceCatalogIds)) {
			return false;
		}

		for (long commerceCatalogId : commerceCatalogIds) {
			if (!contains(permissionChecker, commerceCatalogId, actionId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommerceCatalog commerceCatalog, String actionId)
		throws PortalException {

		if (permissionChecker.isCompanyAdmin(commerceCatalog.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(),
				CommerceCatalog.class.getName(),
				commerceCatalog.getCommerceCatalogId(),
				permissionChecker.getUserId(), actionId) &&
			(commerceCatalog.getUserId() == permissionChecker.getUserId())) {

			return true;
		}

		return permissionChecker.hasPermission(
			commerceCatalog.getGroupId(), CommerceCatalog.class.getName(),
			commerceCatalog.getCommerceCatalogId(), actionId);
	}

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

}