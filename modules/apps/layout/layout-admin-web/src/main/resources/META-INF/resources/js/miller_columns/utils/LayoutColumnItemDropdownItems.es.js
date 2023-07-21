/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const LAYOUT_COLUMN_ITEM_DROPDOWN_ITEMS = [
	{
		label: Liferay.Language.get('view'),
		name: 'viewLayoutURL'
	},

	{
		label: Liferay.Language.get('edit'),
		name: 'editLayoutURL'
	},

	{
		label: Liferay.Language.get('configure'),
		name: 'configureURL'
	},

	{
		icons: {right: 'shortcut'},
		label: Liferay.Language.get('preview-draft'),
		name: 'previewDraftURL',
		target: '_blank'
	},

	{
		label: Liferay.Language.get('approve-draft'),
		name: 'approveDraftURL'
	},

	{
		/**
		 * Handle copy layout click in order to show simple input modal.
		 * @param {Event} event
		 * @private
		 */
		handleClick: event => {
			event.preventDefault();

			Liferay.Util.openWindow({
				dialog: {
					destroyOnHide: true,
					height: 480,
					resizable: false,
					width: 640
				},
				dialogIframe: {
					bodyCssClass: 'dialog-with-footer'
				},
				id: event.data.item.namespace + 'addLayoutDialog',
				title: Liferay.Language.get('copy-page'),
				uri: event.data.item.href
			});
		},
		label: Liferay.Language.get('copy-page'),
		name: 'copyLayoutURL'
	},

	{
		/**
		 * Handle permission item click in order to open the target href in a dialog.
		 * @param {Event} event
		 * @private
		 */
		handleClick: event => {
			Liferay.Util.openInDialog(
				{...event, currentTarget: event.target.element},
				{
					dialog: {
						destroyOnHide: true
					},
					dialogIframe: {
						bodyCssClass: 'dialog-with-footer'
					},
					uri: event.data.item.href
				}
			);
		},
		label: Liferay.Language.get('permissions'),
		name: 'permissionsURL'
	},

	{
		label: Liferay.Language.get('orphan-widgets'),
		name: 'orphanPortletsURL'
	},

	{
		label: Liferay.Language.get('convert-to-content-page'),
		name: 'convertLayoutURL'
	},

	{
		/**
		 * Handle delete item click in order to show a previous confirmation alert.
		 * @param {Event} event
		 * @private
		 */
		handleClick: event => {
			let deleteMessage;

			if (
				event.data.item.layoutColumnItem.hasChild &&
				event.data.item.layoutColumnItem.hasScopeGroup
			) {
				deleteMessage = Liferay.Language.get(
					'this-page-is-being-used-as-a-scope-for-content-and-also-has-child-pages'
				);
			}
			else if (event.data.item.layoutColumnItem.hasChild) {
				deleteMessage = Liferay.Language.get(
					'this-page-has-child-pages-that-will-also-be-removed'
				);
			}
			else if (event.data.item.layoutColumnItem.hasScopeGroup) {
				deleteMessage = Liferay.Language.get(
					'this-page-is-being-used-as-a-scope-for-content'
				);
			}
			else {
				deleteMessage = Liferay.Language.get(
					'are-you-sure-you-want-to-delete-this-page'
				);
			}

			if (!confirm(deleteMessage)) {
				event.preventDefault();
			}
		},
		label: Liferay.Language.get('delete'),
		name: 'deleteURL'
	}
];

export {LAYOUT_COLUMN_ITEM_DROPDOWN_ITEMS};
export default LAYOUT_COLUMN_ITEM_DROPDOWN_ITEMS;
