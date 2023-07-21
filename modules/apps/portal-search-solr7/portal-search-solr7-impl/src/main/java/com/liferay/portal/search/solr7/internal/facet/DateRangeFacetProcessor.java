/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr7.internal.facet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = "class.name=com.liferay.portal.kernel.search.facet.DateRangeFacet",
	service = FacetProcessor.class
)
public class DateRangeFacetProcessor extends RangeFacetProcessor {
}