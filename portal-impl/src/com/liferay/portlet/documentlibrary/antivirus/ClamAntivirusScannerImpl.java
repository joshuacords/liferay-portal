/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.antivirus;

import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.document.library.kernel.antivirus.BaseFileAntivirusScanner;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author Michael C. Han
 */
public class ClamAntivirusScannerImpl extends BaseFileAntivirusScanner {

	@Override
	public void scan(File file) throws AntivirusScannerException {
		int exitValue = 0;

		try {
			exitValue = _execute("clamdscan", file);
		}
		catch (InterruptedException | IOException exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to successfully execute clamdscan", exception);
			}

			exitValue = -1;
		}

		try {
			if ((exitValue != 0) && (exitValue != 1)) {
				exitValue = _execute("clamscan", file);
			}

			if (exitValue == 1) {
				throw new AntivirusScannerException(
					"Virus detected in " + file.getAbsolutePath(),
					AntivirusScannerException.VIRUS_DETECTED);
			}
			else if (exitValue >= 2) {
				throw new AntivirusScannerException(
					AntivirusScannerException.PROCESS_FAILURE);
			}
		}
		catch (InterruptedException | IOException exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to successfully execute clamscan", exception);
			}

			throw new AntivirusScannerException(
				AntivirusScannerException.PROCESS_FAILURE, exception);
		}
	}

	private int _execute(String command, File file)
		throws InterruptedException, IOException {

		Process process = null;

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
				command, "--stdout", "--no-summary", file.getAbsolutePath());

			processBuilder.redirectErrorStream(true);

			process = processBuilder.start();

			process.waitFor();

			return process.exitValue();
		}
		finally {
			if (process != null) {
				process.destroy();
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClamAntivirusScannerImpl.class);

}