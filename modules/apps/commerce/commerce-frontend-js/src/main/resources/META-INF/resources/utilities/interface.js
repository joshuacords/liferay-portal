/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import AJAX from './AJAX/index';
import {validateEmailAddress} from './email';
import * as Events from './eventsDefinitions';
import {getRandomId, liferayNavigate, serializeParameters} from './index';

const CommerceFrontendUtils = {
	AJAX,
	Events,
	getRandomId,
	liferayNavigate,
	serializeParameters,
	validateEmailAddress
};

export default CommerceFrontendUtils;
