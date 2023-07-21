/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller.comet;

/**
 * @author Edward Han
 * @author Raymond Augé
 */
public class CometHandlerPoolUtil {

	public static void closeCometHandler(String sessionId)
		throws CometException {

		getCometHandlerPool().closeCometHandler(sessionId);
	}

	public static void closeCometHandlers() throws CometException {
		getCometHandlerPool().closeCometHandlers();
	}

	public static CometHandler getCometHandler(String sessionId) {
		return getCometHandlerPool().getCometHandler(sessionId);
	}

	public static CometHandlerPool getCometHandlerPool() {
		return _cometHandlerPool;
	}

	public static void startCometHandler(
			CometSession cometSession, CometHandler cometHandler)
		throws CometException {

		getCometHandlerPool().startCometHandler(cometSession, cometHandler);
	}

	public void setCometHandlerPool(CometHandlerPool cometHandlerPool) {
		_cometHandlerPool = cometHandlerPool;
	}

	private static CometHandlerPool _cometHandlerPool;

}