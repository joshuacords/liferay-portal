/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';

class AssetCategoriesManagementToolbarDefaultEventHandler extends DefaultEventHandler {
	deleteSelectedCategories() {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(this.one('#fm'));
		}
	}

	selectCategory(itemData) {
		const namespace = this.namespace;

		AUI().use('liferay-item-selector-dialog', A => {
			const itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: this.ns('selectCategory'),
				on: {
					selectedItemChange(event) {
						const selectedItem = event.newVal;

						const category = selectedItem
							? selectedItem[Object.keys(selectedItem)[0]]
							: null;

						if (category) {
							location.href = Liferay.Util.addParams(
								namespace + 'categoryId=' + category.categoryId,
								itemData.viewCategoriesURL
							);
						}
					}
				},
				strings: {
					add: Liferay.Language.get('select'),
					cancel: Liferay.Language.get('cancel')
				},
				title: Liferay.Language.get('select-category'),
				url: itemData.categoriesSelectorURL
			});

			itemSelectorDialog.open();
		});
	}
}

export default AssetCategoriesManagementToolbarDefaultEventHandler;
