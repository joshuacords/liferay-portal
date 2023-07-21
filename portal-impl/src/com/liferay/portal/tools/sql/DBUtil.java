/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.sql;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;

/**
 * @author     Alexander Chow
 * @author     Ganesh Ram
 * @deprecated As of Bunyan (6.0.x)
 */
@Deprecated
public abstract class DBUtil implements DB {

	public static DB getInstance() {
		return DBManagerUtil.getDB();
	}

}