/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React from 'react';

import TooltipTextRenderer from './TooltipTextRenderer';

function DefaultRenderer(props) {
	switch (true) {
		case typeof props.value === 'number':
			return <>{props.value}</>;
		case !(props.value instanceof Object):
			return <>{props.value || ''}</>;
		case !!props.value.icon:
			return <ClayIcon symbol={props.value.icon} />;
		case !!props.value.iconSymbol && !!props.value.text:
			return <TooltipTextRenderer value={props.value} />;
		case !!props.value.label:
			return <>{props.value.label}</>;
		default:
			throw new Error(
				`The object ${JSON.stringify(
					props.value
				)} doesn't match the template schema`
			);
	}
}

DefaultRenderer.propTypes = {
	value: PropTypes.oneOfType([
		PropTypes.string,
		PropTypes.number,
		PropTypes.shape({
			label: PropTypes.string
		}),
		PropTypes.shape({
			icon: PropTypes.string
		}),
		PropTypes.shape({
			iconSymbol: PropTypes.string,
			text: PropTypes.string
		})
	])
};

export default DefaultRenderer;
