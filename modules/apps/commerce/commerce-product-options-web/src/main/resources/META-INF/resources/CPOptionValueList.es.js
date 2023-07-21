/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import dom from 'metal-dom';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './CPOptionValueList.soy';

/**
 * CPOptionValueList
 *
 */

class CPOptionValueList extends Component {
	_handleAddOptionValueClick(event) {
		event.stopImmediatePropagation();
		event.preventDefault();

		this.emit('addOptionValue');
	}

	_handleSelectOptionValueClick(event) {
		var target = event.target;

		var row = dom.closest(target, 'tr');

		var cpOptionValueId = row.getAttribute('data-id');

		this.emit('optionValueSelected', cpOptionValueId);
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

CPOptionValueList.STATE = {
	currentCPOptionValueId: Config.string(),
	optionValues: Config.array().value([])
};

// Register component

Soy.register(CPOptionValueList, templates);

export default CPOptionValueList;
