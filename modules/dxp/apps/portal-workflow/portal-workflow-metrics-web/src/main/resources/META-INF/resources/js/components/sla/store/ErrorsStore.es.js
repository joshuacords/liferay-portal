/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {createContext, useState} from 'react';

const useErrors = () => {
	const [errors, setErrors] = useState({});

	return {errors, setErrors};
};

const Errors = createContext({});

export {Errors, useErrors};
