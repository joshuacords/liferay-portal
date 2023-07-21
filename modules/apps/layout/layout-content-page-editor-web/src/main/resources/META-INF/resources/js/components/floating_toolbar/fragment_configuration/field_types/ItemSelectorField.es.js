/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import '../../../common/AssetSelector.es';

import './ItemSelectorFieldDelegateTemplate.soy';
import getConnectedComponent from '../../../../store/ConnectedComponent.es';
import {openAssetBrowser} from '../../../../utils/FragmentsEditorDialogUtils';
import {setIn} from '../../../../utils/FragmentsEditorUpdateUtils.es';
import templates from './ItemSelectorField.soy';

/**
 * ItemSelectorField
 */
class ItemSelectorField extends Component {
	/**
	 * @inheritdoc
	 * @review
	 */
	prepareStateForRender(state) {
		let nextState = state;

		const {typeOptions} = this.field;

		if (typeOptions) {
			const {className, enableSelectTemplate = false} = typeOptions;

			if (className) {
				nextState = setIn(nextState, ['selectedClassName'], className);
			}

			nextState = setIn(
				nextState,
				['enableSelectTemplate'],
				enableSelectTemplate
			);
		}

		return nextState;
	}

	/**
	 * @inheritDoc
	 * @review
	 */
	syncConfigurationValues() {
		if (
			this.configurationValues &&
			this.configurationValues[this.field.name] &&
			this.configurationValues[this.field.name].className
		) {
			const {className} = this.configurationValues[this.field.name];

			const itemType = this.availableAssets.find(
				availableAsset => availableAsset.className === className
			);

			this.availableTemplates = itemType.availableTemplates;
		}
		else {
			this.availableTemplates = [];
		}
	}

	/**
	 * Handle the click in the plus button to open the dialog to select an item
	 * @review
	 */
	_handleItemSelectClick() {
		const className = this.field.typeOptions.className;

		const itemType = this.availableAssets.find(
			availableAsset => availableAsset.className === className
		);

		if (itemType) {
			this._openAssetBrowser(itemType.href, itemType.typeName);
		}
	}

	/**
	 *
	 * @review
	 */
	_handleSelectTemplateValueChanged() {
		const targetElement = window.event.delegateTarget;

		const selectedItem = this.configurationValues[this.field.name];

		selectedItem.template =
			targetElement.options[targetElement.selectedIndex].value;

		this.emit('fieldValueChanged', {
			name: this.field.name,
			value: selectedItem
		});
	}

	/**
	 * Handle the click in the item type dropdown
	 * @param {Event} event
	 * @review
	 */
	_handleItemTypeClick(event) {
		const {
			assetBrowserUrl,
			assetBrowserWindowTitle
		} = event.delegateTarget.dataset;

		this._openAssetBrowser(assetBrowserUrl, assetBrowserWindowTitle);
	}

	/**
	 * Opens asset browser
	 * @param {string} assetBrowserURL
	 * @param {string} assetBrowserWindowTitle
	 * @review
	 */
	_openAssetBrowser(assetBrowserURL, assetBrowserWindowTitle) {
		openAssetBrowser({
			assetBrowserURL,
			callback: selectedAssetEntry => {
				this.emit('fieldValueChanged', {
					name: this.field.name,
					value: {
						className: selectedAssetEntry.className,
						classNameId: selectedAssetEntry.classNameId,
						classPK: selectedAssetEntry.classPK,
						title: selectedAssetEntry.title
					}
				});
			},
			eventName: `${this.portletNamespace}selectAsset`,
			modalTitle: assetBrowserWindowTitle
		});
	}
}

ItemSelectorField.STATE = {
	/**
	 * Available templates of the current className
	 * @default []
	 * @instance
	 * @memberOf ItemSelectorField
	 * @type {array}
	 */
	availableTemplates: Config.arrayOf(
		Config.shapeOf({
			key: Config.string(),
			label: Config.string()
		})
	)
		.internal()
		.value([]),

	/**
	 * Fragment Entry Link Configuration values
	 * @instance
	 * @memberOf ItemSelectorField
	 * @type {object}
	 */
	configurationValues: Config.object(),

	/**
	 * The configuration field
	 * @review
	 * @type {object}
	 */
	field: Config.shapeOf({
		dataType: Config.string(),
		defaultValue: Config.string(),
		description: Config.string(),
		label: Config.string(),
		name: Config.string(),
		type: Config.string(),
		typeOptions: Config.object()
	})
};

const ConnectedItemSelectorField = getConnectedComponent(ItemSelectorField, [
	'availableAssets',
	'portletNamespace',
	'spritemap'
]);

Soy.register(ConnectedItemSelectorField, templates);

export {ItemSelectorField, ConnectedItemSelectorField};
export default ConnectedItemSelectorField;
