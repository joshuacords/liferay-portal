/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.Collection;

/**
 * @author Michael C. Han
 */
public class DestinationFactoryUtil {

	public static Destination createDestination(
		DestinationConfiguration destinationConfiguration) {

		return _destinationFactory.createDestination(destinationConfiguration);
	}

	public static Collection<String> getDestinationTypes() {
		return _destinationFactory.getDestinationTypes();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected DestinationFactory getDestinationFactory() {
		return _destinationFactory;
	}

	private DestinationFactoryUtil() {
	}

	private static volatile DestinationFactory _destinationFactory =
		ServiceProxyFactory.newServiceTrackedInstance(
			DestinationFactory.class, DestinationFactoryUtil.class,
			"_destinationFactory", true);

}