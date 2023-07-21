/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

// Blackbox the AUI dependency Liferay Item Selector Dialog

import {NAMESPACE} from '../utilities/constants';

export function itemSelectorDialogSelection({title, url}, callback) {
	const A = AUI();

	if (A) {
		A.use('liferay-item-selector-dialog', A => {
			const itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: 'selectedItemChange',
				on: {
					selectedItemChange: event => {
						const newVal = event.newVal;

						if (newVal) {
							callback(newVal);
						}
					}
				},
				strings: {
					add: Liferay.Language.get('done'),
					cancel: Liferay.Language.get('cancel')
				},
				title,
				url
			});

			itemSelectorDialog.open();
		});
	}
}

export function itemSelectorDialogWrapper({formField, formName, title, url}) {
	const A = AUI();

	if (A) {
		A.use('liferay-item-selector-dialog', A => {
			const itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: 'selectedItemChange',
				on: {
					selectedItemChange: event => {
						const newVal = event.newVal;

						if (newVal) {
							const selectedItems = JSON.parse(newVal);

							Liferay.Util.postForm(document[formName], {
								data: {
									[`${NAMESPACE}${formField}`]: selectedItems.key
								}
							});
						}
					}
				},
				strings: {
					add: Liferay.Language.get('done'),
					cancel: Liferay.Language.get('cancel')
				},
				title,
				url
			});

			itemSelectorDialog.open();
		});
	}
}
