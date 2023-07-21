/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {UPDATE_LAST_SAVE_DATE} from '../actions/actions.es';
import CreateContentDialog from '../components/content/CreateContentDialog.es';

/**
 * @private
 * @review
 * @type {null|{detach: Function}}
 */
let _widgetConfigurationChangeHandler = null;

/**
 * Possible types that can be returned by the image selector
 */
const DOWNLOAD_FILE_ENTRY_IMAGE_SELECTOR_RETURN_TYPE =
	'com.liferay.item.selector.criteria.DownloadFileEntryItemSelectorReturnType';

/**
 * @param {object} options
 * @param {function} options.callback
 * @param {string} options.assetBrowserURL
 * @param {string} options.eventName
 * @param {string} options.modalTitle
 * @param {function} [options.destroyedCallback=null]
 */
function openAssetBrowser({
	assetBrowserURL,
	callback,
	destroyedCallback = null,
	eventName,
	modalTitle
}) {
	Liferay.Util.selectEntity(
		{
			dialog: {
				constrain: true,
				destroyOnHide: true,
				modal: true
			},
			eventName,
			title: modalTitle,
			uri: assetBrowserURL
		},
		event => {
			if (event.assetclassnameid) {
				callback({
					className: event.assetclassname,
					classNameId: event.assetclassnameid,
					classPK: event.assetclasspk,
					title: event.assettitle
				});
			}
			else if (destroyedCallback) {
				destroyedCallback();
			}
		}
	);
}

/**
 * @param {object} store Store
 * @return {CreateContentDialog}
 */
function openCreateContentDialog(store) {
	return new CreateContentDialog({
		store
	});
}

/**
 * @param {object} options
 * @param {function} options.callback
 * @param {string} options.imageSelectorURL
 * @param {string} options.portletNamespace
 * @param {function} [options.destroyedCallback=null]
 */
function openImageSelector({
	callback,
	destroyedCallback = null,
	imageSelectorURL,
	portletNamespace
}) {
	AUI().use('liferay-item-selector-dialog', A => {
		const itemSelector = new A.LiferayItemSelectorDialog({
			eventName: `${portletNamespace}selectImage`,
			on: {
				selectedItemChange: event => {
					const selectedItem = event.newVal || {};

					const {returnType, value} = selectedItem;
					const selectedImage = {};

					if (returnType === 'URL') {
						selectedImage.title = value;
						selectedImage.url = value;
					}

					if (
						returnType ===
						DOWNLOAD_FILE_ENTRY_IMAGE_SELECTOR_RETURN_TYPE
					) {
						const fileEntry = JSON.parse(value);

						selectedImage.title = fileEntry.title;
						selectedImage.url = fileEntry.url;
					}

					if (selectedImage.url) {
						callback(selectedImage);
					}
				},

				visibleChange: event => {
					if (event.newVal === false && destroyedCallback) {
						destroyedCallback();
					}
				}
			},
			title: Liferay.Language.get('select'),
			url: imageSelectorURL
		});

		itemSelector.open();
	});
}

/**
 * @param {{dispatch: Function}} store
 * @review
 */
function startListeningWidgetConfigurationChange(store) {
	stopListeningWidgetConfigurationChange();

	let submitFormHandler = null;

	_widgetConfigurationChangeHandler = Liferay.after('popupReady', event => {
		const configurationForm = event.win.document.querySelector(
			'.portlet-configuration-setup,.portlet-configuration-edit-communications,.portlet-configuration-edit-scope,.portlet-configuration-edit-sharing,.portlet-configuration-edit-supported-clients'
		);

		if (configurationForm) {
			if (submitFormHandler) {
				submitFormHandler.detach();

				submitFormHandler = null;
			}

			submitFormHandler = event.win.Liferay.on('submitForm', () => {
				store.dispatch({
					lastSaveDate: new Date(),
					type: UPDATE_LAST_SAVE_DATE
				});
			});
		}
	});
}

/**
 * @review
 */
function stopListeningWidgetConfigurationChange() {
	if (_widgetConfigurationChangeHandler) {
		_widgetConfigurationChangeHandler.detach();

		_widgetConfigurationChangeHandler = null;
	}
}

export {
	openAssetBrowser,
	openCreateContentDialog,
	openImageSelector,
	startListeningWidgetConfigurationChange,
	stopListeningWidgetConfigurationChange
};
