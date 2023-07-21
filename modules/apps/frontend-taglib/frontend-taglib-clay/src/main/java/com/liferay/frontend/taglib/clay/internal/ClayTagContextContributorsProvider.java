/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.internal;

import com.liferay.frontend.taglib.clay.servlet.taglib.contributor.ClayTagContextContributor;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Rodolfo Roza Miranda
 */
@Component(immediate = true, service = {})
public class ClayTagContextContributorsProvider {

	public static List<ClayTagContextContributor> getClayTagContextContributors(
		String key) {

		if (_clayTagContextContributorsProvider == null) {
			_log.error(
				"Unable to get list of Clay tag context contributors for key " +
					key);

			return Collections.emptyList();
		}

		ServiceTrackerMap<String, List<ClayTagContextContributor>>
			clayTagContextContributors =
				_clayTagContextContributorsProvider._clayTagContextContributors;

		return clayTagContextContributors.getService(key);
	}

	public ClayTagContextContributorsProvider() {
		_clayTagContextContributorsProvider = this;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_clayTagContextContributors =
			ServiceTrackerMapFactory.openMultiValueMap(
				bundleContext, ClayTagContextContributor.class,
				"(clay.tag.context.contributor.key=*)",
				new PropertyServiceReferenceMapper<>(
					"clay.tag.context.contributor.key"),
				new PropertyServiceReferenceComparator<>("service.ranking"));
	}

	@Deactivate
	protected void deactivate() {
		_clayTagContextContributors.close();

		_clayTagContextContributors = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayTagContextContributorsProvider.class);

	private static ClayTagContextContributorsProvider
		_clayTagContextContributorsProvider;

	private ServiceTrackerMap<String, List<ClayTagContextContributor>>
		_clayTagContextContributors;

}