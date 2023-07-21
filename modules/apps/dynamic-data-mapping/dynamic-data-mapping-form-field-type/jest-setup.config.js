/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

window.AlloyEditor = {
	Selections: [
		{
			buttons: ['linkEdit'],
			name: 'link'
		},
		{
			buttons: [
				'styles',
				'bold',
				'italic',
				'underline',
				'link',
				'twitter'
			],
			name: 'text'
		}
	]
};

window.AUI = () => ({
	use: (...modules) => {
		const callback = modules[modules.length - 1];

		callback({
			LiferayAlloyEditor: () => ({
				render: () => ({
					destroy: () => {},
					getHTML: () => 'test',
					getNativeEditor: () => ({
						on: () => true,
						setData: () => false
					})
				})
			}),
			one: () => ({
				innerHTML: () => {}
			})
		});
	}
});

window.Liferay.ThemeDisplay.getLanguageId = () => 'end_US';
window.Liferay.Util.isEqual = (a, b) => a === b;

window.themeDisplay = window.Liferay.ThemeDisplay;
