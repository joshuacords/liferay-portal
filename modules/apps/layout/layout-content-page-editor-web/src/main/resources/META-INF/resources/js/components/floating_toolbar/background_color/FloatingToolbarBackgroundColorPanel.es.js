/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import '../common/FloatingToolbarColorPicker.es';

import './FloatingToolbarBackgroundColorPanelDelegateTemplate.soy';
import {updateRowConfigAction} from '../../../actions/updateRowConfig.es';
import getConnectedComponent from '../../../store/ConnectedComponent.es';
import {CONFIG_KEYS} from '../../../utils/rowConstants';
import templates from './FloatingToolbarBackgroundColorPanel.soy';

/**
 * FloatingToolbarBackgroundColorPanel
 */
class FloatingToolbarBackgroundColorPanel extends Component {
	/**
	 * Handle Clear button click
	 * @private
	 * @review
	 */
	_handleClearButtonClick() {
		this._updateRowConfig({
			[CONFIG_KEYS.backgroundColorCssClass]: ''
		});
	}

	/**
	 * Handle BackgroundColor button click
	 * @param {Event} event
	 * @private
	 * @review
	 */
	_handleBackgroundColorButtonClick(event) {
		this._updateRowConfig({
			[CONFIG_KEYS.backgroundColorCssClass]: event.color
		});
	}

	/**
	 * Updates row configuration
	 * @param {object} config Row configuration
	 * @private
	 * @review
	 */
	_updateRowConfig(config) {
		this.store.dispatch(updateRowConfigAction(this.itemId, config));
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FloatingToolbarBackgroundColorPanel.STATE = {
	/**
	 * @default undefined
	 * @memberof FloatingToolbarBackgroundColorPanel
	 * @review
	 * @type {!string}
	 */
	itemId: Config.string().required()
};

const ConnectedFloatingToolbarBackgroundColorPanel = getConnectedComponent(
	FloatingToolbarBackgroundColorPanel,
	['themeColorsCssClasses']
);

Soy.register(ConnectedFloatingToolbarBackgroundColorPanel, templates);

export {
	ConnectedFloatingToolbarBackgroundColorPanel,
	FloatingToolbarBackgroundColorPanel
};
export default ConnectedFloatingToolbarBackgroundColorPanel;
