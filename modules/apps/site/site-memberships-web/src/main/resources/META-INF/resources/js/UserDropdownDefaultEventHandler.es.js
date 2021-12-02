/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
