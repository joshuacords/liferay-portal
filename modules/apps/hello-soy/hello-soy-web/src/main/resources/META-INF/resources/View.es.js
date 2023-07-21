/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import './Footer.soy';

import './Header.soy';
import templates from './View.soy';

class View extends Component {}

// Register component

Soy.register(View, templates);

export default View;
