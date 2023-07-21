/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AssetTagStats service. Represents a row in the &quot;AssetTagStats&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagStatsModel
 * @deprecated As of Judson (7.1.x), replaced by {@link
 com.liferay.asset.tag.stats.model.impl.AssetTagStatsImpl}
 * @generated
 */
@Deprecated
@ImplementationClassName(
	"com.liferay.portlet.asset.model.impl.AssetTagStatsImpl"
)
@ProviderType
public interface AssetTagStats extends AssetTagStatsModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portlet.asset.model.impl.AssetTagStatsImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetTagStats, Long> TAG_STATS_ID_ACCESSOR =
		new Accessor<AssetTagStats, Long>() {

			@Override
			public Long get(AssetTagStats assetTagStats) {
				return assetTagStats.getTagStatsId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetTagStats> getTypeClass() {
				return AssetTagStats.class;
			}

		};

}