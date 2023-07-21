/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.recommendation.service;

import com.liferay.commerce.machine.learning.recommendation.model.UserCommerceMLRecommendation;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public interface UserCommerceMLRecommendationService {

	public UserCommerceMLRecommendation addUserCommerceMLRecommendation(
			UserCommerceMLRecommendation userCommerceMLRecommendation)
		throws PortalException;

	public UserCommerceMLRecommendation create();

	public List<UserCommerceMLRecommendation> getUserCommerceMLRecommendations(
			long companyId, long commerceAccountId, long[] assetCategoryIds)
		throws PortalException;

}