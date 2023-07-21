/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import getConnectedReactComponentAdapter from '../../../store/ReactComponentAdapter.es';
import SidebarComments from './SidebarComments.es';
import templates from './SidebarCommentsPanel.soy';

const SidebarCommentsPanel = getConnectedReactComponentAdapter(
	SidebarComments,
	templates
);

export {SidebarCommentsPanel};
export default SidebarCommentsPanel;
