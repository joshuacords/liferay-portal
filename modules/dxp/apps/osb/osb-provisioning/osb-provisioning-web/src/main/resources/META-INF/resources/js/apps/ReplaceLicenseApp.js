/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import ErrorBoundary from '../components/ErrorBoundary';
import ReplaceLicense from '../components/license_replacement/ReplaceLicense';

export default props => (
	<ErrorBoundary>
		<ReplaceLicense {...props} />
	</ErrorBoundary>
);
