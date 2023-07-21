/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';

function LocalizedFieldRenderer(props) {
	return Liferay.Language.get(props.value);
}

LocalizedFieldRenderer.propTypes = {
	value: PropTypes.string
};

export default LocalizedFieldRenderer;
