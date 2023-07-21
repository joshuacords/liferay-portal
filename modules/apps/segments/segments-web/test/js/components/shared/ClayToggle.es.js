/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ClayToggle from '../../../../src/main/resources/META-INF/resources/js/components/shared/ClayToggle.es';

describe('ClayToggle', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {asFragment} = render(<ClayToggle onChange={jest.fn()} />);

		expect(asFragment()).toMatchSnapshot();
	});
});
