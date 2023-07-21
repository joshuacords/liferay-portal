/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import './PasswordRegister.soy';

import Soy from 'metal-soy';

import Text from '../Text/Text.es';
import templates from './Password.soy';

class Password extends Text {}

Soy.register(Password, templates);

export default Password;
