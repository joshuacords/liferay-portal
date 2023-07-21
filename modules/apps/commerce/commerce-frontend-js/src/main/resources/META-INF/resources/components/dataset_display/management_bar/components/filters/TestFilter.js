/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

function getOdataString() {
	return `test ne 4`;
}

function TestFilter(props) {
	const [value, setValue] = useState(props.value);

	return (
		<ClayDropDown.Caption>
			<div className="form-group">
				<div className="input-group">
					<div
						className={classNames('input-group-item', {
							'input-group-prepend': props.inputText
						})}
					>
						<input
							aria-label={props.label}
							className="form-control"
							onChange={e => setValue(e.target.value)}
							type="text"
							value={value || ''}
						/>
					</div>
					<div className="input-group-append input-group-item input-group-item-shrink">
						<span className="input-group-text">
							{Liferay.Language.get('test')}
						</span>
					</div>
				</div>

				<div className="mt-3">
					<ClayButton
						disabled={value === props.value}
						onClick={() =>
							props.actions.updateFilterState(
								props.id,
								value,
								value,
								getOdataString(value, props.id)
							)
						}
						small
					>
						{props.value
							? Liferay.Language.get('edit-filter')
							: Liferay.Language.get('add-filter')}
					</ClayButton>
				</div>
			</div>
		</ClayDropDown.Caption>
	);
}

TestFilter.propTypes = {
	id: PropTypes.string.isRequired,
	invisible: PropTypes.bool,
	label: PropTypes.string.isRequired,
	value: PropTypes.string
};

export default TestFilter;
