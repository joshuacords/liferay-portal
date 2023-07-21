/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import {ClayTooltipProvider} from '@clayui/tooltip';
import Proptypes from 'prop-types';
import React from 'react';

function TooltipTable(props) {
	return (
		<div className="bg-dark">
			<table className="tooltip-table">
				<tbody>
					{props.value.details.map((detail, i) => (
						<tr key={i}>
							<td className="table-column-text-start">
								{detail.label}
							</td>
							<td className="table-column-text-end">
								{Array.isArray(detail.value)
									? detail.value.join(' | ')
									: detail.value}
							</td>
						</tr>
					))}
					<tr>
						<td className="table-column-text-start">
							{props.value.final.label ||
								Liferay.Language.get('final-price')}
						</td>
						<td className="table-column-text-end">
							{props.value.final.value}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	);
}

function TooltipPriceRenderer(props) {
	if (!props.value) {
		return null;
	}

	return (
		<>
			{props.value.final.value}
			{props.value.details && (
				<ClayTooltipProvider
					contentRenderer={() => <TooltipTable {...props} />}
					delay={0}
				>
					<span
						className="tooltip-provider"
						title={Liferay.Language.get('price-summary')}
					>
						<ClayIcon symbol="info-circle" />
					</span>
				</ClayTooltipProvider>
			)}
		</>
	);
}

TooltipPriceRenderer.propTypes = {
	value: Proptypes.shape({
		details: Proptypes.arrayOf(
			Proptypes.oneOfType([
				Proptypes.shape({
					label: Proptypes.string,
					value: Proptypes.oneOfType([
						Proptypes.number,
						Proptypes.string
					])
				}),
				Proptypes.shape({
					label: Proptypes.string,
					value: Proptypes.arrayOf(
						Proptypes.oneOfType([
							Proptypes.string,
							Proptypes.number
						])
					)
				})
			])
		),
		final: Proptypes.shape({
			label: Proptypes.string,
			value: Proptypes.string.isRequired
		}).isRequired
	})
};

export default TooltipPriceRenderer;
