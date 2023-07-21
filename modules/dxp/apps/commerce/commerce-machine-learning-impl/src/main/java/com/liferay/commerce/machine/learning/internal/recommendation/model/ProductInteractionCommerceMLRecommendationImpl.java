/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.recommendation.model;

import com.liferay.commerce.machine.learning.recommendation.model.ProductInteractionCommerceMLRecommendation;

/**
 * @author Riccardo Ferrari
 */
public class ProductInteractionCommerceMLRecommendationImpl
	extends BaseCommerceMLRecommendationImpl
	implements ProductInteractionCommerceMLRecommendation {

	@Override
	public int getRank() {
		return _rank;
	}

	@Override
	public void setRank(int rank) {
		_rank = rank;
	}

	private int _rank;

}