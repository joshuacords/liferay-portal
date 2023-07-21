/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PublishButton from '../../../../src/main/resources/META-INF/resources/admin/js/components/PublishButton/PublishButton.es';

const formInstanceId = '12345';
const namespace = 'portlet_namespace';
const spritemap = 'spritemap';
const url = 'publish/url';

const props = {
	formInstanceId,
	namespace,
	published: true,
	resolvePublishURL: () =>
		new Promise(resolve =>
			resolve({
				formInstanceId,
				publishURL: 'published/form'
			})
		),
	spritemap,
	submitForm: () => false,
	url
};

const mockEvent = {preventDefault: () => false};

describe('PublishButton', () => {
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

	it('renders published', () => {
		component = new PublishButton(props);

		expect(component).toMatchSnapshot();
	});

	it('renders unpublished', () => {
		component = new PublishButton({
			...props,
			published: false
		});

		expect(component).toMatchSnapshot();
	});

	describe('publish()', () => {
		it('calls submitForm()', () => {
			const submitForm = jest.fn();

			component = new PublishButton({
				...props,
				submitForm
			});

			return component
				.publish(mockEvent)
				.then(() => expect(submitForm).toHaveBeenCalled());
		});
	});

	describe('unpublish()', () => {
		it('calls submitForm()', () => {
			const submitForm = jest.fn();

			component = new PublishButton({
				...props,
				submitForm
			});

			return component
				.publish(mockEvent)
				.then(() => expect(submitForm).toHaveBeenCalled());
		});
	});

	describe('toggle()', () => {
		it('calls publish() when props.published=false', () => {
			component = new PublishButton({
				...props,
				published: false
			});

			const publishSpy = jest.spyOn(component, 'publish');

			publishSpy.mockImplementation(() => Promise.resolve());

			return component
				.toggle(mockEvent)
				.then(() => expect(publishSpy).toHaveBeenCalled());
		});

		it('calls unpublish() when props.published=true', () => {
			component = new PublishButton({
				...props,
				published: true
			});

			const unpublishSpy = jest.spyOn(component, 'unpublish');

			unpublishSpy.mockImplementation(() => Promise.resolve());

			return component
				.toggle(mockEvent)
				.then(() => expect(unpublishSpy).toHaveBeenCalled());
		});

		it('is called when button is clicked', () => {
			component = new PublishButton({
				...props,
				published: true
			});

			const toggleSpy = jest.spyOn(component, 'toggle');

			component.refs.button.emit('click');

			jest.runAllTimers();

			expect(toggleSpy).toHaveBeenCalled();
		});
	});
});
