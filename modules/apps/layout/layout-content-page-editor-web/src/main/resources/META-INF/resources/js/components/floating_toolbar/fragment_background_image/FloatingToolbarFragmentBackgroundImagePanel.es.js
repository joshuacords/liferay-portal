/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './FloatingToolbarFragmentBackgroundImagePanelDelegateTemplate.soy';
import {updateEditableValueContentAction} from '../../../actions/updateEditableValue.es';
import {getConnectedComponent} from '../../../store/ConnectedComponent.es';
import {openImageSelector} from '../../../utils/FragmentsEditorDialogUtils';
import {BACKGROUND_IMAGE_FRAGMENT_ENTRY_PROCESSOR} from '../../../utils/constants';
import templates from './FloatingToolbarFragmentBackgroundImagePanel.soy';

/**
 * FloatingToolbarFragmentBackgroundImagePanel
 */
class FloatingToolbarFragmentBackgroundImagePanel extends Component {
	/**
	 * Show image selector
	 * @private
	 * @review
	 */
	_handleSelectButtonClick() {
		openImageSelector({
			callback: image => this._updateFragmentBackgroundImage(image),
			imageSelectorURL: this.imageSelectorURL,
			portletNamespace: this.portletNamespace
		});
	}

	/**
	 * Remove existing image if any
	 * @private
	 * @review
	 */
	_handleClearButtonClick() {
		this._updateFragmentBackgroundImage('');
	}

	/**
	 * Dispatches action to update editableValues with new background image url
	 * @param {string} backgroundImageURL
	 */
	_updateFragmentBackgroundImage(image) {
		this.store.dispatch(
			updateEditableValueContentAction(
				this.item.fragmentEntryLinkId,
				BACKGROUND_IMAGE_FRAGMENT_ENTRY_PROCESSOR,
				this.item.editableId,
				image
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
FloatingToolbarFragmentBackgroundImagePanel.STATE = {
	/**
	 * @default undefined
	 * @memberof FloatingToolbarFragmentBackgroundImagePanel
	 * @review
	 * @type {!object}
	 */
	item: Config.object().required()
};

const ConnectedFloatingToolbarFragmentBackgroundImagePanel = getConnectedComponent(
	FloatingToolbarFragmentBackgroundImagePanel,
	[
		'defaultSegmentsExperienceId',
		'imageSelectorURL',
		'languageId',
		'portletNamespace',
		'segmentsExperienceId'
	]
);

Soy.register(ConnectedFloatingToolbarFragmentBackgroundImagePanel, templates);

export {
	ConnectedFloatingToolbarFragmentBackgroundImagePanel,
	FloatingToolbarFragmentBackgroundImagePanel
};
export default ConnectedFloatingToolbarFragmentBackgroundImagePanel;
