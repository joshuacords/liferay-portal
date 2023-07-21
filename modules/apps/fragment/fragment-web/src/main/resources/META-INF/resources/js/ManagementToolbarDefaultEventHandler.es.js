/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler, openSimpleInputModal} from 'frontend-js-web';
import {Config} from 'metal-state';

class ManagementToolbarDefaultEventHandler extends DefaultEventHandler {
	addFragmentEntry(itemData) {
		openSimpleInputModal({
			dialogTitle: itemData.title,
			formSubmitURL: itemData.addFragmentEntryURL,
			mainFieldLabel: Liferay.Language.get('name'),
			mainFieldName: 'name',
			mainFieldPlaceholder: Liferay.Language.get('name'),
			namespace: this.namespace,
			spritemap: this.spritemap
		});
	}

	copySelectedFragmentEntries() {
		const fragmentEntryIds = Liferay.Util.listCheckedExcept(
			this.one('#fm'),
			this.ns('allRowIds')
		);

		this.one('#fragmentCollectionId').value = this.fragmentCollectionId;
		this.one('#fragmentEntryIds').value = fragmentEntryIds;

		submitForm(this.one('#fragmentEntryFm'), this.copyFragmentEntryURL);
	}

	copyToSelectedFragmentEntries() {
		this._selectFragmentCollection(this.copyFragmentEntryURL);
	}

	copyToSelectedContributedFragmentEntries() {
		const fragmentEntryKeys = Liferay.Util.listCheckedExcept(
			this.one('#fm'),
			this.ns('allRowIds')
		);

		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					destroyOnHide: true,
					modal: true
				},
				eventName: this.ns('selectFragmentCollection'),
				id: this.ns('selectFragmentCollection'),
				title: Liferay.Language.get('select-collection'),
				uri: this.selectFragmentCollectionURL
			},
			selectedItem => {
				if (selectedItem) {
					this.one('#fragmentCollectionId').value = selectedItem.id;
					this.one('#fragmentEntryKeys').value = fragmentEntryKeys;

					submitForm(
						this.one('#fragmentEntryFm'),
						this.copyContributedFragmentEntryURL
					);
				}
			}
		);
	}

	deleteSelectedFragmentEntries() {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(this.one('#fm'), this.deleteFragmentEntriesURL);
		}
	}

	exportSelectedFragmentEntries() {
		submitForm(this.one('#fm'), this.exportFragmentEntriesURL);
	}

	moveSelectedFragmentEntries() {
		this._selectFragmentCollection(this.moveFragmentEntryURL);
	}

	_selectFragmentCollection(targetFragmentEntryURL) {
		const fragmentEntryIds = Liferay.Util.listCheckedExcept(
			this.one('#fm'),
			this.ns('allRowIds')
		);

		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					destroyOnHide: true,
					modal: true
				},
				eventName: this.ns('selectFragmentCollection'),
				id: this.ns('selectFragmentCollection'),
				title: Liferay.Language.get('select-collection'),
				uri: this.selectFragmentCollectionURL
			},
			selectedItem => {
				if (selectedItem) {
					this.one('#fragmentCollectionId').value = selectedItem.id;
					this.one('#fragmentEntryIds').value = fragmentEntryIds;

					submitForm(
						this.one('#fragmentEntryFm'),
						targetFragmentEntryURL
					);
				}
			}
		);
	}
}

ManagementToolbarDefaultEventHandler.STATE = {
	copyContributedFragmentEntryURL: Config.string(),
	copyFragmentEntryURL: Config.string(),
	deleteFragmentEntriesURL: Config.string(),
	exportFragmentEntriesURL: Config.string(),
	fragmentCollectionId: Config.string(),
	moveFragmentEntryURL: Config.string(),
	selectFragmentCollectionURL: Config.string(),
	spritemap: Config.string()
};

export default ManagementToolbarDefaultEventHandler;
