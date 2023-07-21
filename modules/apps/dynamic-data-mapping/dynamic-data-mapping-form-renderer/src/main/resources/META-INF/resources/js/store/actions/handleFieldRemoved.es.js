/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {PagesVisitor} from '../../util/visitors.es';

export default (pages, name) => {
	const visitor = new PagesVisitor(pages);

	return visitor.mapColumns(column => {
		return {
			...column,
			fields: column.fields.filter(field => field.name !== name)
		};
	});
};
