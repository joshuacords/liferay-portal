/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.sender;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface SingleDestinationMessageSenderFactory {

	public SingleDestinationMessageSender createSingleDestinationMessageSender(
		String destinationName);

	public SingleDestinationSynchronousMessageSender
		createSingleDestinationSynchronousMessageSender(
			String destinationName, SynchronousMessageSender.Mode mode);

	public int getModesCount();

	public SynchronousMessageSender getSynchronousMessageSender(
		SynchronousMessageSender.Mode mode);

}