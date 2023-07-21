/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.data.integration;

import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class BatchEngineTaskItemDelegateResourceMapper {

	public BatchEngineTaskItemDelegateResourceMapper(
		String resourceName, Map<String, String> fieldMapping,
		String batchEngineTaskItemDelegate) {

		_resourceName = resourceName;
		_fieldMapping = fieldMapping;
		_batchEngineTaskItemDelegate = batchEngineTaskItemDelegate;
	}

	public String getBatchEngineTaskItemDelegate() {
		return _batchEngineTaskItemDelegate;
	}

	public Map<String, String> getFieldMapping() {
		return _fieldMapping;
	}

	public String getResourceName() {
		return _resourceName;
	}

	private final String _batchEngineTaskItemDelegate;
	private final Map<String, String> _fieldMapping;
	private final String _resourceName;

}