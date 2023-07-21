/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;

import java.util.List;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
@OSGiBeanProperties(
	property = "proxy.bean=true", service = WorkflowDefinitionManager.class
)
public class WorkflowDefinitionManagerProxyBean
	extends BaseProxyBean implements WorkflowDefinitionManager {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #deployWorkflowDefinition(long, long, String, String,
	 *             byte[])}
	 */
	@Deprecated
	@Override
	public WorkflowDefinition deployWorkflowDefinition(
		long companyId, long userId, String title, byte[] bytes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition deployWorkflowDefinition(
		long companyId, long userId, String title, String name, byte[] bytes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getActiveWorkflowDefinitionCount(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getActiveWorkflowDefinitionCount(long companyId, String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
		long companyId, int start, int end,
		OrderByComparator<WorkflowDefinition> orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
		long companyId, String name, int start, int end,
		OrderByComparator<WorkflowDefinition> orderByComparator) {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getLatestWorkflowDefinition(long, String)}
	 */
	@Deprecated
	@Override
	public WorkflowDefinition getLatestKaleoDefinition(
		long companyId, String name) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition getLatestWorkflowDefinition(
		long companyId, String name) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getLatestWorkflowDefinitions(
		long companyId, int start, int end,
		OrderByComparator<WorkflowDefinition> orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition getWorkflowDefinition(
		long companyId, String name, int version) {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getWorkflowDefinitionCount(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getWorkflowDefinitionCount(long companyId, String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getWorkflowDefinitions(
		long companyId, int start, int end,
		OrderByComparator<WorkflowDefinition> orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowDefinition> getWorkflowDefinitions(
		long companyId, String name, int start, int end,
		OrderByComparator<WorkflowDefinition> orderByComparator) {

		throw new UnsupportedOperationException();
	}

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
	@Override
	public WorkflowDefinition saveWorkflowDefinition(
			long companyId, long userId, String title, String name,
			byte[] bytes)
		throws WorkflowException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void undeployWorkflowDefinition(
		long companyId, long userId, String name, int version) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition updateActive(
		long companyId, long userId, String name, int version, boolean active) {

		throw new UnsupportedOperationException();
	}

	@Override
	public WorkflowDefinition updateTitle(
		long companyId, long userId, String name, int version, String title) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void validateWorkflowDefinition(byte[] bytes) {
		throw new UnsupportedOperationException();
	}

}