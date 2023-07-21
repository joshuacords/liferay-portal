/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AssetListEntry service. Represents a row in the &quot;AssetListEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.asset.list.model.impl.AssetListEntryImpl")
@ProviderType
public interface AssetListEntry extends AssetListEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.asset.list.model.impl.AssetListEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetListEntry, Long>
		ASSET_LIST_ENTRY_ID_ACCESSOR = new Accessor<AssetListEntry, Long>() {

			@Override
			public Long get(AssetListEntry assetListEntry) {
				return assetListEntry.getAssetListEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetListEntry> getTypeClass() {
				return AssetListEntry.class;
			}

		};

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntries(AssetListEntry,
	 long)}
	 */
	@Deprecated
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry>
		getAssetEntries(long segmentsEntryId);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntries(AssetListEntry,
	 long, int, int)}
	 */
	@Deprecated
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry>
		getAssetEntries(long segmentsEntryId, int start, int end);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntries(AssetListEntry,
	 long[])}
	 */
	@Deprecated
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry>
		getAssetEntries(long[] segmentsEntryIds);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntries(AssetListEntry,
	 long[], int, int)}
	 */
	@Deprecated
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry>
		getAssetEntries(long[] segmentsEntryIds, int start, int end);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntriesCount(
	 AssetListEntry, long)}
	 */
	@Deprecated
	public int getAssetEntriesCount(long segmentsEntryId);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntriesCount(
	 AssetListEntry, long[])}
	 */
	@Deprecated
	public int getAssetEntriesCount(long[] segmentsEntryIds);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntryQuery(
	 AssetListEntry, long)}
	 */
	@Deprecated
	public com.liferay.asset.kernel.service.persistence.AssetEntryQuery
		getAssetEntryQuery(long segmentsEntryId);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 AssetListAssetEntryProvider#getAssetEntryQuery(
	 AssetListEntry, long[])}
	 */
	@Deprecated
	public com.liferay.asset.kernel.service.persistence.AssetEntryQuery
		getAssetEntryQuery(long[] segmentsEntryIds);

	public String getTypeLabel();

	public String getTypeSettings(long segmentsEntryId);

}