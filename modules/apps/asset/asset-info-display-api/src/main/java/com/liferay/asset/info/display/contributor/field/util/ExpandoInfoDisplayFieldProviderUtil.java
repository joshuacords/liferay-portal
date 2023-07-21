/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.contributor.field.util;

import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.info.display.field.ExpandoInfoDisplayFieldProvider;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Jürgen Kappler
 */
public class ExpandoInfoDisplayFieldProviderUtil {

	public static List<InfoDisplayField> getExpandoInfoDisplayFields(
		String className, Locale locale) {

		ExpandoInfoDisplayFieldProvider expandoInfoDisplayFieldProvider =
			_serviceTracker.getService();

		return expandoInfoDisplayFieldProvider.
			getContributorExpandoInfoDisplayFields(className, locale);
	}

	public static Map<String, Object> getExpandoInfoDisplayFieldsValues(
		String className, Object displayObject, Locale locale) {

		ExpandoInfoDisplayFieldProvider expandoInfoDisplayFieldProvider =
			_serviceTracker.getService();

		return expandoInfoDisplayFieldProvider.
			getContributorExpandoInfoDisplayFieldsValues(
				className, displayObject, locale);
	}

	private static final ServiceTracker
		<ExpandoInfoDisplayFieldProvider, ExpandoInfoDisplayFieldProvider>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ExpandoInfoDisplayFieldProviderUtil.class);

		ServiceTracker
			<ExpandoInfoDisplayFieldProvider, ExpandoInfoDisplayFieldProvider>
				serviceTracker = new ServiceTracker<>(
					bundle.getBundleContext(),
					ExpandoInfoDisplayFieldProvider.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}