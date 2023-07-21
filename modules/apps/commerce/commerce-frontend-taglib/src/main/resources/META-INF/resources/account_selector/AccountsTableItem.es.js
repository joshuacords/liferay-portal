/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

'use strict';

import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './AccountsTableItem.soy';

import '../autocomplete_item/AutocompleteItem.es';

class AccountsTableItem extends Component {
	_handleItemClick() {
		this.emit('selectAccount', {
			accountId: this.accountId,
			name: this.name,
			thumbnail: this.thumbnail
		});
	}
}

Soy.register(AccountsTableItem, template);

AccountsTableItem.STATE = {
	accountId: Config.oneOfType([Config.number(), Config.string()]).required(),
	name: Config.string(),
	query: Config.string(),
	thumbnail: Config.string()
};

export {AccountsTableItem};
export default AccountsTableItem;
