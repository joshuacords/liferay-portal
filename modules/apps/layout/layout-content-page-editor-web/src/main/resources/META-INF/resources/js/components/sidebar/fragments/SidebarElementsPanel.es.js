/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import './SidebarAvailableElements.es';
import templates from './SidebarElementsPanel.soy';

/**
 * SidebarElementsPanel
 */
class SidebarElementsPanel extends Component {}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
SidebarElementsPanel.STATE = {};

Soy.register(SidebarElementsPanel, templates);

export {SidebarElementsPanel};
export default SidebarElementsPanel;
