/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.field.util;

import com.liferay.asset.info.display.contributor.util.AssetInfoDisplayContributorFieldUtil;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.info.display.field.InfoDisplayFieldProvider;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Jürgen Kappler
 */
public class AssetEntryInfoDisplayFieldProviderUtil {

	public static Set<InfoDisplayField> getInfoDisplayFields(
		Locale locale, String... className) {

		InfoDisplayFieldProvider infoDisplayFieldProvider =
			_serviceTracker.getService();

		return infoDisplayFieldProvider.getContributorInfoDisplayFields(
			locale, className);
	}

	public static Map<String, Object> getInfoDisplayFields(
			String className, Object displayObject, Locale locale)
		throws PortalException {

		InfoDisplayFieldProvider infoDisplayFieldProvider =
			_serviceTracker.getService();

		return infoDisplayFieldProvider.getContributorInfoDisplayFieldsValues(
			className, displayObject, locale);
	}

	private static final ServiceTracker
		<InfoDisplayFieldProvider, InfoDisplayFieldProvider> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AssetInfoDisplayContributorFieldUtil.class);

		ServiceTracker<InfoDisplayFieldProvider, InfoDisplayFieldProvider>
			serviceTracker = new ServiceTracker<>(
				bundle.getBundleContext(), InfoDisplayFieldProvider.class,
				null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}