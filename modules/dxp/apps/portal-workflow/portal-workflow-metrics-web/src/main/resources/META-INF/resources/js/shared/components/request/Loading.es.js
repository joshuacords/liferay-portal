/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useContext, useState} from 'react';

import LoadingState from '../loading/LoadingState.es';

function useLoading() {
	const [loading, setLoading] = useState(false);

	return {
		loading,
		setLoading
	};
}

const LoadingContext = createContext(false);

function Loading({children}) {
	const {loading} = useContext(LoadingContext);

	return (
		loading &&
		(children || (
			<div className="pb-6 pt-5">
				<LoadingState />
			</div>
		))
	);
}

export {Loading, LoadingContext, useLoading};
