/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useEffect} from 'react';

export default function useClickOutside(callback, ref) {
	return useEffect(() => {
		const handleClickOutside = event => {
			if (ref && !ref.current.contains(event.target)) {
				callback(event);
			}
		};

		document.addEventListener('mousedown', handleClickOutside);

		return () =>
			document.removeEventListener('mousedown', handleClickOutside);
	});
}
