/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.index;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.index.IndexNameBuilder;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration",
	immediate = true, service = IndexNameBuilder.class
)
public class CompanyIdIndexNameBuilder implements IndexNameBuilder {

	@Override
	public String getIndexName(long companyId) {
		return _indexNamePrefix + companyId;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		ElasticsearchConfiguration elasticsearchConfiguration =
			ConfigurableUtil.createConfigurable(
				ElasticsearchConfiguration.class, properties);

		setIndexNamePrefix(elasticsearchConfiguration.indexNamePrefix());
	}

	protected void setIndexNamePrefix(String indexNamePrefix) {
		if (indexNamePrefix == null) {
			_indexNamePrefix = StringPool.BLANK;
		}
		else {
			_indexNamePrefix = StringUtil.toLowerCase(
				StringUtil.trim(indexNamePrefix));
		}
	}

	private volatile String _indexNamePrefix;

}