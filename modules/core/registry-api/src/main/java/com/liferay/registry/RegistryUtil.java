/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry;

/**
 * @author Raymond Augé
 */
public class RegistryUtil {

	public static Registry getRegistry() {
		if (_registry != null) {
			return _registry.getRegistry();
		}

		throw new NullPointerException("A registry instance was never set");
	}

	public static void setRegistry(Registry registry) {
		if (_registry != null) {
			registry = _registry.setRegistry(registry);
		}
		else if (registry != null) {
			registry = registry.setRegistry(registry);
		}

		_registry = registry;
	}

	private static Registry _registry;

}