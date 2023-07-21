/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.index;

import org.elasticsearch.client.AdminClient;

/**
 * @author Michael C. Han
 */
public interface IndexFactory {

	public void createIndices(AdminClient adminClient, long companyId);

	public void deleteIndices(AdminClient adminClient, long companyId);

}