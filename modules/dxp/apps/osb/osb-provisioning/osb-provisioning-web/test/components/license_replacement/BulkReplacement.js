/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import BulkReplacement from '../../../src/main/resources/META-INF/resources/js/components/license_replacement/BulkReplacement';

describe('BulkReplacement', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = render(
			<BulkReplacement
				accountKey="KEY-1"
				replacementURL="/replacement/url"
			/>
		);

		expect(container).toBeTruthy();
	});

	it('triggers the replacement modal when the replace button is clicked', async () => {
		const {getByText} = render(
			<div>
				<button
					onClick={() => {
						const event = new CustomEvent('bulkReplaceLicenses', {
							detail: {
								licenseKeyIds: 'id123',
								modalVisible: true
							}
						});

						window.dispatchEvent(event);
					}}
				>
					Replace
				</button>

				<BulkReplacement
					accountKey="KEY-1"
					replacementURL="/replacement/url"
				/>
			</div>
		);

		fireEvent.click(getByText('Replace'));

		await wait(() => {
			getByText('start-date');
			getByText('expiration-date');
		});
	});
});
