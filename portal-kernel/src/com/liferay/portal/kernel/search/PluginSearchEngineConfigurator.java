/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;

/**
 * @author Michael C. Han
 */
public class PluginSearchEngineConfigurator
	extends BaseSearchEngineConfigurator {

	public void setDefaultSearchEngineId(String defaultSearchEngineId) {
		_defaultSearchEngineId = defaultSearchEngineId;
	}

	@Override
	protected String getDefaultSearchEngineId() {
		return _defaultSearchEngineId;
	}

	@Override
	protected IndexSearcher getIndexSearcher() {
		BeanLocator beanLocator = PortalBeanLocatorUtil.getBeanLocator();

		return (IndexSearcher)beanLocator.locate(
			IndexSearcherProxyBean.class.getName());
	}

	@Override
	protected IndexWriter getIndexWriter() {
		BeanLocator beanLocator = PortalBeanLocatorUtil.getBeanLocator();

		return (IndexWriter)beanLocator.locate(
			IndexWriterProxyBean.class.getName());
	}

	@Override
	protected ClassLoader getOperatingClassloader() {
		return PortletClassLoaderUtil.getClassLoader();
	}

	@Override
	protected SearchEngineHelper getSearchEngineHelper() {
		return SearchEngineHelperUtil.getSearchEngineHelper();
	}

	private String _defaultSearchEngineId;

}