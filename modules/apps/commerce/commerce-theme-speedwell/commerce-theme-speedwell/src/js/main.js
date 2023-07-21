/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI().ready(() => {
	const Speedwell = window.Speedwell;

	if (!!Speedwell && !!Speedwell.features) {
		Speedwell.features.sliders = [];

		if (
			'sliderCallbacks' in Speedwell.features &&
			Speedwell.features.sliderCallbacks.length
		) {
			Speedwell.features.sliderCallbacks.forEach(cb => {
				const componentReady = Liferay.component('SpeedwellSlider');

				if (componentReady) {
					Speedwell.features.sliders.push(cb(componentReady));
				}
			});

			Speedwell.features.sliderCallbacks = [];
		}
	}
});
