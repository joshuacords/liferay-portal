/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ExternalLinksTabPane from '../../../src/main/resources/META-INF/resources/js/components/side_panel/ExternalLinksTabPane';

const defaultProp = [
	{domain: 'dossiera', key: '123', label: 'dossiera-project', url: '/'}
];

function renderExternalLinksTabPane(props) {
	return render(<ExternalLinksTabPane links={props} />);
}

describe('ExternalLinksTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {getByText} = renderExternalLinksTabPane(defaultProp);

		getByText('dossiera-project');
	});

	it('displays a message when there are no external links added for the account', () => {
		const {getByText} = renderExternalLinksTabPane([]);

		getByText('no-external-links-were-found');
	});

	it('displays an external link icon on hover when a url is provided', () => {
		const {getByLabelText} = renderExternalLinksTabPane(defaultProp);

		getByLabelText('external-link');
	});

	it('displays no external link icon on hover when a url is not provided', () => {
		const {queryByLabelText} = renderExternalLinksTabPane([
			{
				domain: 'dossiera',
				key: '123',
				label: 'dossiera-project'
			}
		]);

		expect(queryByLabelText('external-link')).toBeFalsy();
	});
});
