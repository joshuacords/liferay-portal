/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

export const AppContext = React.createContext();

export const AppStatus = {
	slaDeleted: 'sla-deleted',
	slaSaved: 'sla-saved',
	slaUpdated: 'sla-updated'
};
