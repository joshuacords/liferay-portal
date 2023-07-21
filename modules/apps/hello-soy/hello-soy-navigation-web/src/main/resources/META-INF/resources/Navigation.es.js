/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import 'hello-soy-web/Footer.soy';

import 'hello-soy-web/Header.soy';

import templates from './Navigation.soy';

class Navigation extends Component {}

// Register component

Soy.register(Navigation, templates);

export default Navigation;
