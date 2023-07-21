/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.service.impl;

import com.liferay.asset.list.model.AssetListEntryUsage;
import com.liferay.asset.list.service.base.AssetListEntryUsageLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(
	property = "model.class.name=com.liferay.asset.list.model.AssetListEntryUsage",
	service = AopService.class
)
public class AssetListEntryUsageLocalServiceImpl
	extends AssetListEntryUsageLocalServiceBaseImpl {

	@Override
	public AssetListEntryUsage addAssetListEntryUsage(
			long userId, long groupId, long assetListEntryId, long classNameId,
			long classPK, String portletId, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long assetListEntryUsageId = counterLocalService.increment();

		AssetListEntryUsage assetListEntryUsage =
			assetListEntryUsagePersistence.create(assetListEntryUsageId);

		assetListEntryUsage.setUuid(serviceContext.getUuid());
		assetListEntryUsage.setGroupId(groupId);
		assetListEntryUsage.setCompanyId(user.getCompanyId());
		assetListEntryUsage.setUserId(userId);
		assetListEntryUsage.setUserName(user.getFullName());
		assetListEntryUsage.setCreateDate(
			serviceContext.getCreateDate(new Date()));
		assetListEntryUsage.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		assetListEntryUsage.setAssetListEntryId(assetListEntryId);
		assetListEntryUsage.setClassNameId(classNameId);
		assetListEntryUsage.setClassPK(classPK);
		assetListEntryUsage.setPortletId(portletId);

		return assetListEntryUsagePersistence.update(assetListEntryUsage);
	}

	@Override
	public AssetListEntryUsage fetchAssetListEntryUsage(
		long classNameId, long classPK, String portletId) {

		return assetListEntryUsagePersistence.fetchByC_C_P(
			classNameId, classPK, portletId);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId) {

		return assetListEntryUsagePersistence.findByAssetListEntryId(
			assetListEntryId);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return assetListEntryUsagePersistence.findByAssetListEntryId(
			assetListEntryId, start, end, orderByComparator);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, long classNameId) {

		return assetListEntryUsagePersistence.findByA_C(
			assetListEntryId, classNameId);
	}

	@Override
	public List<AssetListEntryUsage> getAssetListEntryUsages(
		long assetListEntryId, long classNameId, int start, int end,
		OrderByComparator<AssetListEntryUsage> orderByComparator) {

		return assetListEntryUsagePersistence.findByA_C(
			assetListEntryId, classNameId, start, end, orderByComparator);
	}

	@Override
	public int getAssetListEntryUsagesCount(long assetListEntryId) {
		return assetListEntryUsagePersistence.countByAssetListEntryId(
			assetListEntryId);
	}

	@Override
	public int getAssetListEntryUsagesCount(
		long assetListEntryId, long classNameId) {

		return assetListEntryUsagePersistence.countByA_C(
			assetListEntryId, classNameId);
	}

}