/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index.instant;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.elasticsearch7.spi.index.IndexRegistrar;
import com.liferay.portal.search.elasticsearch7.spi.index.helper.IndexRegistrarHelper;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(service = IndexRegistrar.class)
public class InstancesAndProcessesIndexRegistrar implements IndexRegistrar {

	public static final String INDEX_NAME_WORKFLOW_INSTANCES =
		"workflow-instances";

	public static final String INDEX_NAME_WORKFLOW_PROCESSES =
		"workflow-processes";

	public static final String TYPE_MAPPING_FILE_NAME_WORKFLOW_INSTANCES =
		"workflow-instances-type-mappings.json";

	public static final String TYPE_MAPPING_FILE_NAME_WORKFLOW_PROCESSES =
		"workflow-processes-type-mappings.json";

	@Override
	public void register(IndexRegistrarHelper indexRegistrarHelper) {
		indexRegistrarHelper.createIndex(
			INDEX_NAME_WORKFLOW_INSTANCES,
			indexSettingsDefinition -> indexSettingsDefinition.setSource(
				StringUtil.read(
					getClass(), TYPE_MAPPING_FILE_NAME_WORKFLOW_INSTANCES)));

		indexRegistrarHelper.createIndex(
			INDEX_NAME_WORKFLOW_PROCESSES,
			indexSettingsDefinition ->
				indexSettingsDefinition.setIndexSettingsResourceName(
					TYPE_MAPPING_FILE_NAME_WORKFLOW_PROCESSES));
	}

}