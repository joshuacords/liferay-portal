/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

function getOdataString(value, key) {
	return `${key} eq ${value}`;
}

function NumberFilter(props) {
	const [value, setValue] = useState(props.value);

	return (
		<>
			<ClayDropDown.Caption>
				<div className="form-group">
					<div className="input-group">
						<div
							className={classNames('input-group-item', {
								'input-group-prepend': props.inputText
							})}
						>
							<input
								className="form-control"
								max={props.max}
								min={props.min}
								onChange={e => setValue(e.target.value)}
								type="number"
								value={value || ''}
							/>
						</div>
						{props.inputText && (
							<div className="input-group-append input-group-item input-group-item-shrink">
								<span className="input-group-text">
									{props.inputText}
								</span>
							</div>
						)}
					</div>
				</div>
			</ClayDropDown.Caption>
			<ClayDropDown.Divider />
			<ClayDropDown.Caption>
				<ClayButton
					disabled={Number(value) === props.value}
					onClick={() =>
						props.actions.updateFilterState(
							props.id,
							Number(value),
							value,
							getOdataString(Number(value, props.id))
						)
					}
					small
				>
					{props.value
						? Liferay.Language.get('edit-filter')
						: Liferay.Language.get('add-filter')}
				</ClayButton>
			</ClayDropDown.Caption>
		</>
	);
}

NumberFilter.propTypes = {
	id: PropTypes.string.isRequired,
	inputText: PropTypes.string,
	invisible: PropTypes.bool,
	label: PropTypes.string.isRequired,
	max: PropTypes.number,
	min: PropTypes.number,
	type: PropTypes.oneOf(['number']).isRequired,
	value: PropTypes.number
};

export default NumberFilter;
