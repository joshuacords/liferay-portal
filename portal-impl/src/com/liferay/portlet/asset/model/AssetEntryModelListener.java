/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.model;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.model.BaseModelListener;

/**
 * @author     Bryan Engler
 * @deprecated As of Judson (7.1.x), moved to {@link
 *             com.liferay.asset.internal.model.listener.AssetEntryModelListener}
 */
@Deprecated
public class AssetEntryModelListener extends BaseModelListener<AssetEntry> {

	@Override
	public void onAfterCreate(AssetEntry assetEntry) {
		assetEntry.setListable(true);
	}

}