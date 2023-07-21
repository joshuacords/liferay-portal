/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler.util;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class LdapHandlerThreadLocal {

	public static void clearSocketAddress() {
		_socketAddress.remove();
	}

	public static boolean isHostAllowed(String[] allowList) {
		if (allowList.length == 0) {
			return true;
		}

		SocketAddress socketAddress = _socketAddress.get();

		if (!(socketAddress instanceof InetSocketAddress)) {
			return false;
		}

		InetSocketAddress inetSocketAddress = (InetSocketAddress)socketAddress;

		InetAddress inetAddress = inetSocketAddress.getAddress();

		String hostAddress = inetAddress.getHostAddress();

		if (ArrayUtil.contains(allowList, hostAddress)) {
			return true;
		}

		Set<String> computerAddresses = PortalUtil.getComputerAddresses();

		if (computerAddresses.contains(hostAddress) &&
			ArrayUtil.contains(allowList, _SERVER_IP)) {

			return true;
		}

		return false;
	}

	public static void setSocketAddress(SocketAddress socketAddress) {
		_socketAddress.set(socketAddress);
	}

	private static final String _SERVER_IP = "SERVER_IP";

	private static final ThreadLocal<SocketAddress> _socketAddress =
		new CentralizedThreadLocal<>(
			LdapHandlerThreadLocal.class + "._socketAddress");

}