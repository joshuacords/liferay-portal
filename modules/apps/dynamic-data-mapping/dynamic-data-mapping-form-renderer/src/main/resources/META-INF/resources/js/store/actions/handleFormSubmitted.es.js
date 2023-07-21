/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {evaluate} from '../../util/evaluation.es';
import {PagesVisitor} from '../../util/visitors.es';

export default evaluatorContext => {
	return evaluate(null, evaluatorContext).then(evaluatedPages => {
		let validForm = true;
		const visitor = new PagesVisitor(evaluatedPages);

		visitor.mapFields(({valid}) => {
			if (!valid) {
				validForm = false;
			}
		});

		return Promise.resolve(validForm);
	});
};
