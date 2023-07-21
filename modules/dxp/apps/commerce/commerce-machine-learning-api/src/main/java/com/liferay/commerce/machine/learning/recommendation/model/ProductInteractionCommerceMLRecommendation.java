/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.recommendation.model;

/**
 * @author Riccardo Ferrari
 */
public interface ProductInteractionCommerceMLRecommendation
	extends CommerceMLRecommendation {

	public int getRank();

	public void setRank(int rank);

}