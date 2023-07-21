/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Date;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowPermissionAdvice {

	public static WorkflowTaskManager create(
		WorkflowTaskManager workflowTaskManager) {

		return (WorkflowTaskManager)ProxyUtil.newProxyInstance(
			WorkflowPermissionAdvice.class.getClassLoader(),
			new Class<?>[] {WorkflowTaskManager.class},
			new WorkflowPermissionInvocationHandler(workflowTaskManager));
	}

	private static final Method _METHOD_ASSIGN_WORKFLOW_TASK_TO_USER;

	static {
		try {
			_METHOD_ASSIGN_WORKFLOW_TASK_TO_USER =
				WorkflowTaskManager.class.getMethod(
					"assignWorkflowTaskToUser", long.class, long.class,
					long.class, long.class, String.class, Date.class,
					Map.class);
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new ExceptionInInitializerError(noSuchMethodException);
		}
	}

	private static class WorkflowPermissionInvocationHandler
		implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments)
			throws Throwable {

			if (_METHOD_ASSIGN_WORKFLOW_TASK_TO_USER.equals(method)) {
				long userId = (Long)arguments[1];

				PermissionChecker permissionChecker =
					PermissionThreadLocal.getPermissionChecker();

				if (permissionChecker.getUserId() != userId) {
					throw new PrincipalException();
				}
			}

			try {
				return method.invoke(_workflowTaskManager, arguments);
			}
			catch (InvocationTargetException invocationTargetException) {
				throw invocationTargetException.getCause();
			}
		}

		private WorkflowPermissionInvocationHandler(
			WorkflowTaskManager workflowTaskManager) {

			_workflowTaskManager = workflowTaskManager;
		}

		private final WorkflowTaskManager _workflowTaskManager;

	}

}