/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.index;

/**
 * @author Michael C. Han
 */
public class OpenIndexRequest implements IndexRequest<OpenIndexResponse> {

	public OpenIndexRequest(String... indexNames) {
		_indexNames = indexNames;
	}

	@Override
	public OpenIndexResponse accept(IndexRequestExecutor indexRequestExecutor) {
		return indexRequestExecutor.executeIndexRequest(this);
	}

	@Override
	public String[] getIndexNames() {
		return _indexNames;
	}

	public IndicesOptions getIndicesOptions() {
		return _indicesOptions;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement. This method
	 *             should not be in the parent interface.  Only certain
	 *             IndexRequests work with mappings.
	 */
	@Deprecated
	@Override
	public String getMappingName() {
		return null;
	}

	public long getTimeout() {
		return _timeout;
	}

	public int getWaitForActiveShards() {
		return _waitForActiveShards;
	}

	public void setIndicesOptions(IndicesOptions indicesOptions) {
		_indicesOptions = indicesOptions;
	}

	public void setTimeout(long timeout) {
		_timeout = timeout;
	}

	public void setWaitForActiveShards(int waitForActiveShards) {
		_waitForActiveShards = waitForActiveShards;
	}

	private final String[] _indexNames;
	private IndicesOptions _indicesOptions;
	private long _timeout;
	private int _waitForActiveShards;

}