/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PreviewButton from '../../../../src/main/resources/META-INF/resources/admin/js/components/PreviewButton/PreviewButton.es';
import Notifications from '../../../../src/main/resources/META-INF/resources/admin/js/util/Notifications.es';

const previewURL = '/my/form/preview';
const spritemap = 'spritemap';

const props = {
	resolvePreviewURL: () => Promise.resolve(previewURL),
	spritemap
};

describe('PreviewButton', () => {
	let component;

	afterEach(() => {
		if (component) {
			component.dispose();
		}
	});

	beforeEach(() => {
		jest.useFakeTimers();
		fetch.resetMocks();
	});

	it('renders', () => {
		component = new PreviewButton(props);

		expect(component).toMatchSnapshot();
	});

	describe('preview()', () => {
		it('calls fetch with published=true', () => {
			component = new PreviewButton(props);

			const windowOpenSpy = jest.spyOn(window, 'open');

			windowOpenSpy.mockImplementation(() => null);

			return component
				.preview()
				.then(() =>
					expect(windowOpenSpy).toHaveBeenCalledWith(
						previewURL,
						'_blank'
					)
				);
		});

		it('is called when button is clicked', () => {
			component = new PreviewButton(props);

			const previewSpy = jest.spyOn(component, 'preview');

			component.refs.button.emit('click');

			jest.runAllTimers();

			expect(previewSpy).toHaveBeenCalled();
		});

		it('shows error notification when resolvePreviewURL fails', () => {
			component = new PreviewButton({
				...props,
				resolvePreviewURL: () => Promise.reject()
			});

			const notificationSpy = jest.spyOn(Notifications, 'showError');

			component.preview().catch(() => {
				expect(notificationSpy).toHaveBeenCalled();
			});
		});
	});
});
