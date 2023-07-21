/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr7.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.BaseSearchEngine;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {"search.engine.id=SYSTEM_ENGINE", "search.engine.impl=Solr"},
	service = {SearchEngine.class, SolrSearchEngine.class}
)
public class SolrSearchEngine extends BaseSearchEngine {

	@Override
	public synchronized String backup(long companyId, String backupName) {
		return StringPool.BLANK;
	}

	@Override
	public void initialize(long companyId) {
		super.initialize(companyId);
	}

	@Override
	public synchronized void removeBackup(long companyId, String backupName) {
	}

	@Override
	public void removeCompany(long companyId) {
		super.removeCompany(companyId);
	}

	@Override
	public synchronized void restore(long companyId, String backupName) {
	}

	@Override
	@Reference(target = "(search.engine.impl=Solr)", unbind = "-")
	public void setIndexSearcher(IndexSearcher indexSearcher) {
		super.setIndexSearcher(indexSearcher);
	}

	@Override
	@Reference(target = "(search.engine.impl=Solr)", unbind = "-")
	public void setIndexWriter(IndexWriter indexWriter) {
		super.setIndexWriter(indexWriter);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		setVendor(MapUtil.getString(properties, "search.engine.impl"));
	}

}