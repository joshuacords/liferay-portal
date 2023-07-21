/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import SupportInformation from '../../../src/main/resources/META-INF/resources/js/components/support_information/SupportInformation';

function renderSupportInformation() {
	return render(
		<SupportInformation
			account={{
				code: '123',
				editAccountURL: 'edit/account/url',
				key: '123',
				name: 'Test Account',
				region: 'United States',
				status: 'Active',
				tier: 'Regular'
			}}
			accountAttachmentURL="account/attachment/url"
			instructions="Sample instructions text"
			language={{id: 'en_US', name: 'English'}}
			languageList={[
				{id: 'en_US', name: 'English'},
				{id: 'zh_CN', name: 'Chinese'},
				{id: 'es_ES', name: 'Spanish'}
			]}
			oemInstructionsFileName="oemInstructionsFile"
			regionNames={['United States', 'China', 'Spain']}
			updateAccountAttachmentURL="update/account/attachment/url"
			updateAccountURL="edit/account/url"
			updateInstructionsURL="update/instructions/url"
			updateLanguageIdURL="update/language/id/url"
		/>
	);
}

describe('SupportInformation', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSupportInformation();

		expect(container).toBeTruthy();
	});

	it('displays Details section', () => {
		const {getByText} = renderSupportInformation();

		getByText('details');
	});

	it('displays Instructions section', () => {
		const {getByText} = renderSupportInformation();

		getByText('support-instructions');
	});
});
