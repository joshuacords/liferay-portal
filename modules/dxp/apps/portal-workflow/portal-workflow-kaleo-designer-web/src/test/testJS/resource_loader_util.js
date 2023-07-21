/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI().use('aui-io', A => {
	Liferay.Test = Liferay.Test || {};

	var loadResource = function(resource) {
		return new Promise((resolve, reject) => {
			A.io.request('/base/src/test/resources/' + resource, {
				dataType: 'text',
				on: {
					failure(err) {
						reject(err);
					},
					success() {
						resolve(this.get('responseData'));
					}
				}
			});
		});
	};

	Liferay.Test.loadResource = loadResource;
});
