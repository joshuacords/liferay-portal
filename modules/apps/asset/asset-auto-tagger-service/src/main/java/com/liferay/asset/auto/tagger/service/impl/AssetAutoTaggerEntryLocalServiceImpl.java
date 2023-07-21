/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.auto.tagger.service.impl;

import com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry;
import com.liferay.asset.auto.tagger.service.base.AssetAutoTaggerEntryLocalServiceBaseImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.portal.aop.AopService;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = "model.class.name=com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry",
	service = AopService.class
)
public class AssetAutoTaggerEntryLocalServiceImpl
	extends AssetAutoTaggerEntryLocalServiceBaseImpl {

	@Override
	public AssetAutoTaggerEntry addAssetAutoTaggerEntry(
		AssetEntry assetEntry, AssetTag assetTag) {

		AssetAutoTaggerEntry existingAssetAutoTaggerEntry =
			assetAutoTaggerEntryPersistence.fetchByA_A(
				assetEntry.getEntryId(), assetTag.getTagId());

		if (existingAssetAutoTaggerEntry != null) {
			return existingAssetAutoTaggerEntry;
		}

		long assetAutoTaggerEntryId = counterLocalService.increment();

		AssetAutoTaggerEntry assetAutoTaggerEntry =
			assetAutoTaggerEntryPersistence.create(assetAutoTaggerEntryId);

		assetAutoTaggerEntry.setCompanyId(assetEntry.getCompanyId());

		assetAutoTaggerEntry.setGroupId(assetEntry.getGroupId());
		assetAutoTaggerEntry.setAssetEntryId(assetEntry.getEntryId());
		assetAutoTaggerEntry.setAssetTagId(assetTag.getTagId());

		return assetAutoTaggerEntryPersistence.update(assetAutoTaggerEntry);
	}

	@Override
	public AssetAutoTaggerEntry fetchAssetAutoTaggerEntry(
		long assetEntryId, long assetTagId) {

		return assetAutoTaggerEntryPersistence.fetchByA_A(
			assetEntryId, assetTagId);
	}

	@Override
	public List<AssetAutoTaggerEntry> getAssetAutoTaggerEntries(
		AssetEntry assetEntry) {

		return assetAutoTaggerEntryPersistence.findByAssetEntryId(
			assetEntry.getEntryId());
	}

	@Override
	public List<AssetAutoTaggerEntry> getAssetAutoTaggerEntries(
		AssetTag assetTag) {

		return assetAutoTaggerEntryPersistence.findByAssetTagId(
			assetTag.getTagId());
	}

}