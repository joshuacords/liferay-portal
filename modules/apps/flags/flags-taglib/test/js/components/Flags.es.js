/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom/extend-expect';
import {
	cleanup,
	fireEvent,
	render,
	waitForElement
} from '@testing-library/react';
import React from 'react';
import {act} from 'react-dom/test-utils';

import Flags from '../../../src/main/resources/META-INF/resources/flags/js/components/Flags.es';

const formDataToObject = formData =>
	Array.from(formData).reduce(
		(memo, [key, val]) => ({
			...memo,
			[key]: val
		}),
		{}
	);

function _renderFlagsComponent({
	captchaURI = '',
	companyName = 'Liferay',
	baseData = {},
	onlyIcon = false,
	pathTermsOfUse = '/',
	reasons = {value: 'text', value2: 'text2'},
	signedIn = false,
	uri = '//'
} = {}) {
	return render(
		<Flags
			baseData={baseData}
			captchaURI={captchaURI}
			companyName={companyName}
			onlyIcon={onlyIcon}
			pathTermsOfUse={pathTermsOfUse}
			reasons={reasons}
			signedIn={signedIn}
			uri={uri}
		/>,
		{
			baseElement: document.body
		}
	);
}

describe('Flags', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {getByRole, getByText} = _renderFlagsComponent();

		expect(getByText('report'));
		expect(getByRole('button'));
	});

	it('renders with only icon visible (text visually hidden)', () => {
		const {getByText} = _renderFlagsComponent({onlyIcon: true});

		expect(getByText('report')).toHaveClass('sr-only');
	});

	it('submits a report successfully with baseData', async () => {
		const {getByRole} = _renderFlagsComponent({
			baseData: {
				testingField: 'testingValue'
			}
		});
		fetch.mockResponse('');

		await act(async () => {
			fireEvent.click(getByRole('button'));

			const form = await waitForElement(() => getByRole('form'));

			[...form.elements].forEach(element => {
				element.value = 'someValue';
			});

			fireEvent.submit(form);
		});

		expect(fetch).toHaveBeenCalledTimes(1);

		const formDataObj = formDataToObject(fetch.mock.calls[0][1].body);
		expect(formDataObj.testingField).toBe('testingValue');
	});
});
