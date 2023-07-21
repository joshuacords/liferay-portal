/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class WorkflowLogManagerUtil {

	public static int getWorkflowLogCountByWorkflowInstance(
			long companyId, long workflowInstanceId, List<Integer> logTypes)
		throws WorkflowException {

		return getWorkflowLogManager().getWorkflowLogCountByWorkflowInstance(
			companyId, workflowInstanceId, logTypes);
	}

	public static int getWorkflowLogCountByWorkflowTask(
			long companyId, long workflowTaskId, List<Integer> logTypes)
		throws WorkflowException {

		return getWorkflowLogManager().getWorkflowLogCountByWorkflowTask(
			companyId, workflowTaskId, logTypes);
	}

	public static WorkflowLogManager getWorkflowLogManager() {
		return _workflowLogManager;
	}

	public static List<WorkflowLog> getWorkflowLogsByWorkflowInstance(
			long companyId, long workflowInstanceId, List<Integer> logTypes,
			int start, int end,
			OrderByComparator<WorkflowLog> orderByComparator)
		throws WorkflowException {

		return getWorkflowLogManager().getWorkflowLogsByWorkflowInstance(
			companyId, workflowInstanceId, logTypes, start, end,
			orderByComparator);
	}

	public static List<WorkflowLog> getWorkflowLogsByWorkflowTask(
			long companyId, long workflowTaskId, List<Integer> logTypes,
			int start, int end,
			OrderByComparator<WorkflowLog> orderByComparator)
		throws WorkflowException {

		return getWorkflowLogManager().getWorkflowLogsByWorkflowTask(
			companyId, workflowTaskId, logTypes, start, end, orderByComparator);
	}

	public void setWorkflowLogManager(WorkflowLogManager workflowLogManager) {
		_workflowLogManager = workflowLogManager;
	}

	private static WorkflowLogManager _workflowLogManager;

}