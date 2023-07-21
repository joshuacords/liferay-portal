/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.recommendation.model;

import com.liferay.commerce.machine.learning.recommendation.model.UserCommerceMLRecommendation;

/**
 * @author Riccardo Ferrari
 */
public class UserCommerceMLRecommendationImpl
	extends BaseCommerceMLRecommendationImpl
	implements UserCommerceMLRecommendation {

	@Override
	public long[] getAssetCategoryIds() {
		return _assetCategoryIds;
	}

	@Override
	public void setAssetCategoryIds(long[] assetCategoryIds) {
		_assetCategoryIds = assetCategoryIds;
	}

	private long[] _assetCategoryIds;

}