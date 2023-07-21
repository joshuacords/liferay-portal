/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import getAppContext from './Context';
import {Filter} from './filters/index';

function FilterResume(props) {
	const {actions} = getAppContext();
	const [open, setOpen] = useState(false);

	const label = (
		<ClayLabel
			className={classNames(
				'filter-resume component-label tbar-label mr-2',
				props.disabled && 'disabled',
				open && 'active'
			)}
			closeButtonProps={{
				className: 'filter-resume-close',
				disabled: props.disabled,
				onClick: () => actions.updateFilterState(props.id)
			}}
			role="button"
		>
			<div className="filter-resume-content">
				<ClayIcon
					className="mr-2"
					symbol={open ? 'caret-top' : 'caret-bottom'}
				/>
				<div className="label-section">
					{props.label}: {props.formattedValue}
				</div>
			</div>
		</ClayLabel>
	);

	const dropDown = (
		<ClayDropDown
			active={open}
			className="d-inline-flex"
			onActiveChange={setOpen}
			trigger={label}
		>
			<li className="dropdown-subheader">{props.label}</li>
			<Filter {...{...props, actions}} />
		</ClayDropDown>
	);

	return props.disabled ? label : dropDown;
}

FilterResume.propTypes = {
	disabled: PropTypes.bool,
	formattedValue: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
	id: PropTypes.string,
	label: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
};

export default FilterResume;
