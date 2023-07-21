/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index.contributor;

import com.liferay.portal.search.spi.model.index.contributor.IndexContributor;

/**
 * @author Adam Brandizzi
 */
public interface IndexContributorReceiver {

	public void addIndexContributor(IndexContributor indexContributor);

	public void removeIndexContributor(IndexContributor indexContributor);

}