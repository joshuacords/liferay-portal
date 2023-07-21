/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.pacl.permission;

import java.security.BasicPermission;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PortalMessageBusPermission extends BasicPermission {

	public static void checkListen(String destinationName) {
		_pacl.checkListen(destinationName);
	}

	public static void checkSend(String destinationName) {
		_pacl.checkSend(destinationName);
	}

	public PortalMessageBusPermission(String name, String destinationName) {
		super(name);

		_destinationName = destinationName;
	}

	@Override
	public String getActions() {
		return _destinationName;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public interface PACL {

		public void checkListen(String destinationName);

		public void checkSend(String destinationName);

	}

	private static final PACL _pacl = new NoPACL();

	private final String _destinationName;

	private static class NoPACL implements PACL {

		@Override
		public void checkListen(String destinationName) {
		}

		@Override
		public void checkSend(String destinationName) {
		}

	}

}