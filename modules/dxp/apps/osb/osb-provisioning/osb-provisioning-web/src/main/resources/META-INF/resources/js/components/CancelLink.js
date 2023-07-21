/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

export default function CancelLink({redirect}) {
	return (
		<a className="btn btn-secondary" href={redirect} type="button">
			{Liferay.Language.get('cancel')}
		</a>
	);
}

CancelLink.propTypes = {
	redirect: PropTypes.string.isRequired
};
