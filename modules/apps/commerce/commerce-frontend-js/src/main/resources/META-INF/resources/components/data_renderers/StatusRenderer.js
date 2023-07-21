/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

function StatusRenderer(props) {
	return props.value ? (
		<span className="taglib-workflow-status">
			<span className="workflow-status">
				<strong
					className={`label status workflow-status-${props.value.label} ${props.value.label} workflow-value`}
				>
					{props.value.label_i18n}
				</strong>
			</span>
		</span>
	) : null;
}

StatusRenderer.propTypes = {
	value: PropTypes.shape({
		code: PropTypes.number,
		label: PropTypes.string,
		label_i18n: PropTypes.string
	})
};

export default StatusRenderer;
