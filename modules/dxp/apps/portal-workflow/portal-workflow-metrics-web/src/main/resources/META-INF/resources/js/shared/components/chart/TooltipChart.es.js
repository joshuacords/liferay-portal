/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import getCN from 'classnames';

const CLASSNAME = 'workflow-tooltip-chart';

const Body = (...children) =>
	`<tbody class="${CLASSNAME}-body">${children.join('')}</tbody>`;

const Column = ({align, children, className = '', weight}) => {
	const classes = getCN(`${CLASSNAME}-column`, className, {
		[`font-weight-${weight}`]: weight,
		[`text-${align}`]: align
	});

	return `
		<td>
			<div class="${CLASSNAME}-content ${classes}">
				${children}					
			</div>
		</td>
		`;
};

const Header = (...children) =>
	`<thead class="${CLASSNAME}-header">${children.join('')}</thead>`;

const Row = (...children) =>
	`<tr class="${CLASSNAME}-row">${children.join('')}</tr>`;

const TooltipChart = ({header, rows}) => {
	const columnRows = rows.map(({columns}) => columns[0]);

	return `
		<table class="${CLASSNAME}">
			${Header(
				Row(
					header.map(({label, weight, width}) =>
						Column({
							children: label,
							weight,
							width
						})
					)
				)
			)}

			${Body(
				Row(
					...columnRows.map(({label, weight}) =>
						Column({
							children: label,
							weight
						})
					)
				)
			)}
		</table>
	`;
};

export default TooltipChart;
