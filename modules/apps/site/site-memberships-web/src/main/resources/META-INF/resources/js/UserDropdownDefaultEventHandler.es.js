/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler, ItemSelectorDialog} from 'frontend-js-web';
import dom from 'metal-dom';

class UserDropdownDefaultEventHandler extends DefaultEventHandler {
	deleteGroupUsers(itemData) {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(document.hrefFm, itemData.deleteGroupUsersURL);
		}
	}

	assignSiteRoles(itemData) {
		const itemSelectorDialog = new ItemSelectorDialog({
			buttonAddLabel: Liferay.Language.get('done'),
			eventName: this.ns('selectUsersRoles'),
			title: Liferay.Language.get('assign-site-roles'),
			url: itemData.assignSiteRolesURL
		});

		itemSelectorDialog.on('selectedItemChange', event => {
			const selectedItem = event.selectedItem;

			if (selectedItem) {
				const editUserGroupRoleFm = this.one('#editUserGroupRoleFm');

				var availableRowIds = document.createElement('input');
				availableRowIds.setAttribute(
					'name',
					itemData.namespace + 'availableRowIds'
				);
				availableRowIds.value = selectedItem.available
					.reduce((acc, item) => {
						acc.push(item.value);

						return acc;
					}, [])
					.join(',');

				dom.append(editUserGroupRoleFm, availableRowIds);

				selectedItem.current.forEach(item => {
					dom.append(editUserGroupRoleFm, item);
				});

				submitForm(editUserGroupRoleFm, itemData.editUserGroupRoleURL);
			}
		});

		itemSelectorDialog.open();
	}
}

export default UserDropdownDefaultEventHandler;
