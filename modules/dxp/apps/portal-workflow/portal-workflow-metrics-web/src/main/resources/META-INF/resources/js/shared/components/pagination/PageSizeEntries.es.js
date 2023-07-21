/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {AppContext} from '../../../components/AppContext.es';
import Icon from '../Icon.es';
import PageSizeItem from './PageSizeItem.es';

/**
 * @class
 * @memberof shared/components
 */
class PageSizeEntries extends React.Component {
	render() {
		const {deltas} = this.context;
		const {pageSizeEntries = deltas, selectedPageSize} = this.props;

		return (
			<div className="dropdown pagination-items-per-page">
				<a
					aria-expanded="false"
					aria-haspopup="true"
					className="dropdown-toggle"
					data-toggle="dropdown"
					href="#1"
					role="button"
				>
					{`${selectedPageSize} ${'Entries'}`}
					<Icon iconName="caret-double-l" />
				</a>
				<div className="dropdown-menu dropdown-menu-top">
					{pageSizeEntries.map((pageSizeKey, index) => (
						<PageSizeItem
							key={`${index}_${pageSizeKey}`}
							pageSize={pageSizeKey}
						/>
					))}
				</div>
			</div>
		);
	}
}

PageSizeEntries.contextType = AppContext;
export default PageSizeEntries;
