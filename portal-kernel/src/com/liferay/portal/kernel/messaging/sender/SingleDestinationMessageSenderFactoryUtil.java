/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.sender;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class SingleDestinationMessageSenderFactoryUtil {

	public static SingleDestinationMessageSender
		createSingleDestinationMessageSender(String destinationName) {

		return _singleDestinationMessageSenderFactory.
			createSingleDestinationMessageSender(destinationName);
	}

	public static SingleDestinationSynchronousMessageSender
		createSingleDestinationSynchronousMessageSender(
			String destinationName, SynchronousMessageSender.Mode mode) {

		return _singleDestinationMessageSenderFactory.
			createSingleDestinationSynchronousMessageSender(
				destinationName, mode);
	}

	public static int getModeCount() {
		return _singleDestinationMessageSenderFactory.getModesCount();
	}

	public static SynchronousMessageSender getSynchronousMessageSender(
		SynchronousMessageSender.Mode mode) {

		return _singleDestinationMessageSenderFactory.
			getSynchronousMessageSender(mode);
	}

	protected SingleDestinationMessageSenderFactory
		getSingleDestinationMessageSenderFactory() {

		return _singleDestinationMessageSenderFactory;
	}

	private SingleDestinationMessageSenderFactoryUtil() {
	}

	private static volatile SingleDestinationMessageSenderFactory
		_singleDestinationMessageSenderFactory =
			ServiceProxyFactory.newServiceTrackedInstance(
				SingleDestinationMessageSenderFactory.class,
				SingleDestinationMessageSenderFactoryUtil.class,
				"_singleDestinationMessageSenderFactory", true);

}