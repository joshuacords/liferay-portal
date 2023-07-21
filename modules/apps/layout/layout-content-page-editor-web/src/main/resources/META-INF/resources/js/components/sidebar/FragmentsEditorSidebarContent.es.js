/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import './comments/SidebarCommentsPanel.es';

import './fragments/SidebarElementsPanel.es';

import './fragments/SidebarSectionsPanel.es';

import './layouts/SidebarLayoutsPanel.es';

import './mapping/SidebarMappingPanel.es';

import './page_content/SidebarPageContentsPanel.es';

import './page_structure/SidebarPageStructurePanel.es';

import './widgets/SidebarWidgetsPanel.es';
import {UPDATE_SELECTED_SIDEBAR_PANEL_ID} from '../../actions/actions.es';
import {getConnectedComponent} from '../../store/ConnectedComponent.es';
import {setIn} from '../../utils/FragmentsEditorUpdateUtils.es';
import templates from './FragmentsEditorSidebarContent.soy';

/**
 * FragmentsEditorSidebarContent
 * @review
 */
class FragmentsEditorSidebarContent extends Component {
	/**
	 * @inheritdoc
	 * @param {object} state
	 * @review
	 */
	prepareStateForRender(state) {
		let nextState = state;

		if (state.selectedSidebarPanelId && state.sidebarPanels) {
			const selectedSidebarPanel = state.sidebarPanels.find(
				sidebarPanel =>
					sidebarPanel.sidebarPanelId === state.selectedSidebarPanelId
			);

			if (selectedSidebarPanel) {
				nextState = setIn(
					nextState,
					['_selectedSidebarPanel'],
					selectedSidebarPanel
				);
			}
		}

		return nextState;
	}

	/**
	 * Opens look and feel configuration window
	 * @private
	 * @review
	 */
	_handleLookAndFeeldButtonClick() {
		Liferay.Util.navigate(this.lookAndFeelURL);
	}

	/**
	 * Updates active panel
	 * @param {!MouseEvent} event
	 * @private
	 * @review
	 */
	_handlePanelButtonClick(event) {
		let {sidebarPanelId} = event.delegateTarget.dataset;

		if (this.selectedSidebarPanelId === sidebarPanelId) {
			sidebarPanelId = '';
		}

		this.store.dispatch({
			type: UPDATE_SELECTED_SIDEBAR_PANEL_ID,
			value: sidebarPanelId
		});
	}

	/**
	 * Hides sidebar
	 * @private
	 * @review
	 */
	_hideSidebar() {
		this.store.dispatch({
			type: UPDATE_SELECTED_SIDEBAR_PANEL_ID,
			value: ''
		});
	}
}

const ConnectedFragmentsEditorSidebarContent = getConnectedComponent(
	FragmentsEditorSidebarContent,
	['lookAndFeelURL', 'selectedSidebarPanelId', 'sidebarPanels', 'spritemap']
);

Soy.register(ConnectedFragmentsEditorSidebarContent, templates);

export {ConnectedFragmentsEditorSidebarContent, FragmentsEditorSidebarContent};
export default ConnectedFragmentsEditorSidebarContent;
