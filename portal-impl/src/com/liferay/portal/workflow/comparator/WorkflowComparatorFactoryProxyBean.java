/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.comparator;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactory;

/**
 * @author Shuyang Zhou
 */
@OSGiBeanProperties(
	property = "proxy.bean=true", service = WorkflowComparatorFactory.class
)
public class WorkflowComparatorFactoryProxyBean
	extends BaseProxyBean implements WorkflowComparatorFactory {

	@Override
	public OrderByComparator<WorkflowDefinition> getDefinitionNameComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowInstance> getInstanceCompletedComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowInstance> getInstanceEndDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowInstance> getInstanceStartDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowInstance> getInstanceStateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowLog> getLogCreateDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowLog> getLogUserIdComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskCompletionDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskCreateDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskDueDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskModifiedDateComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskNameComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskUserIdComparator(
		boolean ascending) {

		throw new UnsupportedOperationException();
	}

}