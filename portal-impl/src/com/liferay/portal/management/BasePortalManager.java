/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.management;

import com.liferay.portal.kernel.management.ManageAction;
import com.liferay.portal.kernel.management.ManageActionException;
import com.liferay.portal.kernel.management.PortalManager;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class BasePortalManager implements PortalManager {

	@Override
	public <T> T manage(ManageAction<T> manageAction)
		throws ManageActionException {

		return manageAction.action();
	}

}