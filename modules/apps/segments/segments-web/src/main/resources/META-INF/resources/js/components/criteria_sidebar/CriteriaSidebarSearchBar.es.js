/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React, {Component} from 'react';

class CriteriaSidebarSearchBar extends Component {
	static propTypes = {
		onChange: PropTypes.func.isRequired,
		searchValue: PropTypes.string
	};

	_handleChange = event => {
		this.props.onChange(event.target.value);
	};

	_handleClear = event => {
		event.preventDefault();

		this.props.onChange('');
	};

	render() {
		const {searchValue} = this.props;

		return (
			<div className="input-group">
				<div className="input-group-item">
					<input
						className="form-control input-group-inset input-group-inset-after"
						data-testid="search-input"
						onChange={this._handleChange}
						placeholder={Liferay.Language.get('search')}
						type="text"
						value={searchValue}
					/>

					<div className="input-group-inset-item input-group-inset-item-after">
						<ClayButton
							data-testid="search-button"
							displayType="unstyled"
							onClick={
								searchValue ? this._handleClear : undefined
							}
						>
							<ClayIcon
								symbol={searchValue ? 'times' : 'search'}
							/>
						</ClayButton>
					</div>
				</div>
			</div>
		);
	}
}

export default CriteriaSidebarSearchBar;
