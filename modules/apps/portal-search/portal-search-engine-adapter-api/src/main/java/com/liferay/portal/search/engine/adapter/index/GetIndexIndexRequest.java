/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.index;

/**
 * @author Michael C. Han
 */
public class GetIndexIndexRequest
	implements IndexRequest<GetIndexIndexResponse> {

	public GetIndexIndexRequest(String indexName) {
		setPreferLocalCluster(true);

		_indexName = indexName;
	}

	@Override
	public GetIndexIndexResponse accept(
		IndexRequestExecutor indexRequestExecutor) {

		return indexRequestExecutor.executeIndexRequest(this);
	}

	public String getConnectionId() {
		return _connectionId;
	}

	@Override
	public String[] getIndexNames() {
		return new String[] {_indexName};
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement. This method
	 *             should not be in the parent interface.  Only certain
	 *             IndexRequests work with mappings.
	 */
	@Deprecated
	@Override
	public String getMappingName() {
		throw new UnsupportedOperationException();
	}

	public boolean isPreferLocalCluster() {
		return _preferLocalCluster;
	}

	public void setConnectionId(String connectionId) {
		_connectionId = connectionId;
	}

	public void setPreferLocalCluster(boolean preferLocalCluster) {
		_preferLocalCluster = preferLocalCluster;
	}

	private String _connectionId;
	private final String _indexName;
	private boolean _preferLocalCluster;

}