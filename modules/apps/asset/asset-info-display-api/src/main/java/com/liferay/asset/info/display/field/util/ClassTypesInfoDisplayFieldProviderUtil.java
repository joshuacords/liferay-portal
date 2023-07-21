/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.field.util;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.info.display.field.ClassTypesInfoDisplayFieldProvider;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Jürgen Kappler
 */
public class ClassTypesInfoDisplayFieldProviderUtil {

	public static List<InfoDisplayField> getClassTypeInfoDisplayFields(
			String className, long classTypeId, Locale locale)
		throws PortalException {

		ClassTypesInfoDisplayFieldProvider classTypesInfoDisplayFieldProvider =
			_serviceTracker.getService();

		ClassTypeReader classTypeReader = _getClassTypeReader(className);

		return classTypesInfoDisplayFieldProvider.getClassTypeInfoDisplayFields(
			classTypeReader.getClassType(classTypeId, locale), locale);
	}

	public static List<ClassType> getClassTypes(
			long groupId, String className, Locale locale)
		throws PortalException {

		ClassTypesInfoDisplayFieldProvider classTypesInfoDisplayFieldProvider =
			_serviceTracker.getService();

		return classTypesInfoDisplayFieldProvider.getClassTypes(
			groupId, _getClassTypeReader(className), locale);
	}

	private static ClassTypeReader _getClassTypeReader(String className) {
		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if ((assetRendererFactory == null) ||
			!assetRendererFactory.isSupportsClassTypes()) {

			return null;
		}

		return assetRendererFactory.getClassTypeReader();
	}

	private static final ServiceTracker
		<ClassTypesInfoDisplayFieldProvider, ClassTypesInfoDisplayFieldProvider>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ClassTypesInfoDisplayFieldProviderUtil.class);

		ServiceTracker
			<ClassTypesInfoDisplayFieldProvider,
			 ClassTypesInfoDisplayFieldProvider> serviceTracker =
				new ServiceTracker<>(
					bundle.getBundleContext(),
					ClassTypesInfoDisplayFieldProvider.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}