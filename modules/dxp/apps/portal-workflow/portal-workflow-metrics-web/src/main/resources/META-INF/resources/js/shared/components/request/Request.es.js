/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {Error, ErrorContext, useError} from './Error.es';
import {Loading, LoadingContext, useLoading} from './Loading.es';
import {Success} from './Success.es';

export default function Request({children}) {
	return (
		<LoadingContext.Provider value={useLoading()}>
			<ErrorContext.Provider value={useError()}>
				{children}
			</ErrorContext.Provider>
		</LoadingContext.Provider>
	);
}

Request.Error = Error;
Request.Loading = Loading;
Request.Success = Success;
