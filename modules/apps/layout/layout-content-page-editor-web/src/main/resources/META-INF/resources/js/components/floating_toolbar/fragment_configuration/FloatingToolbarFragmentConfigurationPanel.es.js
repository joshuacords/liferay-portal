/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './field_types/ColorPaletteField.es';

import './field_types/CheckboxField.es';

import './field_types/ItemSelectorField.es';

import './field_types/SelectField.es';

import './field_types/TextField.es';

import './FloatingToolbarFragmentConfigurationPanelDelegateTemplate.soy';
import {updateFragmentConfigurationAction} from '../../../actions/updateEditableValue.es';
import {getConnectedComponent} from '../../../store/ConnectedComponent.es';
import {deleteIn, setIn} from '../../../utils/FragmentsEditorUpdateUtils.es';
import templates from './FloatingToolbarFragmentConfigurationPanel.soy';

/**
 * FloatingToolbarFragmentConfigurationPanel
 */
class FloatingToolbarFragmentConfigurationPanel extends Component {
	/**
	 * Handles Configuration change
	 * @private
	 * @review
	 */
	_handleChangeConfiguration(event) {
		const {name: fieldName, value: fieldValue} = event;

		let nextConfigurationValues = setIn(
			this.item.configurationValues,
			[fieldName],
			fieldValue
		);

		Object.keys(nextConfigurationValues).forEach(key => {
			if (
				nextConfigurationValues[key] ===
				this.item.defaultConfigurationValues[key]
			) {
				nextConfigurationValues = deleteIn(nextConfigurationValues, [
					key
				]);
			}
		});

		this._sendConfiguration(nextConfigurationValues);
	}

	/**
	 * Handles Restore button click
	 * @private
	 * @review
	 */
	_handleRestoreButtonClick() {
		this._sendConfiguration({});
	}

	/**
	 * Update editableValues with new configuration
	 * @param {object} configurationValues
	 * @private
	 * @review
	 */
	_sendConfiguration(configurationValues) {
		this.store.dispatch(
			updateFragmentConfigurationAction(
				this.item.fragmentEntryLinkId,
				configurationValues
			)
		);
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FloatingToolbarFragmentConfigurationPanel.STATE = {
	/**
	 * @default undefined
	 * @memberof FloatingToolbarLinkPanel
	 * @review
	 * @type {object}
	 */
	item: Config.object().value(null),

	/**
	 * @default undefined
	 * @memberof FloatingToolbarFragmentConfigurationPanel
	 * @review
	 * @type {!string}
	 */
	itemId: Config.string().required()
};

const ConnectedFloatingToolbarFragmentConfigurationPanel = getConnectedComponent(
	FloatingToolbarFragmentConfigurationPanel,
	['defaultSegmentsExperienceId', 'segmentsExperienceId', 'spritemap']
);

Soy.register(ConnectedFloatingToolbarFragmentConfigurationPanel, templates);

export {
	ConnectedFloatingToolbarFragmentConfigurationPanel,
	FloatingToolbarFragmentConfigurationPanel
};
export default ConnectedFloatingToolbarFragmentConfigurationPanel;
