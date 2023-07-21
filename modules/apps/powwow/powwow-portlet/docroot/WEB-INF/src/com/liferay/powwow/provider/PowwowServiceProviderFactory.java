/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.provider;

import com.liferay.powwow.provider.bbb.BBBPowwowServiceProvider;
import com.liferay.powwow.provider.zoom.ZoomPowwowServiceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco Calderon
 */
public class PowwowServiceProviderFactory {

	public static PowwowServiceProvider getPowwowServiceProvider(
		String providerType) {

		if (!_powwowServiceProviders.containsKey(providerType)) {
			throw new IllegalArgumentException("Invalid provider type");
		}

		return _powwowServiceProviders.get(providerType);
	}

	private static final Map<String, PowwowServiceProvider>
		_powwowServiceProviders = new HashMap<String, PowwowServiceProvider>() {
			{
				PowwowServiceProvider bbbPowwowServiceProvider =
					new BBBPowwowServiceProvider();

				put(
					bbbPowwowServiceProvider.getPowwowServiceProviderKey(),
					bbbPowwowServiceProvider);

				PowwowServiceProvider zoomPowwowServiceProvider =
					new ZoomPowwowServiceProvider();

				put(
					zoomPowwowServiceProvider.getPowwowServiceProviderKey(),
					zoomPowwowServiceProvider);
			}
		};

}