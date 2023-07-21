/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.notification;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationSender;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = NotificationSenderFactory.class)
public class NotificationSenderFactory {

	public NotificationSender getNotificationSender(String notificationType)
		throws WorkflowException {

		NotificationSender notificationSender = _notificationSenders.get(
			notificationType);

		if (notificationSender == null) {
			throw new WorkflowException(
				"Invalid notification type " + notificationType);
		}

		return notificationSender;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(notification.type=*)"
	)
	protected void addNotificationSender(
		NotificationSender notificationSender, Map<String, Object> properties) {

		String[] notificationTypes = getNotificationTypes(
			notificationSender, properties);

		for (String notificationType : notificationTypes) {
			_notificationSenders.put(notificationType, notificationSender);
		}
	}

	protected String[] getNotificationTypes(
		NotificationSender notificationSender, Map<String, Object> properties) {

		Object value = properties.get("notification.type");

		String[] notificationTypes = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		if (ArrayUtil.isEmpty(notificationTypes)) {
			throw new IllegalArgumentException(
				"The property \"notification.type\" is invalid for " +
					ClassUtil.getClassName(notificationSender));
		}

		return notificationTypes;
	}

	protected void removeNotificationSender(
		NotificationSender notificationSender, Map<String, Object> properties) {

		String[] notificationTypes = getNotificationTypes(
			notificationSender, properties);

		for (String notificationType : notificationTypes) {
			_notificationSenders.remove(notificationType);
		}
	}

	private final Map<String, NotificationSender> _notificationSenders =
		new HashMap<>();

}