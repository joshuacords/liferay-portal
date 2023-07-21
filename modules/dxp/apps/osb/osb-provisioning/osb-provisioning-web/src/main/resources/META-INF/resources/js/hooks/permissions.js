/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {createContext, useContext} from 'react';

const PermissionsContext = createContext();

export function PermissionsProvider({children, permissions = {}}) {
	return (
		<PermissionsContext.Provider value={permissions}>
			{children}
		</PermissionsContext.Provider>
	);
}

export function usePermissions() {
	return useContext(PermissionsContext);
}
