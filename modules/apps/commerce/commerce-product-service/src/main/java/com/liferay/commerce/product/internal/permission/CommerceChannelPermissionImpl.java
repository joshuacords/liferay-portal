/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.permission;

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.permission.CommerceChannelPermission;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(immediate = true, service = CommerceChannelPermission.class)
public class CommerceChannelPermissionImpl
	implements CommerceChannelPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceChannel commerceChannel, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceChannel, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceChannel.class.getName(),
				commerceChannel.getCommerceChannelId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceChannelId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceChannelId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceChannel.class.getName(),
				commerceChannelId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceChannel commerceChannel, String actionId)
		throws PortalException {

		if (contains(
				permissionChecker, commerceChannel.getCommerceChannelId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceChannelId,
			String actionId)
		throws PortalException {

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannel(
				commerceChannelId);

		if (commerceChannel == null) {
			return false;
		}

		return _contains(permissionChecker, commerceChannel, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceChannelIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceChannelIds)) {
			return false;
		}

		for (long commerceChannelId : commerceChannelIds) {
			if (!contains(permissionChecker, commerceChannelId, actionId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommerceChannel commerceChannel, String actionId)
		throws PortalException {

		if (permissionChecker.isCompanyAdmin(commerceChannel.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(),
				CommerceChannel.class.getName(),
				commerceChannel.getCommerceChannelId(),
				permissionChecker.getUserId(), actionId) &&
			(commerceChannel.getUserId() == permissionChecker.getUserId())) {

			return true;
		}

		Group group = _commerceChannelLocalService.getCommerceChannelGroup(
			commerceChannel.getCommerceChannelId());

		return permissionChecker.hasPermission(
			group, CommerceChannel.class.getName(),
			commerceChannel.getCommerceChannelId(), actionId);
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

}