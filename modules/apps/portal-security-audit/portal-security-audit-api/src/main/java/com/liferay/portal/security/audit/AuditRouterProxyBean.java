/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.audit;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;

/**
 * @author Michael C. Han
 */
public class AuditRouterProxyBean extends BaseProxyBean implements AuditRouter {

	@Override
	public boolean isDeployed() {
		return false;
	}

	@Override
	public void route(AuditMessage auditMessage) {
		throw new UnsupportedOperationException();
	}

}