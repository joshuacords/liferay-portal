/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function(A, Liferay) {
	var Language = {};

	Language.get = function(key) {
		return key;
	};

	A.use('io-base', A => {
		Language.get = A.cached((key, extraParams) => {
			var url =
				themeDisplay.getPathContext() +
				'/language/' +
				themeDisplay.getLanguageId() +
				'/' +
				key +
				'/';

			if (extraParams) {
				if (typeof extraParams == 'string') {
					url += extraParams;
				}
				else if (Array.isArray(extraParams)) {
					url += extraParams.join('/');
				}
			}

			var headers = {
				'X-CSRF-Token': Liferay.authToken
			};

			var value = '';

			A.io(url, {
				headers,
				method: 'GET',
				on: {
					complete(i, o) {
						value = o.responseText;
					}
				},
				sync: true
			});

			return value;
		});
	});

	Liferay.Language = Language;
})(AUI(), Liferay);
