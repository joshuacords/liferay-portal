/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.url.provider.util;

import com.liferay.asset.info.display.url.provider.AssetInfoEditURLProvider;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Jürgen Kappler
 */
public class AssetInfoEditURLProviderUtil {

	public static String getURL(
		String className, long classPK, HttpServletRequest httpServletRequest) {

		AssetInfoEditURLProvider assetInfoEditURLProvider =
			_serviceTracker.getService();

		return assetInfoEditURLProvider.getURL(
			className, classPK, httpServletRequest);
	}

	private static final ServiceTracker
		<AssetInfoEditURLProvider, AssetInfoEditURLProvider> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AssetInfoEditURLProvider.class);

		ServiceTracker<AssetInfoEditURLProvider, AssetInfoEditURLProvider>
			serviceTracker = new ServiceTracker<>(
				bundle.getBundleContext(), AssetInfoEditURLProvider.class,
				null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}