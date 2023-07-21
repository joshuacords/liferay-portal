/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './CPOptionList.soy';

/**
 * CPOptionList
 *
 */

class CPOptionList extends Component {
	_handleEditValues(event) {
		var target = event.currentTarget;

		var cpOptionId = target.getAttribute('data-id');

		this.emit('optionSelected', cpOptionId);
		this.emit('editValues', cpOptionId);
	}

	_handleAddOptionClick(event) {
		event.stopImmediatePropagation();
		event.preventDefault();

		this.emit('addOption');
	}

	_handleSelectOptionClick(event) {
		var target = event.currentTarget;

		var cpOptionId = target.getAttribute('data-id');

		this.emit('optionSelected', cpOptionId);
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

CPOptionList.STATE = {
	currentCPOptionId: Config.string(),
	options: Config.array().value([])
};

// Register component

Soy.register(CPOptionList, templates);

export default CPOptionList;
