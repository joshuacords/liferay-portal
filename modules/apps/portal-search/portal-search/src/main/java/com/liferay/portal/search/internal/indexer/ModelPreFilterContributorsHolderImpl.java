/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.indexer;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;

import java.util.List;
import java.util.function.Consumer;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = ModelPreFilterContributorsHolder.class)
public class ModelPreFilterContributorsHolderImpl
	implements ModelPreFilterContributorsHolder {

	@Override
	public void forEach(
		String entryClassName, Consumer<ModelPreFilterContributor> consumer) {

		List<ModelPreFilterContributor> list = _serviceTrackerMap.getService(
			"ALL");

		if (list != null) {
			list.forEach(consumer);
		}

		list = _serviceTrackerMap.getService(entryClassName);

		if (list != null) {
			list.forEach(consumer);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, ModelPreFilterContributor.class,
			"indexer.class.name");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, List<ModelPreFilterContributor>>
		_serviceTrackerMap;

}