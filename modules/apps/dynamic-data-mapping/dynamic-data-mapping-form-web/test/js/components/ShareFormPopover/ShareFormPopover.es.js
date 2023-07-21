/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ShareFormPopover from '../../../../src/main/resources/META-INF/resources/admin/js/components/ShareFormPopover/ShareFormPopover.es';

const spritemap = 'spritemap';
const url = 'publish/url';

const props = {
	spritemap,
	strings: {
		'copied-to-clipboard': 'copied-to-clipboard'
	},
	url
};

let shareFormIcon;

describe('ShareFormPopover', () => {
	let component;

	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	beforeEach(() => {
		shareFormIcon = document.createElement('span');
		shareFormIcon.classList.add('share-form-icon');

		document.querySelector('body').appendChild(shareFormIcon);

		jest.useFakeTimers();
		fetch.resetMocks();
	});

	it('renders the default markup', () => {
		component = new ShareFormPopover(props);
		expect(component).toMatchSnapshot();
	});

	it("copies the sharable URL to user's clipboard", () => {
		component = new ShareFormPopover(props);
		component._clipboard.emit('success');

		jest.runAllTimers();

		expect(component.state.success).toBeTruthy();
		expect(component).toMatchSnapshot();
	});
});
