/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fireEvent} from '@testing-library/react';

import '@testing-library/jest-dom';

import FilterVisibility from '../../src/main/resources/META-INF/resources/js/FilterVisibility';

const NAMESPACE = '_com_liferay_asset_list_web_portlet_AssetListPortlet_';

const CMS_TYPE = '20001';
const CUSTOM_OBJECT_TYPE = '20002';
const NON_OBJECT_TYPE = '20003';

const _fireSourceChange = () => {
	const [, onSourceChange] = Liferay.on.mock.calls.find(
		([eventName]) => eventName === `${NAMESPACE}sourceChange`
	);

	onSourceChange();
};

const _getAssetFilterBuilder = () =>
	document.getElementById(`${NAMESPACE}assetFilterBuilderWrapper`);

const _getCollectionFilterBuilder = () =>
	document.getElementById(`${NAMESPACE}collectionFilterBuilderWrapper`);

const _renderSource = (itemType) => {
	document.body.innerHTML = `
		<select id="${NAMESPACE}anyAssetType">
			<option data-object="true" value="${CMS_TYPE}">web-content-article</option>
			<option data-object="true" value="${CUSTOM_OBJECT_TYPE}">custom-object</option>
			<option data-object="false" value="${NON_OBJECT_TYPE}">wiki-page</option>
			<option value="false">select-types</option>
			<option value="true">all-types</option>
		</select>
		<div id="${NAMESPACE}collectionFilterBuilderWrapper"></div>
		<fieldset id="${NAMESPACE}assetFilterBuilderWrapper"></fieldset>
	`;

	_selectItemType(itemType);

	FilterVisibility({namespace: NAMESPACE});
};

const _selectItemType = (value) => {
	const itemTypeSelect = document.getElementById(`${NAMESPACE}anyAssetType`);

	fireEvent.change(itemTypeSelect, {target: {value}});
};

describe('FilterVisibility', () => {
	beforeEach(() => {
		jest.clearAllMocks();
	});

	it('shows the collection filter builder for a CMS item type', () => {
		_renderSource(CMS_TYPE);

		expect(_getCollectionFilterBuilder()).not.toHaveClass('hide');
		expect(_getAssetFilterBuilder()).toHaveClass('hide');
	});

	it('shows the collection filter builder for a custom object item type', () => {
		_renderSource(CUSTOM_OBJECT_TYPE);

		expect(_getCollectionFilterBuilder()).not.toHaveClass('hide');
		expect(_getAssetFilterBuilder()).toHaveClass('hide');
	});

	it('shows the asset filter builder for a non object item type', () => {
		_renderSource(NON_OBJECT_TYPE);

		expect(_getCollectionFilterBuilder()).toHaveClass('hide');
		expect(_getAssetFilterBuilder()).not.toHaveClass('hide');
	});

	it('shows the collection filter builder for multiple item types', () => {
		_renderSource('true');

		expect(_getCollectionFilterBuilder()).not.toHaveClass('hide');
		expect(_getAssetFilterBuilder()).toHaveClass('hide');
	});

	it('swaps in the collection filter builder when a custom object becomes the item type', () => {
		_renderSource(NON_OBJECT_TYPE);

		_selectItemType(CUSTOM_OBJECT_TYPE);

		_fireSourceChange();

		expect(_getCollectionFilterBuilder()).not.toHaveClass('hide');
		expect(_getAssetFilterBuilder()).toHaveClass('hide');
	});

	it('announces the collection filter builder is out of use for a non object item type', () => {
		_renderSource(CUSTOM_OBJECT_TYPE);

		_selectItemType(NON_OBJECT_TYPE);

		_fireSourceChange();

		expect(Liferay.fire).toHaveBeenCalledWith(
			`${NAMESPACE}filterVisibilityChange`,
			{showCollection: false}
		);
	});
});
