/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {act, renderHook} from '@testing-library/react-hooks';

import {useErrors} from '../../../../src/main/resources/META-INF/resources/js/components/sla/store/ErrorsStore.es';

test('Should test with error', () => {
	const {result} = renderHook(() => useErrors());

	act(() => result.current.setErrors({test: 'test'}));
	expect(result.current.errors).toMatchObject({test: 'test'});
});

test('Should test without error', () => {
	const {result} = renderHook(() => useErrors());

	act(() => result.current.setErrors({}));
	expect(result.current.errors).toMatchObject({});
});
