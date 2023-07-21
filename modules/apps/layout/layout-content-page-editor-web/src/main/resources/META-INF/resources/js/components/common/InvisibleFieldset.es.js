/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint no-unused-vars: "warn" */

import PropTypes from 'prop-types';
import React from 'react';

const InvisibleFieldset = props => (
	<fieldset disabled={props.disabled}>{props.children}</fieldset>
);

InvisibleFieldset.defaultProps = {
	disabled: false
};

InvisibleFieldset.propTypes = {
	children: PropTypes.node.isRequired,
	disabled: PropTypes.bool
};

export {InvisibleFieldset};
export default InvisibleFieldset;
