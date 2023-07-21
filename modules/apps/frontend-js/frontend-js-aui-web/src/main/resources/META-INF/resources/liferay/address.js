/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-address',
	() => {
		if (!Liferay.Address) {
			Liferay.Address = {
				getCountries(callback) {
					Liferay.Service(
						'/country/get-countries',
						{
							active: true
						},
						callback
					);
				},

				getRegions(callback, selectKey) {
					Liferay.Service(
						'/region/get-regions',
						{
							active: true,
							countryId: Number(selectKey)
						},
						callback
					);
				}
			};
		}
	},
	'',
	{
		requires: []
	}
);
