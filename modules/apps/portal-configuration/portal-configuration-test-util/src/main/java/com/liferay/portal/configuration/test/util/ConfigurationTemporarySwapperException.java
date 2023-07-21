/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.test.util;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author     Drew Brokke
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ConfigurationTemporarySwapperException extends PortalException {

	public ConfigurationTemporarySwapperException(String msg) {
		super(msg);
	}

	public static class MustFindService
		extends ConfigurationTemporarySwapperException {

		public MustFindService(Class<?> serviceClass) {
			super(
				String.format(
					"No service found that implements interface \"%s\"",
					serviceClass));
		}

	}

	public static class ServiceMustConsumeConfiguration
		extends ConfigurationTemporarySwapperException {

		public ServiceMustConsumeConfiguration(
			String componentName, String pid) {

			super(
				String.format(
					"Component \"%s\" does not consume configuration \"%s\"",
					componentName, pid));
		}

	}

	public static class ServiceMustHaveBundle
		extends ConfigurationTemporarySwapperException {

		public ServiceMustHaveBundle(Class<?> serviceClass) {
			super(
				String.format(
					"No bundle found for the service \"%s\"", serviceClass));
		}

	}

}