/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.antivirus;

import com.liferay.document.library.kernel.antivirus.AntivirusScanner;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 */
@Deprecated
public class DummyAntivirusScannerImpl implements AntivirusScanner {

	@Override
	public boolean isActive() {
		return _ACTIVE;
	}

	@Override
	public void scan(byte[] bytes) {
	}

	@Override
	public void scan(File file) {
	}

	@Override
	public void scan(InputStream inputStream) {
	}

	private static final boolean _ACTIVE = false;

}