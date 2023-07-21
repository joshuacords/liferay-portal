/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import Icon from '../Icon.es';

export default class FilterSearch extends React.Component {
	render() {
		const {children, filteredItems, onChange, totalCount} = this.props;

		const emptyResults = filteredItems.length === 0;
		const searchEnabled = totalCount > 12;

		return (
			<>
				{searchEnabled && (
					<form>
						<div className="dropdown-section">
							<div className="input-group input-group-sm">
								<div className="input-group-item">
									<input
										className="form-control input-group-inset input-group-inset-after"
										onChange={onChange}
										placeholder={Liferay.Language.get(
											'search-for'
										)}
										type="text"
									/>

									<span className="input-group-inset-item input-group-inset-item-after">
										<button
											className="btn btn-unstyled"
											type="button"
										>
											<Icon iconName="search" />
										</button>
									</span>
								</div>
							</div>
						</div>
					</form>
				)}

				{emptyResults && (
					<ul className="list-unstyled">
						<li>
							<span className="disabled dropdown-item">
								{Liferay.Language.get('no-results-were-found')}
							</span>
						</li>
					</ul>
				)}

				{children}
			</>
		);
	}
}
