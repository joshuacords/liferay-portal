/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.audit;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;

/**
 * @author Michael C. Han
 */
public interface AuditRouter {

	@MessagingProxy(mode = ProxyMode.SYNC)
	public boolean isDeployed();

	@MessagingProxy(mode = ProxyMode.ASYNC)
	public void route(AuditMessage auditMessage) throws AuditException;

}