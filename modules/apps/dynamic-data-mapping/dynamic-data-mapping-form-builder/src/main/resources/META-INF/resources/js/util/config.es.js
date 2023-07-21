/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Config} from 'metal-state';

export const pageStructure = Config.shapeOf({
	description: Config.string(),
	rows: Config.arrayOf(
		Config.shapeOf({
			columns: Config.arrayOf(
				Config.shapeOf({
					fields: Config.array(),
					size: Config.number()
				})
			)
		})
	),
	title: Config.string()
});

export const focusedFieldStructure = Config.shapeOf({
	columnIndex: Config.number(),
	name: Config.string().required(),
	pageIndex: Config.number(),
	rowIndex: Config.number()
});

export const ruleStructure = Config.shapeOf({
	actions: Config.arrayOf(
		Config.shapeOf({
			action: Config.string(),
			label: Config.string(),
			target: Config.string()
		})
	),
	conditions: Config.arrayOf(
		Config.shapeOf({
			operands: Config.arrayOf(
				Config.shapeOf({
					label: Config.string(),
					repeatable: Config.bool(),
					type: Config.string(),
					value: Config.string()
				})
			),
			operator: Config.string()
		})
	),
	logicalOperator: Config.string()
});
