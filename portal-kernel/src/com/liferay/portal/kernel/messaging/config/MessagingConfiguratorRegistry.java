/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class MessagingConfiguratorRegistry {

	public static List<MessagingConfigurator> getMessagingConfigurators(
		String servletContextName) {

		return _messagingConfigurators.get(servletContextName);
	}

	public static void registerMessagingConfigurator(
		String servletContextName,
		MessagingConfigurator messagingConfigurator) {

		List<MessagingConfigurator> messagingConfigurators =
			_messagingConfigurators.get(servletContextName);

		if (messagingConfigurators == null) {
			messagingConfigurators = new ArrayList<>();

			_messagingConfigurators.put(
				servletContextName, messagingConfigurators);
		}

		messagingConfigurators.add(messagingConfigurator);
	}

	public static void unregisterMessagingConfigurator(
		String servletContextName,
		MessagingConfigurator messagingConfigurator) {

		List<MessagingConfigurator> messagingConfigurators =
			_messagingConfigurators.get(servletContextName);

		if (messagingConfigurators != null) {
			messagingConfigurators.remove(messagingConfigurator);

			if (messagingConfigurators.isEmpty()) {
				_messagingConfigurators.remove(servletContextName);
			}
		}
	}

	private static final Map<String, List<MessagingConfigurator>>
		_messagingConfigurators = new ConcurrentHashMap<>();

}