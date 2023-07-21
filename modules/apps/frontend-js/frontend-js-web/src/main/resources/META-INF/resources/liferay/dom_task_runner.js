/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function(Liferay) {
	var DOMTaskRunner = {
		_scheduledTasks: [],

		_taskStates: [],

		addTask(task) {
			var instance = this;

			instance._scheduledTasks.push(task);
		},

		addTaskState(state) {
			var instance = this;

			instance._taskStates.push(state);
		},

		reset() {
			var instance = this;

			instance._taskStates.length = 0;
			instance._scheduledTasks.length = 0;
		},

		runTasks(node) {
			var instance = this;

			var scheduledTasks = instance._scheduledTasks;
			var taskStates = instance._taskStates;

			var tasksLength = scheduledTasks.length;
			var taskStatesLength = taskStates.length;

			for (var i = 0; i < tasksLength; i++) {
				var task = scheduledTasks[i];

				var taskParams = task.params;

				for (var j = 0; j < taskStatesLength; j++) {
					var state = taskStates[j];

					if (task.condition(state, taskParams, node)) {
						task.action(state, taskParams, node);
					}
				}
			}
		}
	};

	Liferay.DOMTaskRunner = DOMTaskRunner;
})(Liferay);
