/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useContext} from 'react';

import StateContext from '../StateContext.es';

function useSelector(selector) {
	const state = useContext(StateContext);

	return selector(state);
}

export {useSelector};
export default useSelector;
