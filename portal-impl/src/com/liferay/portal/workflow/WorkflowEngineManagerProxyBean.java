/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.workflow.WorkflowEngineManager;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@OSGiBeanProperties(
	property = "proxy.bean=true", service = WorkflowEngineManager.class
)
public class WorkflowEngineManagerProxyBean
	extends BaseProxyBean implements WorkflowEngineManager {

	@Override
	public String getKey() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> getOptionalAttributes() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDeployed() {
		return false;
	}

}