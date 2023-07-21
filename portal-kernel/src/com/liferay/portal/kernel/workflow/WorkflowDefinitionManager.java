/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
@MessagingProxy(mode = ProxyMode.SYNC)
public interface WorkflowDefinitionManager {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #deployWorkflowDefinition(long, long, String, String,
	 *             byte[])}
	 */
	@Deprecated
	public WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, String title, byte[] bytes)
		throws WorkflowException;

	public default WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, String title, String name,
			byte[] bytes)
		throws WorkflowException {

		throw new UnsupportedOperationException();
	}

	public int getActiveWorkflowDefinitionCount(long companyId)
		throws WorkflowException;

	public int getActiveWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException;

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getLatestWorkflowDefinition(long, String)}
	 */
	@Deprecated
	public WorkflowDefinition getLatestKaleoDefinition(
			long companyId, String name)
		throws WorkflowException;

	public default WorkflowDefinition getLatestWorkflowDefinition(
			long companyId, String name)
		throws WorkflowException {

		throw new UnsupportedOperationException();
	}

	public default List<WorkflowDefinition> getLatestWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException {

		Map<String, WorkflowDefinition> latestWorkflowDefinitions =
			new HashMap<>();

		List<WorkflowDefinition> workflowDefinitions = getWorkflowDefinitions(
			companyId, start, end, orderByComparator);

		for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
			String name = workflowDefinition.getName();

			WorkflowDefinition latestWorkflowDefinition =
				latestWorkflowDefinitions.get(name);

			if (latestWorkflowDefinition != null) {
				if (workflowDefinition.getVersion() >
						latestWorkflowDefinition.getVersion()) {

					latestWorkflowDefinitions.put(name, workflowDefinition);
				}
			}
			else {
				latestWorkflowDefinitions.put(name, workflowDefinition);
			}
		}

		return ListUtil.fromMapValues(latestWorkflowDefinitions);
	}

	public WorkflowDefinition getWorkflowDefinition(
			long companyId, String name, int version)
		throws WorkflowException;

	public int getWorkflowDefinitionCount(long companyId)
		throws WorkflowException;

	public int getWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException;

	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	/**
	 * Saves a workflow definition without activating it or validating its data.
	 * To save the definition, validate its data, and activate it, use {@link
	 * #deployWorkflowDefinition(long, long, String, String, byte[])} instead.
	 *
	 * @param  companyId the company ID of the workflow definition
	 * @param  userId the ID of the user saving the workflow definition
	 * @param  title the workflow definition's title
	 * @param  name the workflow definition's name
	 * @param  bytes the data saved as the workflow definition's content
	 * @return the workflow definition
	 * @throws WorkflowException if there was an issue saving the workflow
	 *         definition
	 */
	public default WorkflowDefinition saveWorkflowDefinition(
			long companyId, long userId, String title, String name,
			byte[] bytes)
		throws WorkflowException {

		throw new UnsupportedOperationException();
	}

	public void undeployWorkflowDefinition(
			long companyId, long userId, String name, int version)
		throws WorkflowException;

	public WorkflowDefinition updateActive(
			long companyId, long userId, String name, int version,
			boolean active)
		throws WorkflowException;

	public WorkflowDefinition updateTitle(
			long companyId, long userId, String name, int version, String title)
		throws WorkflowException;

	public void validateWorkflowDefinition(byte[] bytes)
		throws WorkflowException;

}