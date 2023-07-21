/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import AJAX from '../../../utilities/AJAX/index';

const CATALOG_PATH = '/catalog';

const VERSION = 'v1.0';

function resolveCatalogPath(basePath = '', catalogId = '') {
	return `${basePath}${VERSION}${CATALOG_PATH}/${catalogId}`;
}

export default basePath => ({
	getCatalogById: catalogId =>
		AJAX.GET(resolveCatalogPath(basePath, catalogId))
});
