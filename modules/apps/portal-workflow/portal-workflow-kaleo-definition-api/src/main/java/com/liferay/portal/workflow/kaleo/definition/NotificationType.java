/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

import java.util.Objects;

/**
 * @author Michael C. Han
 */
public enum NotificationType {

	EMAIL("email"), IM("im"), PRIVATE_MESSAGE("private-message"),
	PUSH_NOTIFICATION("push-notification"),
	USER_NOTIFICATION("user-notification");

	public static NotificationType parse(String value) {
		if (Objects.equals(EMAIL.getValue(), value)) {
			return EMAIL;
		}
		else if (Objects.equals(IM.getValue(), value)) {
			return IM;
		}
		else if (Objects.equals(PRIVATE_MESSAGE.getValue(), value)) {
			return PRIVATE_MESSAGE;
		}
		else if (Objects.equals(PUSH_NOTIFICATION.getValue(), value)) {
			return PUSH_NOTIFICATION;
		}
		else if (Objects.equals(USER_NOTIFICATION.getValue(), value)) {
			return USER_NOTIFICATION;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private NotificationType(String value) {
		_value = value;
	}

	private final String _value;

}