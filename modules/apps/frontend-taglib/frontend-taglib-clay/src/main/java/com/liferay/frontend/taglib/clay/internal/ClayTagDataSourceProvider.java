/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.internal;

import com.liferay.frontend.taglib.clay.servlet.taglib.data.ClayTagDataSource;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Rodolfo Roza Miranda
 */
@Component(immediate = true, service = {})
public class ClayTagDataSourceProvider {

	public static <T> ClayTagDataSource<T> getClayTagDataSource(String key) {
		if (_clayTagDataSourceProvider == null) {
			_log.error(
				"Unable to get list of Clay tag data sources for key " + key);

			return null;
		}

		ServiceTrackerMap<String, ClayTagDataSource> clayTagDataSources =
			_clayTagDataSourceProvider._clayTagDataSources;

		return clayTagDataSources.getService(key);
	}

	public ClayTagDataSourceProvider() {
		_clayTagDataSourceProvider = this;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_clayTagDataSources = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ClayTagDataSource.class,
			"(clay.tag.data.source.key=*)",
			new PropertyServiceReferenceMapper<>("clay.tag.data.source.key"),
			new PropertyServiceReferenceComparator<>("service.ranking"));
	}

	@Deactivate
	protected void deactivate() {
		_clayTagDataSources.close();

		_clayTagDataSources = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayTagDataSourceProvider.class);

	private static ClayTagDataSourceProvider _clayTagDataSourceProvider;

	private ServiceTrackerMap<String, ClayTagDataSource> _clayTagDataSources;

}