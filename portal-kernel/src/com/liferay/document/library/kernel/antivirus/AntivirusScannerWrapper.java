/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.antivirus;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 */
@Deprecated
public class AntivirusScannerWrapper implements AntivirusScanner {

	public AntivirusScannerWrapper(AntivirusScanner antivirusScanner) {
		_antivirusScanner = antivirusScanner;

		_originalAntivirusScanner = antivirusScanner;
	}

	@Override
	public boolean isActive() {
		return _antivirusScanner.isActive();
	}

	@Override
	public void scan(byte[] bytes) throws AntivirusScannerException {
		_antivirusScanner.scan(bytes);
	}

	@Override
	public void scan(File file) throws AntivirusScannerException {
		_antivirusScanner.scan(file);
	}

	@Override
	public void scan(InputStream inputStream) throws AntivirusScannerException {
		_antivirusScanner.scan(inputStream);
	}

	public void setAntivirusScanner(AntivirusScanner antivirusScanner) {
		if (antivirusScanner == null) {
			_antivirusScanner = _originalAntivirusScanner;
		}
		else {
			_antivirusScanner = antivirusScanner;
		}
	}

	private AntivirusScanner _antivirusScanner;
	private final AntivirusScanner _originalAntivirusScanner;

}