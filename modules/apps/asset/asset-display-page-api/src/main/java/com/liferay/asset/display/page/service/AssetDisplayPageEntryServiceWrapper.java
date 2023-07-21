/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetDisplayPageEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetDisplayPageEntryService
 * @generated
 */
public class AssetDisplayPageEntryServiceWrapper
	implements AssetDisplayPageEntryService,
			   ServiceWrapper<AssetDisplayPageEntryService> {

	public AssetDisplayPageEntryServiceWrapper(
		AssetDisplayPageEntryService assetDisplayPageEntryService) {

		_assetDisplayPageEntryService = assetDisplayPageEntryService;
	}

	@Override
	public com.liferay.asset.display.page.model.AssetDisplayPageEntry
			addAssetDisplayPageEntry(
				long userId, long groupId, long classNameId, long classPK,
				long layoutPageTemplateEntryId, int type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		return _assetDisplayPageEntryService.addAssetDisplayPageEntry(
			userId, groupId, classNameId, classPK, layoutPageTemplateEntryId,
			type, serviceContext);
	}

	@Override
	public com.liferay.asset.display.page.model.AssetDisplayPageEntry
			addAssetDisplayPageEntry(
				long userId, long groupId, long classNameId, long classPK,
				long layoutPageTemplateEntryId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		return _assetDisplayPageEntryService.addAssetDisplayPageEntry(
			userId, groupId, classNameId, classPK, layoutPageTemplateEntryId,
			serviceContext);
	}

	@Override
	public void deleteAssetDisplayPageEntry(
			long groupId, long classNameId, long classPK)
		throws Exception {

		_assetDisplayPageEntryService.deleteAssetDisplayPageEntry(
			groupId, classNameId, classPK);
	}

	@Override
	public com.liferay.asset.display.page.model.AssetDisplayPageEntry
			fetchAssetDisplayPageEntry(
				long groupId, long classNameId, long classPK)
		throws Exception {

		return _assetDisplayPageEntryService.fetchAssetDisplayPageEntry(
			groupId, classNameId, classPK);
	}

	@Override
	public java.util.List
		<com.liferay.asset.display.page.model.AssetDisplayPageEntry>
			getAssetDisplayPageEntriesByLayoutPageTemplateEntryId(
				long layoutPageTemplateEntryId) {

		return _assetDisplayPageEntryService.
			getAssetDisplayPageEntriesByLayoutPageTemplateEntryId(
				layoutPageTemplateEntryId);
	}

	@Override
	public int getAssetDisplayPageEntriesCountByLayoutPageTemplateEntryId(
		long layoutPageTemplateEntryId) {

		return _assetDisplayPageEntryService.
			getAssetDisplayPageEntriesCountByLayoutPageTemplateEntryId(
				layoutPageTemplateEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetDisplayPageEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.asset.display.page.model.AssetDisplayPageEntry
			updateAssetDisplayPageEntry(
				long assetDisplayPageEntryId, long layoutPageTemplateEntryId,
				int type)
		throws Exception {

		return _assetDisplayPageEntryService.updateAssetDisplayPageEntry(
			assetDisplayPageEntryId, layoutPageTemplateEntryId, type);
	}

	@Override
	public AssetDisplayPageEntryService getWrappedService() {
		return _assetDisplayPageEntryService;
	}

	@Override
	public void setWrappedService(
		AssetDisplayPageEntryService assetDisplayPageEntryService) {

		_assetDisplayPageEntryService = assetDisplayPageEntryService;
	}

	private AssetDisplayPageEntryService _assetDisplayPageEntryService;

}