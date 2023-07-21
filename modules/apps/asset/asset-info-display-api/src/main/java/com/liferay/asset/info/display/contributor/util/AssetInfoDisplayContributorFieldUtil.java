/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.contributor.util;

import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * @author     Jürgen Kappler
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 *             com.liferay.info.display.field.InfoDisplayFieldProvider}
 */
@Deprecated
public class AssetInfoDisplayContributorFieldUtil {

	public static List<InfoDisplayContributorField>
		getInfoDisplayContributorFields(String className) {

		List<InfoDisplayContributorField> infoDisplayContributorFields =
			Optional.ofNullable(
				ListUtil.copy(_serviceTrackerMap.getService(className))
			).orElse(
				new ArrayList<>()
			);

		infoDisplayContributorFields.addAll(
			ExpandoInfoDisplayContributorFieldUtil.
				getInfoDisplayContributorFields(className));

		return infoDisplayContributorFields;
	}

	private static final ServiceTrackerMap
		<String, List<InfoDisplayContributorField>> _serviceTrackerMap;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AssetInfoDisplayContributorFieldUtil.class);

		BundleContext bundleContext = bundle.getBundleContext();

		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, InfoDisplayContributorField.class,
			"(model.class.name=*)",
			(serviceReference, emitter) -> emitter.emit(
				(String)serviceReference.getProperty("model.class.name")));
	}

}