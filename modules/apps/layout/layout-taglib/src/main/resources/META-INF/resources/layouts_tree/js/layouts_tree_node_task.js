/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-layouts-tree-node-task',
	A => {
		var LayoutsTreeNodeTask = A.Component.create({
			EXTENDS: A.TreeNodeTask,

			NAME: 'layoutstreenodetask',

			prototype: {
				_uiSetChecked(val) {
					var instance = this;

					instance._syncIconCheckUI();

					instance
						.get('contentBox')
						.toggleClass(
							A.getClassName('tree', 'node', 'checked'),
							val
						);
				},

				renderUI() {
					var instance = this;

					LayoutsTreeNodeTask.superclass.renderUI.apply(
						instance,
						arguments
					);

					var checkEl = instance.get('checkEl');

					if (checkEl) {
						checkEl.remove();
					}
				},

				toggleCheck() {
					var instance = this;

					var checked = instance.get('checked');

					if (checked) {
						instance.uncheck();
					}
					else {
						instance.check();
					}
				}
			}
		});

		A.LayoutsTreeNodeTask = LayoutsTreeNodeTask;

		A.TreeNode.nodeTypes['liferay-task'] = LayoutsTreeNodeTask;
	},
	'',
	{
		requires: ['aui-tree-node']
	}
);
