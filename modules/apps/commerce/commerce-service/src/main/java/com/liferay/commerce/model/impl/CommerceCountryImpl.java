/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.commerce.util.comparator.CommerceRegionPriorityComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCountryImpl extends CommerceCountryBaseImpl {

	public CommerceCountryImpl() {
	}

	@Override
	public List<CommerceRegion> getCommerceRegions() {
		return CommerceRegionLocalServiceUtil.getCommerceRegions(
			getCommerceCountryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new CommerceRegionPriorityComparator());
	}

}