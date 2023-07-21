/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.contributor;

import java.util.List;

/**
 * @author     Lance Ji
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             InfoDisplayContributorFieldTracker}
 */
@Deprecated
public interface AssetDisplayContributorTracker {

	public AssetDisplayContributor getAssetDisplayContributor(String className);

	public AssetDisplayContributor
		getAssetDisplayContributorByAssetURLSeparator(String assetURLSeparator);

	public List<AssetDisplayContributor> getAssetDisplayContributors();

}