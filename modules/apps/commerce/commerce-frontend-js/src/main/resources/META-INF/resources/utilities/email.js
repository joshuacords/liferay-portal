/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const EMAIL_VALIDATION_PATTERN = /^(\D)+(\w)*((\.(\w)+)?)+@(\D)+(\w)*((\.(\D)+(\w)*)+)?(\.)[a-z]{2,}$/;

export function validateEmailAddress(address) {
	return new Promise((resolve, reject) => {
		const pattern = new RegExp(EMAIL_VALIDATION_PATTERN),
			isValid = pattern.exec(address).length;

		if (isValid) {
			resolve(address);
		}

		reject();
	});
}
