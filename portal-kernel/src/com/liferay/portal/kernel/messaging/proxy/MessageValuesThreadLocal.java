/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.messaging.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tina Tian
 */
public class MessageValuesThreadLocal {

	public static Object getValue(String key) {
		Map<String, Object> messageValues = _messageValuesThreadLocal.get();

		if (messageValues == null) {
			return null;
		}

		return messageValues.get(_THREAD_LOCAL_KEY_PREFIX.concat(key));
	}

	public static void populateMessageFromThreadLocals(Message message) {
		Map<String, Object> messageValues = _messageValuesThreadLocal.get();

		if (messageValues == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : messageValues.entrySet()) {
			message.put(entry.getKey(), entry.getValue());
		}
	}

	public static void populateThreadLocalsFromMessage(Message message) {
		Map<String, Object> values = message.getValues();

		if (values == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : values.entrySet()) {
			String key = entry.getKey();

			if (key.startsWith(_THREAD_LOCAL_KEY_PREFIX)) {
				doSetValue(key, entry.getValue());
			}
		}
	}

	public static void setValue(String key, Object value) {
		doSetValue(_THREAD_LOCAL_KEY_PREFIX.concat(key), value);
	}

	protected static void doSetValue(String key, Object value) {
		Map<String, Object> messageValues = _messageValuesThreadLocal.get();

		if (messageValues == null) {
			messageValues = new HashMap<>();

			_messageValuesThreadLocal.set(messageValues);
		}

		messageValues.put(key, value);
	}

	private static final String _THREAD_LOCAL_KEY_PREFIX =
		"THREAD_LOCAL_KEY_PREFIX#";

	private static final ThreadLocal<Map<String, Object>>
		_messageValuesThreadLocal = new CentralizedThreadLocal<>(
			MessageValuesThreadLocal.class.getName());

}