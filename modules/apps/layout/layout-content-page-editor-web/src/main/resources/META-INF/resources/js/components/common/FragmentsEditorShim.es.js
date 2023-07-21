/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import templates from './FragmentsEditorShim.soy';

/**
 * FragmentsEditorShim
 */
class FragmentsEditorShim extends Component {}

Soy.register(FragmentsEditorShim, templates);

export {FragmentsEditorShim};
export default FragmentsEditorShim;
