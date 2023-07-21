/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {NAMESPACE} from '../utilities/constants';

const HiddenForm = React.forwardRef(
	({fields = {}, formAction = '', formName = ''}, ref) => (
		<form action={formAction} method="post" name={formName} ref={ref}>
			<HiddenFields fields={fields} />
		</form>
	)
);

function HiddenFields({fields}) {
	return Object.entries(fields).map(([key, value]) => (
		<input
			key={`${NAMESPACE}${key}`}
			name={`${NAMESPACE}${key}`}
			type="hidden"
			value={value}
		/>
	));
}

HiddenForm.propTypes = {
	fields: PropTypes.object,
	formAction: PropTypes.string,
	formName: PropTypes.string
};

export default HiddenForm;
