/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.servlet;

import com.liferay.vldap.server.internal.VLDAPServer;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
@Component(service = {})
public class VLDAPConfigurator {

	@Activate
	public void activate() throws Exception {
		_vldapServer = new VLDAPServer();

		_vldapServer.init();
	}

	@Deactivate
	public void deactivate() throws Exception {
		_vldapServer.destroy();
	}

	private VLDAPServer _vldapServer;

}