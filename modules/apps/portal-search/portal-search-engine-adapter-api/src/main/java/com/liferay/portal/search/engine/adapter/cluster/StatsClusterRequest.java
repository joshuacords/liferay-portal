/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.cluster;

/**
 * @author Dylan Rebelak
 */
public class StatsClusterRequest
	implements ClusterRequest<StatsClusterResponse> {

	public StatsClusterRequest(String[] nodeIds) {
		_nodeIds = nodeIds;
	}

	@Override
	public StatsClusterResponse accept(
		ClusterRequestExecutor clusterRequestExecutor) {

		return clusterRequestExecutor.executeClusterRequest(this);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getNodeIds()}
	 */
	@Deprecated
	@Override
	public String[] getIndexNames() {
		return _nodeIds;
	}

	public String[] getNodeIds() {
		return _nodeIds;
	}

	private final String[] _nodeIds;

}