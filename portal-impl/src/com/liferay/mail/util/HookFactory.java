/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.util;

import com.liferay.mail.kernel.util.Hook;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 * @author Peter Fellwock
 */
public class HookFactory {

	public static Hook getInstance() {
		return _hookFactory._serviceTracker.getService();
	}

	private HookFactory() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(Hook.class);

		_serviceTracker.open();
	}

	private static final HookFactory _hookFactory = new HookFactory();

	private final ServiceTracker<Hook, Hook> _serviceTracker;

}