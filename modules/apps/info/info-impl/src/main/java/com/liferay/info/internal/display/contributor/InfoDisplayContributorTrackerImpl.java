/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.display.contributor;

import com.liferay.info.display.contributor.InfoDisplayContributor;
import com.liferay.info.display.contributor.InfoDisplayContributorTracker;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = InfoDisplayContributorTracker.class)
public class InfoDisplayContributorTrackerImpl
	implements InfoDisplayContributorTracker {

	@Override
	public InfoDisplayContributor getInfoDisplayContributor(String className) {
		return _infoDisplayContributorMap.getService(className);
	}

	@Override
	public InfoDisplayContributor getInfoDisplayContributorByURLSeparator(
		String urlSeparator) {

		return _infoDisplayContributorByURLSeparatorMap.getService(
			urlSeparator);
	}

	@Override
	public List<InfoDisplayContributor> getInfoDisplayContributors() {
		return new ArrayList(_infoDisplayContributorMap.values());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_infoDisplayContributorMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, InfoDisplayContributor.class, null,
				(serviceReference, emitter) -> {
					InfoDisplayContributor infoDisplayContributor =
						bundleContext.getService(serviceReference);

					try {
						emitter.emit(infoDisplayContributor.getClassName());
					}
					finally {
						bundleContext.ungetService(serviceReference);
					}
				});
		_infoDisplayContributorByURLSeparatorMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, InfoDisplayContributor.class, null,
				(serviceReference, emitter) -> {
					InfoDisplayContributor infoDisplayContributor =
						bundleContext.getService(serviceReference);

					try {
						emitter.emit(
							infoDisplayContributor.getInfoURLSeparator());
					}
					finally {
						bundleContext.ungetService(serviceReference);
					}
				});
	}

	private ServiceTrackerMap<String, InfoDisplayContributor>
		_infoDisplayContributorByURLSeparatorMap;
	private ServiceTrackerMap<String, InfoDisplayContributor>
		_infoDisplayContributorMap;

}