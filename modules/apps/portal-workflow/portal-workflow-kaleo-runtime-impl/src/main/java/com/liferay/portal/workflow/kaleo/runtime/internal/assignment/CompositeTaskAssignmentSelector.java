/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignmentSelector;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = CompositeTaskAssignmentSelector.class)
public class CompositeTaskAssignmentSelector implements TaskAssignmentSelector {

	@Override
	public Collection<KaleoTaskAssignment> calculateTaskAssignments(
			KaleoTaskAssignment kaleoTaskAssignment,
			ExecutionContext executionContext)
		throws PortalException {

		String assigneeClassName = kaleoTaskAssignment.getAssigneeClassName();

		TaskAssignmentSelector taskAssignmentSelector =
			_taskAssignmentSelectors.get(assigneeClassName);

		if (taskAssignmentSelector == null) {
			throw new IllegalArgumentException(
				"No task assignment selector found for class name " +
					assigneeClassName);
		}

		return taskAssignmentSelector.calculateTaskAssignments(
			kaleoTaskAssignment, executionContext);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(assignee.class.name=*)"
	)
	protected void addTaskAssignmentSelector(
		TaskAssignmentSelector taskAssignmentSelector,
		Map<String, Object> properties) {

		String[] assigneeClassNames = getAssigneeClassNames(
			taskAssignmentSelector, properties);

		for (String assigneeClassName : assigneeClassNames) {
			_taskAssignmentSelectors.put(
				assigneeClassName, taskAssignmentSelector);
		}
	}

	protected String[] getAssigneeClassNames(
		TaskAssignmentSelector taskAssignmentSelector,
		Map<String, Object> properties) {

		Object value = properties.get("assignee.class.name");

		String[] assigneeClassNames = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		if (ArrayUtil.isEmpty(assigneeClassNames)) {
			throw new IllegalArgumentException(
				"The property \"assignee.class.name\" is invalid for " +
					ClassUtil.getClassName(taskAssignmentSelector));
		}

		return assigneeClassNames;
	}

	protected void removeTaskAssignmentSelector(
		TaskAssignmentSelector taskAssignmentSelector,
		Map<String, Object> properties) {

		String[] assigneeClassNames = getAssigneeClassNames(
			taskAssignmentSelector, properties);

		for (String assigneeClassName : assigneeClassNames) {
			_taskAssignmentSelectors.remove(assigneeClassName);
		}
	}

	private final Map<String, TaskAssignmentSelector> _taskAssignmentSelectors =
		new HashMap<>();

}