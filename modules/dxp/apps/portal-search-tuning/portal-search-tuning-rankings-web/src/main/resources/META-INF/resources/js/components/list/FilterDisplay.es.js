/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import {ClayResultsBar} from '@clayui/management-toolbar';
import {PropTypes} from 'prop-types';
import React, {Component} from 'react';

import {sub} from '../../utils/language.es';

class FilterDisplay extends Component {
	static propTypes = {
		onClear: PropTypes.func,
		searchBarTerm: PropTypes.string,
		totalResultsCount: PropTypes.number
	};

	render() {
		const {onClear, searchBarTerm, totalResultsCount} = this.props;

		return (
			<ClayResultsBar title={Liferay.Language.get('filter')}>
				<ClayResultsBar.Item expand>
					<span className="component-text text-truncate-inline">
						<span className="text-truncate">
							{sub(Liferay.Language.get('x-results-for-x'), [
								totalResultsCount,
								searchBarTerm
							])}
						</span>
					</span>
				</ClayResultsBar.Item>

				<ClayResultsBar.Item>
					<ClayButton
						className="component-link tbar-link"
						displayType="unstyled"
						onClick={onClear}
						small
						title={Liferay.Language.get('clear')}
					>
						{Liferay.Language.get('clear')}
					</ClayButton>
				</ClayResultsBar.Item>
			</ClayResultsBar>
		);
	}
}

export default FilterDisplay;
