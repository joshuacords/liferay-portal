/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useContext, useState} from 'react';

import ReloadButton from '../list/ReloadButton.es';

function useError() {
	const [error, setError] = useState(null);

	return {
		error,
		setError
	};
}

const ErrorContext = createContext(null);

function Error({children}) {
	const {error} = useContext(ErrorContext);

	return (
		error &&
		(children || (
			<div className="pb-6 pt-5 text-center">
				<p className="small">
					{Liferay.Language.get(
						'there-was-a-problem-retrieving-data-please-try-reloading-the-page'
					)}
				</p>
				<ReloadButton />
			</div>
		))
	);
}

export {Error, ErrorContext, useError};
