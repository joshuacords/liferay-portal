/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.search.configuration.ElasticsearchConnectionConfigurationWrapper;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.ElasticsearchConnectionConfiguration",
	service = {}
)
public class ElasticsearchConnectionConfigurationWrapperHelper {

	@Activate
	protected void activate(Map<String, Object> properties) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				ConfigurableUtil.createConfigurable(
					ElasticsearchConnectionConfiguration.class, properties);

		ElasticsearchConnectionConfigurationWrapperImpl
			elasticsearchConnectionConfigurationWrapperImpl =
				(ElasticsearchConnectionConfigurationWrapperImpl)
					elasticsearchConnectionConfigurationWrapper;

		elasticsearchConnectionConfigurationWrapperImpl.
			addElasticsearchConnectionConfiguration(
				elasticsearchConnectionConfiguration);
	}

	@Deactivate
	protected void deactivate(Map<String, Object> properties) {
		ElasticsearchConnectionConfiguration
			elasticsearchConnectionConfiguration =
				ConfigurableUtil.createConfigurable(
					ElasticsearchConnectionConfiguration.class, properties);

		ElasticsearchConnectionConfigurationWrapperImpl
			elasticsearchConnectionConfigurationWrapperImpl =
				(ElasticsearchConnectionConfigurationWrapperImpl)
					elasticsearchConnectionConfigurationWrapper;

		elasticsearchConnectionConfigurationWrapperImpl.
			removeElasticsearchConnectionConfiguration(
				elasticsearchConnectionConfiguration.connectionId());
	}

	@Reference
	protected ElasticsearchConnectionConfigurationWrapper
		elasticsearchConnectionConfigurationWrapper;

}