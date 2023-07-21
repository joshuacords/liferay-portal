/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.test.MockRegistrationReference;
import com.liferay.portal.kernel.nio.intraband.welder.Welder;

/**
 * @author Shuyang Zhou
 */
public class MockWelder implements Welder {

	@Override
	public void destroy() {
	}

	public boolean isClientWelded() {
		return _clientWelded;
	}

	public boolean isServerWelded() {
		return _SERVER_WELDED;
	}

	@Override
	public RegistrationReference weld(Intraband intraband) {
		_clientWelded = true;

		return new MockRegistrationReference(intraband);
	}

	private static final boolean _SERVER_WELDED = false;

	private boolean _clientWelded;

}