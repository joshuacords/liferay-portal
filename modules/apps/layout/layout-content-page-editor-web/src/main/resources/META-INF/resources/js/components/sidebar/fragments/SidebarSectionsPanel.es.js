/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import './SidebarAvailableSections.es';
import templates from './SidebarSectionsPanel.soy';

/**
 * SidebarSectionsPanel
 */
class SidebarSectionsPanel extends Component {}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
SidebarSectionsPanel.STATE = {};

Soy.register(SidebarSectionsPanel, templates);

export {SidebarSectionsPanel};
export default SidebarSectionsPanel;
