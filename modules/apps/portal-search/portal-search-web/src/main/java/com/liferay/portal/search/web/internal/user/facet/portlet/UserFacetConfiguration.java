/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.user.facet.portlet;

/**
 * @author Lino Alves
 */
public interface UserFacetConfiguration {

	public int getFrequencyThreshold();

	public int getMaxTerms();

	public void setFrequencyThreshold(int frequencyThreshold);

	public void setMaxTerms(int maxTerms);

}