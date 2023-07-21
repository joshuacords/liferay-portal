/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLabel from '@clayui/label';
import PropTypes from 'prop-types';
import React from 'react';

function LabelRenderer(props) {
	return props.value ? (
		<ClayLabel displayType={props.value.displayStyle || 'info'}>
			{typeof props.value === 'string' ? props.value : props.value.label}
		</ClayLabel>
	) : null;
}

LabelRenderer.propTypes = {
	value: PropTypes.oneOfType([
		PropTypes.shape({
			displayStyle: PropTypes.oneOf([
				'success',
				'info',
				'secondary',
				'warning',
				'danger'
			]),
			label: PropTypes.string
		}),
		PropTypes.string
	])
};

export default LabelRenderer;
