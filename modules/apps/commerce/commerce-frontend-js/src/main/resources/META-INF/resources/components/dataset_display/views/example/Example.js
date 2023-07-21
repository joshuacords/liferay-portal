/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

function Example(props) {
	return (
		<ClayList className="bg-white mb-0 p-3">
			<pre className="mb-0 text-wrap">{JSON.stringify(props.items)}</pre>
		</ClayList>
	);
}

Example.propTypes = {
	dataRenderers: PropTypes.object,
	datasetDisplayContext: PropTypes.any,
	items: PropTypes.array
};

Example.defaultProps = {
	items: []
};

export default Example;
