/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom/extend-expect';
import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import SegmentEdit from '../../../../src/main/resources/META-INF/resources/js/components/segment_edit/SegmentEdit.es';
import {SOURCES} from '../../../../src/main/resources/META-INF/resources/js/utils/constants.es';

const SOURCE_ICON_TESTID = 'source-icon';

const PROPERTY_GROUPS_BASIC = [
	{
		entityName: 'First Test Values Group',
		name: 'First Test Values Group',
		properties: [
			{
				label: 'Value',
				name: 'value',
				options: [],
				selectEntity: null,
				type: 'string'
			}
		],
		propertyKey: 'first-test-values-group'
	}
];

const DEFAULT_REDIRECT = '/test-url';

const CONTRIBUTORS = [
	{
		conjunctionId: 'and',
		conjunctionInputId: 'conjunction-input-1',
		initialQuery: "(value eq 'value')",
		inputId: 'input-id-for-backend-form',
		propertyKey: 'first-test-values-group'
	}
];

function _renderSegmentEditComponent({
	source = undefined,
	redirect = DEFAULT_REDIRECT,
	hasUpdatePermission = undefined,
	contributors = undefined,
	showInEditMode = undefined
} = {}) {
	return render(
		<SegmentEdit
			availableLocales={{
				en_US: ''
			}}
			contributors={contributors}
			defaultLanguageId="en_US"
			hasUpdatePermission={hasUpdatePermission}
			initialSegmentName={{
				en_US: 'Segment title'
			}}
			locale="en_US"
			redirect={redirect}
			showInEditMode={showInEditMode}
			source={source}
		/>
	);
}

describe('SegmentEdit', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {asFragment} = _renderSegmentEditComponent();

		expect(asFragment()).toMatchSnapshot();
	});

	it('renders with an analytics cloud icon', () => {
		const {icon, name} = SOURCES.ASAH_FARO_BACKEND;

		const {getByTestId} = _renderSegmentEditComponent({
			source: name
		});

		const image = getByTestId(SOURCE_ICON_TESTID);

		expect(image).toHaveAttribute('src', icon);
	});

	it('renders with a dxp icon', () => {
		const {icon, name} = SOURCES.DEFAULT;

		const {getByTestId} = _renderSegmentEditComponent({
			source: name
		});

		const image = getByTestId(SOURCE_ICON_TESTID);

		expect(image).toHaveAttribute('src', icon);
	});

	it('renders with edit buttons if the user has update permissions', () => {
		const hasUpdatePermission = true;

		const {asFragment} = _renderSegmentEditComponent({
			hasUpdatePermission
		});

		expect(asFragment()).toMatchSnapshot();
	});

	it('renders without edit buttons if the user does not have update permissions', () => {
		const hasUpdatePermission = false;

		const {asFragment} = _renderSegmentEditComponent({
			hasUpdatePermission
		});

		expect(asFragment()).toMatchSnapshot();
	});

	it('renders with given values', () => {
		const hasUpdatePermission = true;

		const {asFragment, getByTestId} = render(
			<SegmentEdit
				availableLocales={{
					en_US: ''
				}}
				contributors={CONTRIBUTORS}
				defaultLanguageId="en_US"
				hasUpdatePermission={hasUpdatePermission}
				initialSegmentName={{
					en_US: 'Segment title'
				}}
				locale="en_US"
				propertyGroups={PROPERTY_GROUPS_BASIC}
				redirect="/test-url"
				showInEditMode={true}
			/>
		);

		expect(getByTestId(CONTRIBUTORS[0].inputId).value).toBe(
			CONTRIBUTORS[0].initialQuery
		);

		expect(asFragment()).toMatchSnapshot();
	});

	it('redirects when cancelling without any edition', () => {
		const mockConfirm = jest.fn();
		const mockNavigate = jest.fn();
		window.Liferay.Util.navigate = mockNavigate;
		window.confirm = mockConfirm;

		const hasUpdatePermission = true;

		const {getByText} = _renderSegmentEditComponent({
			contributors: CONTRIBUTORS,
			hasUpdatePermission,
			propertyGroups: PROPERTY_GROUPS_BASIC
		});

		const cancelButton = getByText('cancel');

		expect(cancelButton).not.toBe(null);

		fireEvent.click(cancelButton);

		expect(mockNavigate).toHaveBeenCalledTimes(1);
		expect(mockNavigate).toHaveBeenCalledWith(DEFAULT_REDIRECT);
		expect(mockConfirm).toHaveBeenCalledTimes(0);
	});

	it('redirects when cancelling after title edition', done => {
		const mockConfirm = jest.fn();
		const mockNavigate = jest.fn();
		window.Liferay.Util.navigate = mockNavigate;
		window.confirm = mockConfirm;

		const hasUpdatePermission = true;

		const {getByTestId, getByText} = _renderSegmentEditComponent({
			contributors: CONTRIBUTORS,
			hasUpdatePermission,
			propertyGroups: PROPERTY_GROUPS_BASIC
		});

		const localizedInput = getByTestId('localized-main-input');
		const cancelButton = getByText('cancel');

		fireEvent.change(localizedInput, {target: {value: 'A'}});

		wait(() => expect(localizedInput.value).toBe('A')).then(() => {
			expect(cancelButton).not.toBe(null);

			fireEvent.click(cancelButton);

			expect(mockNavigate).toHaveBeenCalledTimes(0);

			expect(mockConfirm).toHaveBeenCalledTimes(1);
			expect(mockConfirm).toHaveBeenCalledWith(
				'criteria-cancel-confirmation-message'
			);
			done();
		});
	});
});
