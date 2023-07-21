/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ItemSelectorDialog, PortletBase} from 'frontend-js-web';

class SharedAssets extends PortletBase {
	constructor(config, ...args) {
		super(config, ...args);

		this._selectAssetTypeURL = config.selectAssetTypeURL;
		this._viewAssetTypeURL = config.viewAssetTypeURL;
	}

	handleFilterItemClicked(event) {
		const itemData = event.data.item.data;
		const namespace = this.namespace;
		const viewAssetTypeURL = this._viewAssetTypeURL;

		if (itemData.action === 'openAssetTypesSelector') {
			const itemSelectorDialog = new ItemSelectorDialog({
				buttonAddLabel: Liferay.Language.get('select'),
				eventName: namespace + 'selectAssetType',
				title: Liferay.Language.get('select-asset-type'),
				url: this._selectAssetTypeURL
			});

			itemSelectorDialog.open();

			itemSelectorDialog.on('selectedItemChange', event => {
				const selectedItem = event.selectedItem;

				if (selectedItem) {
					let uri = viewAssetTypeURL;

					uri = Liferay.Util.addParams(
						namespace + 'className=' + selectedItem,
						uri
					);

					location.href = uri;
				}
			});
		}
	}
}

export default SharedAssets;
