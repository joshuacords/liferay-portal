/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import launcher from '../../../src/main/resources/META-INF/resources/components/autocomplete/entry';

import '../../../src/main/resources/META-INF/resources/styles/main.scss';

launcher('autocomplete', 'autocomplete-root', {
	apiUrl: '/o/headless-commerce-admin-catalog/v1.0/products/',
	id: 'autocomplete',
	initialLabel: 'Min',
	initialValue: 'initial-value',
	inputName: 'test-name',
	itemsKey: 'productId',
	itemsLabel: 'externalReferenceCode',
	onUpdate: (value, itemData) =>
		// eslint-disable-next-line no-console
		console.log(`Value: ${value}`, `Data: ${JSON.stringify(itemData)}`)
});

launcher('autocomplete-2', 'autocomplete-root-2', {
	apiUrl: '/o/headless-commerce-admin-catalog/v1.0/products/',
	autofill: true,
	fetchDataDebounce: 1000,
	id: 'autocomplete-2',
	initialLabel: 'Initial Label',
	initialValue: 'initial-value',
	inputName: 'test-name',
	itemsKey: 'productId',
	itemsLabel: 'externalReferenceCode',
	onUpdate: (value, itemData) =>
		// eslint-disable-next-line no-console
		console.log(`Value: ${value}`, `Data: ${JSON.stringify(itemData)}`)
});
