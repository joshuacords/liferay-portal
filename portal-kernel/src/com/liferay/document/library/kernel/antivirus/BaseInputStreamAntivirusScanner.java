/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.antivirus;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Michael C. Han
 */
public abstract class BaseInputStreamAntivirusScanner
	implements AntivirusScanner {

	@Override
	public boolean isActive() {
		return _ACTIVE;
	}

	@Override
	public void scan(File file) throws AntivirusScannerException {
		try (InputStream inputStream = new FileInputStream(file)) {
			scan(inputStream);
		}
		catch (FileNotFoundException fileNotFoundException) {
			throw new SystemException(
				"Unable to scan file", fileNotFoundException);
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(ioException, ioException);
			}
		}
	}

	private static final boolean _ACTIVE = true;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseInputStreamAntivirusScanner.class);

}