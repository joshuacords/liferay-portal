/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.assignment;

import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignmentSelector;
import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignmentSelectorRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true, service = TaskAssignmentSelectorRegistry.class)
public class TaskAssignmentSelectorTracker
	implements TaskAssignmentSelectorRegistry {

	@Override
	public TaskAssignmentSelector getTaskAssignmentSelector(
		String assigneeClassName) {

		return _taskAssignmentSelectors.get(assigneeClassName);
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

		Object assigneeClassName = properties.get("assignee.class.name");

		_taskAssignmentSelectors.put(
			assigneeClassName.toString(), taskAssignmentSelector);
	}

	protected void removeTaskAssignmentSelector(
		TaskAssignmentSelector taskAssignmentSelector,
		Map<String, Object> properties) {

		String assigneeClassName = (String)properties.get(
			"assignee.class.name");

		_taskAssignmentSelectors.remove(assigneeClassName);
	}

	private final Map<String, TaskAssignmentSelector> _taskAssignmentSelectors =
		new ConcurrentHashMap<>();

}