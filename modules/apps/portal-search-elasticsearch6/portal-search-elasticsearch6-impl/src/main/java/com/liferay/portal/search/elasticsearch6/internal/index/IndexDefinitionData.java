/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.index;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.spi.index.IndexDefinition;

import java.util.Map;
import java.util.Objects;

/**
 * @author André de Oliveira
 */
public class IndexDefinitionData {

	public IndexDefinitionData(
		IndexDefinition indexDefinition, Map<String, Object> properties) {

		_index = _getIndexName(
			properties.get(IndexDefinition.PROPERTY_KEY_INDEX_NAME));
		_source = _getSource(
			indexDefinition,
			properties.get(
				IndexDefinition.PROPERTY_KEY_INDEX_SETTINGS_RESOURCE_NAME));
	}

	public String getIndex() {
		return _index;
	}

	public String getSource() {
		return _source;
	}

	private static String _getIndexName(Object property) {
		return String.valueOf(Objects.requireNonNull(property));
	}

	private static String _getSource(
		IndexDefinition indexDefinition, Object property) {

		String resourceName = String.valueOf(Objects.requireNonNull(property));

		return StringUtil.read(indexDefinition.getClass(), resourceName);
	}

	private final String _index;
	private final String _source;

}