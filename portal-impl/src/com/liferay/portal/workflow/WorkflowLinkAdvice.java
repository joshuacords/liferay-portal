/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.RequiredWorkflowDefinitionException;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowLinkAdvice {

	public static WorkflowDefinitionManager create(
		WorkflowDefinitionManager workflowDefinitionManager) {

		return (WorkflowDefinitionManager)ProxyUtil.newProxyInstance(
			WorkflowLinkAdvice.class.getClassLoader(),
			new Class<?>[] {WorkflowDefinitionManager.class},
			new WorkflowLinkInvocationHandler(workflowDefinitionManager));
	}

	private static final Method _UPDATE_ACTIVE_METHOD;

	static {
		try {
			_UPDATE_ACTIVE_METHOD = WorkflowDefinitionManager.class.getMethod(
				"updateActive", long.class, long.class, String.class, int.class,
				boolean.class);
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new ExceptionInInitializerError(noSuchMethodException);
		}
	}

	private static class WorkflowLinkInvocationHandler
		implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments)
			throws Throwable {

			if (_UPDATE_ACTIVE_METHOD.equals(method)) {
				boolean active = (Boolean)arguments[4];

				if (!active) {
					long companyId = (Long)arguments[0];
					String name = (String)arguments[2];
					int version = (Integer)arguments[3];

					List<WorkflowDefinitionLink> workflowDefinitionLinks =
						WorkflowDefinitionLinkLocalServiceUtil.
							getWorkflowDefinitionLinks(
								companyId, name, version);

					if (!workflowDefinitionLinks.isEmpty()) {
						throw new RequiredWorkflowDefinitionException(
							workflowDefinitionLinks);
					}
				}
			}

			try {
				return method.invoke(_workflowDefinitionManager, arguments);
			}
			catch (InvocationTargetException invocationTargetException) {
				throw invocationTargetException.getCause();
			}
		}

		private WorkflowLinkInvocationHandler(
			WorkflowDefinitionManager workflowDefinitionManager) {

			_workflowDefinitionManager = workflowDefinitionManager;
		}

		private final WorkflowDefinitionManager _workflowDefinitionManager;

	}

}