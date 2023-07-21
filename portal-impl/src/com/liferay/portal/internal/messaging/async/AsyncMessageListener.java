/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.internal.messaging.async;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.process.ProcessCallable;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class AsyncMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		ProcessCallable<?> processCallable =
			(ProcessCallable<?>)message.getPayload();

		processCallable.call();
	}

}