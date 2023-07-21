/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.directory.web.internal.upgrade.v1_0_0;

import com.liferay.directory.web.internal.constants.DirectoryPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

/**
 * @author Peter Fellwock
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"11", DirectoryPortletKeys.DIRECTORY},
			{"186", DirectoryPortletKeys.FRIENDS_DIRECTORY},
			{"187", DirectoryPortletKeys.SITE_MEMBERS_DIRECTORY},
			{"188", DirectoryPortletKeys.MY_SITES_DIRECTORY}
		};
	}

}