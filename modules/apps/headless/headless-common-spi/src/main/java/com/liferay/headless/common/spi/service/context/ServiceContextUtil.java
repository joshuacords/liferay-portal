/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.common.spi.service.context;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Víctor Galán
 */
public class ServiceContextUtil {

	public static ServiceContext createServiceContext(
		long groupId, String viewableBy) {

		return createServiceContext(
			new Long[0], new String[0], groupId, viewableBy);
	}

	public static ServiceContext createServiceContext(
		Long[] assetCategoryIds, String[] assetTagNames, Long groupId,
		String viewableBy) {

		return createServiceContext(
			assetCategoryIds, assetTagNames, null, groupId, viewableBy);
	}

	public static ServiceContext createServiceContext(
		Long[] assetCategoryIds, String[] assetTagNames,
		Map<String, Serializable> expandoBridgeAttributes, Long groupId,
		String viewableBy) {

		ServiceContext serviceContext = new ServiceContext();

		if (StringUtil.equalsIgnoreCase(viewableBy, "anyone")) {
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
		}
		else if (StringUtil.equalsIgnoreCase(viewableBy, "members")) {
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);
		}
		else {
			serviceContext.setAddGroupPermissions(false);
			serviceContext.setAddGuestPermissions(false);
		}

		if (assetCategoryIds != null) {
			serviceContext.setAssetCategoryIds(
				ArrayUtil.toArray(assetCategoryIds));
		}

		if (assetTagNames != null) {
			serviceContext.setAssetTagNames(assetTagNames);
		}

		if (expandoBridgeAttributes != null) {
			serviceContext.setExpandoBridgeAttributes(expandoBridgeAttributes);
		}

		if (groupId != null) {
			serviceContext.setScopeGroupId(groupId);
		}

		return serviceContext;
	}

	public static ServiceContext createServiceContext(
		Map<String, Serializable> expandoBridgeAttributes, long groupId,
		String viewableBy) {

		return createServiceContext(
			new Long[0], new String[0], expandoBridgeAttributes, groupId,
			viewableBy);
	}

}