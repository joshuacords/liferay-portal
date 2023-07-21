/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet.taglib;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.collections.ServiceReferenceMapper;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlos Sierra Andrés
 * @author Raymond Augé
 */
public class DynamicIncludeUtil {

	public static List<DynamicInclude> getDynamicIncludes(String key) {
		return _dynamicIncludeUtil._dynamicIncludes.getService(key);
	}

	public static boolean hasDynamicInclude(String key) {
		if (ListUtil.isEmpty(getDynamicIncludes(key))) {
			return false;
		}

		return true;
	}

	public static void include(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, String key,
		boolean ascendingPriority) {

		List<DynamicInclude> dynamicIncludes = getDynamicIncludes(key);

		if (ListUtil.isEmpty(dynamicIncludes)) {
			return;
		}

		Iterator<DynamicInclude> iterator = null;

		if (ascendingPriority) {
			iterator = dynamicIncludes.iterator();
		}
		else {
			iterator = ListUtil.reverseIterator(dynamicIncludes);
		}

		while (iterator.hasNext()) {
			DynamicInclude dynamicInclude = iterator.next();

			try {
				dynamicInclude.include(
					httpServletRequest, httpServletResponse, key);
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
	}

	private DynamicIncludeUtil() {
		_dynamicIncludes = ServiceTrackerCollections.openMultiValueMap(
			DynamicInclude.class, null,
			new ServiceReferenceMapper<String, DynamicInclude>() {

				@Override
				public void map(
					ServiceReference<DynamicInclude> serviceReference,
					final Emitter<String> emitter) {

					Registry registry = RegistryUtil.getRegistry();

					DynamicInclude dynamicInclude = registry.getService(
						serviceReference);

					dynamicInclude.register(
						new DynamicInclude.DynamicIncludeRegistry() {

							@Override
							public void register(String key) {
								emitter.emit(key);
							}

						});

					registry.ungetService(serviceReference);
				}

			});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DynamicIncludeUtil.class);

	private static final DynamicIncludeUtil _dynamicIncludeUtil =
		new DynamicIncludeUtil();

	private final ServiceTrackerMap<String, List<DynamicInclude>>
		_dynamicIncludes;

}