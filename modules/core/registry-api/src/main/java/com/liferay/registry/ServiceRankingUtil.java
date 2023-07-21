/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class ServiceRankingUtil {

	public static int compare(
		ServiceReference<?> serviceReference1,
		ServiceReference<?> serviceReference2) {

		int value = Integer.compare(
			_getServiceRanking(serviceReference1),
			_getServiceRanking(serviceReference2));

		if (value != 0) {
			return value;
		}

		return -Long.compare(
			(Long)serviceReference1.getProperty("service.id"),
			(Long)serviceReference2.getProperty("service.id"));
	}

	public static <S, T> Optional<Map.Entry<ServiceReference<S>, T>>
		getHighestRankingEntry(Map<ServiceReference<S>, T> services) {

		Set<Map.Entry<ServiceReference<S>, T>> entrySet = services.entrySet();

		if (entrySet.isEmpty()) {
			return Optional.empty();
		}

		Map.Entry<ServiceReference<S>, T> maxEntry = null;

		for (Map.Entry<ServiceReference<S>, T> entry : entrySet) {
			if (maxEntry == null) {
				maxEntry = entry;
			}
			else if (compare(entry.getKey(), maxEntry.getKey()) > 0) {
				maxEntry = entry;
			}
		}

		return Optional.of(maxEntry);
	}

	private static int _getServiceRanking(
		ServiceReference<?> serviceReference) {

		Object serviceRanking = serviceReference.getProperty("service.ranking");

		if (serviceRanking instanceof Integer) {
			return (Integer)serviceRanking;
		}

		if (serviceRanking instanceof String) {
			try {
				return Integer.parseInt((String)serviceRanking);
			}
			catch (NumberFormatException numberFormatException) {
			}
		}

		return 0;
	}

}