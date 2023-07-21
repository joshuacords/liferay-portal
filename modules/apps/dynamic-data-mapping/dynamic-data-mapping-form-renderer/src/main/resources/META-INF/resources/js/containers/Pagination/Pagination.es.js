/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import templates from './Pagination.soy';

class Pagination extends Component {
	_handleItemClicked({delegateTarget: {dataset}}) {
		const {dispatch} = this.context;
		const {pageIndex} = dataset;

		dispatch('paginationItemClicked', {
			pageIndex: Number(pageIndex)
		});
	}

	_handleNextClicked() {
		const {dispatch} = this.context;

		dispatch('paginationNextClicked');
	}

	_handlePreviousClicked() {
		const {dispatch} = this.context;

		dispatch('paginationPreviousClicked');
	}
}

Soy.register(Pagination, templates);

export default Pagination;
