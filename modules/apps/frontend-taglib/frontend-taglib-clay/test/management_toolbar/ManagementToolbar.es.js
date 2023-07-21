/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ManagementToolbar from '../../src/main/resources/META-INF/resources/management_toolbar/ManagementToolbar.es';

describe('ManagementToolbar', () => {
	let managementToolbar;
	let searchContainer;
	let searchContainerCallbacks;
	let searchContainerHandle;

	afterEach(() => managementToolbar.dispose());

	beforeAll(() => {
		global.Liferay = {
			componentReady() {
				return {
					then: jest.fn(cb => cb(searchContainer))
				};
			}
		};
	});

	beforeEach(() => {
		searchContainerCallbacks = {};
		searchContainerHandle = {detach: jest.fn()};

		searchContainer = {
			fire: jest.fn(eventName =>
				searchContainerCallbacks[eventName].apply(this)
			),
			on: jest.fn(eventName => {
				searchContainerCallbacks[eventName] = jest.fn();

				return searchContainerHandle;
			}),
			select: {
				toggleAllRows: jest.fn()
			}
		};

		managementToolbar = new ManagementToolbar({
			spritemap: ''
		});
	});

	it('listens to the rowToggled event from the registered search container', () => {
		searchContainer.fire('rowToggled');

		expect(searchContainerCallbacks.rowToggled).toHaveBeenCalled();
	});

	it('deselects all searchContainer rows', () => {
		managementToolbar._handleClearSelectionButtonClicked();

		expect(searchContainer.select.toggleAllRows).toHaveBeenCalledWith(
			false,
			true
		);
	});

	it('selects all searchContainer rows', () => {
		managementToolbar._handleSelectAllButtonClicked();

		expect(searchContainer.select.toggleAllRows).toHaveBeenCalledWith(
			true,
			true
		);
	});

	it('toggles the searchContainer selected rows', () => {
		managementToolbar._handleSelectPageCheckboxChanged({
			data: {
				checked: true
			}
		});

		expect(searchContainer.select.toggleAllRows).toHaveBeenCalledWith(true);

		managementToolbar._handleSelectPageCheckboxChanged({
			data: {
				checked: false
			}
		});

		expect(searchContainer.select.toggleAllRows).toHaveBeenCalledWith(
			false
		);
	});
});
