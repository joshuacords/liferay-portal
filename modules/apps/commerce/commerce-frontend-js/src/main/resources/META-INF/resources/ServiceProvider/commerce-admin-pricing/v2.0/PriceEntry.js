/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import AJAX from '../../../utilities/AJAX/index';

const PRICE_ENTRIES_PATH = '/price-entries';

const PRICE_LISTS_PATH = '/price-lists';

const VERSION = 'v2.0';

function resolvePath(basePath = '', priceListId = '', priceEntryId = '') {
	return `${basePath}${VERSION}${PRICE_LISTS_PATH}/${priceListId}/${PRICE_ENTRIES_PATH}/${priceEntryId}`;
}

export default basePath => ({
	addPriceEntry: (priceListId, json) =>
		AJAX.POST(resolvePath(basePath, priceListId), json)
});
