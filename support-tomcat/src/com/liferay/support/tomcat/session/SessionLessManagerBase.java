/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.session;

import org.apache.catalina.Session;
import org.apache.catalina.session.StandardManager;

/**
 * @author Brian Wing Shun Chan
 */
public class SessionLessManagerBase extends StandardManager {

	@Override
	public void add(Session session) {
	}

}