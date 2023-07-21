/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.antivirus;

import java.io.File;
import java.io.InputStream;

/**
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AntivirusScannerUtil {

	public static AntivirusScanner getAntivirusScanner() {
		return _antivirusScanner;
	}

	public static boolean isActive() {
		AntivirusScanner antivirusScanner = getAntivirusScanner();

		if (antivirusScanner == null) {
			return false;
		}

		return antivirusScanner.isActive();
	}

	public static void scan(byte[] bytes) throws AntivirusScannerException {
		if (isActive()) {
			getAntivirusScanner().scan(bytes);
		}
	}

	public static void scan(File file) throws AntivirusScannerException {
		if (isActive()) {
			getAntivirusScanner().scan(file);
		}
	}

	public static void scan(InputStream inputStream)
		throws AntivirusScannerException {

		if (isActive()) {
			getAntivirusScanner().scan(inputStream);
		}
	}

	public void setAntivirusScanner(AntivirusScanner antivirusScanner) {
		_antivirusScanner = antivirusScanner;
	}

	private static AntivirusScanner _antivirusScanner;

}