/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.index;

/**
 * @author Michael C. Han
 */
public class IndicesExistsIndexRequest
	implements IndexRequest<IndicesExistsIndexResponse> {

	public IndicesExistsIndexRequest(String... indexNames) {
		setPreferLocalCluster(true);

		_indexNames = indexNames;
	}

	@Override
	public IndicesExistsIndexResponse accept(
		IndexRequestExecutor indexRequestExecutor) {

		return indexRequestExecutor.executeIndexRequest(this);
	}

	@Override
	public String[] getIndexNames() {
		return _indexNames;
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

	public void setPreferLocalCluster(boolean preferLocalCluster) {
		_preferLocalCluster = preferLocalCluster;
	}

	private final String[] _indexNames;
	private boolean _preferLocalCluster;

}