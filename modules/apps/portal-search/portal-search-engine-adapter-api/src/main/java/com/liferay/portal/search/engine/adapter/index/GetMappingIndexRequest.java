/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.engine.adapter.index;

/**
 * @author Dylan Rebelak
 */
public class GetMappingIndexRequest
	implements IndexRequest<GetMappingIndexResponse> {

	public GetMappingIndexRequest(String[] indexNames, String mappingName) {
		_indexNames = indexNames;
		_mappingName = mappingName;
	}

	@Override
	public GetMappingIndexResponse accept(
		IndexRequestExecutor indexRequestExecutor) {

		return indexRequestExecutor.executeIndexRequest(this);
	}

	@Override
	public String[] getIndexNames() {
		return _indexNames;
	}

	@Override
	public String getMappingName() {
		return _mappingName;
	}

	private final String[] _indexNames;
	private final String _mappingName;

}