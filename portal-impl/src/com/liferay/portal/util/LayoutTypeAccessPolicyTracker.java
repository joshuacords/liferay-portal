/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeAccessPolicy;
import com.liferay.portal.kernel.model.impl.DefaultLayoutTypeAccessPolicyImpl;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.collections.ServiceReferenceMapper;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

/**
 * @author Adolfo Pérez
 */
public class LayoutTypeAccessPolicyTracker {

	public static LayoutTypeAccessPolicy getLayoutTypeAccessPolicy(
		Layout layout) {

		return getLayoutTypeAccessPolicy(layout.getType());
	}

	public static LayoutTypeAccessPolicy getLayoutTypeAccessPolicy(
		String type) {

		LayoutTypeAccessPolicy layoutTypeAccessPolicy =
			_serviceTrackerMap.getService(type);

		if (layoutTypeAccessPolicy == null) {
			return DefaultLayoutTypeAccessPolicyImpl.create();
		}

		return layoutTypeAccessPolicy;
	}

	private static final ServiceTrackerMap<String, LayoutTypeAccessPolicy>
		_serviceTrackerMap = ServiceTrackerCollections.openSingleValueMap(
			LayoutTypeAccessPolicy.class,
			"(&(layout.type=*)(objectClass=" +
				LayoutTypeAccessPolicy.class.getName() + "))",
			new ServiceReferenceMapper<String, LayoutTypeAccessPolicy>() {

				@Override
				public void map(
					ServiceReference<LayoutTypeAccessPolicy> serviceReference,
					Emitter<String> emitter) {

					String layoutType = (String)serviceReference.getProperty(
						"layout.type");

					emitter.emit(layoutType);
				}

			});

}