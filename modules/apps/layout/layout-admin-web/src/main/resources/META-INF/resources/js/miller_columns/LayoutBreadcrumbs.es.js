/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './LayoutBreadcrumbs.soy';

/**
 * LayoutBreadcrumbs
 */

class LayoutBreadcrumbs extends Component {}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

LayoutBreadcrumbs.STATE = {
	/**
	 * Breadcrumb entries
	 * @default undefined
	 * @instance
	 * @memberof LayoutBreadcrumbs
	 * @type {!Array}
	 */

	breadcrumbEntries: Config.arrayOf(
		Config.shapeOf({
			title: Config.string().required(),
			url: Config.string().required()
		})
	).required()
};

Soy.register(LayoutBreadcrumbs, templates);

export {LayoutBreadcrumbs};
export default LayoutBreadcrumbs;
