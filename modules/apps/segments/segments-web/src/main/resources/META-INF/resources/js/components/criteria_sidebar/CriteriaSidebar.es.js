/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {Component} from 'react';

import {propertyGroupShape} from '../../utils/types.es';
import CriteriaSidebarCollapse from './CriteriaSidebarCollapse.es';
import CriteriaSidebarSearchBar from './CriteriaSidebarSearchBar.es';

class CriteriaSidebar extends Component {
	static propTypes = {
		onTitleClicked: PropTypes.func,
		propertyGroups: PropTypes.arrayOf(propertyGroupShape),
		propertyKey: PropTypes.string
	};

	state = {
		searchValue: ''
	};

	_handleOnSearchChange = value => {
		this.setState({searchValue: value});
	};

	render() {
		const {onTitleClicked, propertyGroups, propertyKey} = this.props;

		const {searchValue} = this.state;

		return (
			<div className="criteria-sidebar-root">
				<div className="sidebar-header">
					{Liferay.Language.get('properties')}
				</div>

				<div className="sidebar-search">
					<CriteriaSidebarSearchBar
						onChange={this._handleOnSearchChange}
						searchValue={searchValue}
					/>
				</div>

				<div className="sidebar-collapse">
					<CriteriaSidebarCollapse
						onCollapseClick={onTitleClicked}
						propertyGroups={propertyGroups}
						propertyKey={propertyKey}
						searchValue={searchValue}
					/>
				</div>
			</div>
		);
	}
}

export default CriteriaSidebar;
