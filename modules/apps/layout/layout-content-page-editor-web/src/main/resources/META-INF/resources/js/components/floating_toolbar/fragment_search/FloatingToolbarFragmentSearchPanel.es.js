/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './FloatingToolbarFragmentSearchPanelDelegateTemplate.soy';
import {updateFragmentSearchOptions} from '../../../actions/updateFragmentSearchOptions.es';
import getConnectedComponent from '../../../store/ConnectedComponent.es';
import {setIn} from '../../../utils/FragmentsEditorUpdateUtils.es';
import templates from './FloatingToolbarFragmentSearchPanel.soy';

/**
 * FloatingToolbarFragmentSearchPanel
 */
class FloatingToolbarFragmentSearchPanel extends Component {
	/**
	 * @inheritDoc
	 */
	prepareStateForRender(state) {
		let nextState = state;

		nextState = setIn(
			nextState,
			['_checked'],
			!!(
				this.layoutData.nonIndexableFragmentEntryLinkIds &&
				this.layoutData.nonIndexableFragmentEntryLinkIds.indexOf(
					this.item.fragmentEntryLinkId
				) !== -1
			)
		);

		return nextState;
	}

	/**
	 * Handle fragment nonIndexable option change
	 * @param {Event} event
	 */
	_handleFragmentNonIndexableOptionChange(event) {
		this.store.dispatch(
			updateFragmentSearchOptions(
				this.item.fragmentEntryLinkId,
				event.target.checked
			)
		);
	}

	/**
	 * Handle fragment nonIndexable checkbox mousedown
	 * @param {Event} event
	 */
	_handleFragmentNonIndexableOptionMousedown(event) {
		event.preventDefault();
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FloatingToolbarFragmentSearchPanel.STATE = {
	/**
	 * @default undefined
	 * @memberof FloatingToolbarFragmentSearchPanel
	 * @review
	 * @type {!object}
	 */
	item: Config.object().required()
};

const ConnectedFloatingToolbarFragmentSearchPanel = getConnectedComponent(
	FloatingToolbarFragmentSearchPanel,
	['layoutData', 'spritemap']
);

Soy.register(ConnectedFloatingToolbarFragmentSearchPanel, templates);

export {FloatingToolbarFragmentSearchPanel};
export default FloatingToolbarFragmentSearchPanel;
