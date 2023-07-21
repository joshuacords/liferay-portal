/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.asset.entry.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.list.model.AssetListEntry;

import java.util.List;

/**
 * @author Sarai Díaz
 */
public interface AssetListAssetEntryProvider {

	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long segmentsEntryId);

	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long segmentsEntryId, int start,
		int end);

	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long[] segmentsEntryIds);

	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long[] segmentsEntryIds, int start,
		int end);

	public default List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long[] segmentsEntryIds, String userId) {

		return getAssetEntries(assetListEntry, segmentsEntryIds);
	}

	public default List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, long[] segmentsEntryIds, String userId,
		int start, int end) {

		return getAssetEntries(assetListEntry, segmentsEntryIds, start, end);
	}

	public int getAssetEntriesCount(
		AssetListEntry assetListEntry, long segmentsEntryId);

	public int getAssetEntriesCount(
		AssetListEntry assetListEntry, long[] segmentsEntryIds);

	public default int getAssetEntriesCount(
		AssetListEntry assetListEntry, long[] segmentsEntryIds, String userId) {

		return getAssetEntriesCount(assetListEntry, segmentsEntryIds);
	}

	public AssetEntryQuery getAssetEntryQuery(
		AssetListEntry assetListEntry, long segmentsEntryId);

	public AssetEntryQuery getAssetEntryQuery(
		AssetListEntry assetListEntry, long[] segmentsEntryIds);

	public default AssetEntryQuery getAssetEntryQuery(
		AssetListEntry assetListEntry, long[] segmentsEntryIds, String userId) {

		return getAssetEntryQuery(assetListEntry, segmentsEntryIds);
	}

}