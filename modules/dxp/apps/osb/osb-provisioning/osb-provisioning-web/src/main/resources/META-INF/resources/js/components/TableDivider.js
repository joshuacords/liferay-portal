/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayTable from '@clayui/table';
import ClayTableCell from '@clayui/table/lib/Cell';
import PropTypes from 'prop-types';
import React from 'react';

export default function TableDivider({colSpan, title = ''}) {
	return (
		<ClayTable.Row divider>
			<ClayTableCell colSpan={colSpan}>{title}</ClayTableCell>
		</ClayTable.Row>
	);
}

TableDivider.propTypes = {
	colSpan: PropTypes.number.isRequired,
	title: PropTypes.string
};
