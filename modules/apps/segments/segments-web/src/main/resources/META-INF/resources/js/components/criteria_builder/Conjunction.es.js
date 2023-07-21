/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import getCN from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

import {conjunctionShape} from '../../utils/types.es';

class Conjunction extends React.Component {
	static propTypes = {
		className: PropTypes.string,
		conjunctionName: PropTypes.string.isRequired,
		editing: PropTypes.bool.isRequired,
		onClick: PropTypes.func,
		supportedConjunctions: PropTypes.arrayOf(conjunctionShape)
	};

	_getConjunctionLabel(conjunctionName, conjunctions) {
		const conjunction = conjunctions.find(
			({name}) => name === conjunctionName
		);

		return conjunction ? conjunction.label : undefined;
	}

	render() {
		const {
			className,
			conjunctionName,
			editing,
			onClick,
			supportedConjunctions
		} = this.props;

		const classnames = getCN(
			{
				'btn-sm conjunction-button': editing,
				'conjunction-label': !editing
			},
			className
		);

		return editing ? (
			<ClayButton
				className={classnames}
				displayType="secondary"
				onClick={onClick}
			>
				{this._getConjunctionLabel(
					conjunctionName,
					supportedConjunctions
				)}
			</ClayButton>
		) : (
			<div className={classnames}>
				{this._getConjunctionLabel(
					conjunctionName,
					supportedConjunctions
				)}
			</div>
		);
	}
}

export default Conjunction;
