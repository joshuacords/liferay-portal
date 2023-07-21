/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.dynamic.section.internal.util;

import com.liferay.frontend.taglib.dynamic.section.DynamicSection;
import com.liferay.frontend.taglib.dynamic.section.DynamicSectionReplace;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true, service = {})
public class DynamicSectionUtil {

	public static DynamicSectionReplace getReplace(String name) {
		return _dynamicSectionReplaceServiceTrackerMap.getService(name);
	}

	public static List<DynamicSection> getServices(String name) {
		return _dynamicSectionServiceTrackerMap.getService(name);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_dynamicSectionServiceTrackerMap =
			ServiceTrackerMapFactory.openMultiValueMap(
				bundleContext, DynamicSection.class, "(name=*)",
				(serviceReference, emitter) -> emitter.emit(
					(String)serviceReference.getProperty("name")),
				ServiceReference::compareTo);

		_dynamicSectionReplaceServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, DynamicSectionReplace.class, "name");
	}

	private static ServiceTrackerMap<String, DynamicSectionReplace>
		_dynamicSectionReplaceServiceTrackerMap;
	private static ServiceTrackerMap<String, List<DynamicSection>>
		_dynamicSectionServiceTrackerMap;

}