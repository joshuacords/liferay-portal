/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropType from 'prop-types';

function DateRenderer(props) {
	if (!props.value) {
		return null;
	}

	const locale = Liferay.ThemeDisplay.getLanguageId().replace('_', '-');
	const dateOptions = props.options.format || {
		day: 'numeric',
		hour: 'numeric',
		minute: 'numeric',
		month: 'short',
		second: 'numeric',
		year: 'numeric'
	};
	const formattedDate = new Intl.DateTimeFormat(locale, dateOptions).format(
		new Date(props.value)
	);

	return formattedDate;
}

DateRenderer.propTypes = {
	options: PropType.shape({
		format: PropType.object
	}),
	value: PropType.string.isRequired
};

export default DateRenderer;
