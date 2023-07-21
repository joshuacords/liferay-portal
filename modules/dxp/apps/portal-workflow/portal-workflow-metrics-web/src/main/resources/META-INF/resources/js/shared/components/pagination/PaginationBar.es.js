/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {AppContext} from '../../../components/AppContext.es';
import DisplayResult from './DisplayResult.es';
import PageSizeEntries from './PageSizeEntries.es';
import Pagination from './Pagination.es';

/**
 * @class
 * @memberof shared/components
 */
class PaginationBar extends React.Component {
	render() {
		const {deltas, maxPages} = this.context;
		const {
			page,
			pageCount,
			pageSize,
			pageSizes = deltas,
			totalCount
		} = this.props;

		if (totalCount <= pageSizes[0]) {
			return <div className="pagination-bar" />;
		}

		return (
			<div className="pagination-bar">
				<PageSizeEntries
					pageSizeEntries={pageSizes}
					selectedPageSize={pageSize}
				/>

				<DisplayResult
					page={page}
					pageCount={pageCount}
					pageSize={pageSize}
					totalCount={totalCount}
				/>

				<Pagination maxPages={maxPages} totalCount={totalCount} />
			</div>
		);
	}
}

PaginationBar.contextType = AppContext;
export default PaginationBar;
