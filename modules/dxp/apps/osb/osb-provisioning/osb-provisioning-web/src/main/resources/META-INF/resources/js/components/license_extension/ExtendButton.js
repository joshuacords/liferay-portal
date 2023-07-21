/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import HiddenForm from '../HiddenForm';

const ExtendButton = React.forwardRef(
	({disabled = false, fields, formAction, submitHandler}, ref) => (
		<>
			<HiddenForm
				fields={fields}
				formAction={formAction}
				formName="extendLicensesFm"
				ref={ref}
			/>

			<button
				className="btn btn-secondary btn-sm"
				disabled={disabled}
				onClick={submitHandler}
				role="button"
				type="button"
			>
				{Liferay.Language.get('extend')}
			</button>
		</>
	)
);

ExtendButton.propTypes = {
	disabled: PropTypes.bool,
	fields: PropTypes.object.isRequired,
	formAction: PropTypes.string.isRequired,
	submitHandler: PropTypes.func.isRequired
};

export default ExtendButton;
