/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint no-unused-vars: "warn" */

import ClayButton from '@clayui/button';
import PropTypes from 'prop-types';
import React from 'react';

import Loader from './Loader.es';

const Button = ({children, loading, ...props}) => (
	<ClayButton {...props}>
		<span className="d-inline-flex fragments-editor__button">
			{loading && <Loader />}

			{children}
		</span>
	</ClayButton>
);

Button.defaultProps = {
	loading: false
};

Button.propTypes = {
	children: PropTypes.node.isRequired,
	loading: PropTypes.bool
};

export {Button};
export default Button;
