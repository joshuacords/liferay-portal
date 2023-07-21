/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.server;

import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
public class FabricServerUtil {

	public static FabricServer getFabricServer() {
		return _fabricServer;
	}

	public static void start() throws Exception {
		getFabricServer().start();
	}

	public static Future<?> stop() throws Exception {
		return getFabricServer().stop();
	}

	public void setFabricServer(FabricServer fabricServer) {
		_fabricServer = fabricServer;
	}

	private static FabricServer _fabricServer;

}