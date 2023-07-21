/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import Icon from '../../../shared/components/Icon.es';

const FieldError = ({error}) => (
	<div className="form-feedback-group">
		<div className="form-feedback-item">
			<span className="form-feedback-indicator">
				<Icon iconName="exclamation-full" />
			</span>
			{error}
		</div>
	</div>
);

export default FieldError;
