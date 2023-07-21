/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.workflow;

import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

/**
 * @author Adolfo Pérez
 */
public class WorkflowHandlerReplacer<T> implements AutoCloseable {

	public WorkflowHandlerReplacer(
		String className, WorkflowHandler<T> replacementWorkflowHandler) {

		Registry registry = RegistryUtil.getRegistry();

		_serviceRegistration = registry.registerService(
			(Class<WorkflowHandler<?>>)(Class<?>)WorkflowHandler.class,
			replacementWorkflowHandler,
			HashMapBuilder.<String, Object>put(
				"service.ranking", Integer.MAX_VALUE
			).build());
	}

	@Override
	public void close() {
		_serviceRegistration.unregister();
	}

	private final ServiceRegistration<WorkflowHandler<?>> _serviceRegistration;

}