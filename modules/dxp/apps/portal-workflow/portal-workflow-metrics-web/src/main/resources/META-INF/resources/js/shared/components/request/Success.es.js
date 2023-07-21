/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useContext} from 'react';

import {ErrorContext} from './Error.es';
import {LoadingContext} from './Loading.es';

function Success({children}) {
	const {error} = useContext(ErrorContext);
	const {loading} = useContext(LoadingContext);

	return !error && !loading && children;
}

export {Success};
