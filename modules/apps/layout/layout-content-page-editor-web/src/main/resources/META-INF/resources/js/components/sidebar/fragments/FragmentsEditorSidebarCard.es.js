/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import {getConnectedComponent} from '../../../store/ConnectedComponent.es';
import templates from './FragmentsEditorSidebarCard.soy';

/**
 * FragmentsEditorSidebarCard
 * @review
 */
class FragmentsEditorSidebarCard extends Component {
	/**
	 * Callback that is executed when a item entry is clicked.
	 * It propagates a itemClick event with the item information.
	 * @param {!MouseEvent} event
	 * @private
	 * @review
	 */
	_handleClick(event) {
		const {itemGroupId, itemId, itemName} = event.delegateTarget.dataset;

		this.emit('itemClick', {
			itemGroupId,
			itemId,
			itemName
		});
	}
}

const ConnectedFragmentsEditorSidebarCard = getConnectedComponent(
	FragmentsEditorSidebarCard,
	['spritemap']
);

Soy.register(ConnectedFragmentsEditorSidebarCard, templates);

export {ConnectedFragmentsEditorSidebarCard, FragmentsEditorSidebarCard};
export default ConnectedFragmentsEditorSidebarCard;
