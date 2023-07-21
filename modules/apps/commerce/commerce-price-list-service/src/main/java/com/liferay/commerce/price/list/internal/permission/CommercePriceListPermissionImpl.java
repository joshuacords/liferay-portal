/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.permission;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.permission.CommercePriceListPermission;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(immediate = true, service = CommercePriceListPermission.class)
public class CommercePriceListPermissionImpl
	implements CommercePriceListPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommercePriceList commercePriceList, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commercePriceList, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommercePriceList.class.getName(),
				commercePriceList.getCommercePriceListId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commercePriceListId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commercePriceListId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommercePriceList.class.getName(),
				commercePriceListId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommercePriceList commercePriceList, String actionId)
		throws PortalException {

		if (contains(
				permissionChecker, commercePriceList.getCommercePriceListId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commercePriceListId,
			String actionId)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListId);

		if (commercePriceList == null) {
			return false;
		}

		return _contains(permissionChecker, commercePriceList, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] commercePriceListIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commercePriceListIds)) {
			return false;
		}

		for (long commercePriceListId : commercePriceListIds) {
			if (!contains(permissionChecker, commercePriceListId, actionId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommercePriceList commercePriceList, String actionId)
		throws PortalException {

		if (permissionChecker.isCompanyAdmin(
				commercePriceList.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(),
				CommercePriceList.class.getName(),
				commercePriceList.getCommercePriceListId(),
				permissionChecker.getUserId(), actionId) &&
			(commercePriceList.getUserId() == permissionChecker.getUserId())) {

			return true;
		}

		return permissionChecker.hasPermission(
			commercePriceList.getGroupId(), CommercePriceList.class.getName(),
			commercePriceList.getCommercePriceListId(), actionId);
	}

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

}