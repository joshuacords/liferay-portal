/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.security.auth.ScreenNameValidator;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 * @author Peter Fellwock
 */
public class ScreenNameValidatorFactory {

	public static ScreenNameValidator getInstance() {
		return _screenNameValidatorFactory._serviceTracker.getService();
	}

	private ScreenNameValidatorFactory() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(ScreenNameValidator.class);

		_serviceTracker.open();
	}

	private static final ScreenNameValidatorFactory
		_screenNameValidatorFactory = new ScreenNameValidatorFactory();

	private final ServiceTracker<?, ScreenNameValidator> _serviceTracker;

}