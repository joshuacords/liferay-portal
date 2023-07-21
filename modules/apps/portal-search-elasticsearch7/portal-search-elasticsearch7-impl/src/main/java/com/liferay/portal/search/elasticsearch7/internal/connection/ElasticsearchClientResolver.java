/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import org.elasticsearch.client.Client;

/**
 * @author André de Oliveira
 */
public interface ElasticsearchClientResolver {

	public Client getClient();

	public Client getClient(boolean preferLocalCluster);

	public Client getClient(String connectionId, boolean preferLocalCluster);

}