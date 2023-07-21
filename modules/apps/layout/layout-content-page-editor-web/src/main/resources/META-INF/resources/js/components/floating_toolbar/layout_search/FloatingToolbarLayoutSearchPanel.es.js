/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './FloatingToolbarLayoutSearchPanelDelegateTemplate.soy';
import {updateRowConfigAction} from '../../../actions/updateRowConfig.es';
import getConnectedComponent from '../../../store/ConnectedComponent.es';
import {CONFIG_KEYS} from '../../../utils/rowConstants';
import templates from './FloatingToolbarLayoutSearchPanel.soy';

/**
 * FloatingToolbarLayoutSearchPanel
 */
class FloatingToolbarLayoutSearchPanel extends Component {
	/**
	 * Handle layout nonIndexable option change
	 * @param {Event} event
	 */
	_handleLayoutNonIndexableOptionChange(event) {
		this._updateRowConfig({
			[CONFIG_KEYS.nonIndexable]: event.target.checked
		});
	}

	/**
	 * Handle layout nonIndexable checkbox mousedown
	 * @param {Event} event
	 */
	_handleLayoutNonIndexableOptionMousedown(event) {
		event.preventDefault();
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
FloatingToolbarLayoutSearchPanel.STATE = {
	/**
	 * @default undefined
	 * @memberof FloatingToolbarLayoutSearchPanel
	 * @review
	 * @type {!string}
	 */
	itemId: Config.string().required()
};

const ConnectedFloatingToolbarLayoutSearchPanel = getConnectedComponent(
	FloatingToolbarLayoutSearchPanel,
	['layoutData', 'spritemap']
);

Soy.register(ConnectedFloatingToolbarLayoutSearchPanel, templates);

export {FloatingToolbarLayoutSearchPanel};
export default FloatingToolbarLayoutSearchPanel;
