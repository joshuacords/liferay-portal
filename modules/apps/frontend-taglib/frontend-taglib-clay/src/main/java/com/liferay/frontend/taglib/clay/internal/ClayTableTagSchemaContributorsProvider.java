/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.internal;

import com.liferay.frontend.taglib.clay.servlet.taglib.contributor.ClayTableTagSchemaContributor;
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
 * @author Iván Zaera Avellón
 */
@Component(immediate = true, service = {})
public class ClayTableTagSchemaContributorsProvider {

	public static List<ClayTableTagSchemaContributor>
		getClayTableTagSchemaContributors(String key) {

		if (_clayTableTagSchemaContributorsProvider == null) {
			_log.error(
				"Unable to get list of Clay table tag schema contributors " +
					"for key " + key);

			return Collections.emptyList();
		}

		ServiceTrackerMap<String, List<ClayTableTagSchemaContributor>>
			clayTableTagSchemaContributors =
				_clayTableTagSchemaContributorsProvider.
					_clayTableTagSchemaContributors;

		return clayTableTagSchemaContributors.getService(key);
	}

	public ClayTableTagSchemaContributorsProvider() {
		_clayTableTagSchemaContributorsProvider = this;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_clayTableTagSchemaContributors =
			ServiceTrackerMapFactory.openMultiValueMap(
				bundleContext, ClayTableTagSchemaContributor.class,
				"(clay.table.tag.schema.contributor.key=*)",
				new PropertyServiceReferenceMapper<>(
					"clay.table.tag.schema.contributor.key"),
				new PropertyServiceReferenceComparator<>("service.ranking"));
	}

	@Deactivate
	protected void deactivate() {
		_clayTableTagSchemaContributors.close();

		_clayTableTagSchemaContributors = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClayTableTagSchemaContributorsProvider.class);

	private static ClayTableTagSchemaContributorsProvider
		_clayTableTagSchemaContributorsProvider;

	private ServiceTrackerMap<String, List<ClayTableTagSchemaContributor>>
		_clayTableTagSchemaContributors;

}