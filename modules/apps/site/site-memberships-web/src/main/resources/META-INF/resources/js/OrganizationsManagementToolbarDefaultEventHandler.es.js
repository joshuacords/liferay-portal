/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler, ItemSelectorDialog} from 'frontend-js-web';
import dom from 'metal-dom';

class OrganizationsManagementToolbarDefaultEventHandler extends DefaultEventHandler {
	deleteSelectedOrganizations() {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(this.one('#fm'));
		}
	}

	selectOrganizations(itemData) {
		const itemSelectorDialog = new ItemSelectorDialog({
			buttonAddLabel: Liferay.Language.get('done'),
			eventName: this.ns('selectOrganizations'),
			title: Liferay.Language.get('assign-organizations-to-this-site'),
			url: itemData.selectOrganizationsURL
		});

		itemSelectorDialog.on('selectedItemChange', event => {
			const selectedItem = event.selectedItem;

			if (selectedItem) {
				const addGroupOrganizationsFm = this.one(
					'#addGroupOrganizationsFm'
				);

				selectedItem.forEach(item => {
					dom.append(addGroupOrganizationsFm, item);
				});

				submitForm(addGroupOrganizationsFm);
			}
		});

		itemSelectorDialog.open();
	}
}

export default OrganizationsManagementToolbarDefaultEventHandler;
