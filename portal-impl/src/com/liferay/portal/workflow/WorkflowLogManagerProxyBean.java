/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManager;

import java.util.List;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
@OSGiBeanProperties(
	property = "proxy.bean=true", service = WorkflowLogManager.class
)
public class WorkflowLogManagerProxyBean
	extends BaseProxyBean implements WorkflowLogManager {

	@Override
	public int getWorkflowLogCountByWorkflowInstance(
		long companyId, long workflowInstanceId, List<Integer> logTypes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getWorkflowLogCountByWorkflowTask(
		long companyId, long workflowTaskId, List<Integer> logTypes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowLog> getWorkflowLogsByWorkflowInstance(
		long companyId, long workflowInstanceId, List<Integer> logTypes,
		int start, int end, OrderByComparator<WorkflowLog> orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowLog> getWorkflowLogsByWorkflowTask(
		long companyId, long workflowTaskId, List<Integer> logTypes, int start,
		int end, OrderByComparator<WorkflowLog> orderByComparator) {

		throw new UnsupportedOperationException();
	}

}