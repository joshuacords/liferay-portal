/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropType from 'prop-types';

import {getValueFromItem} from '../../utilities/index';

function ListRenderer(props) {
	if (!props.value || props.value.length === 0) {
		return null;
	}
	if (props.options.singleItemLabel && props.value.length === 1) {
		return props.options.singleItemLabel;
	}
	if (props.options.multipleItemsLabel && props.value.length > 1) {
		return props.options.multipleItemsLabel;
	}

	return props.value
		.map(el => getValueFromItem(el, props.options.labelKey))
		.join(props.options.separator || ', ');
}

ListRenderer.propTypes = {
	options: PropType.shape({
		labelKey: PropType.oneOfType([PropType.array, PropType.string])
			.isRequired,
		multipleItemsLabel: PropType.string,
		separator: PropType.string,
		singleItemLabel: PropType.string
	}),
	value: PropType.array
};

export default ListRenderer;
