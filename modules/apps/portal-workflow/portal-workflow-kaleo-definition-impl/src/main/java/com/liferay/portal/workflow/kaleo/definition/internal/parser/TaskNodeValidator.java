/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.parser;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.TaskForm;
import com.liferay.portal.workflow.kaleo.definition.TaskFormReference;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.definition.parser.NodeValidator;

import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
@Component(
	immediate = true, property = "node.type=TASK", service = NodeValidator.class
)
public class TaskNodeValidator extends BaseNodeValidator<Task> {

	@Override
	protected void doValidate(Definition definition, Task task)
		throws KaleoDefinitionValidationException {

		if (task.getIncomingTransitionsCount() == 0) {
			throw new KaleoDefinitionValidationException.
				MustSetIncomingTransition(task.getName());
		}

		if (task.getOutgoingTransitionsCount() == 0) {
			throw new KaleoDefinitionValidationException.
				MustSetOutgoingTransition(task.getName());
		}

		Set<Assignment> assignments = task.getAssignments();

		if ((assignments == null) || assignments.isEmpty()) {
			throw new KaleoDefinitionValidationException.MustSetAssignments(
				task.getName());
		}

		Set<TaskForm> taskForms = task.getTaskForms();

		for (TaskForm taskForm : taskForms) {
			String formDefinition = taskForm.getFormDefinition();

			TaskFormReference taskFormReference =
				taskForm.getTaskFormReference();

			if (Validator.isNull(formDefinition) ||
				(taskFormReference == null)) {

				throw new KaleoDefinitionValidationException.
					MustSetTaskFormDefinitionOrReference(
						task.getName(), taskForm.getName());
			}
		}
	}

}