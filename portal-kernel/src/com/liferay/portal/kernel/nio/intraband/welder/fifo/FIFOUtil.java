/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.welder.fifo;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;

/**
 * @author Shuyang Zhou
 */
public class FIFOUtil {

	public static void createFIFO(File fifoFile) throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder(
			"mkfifo", fifoFile.getAbsolutePath());

		Process mkfifoProcess = null;

		try {
			mkfifoProcess = processBuilder.start();

			int result = mkfifoProcess.waitFor();

			if (result != 0) {
				throw new Exception(
					"Unable to create FIFO with command \"mkfifo\", external " +
						"process returned " + result);
			}
		}
		finally {
			if (mkfifoProcess != null) {
				mkfifoProcess.destroy();
			}
		}
	}

	public static boolean isFIFOSupported() {
		return _FIFO_SUPPORTED;
	}

	private static final boolean _FIFO_SUPPORTED;

	private static final Log _log = LogFactoryUtil.getLog(FIFOUtil.class);

	static {
		boolean fifoSupport = false;

		try {
			File tempFIFOFile = new File(
				System.getProperty("java.io.tmpdir"),
				"temp-fifo-" + System.currentTimeMillis());

			try {
				createFIFO(tempFIFOFile);
			}
			finally {
				if (!tempFIFOFile.delete() && tempFIFOFile.exists()) {
					tempFIFOFile.deleteOnExit();
				}
			}

			fifoSupport = true;
		}
		catch (Throwable t) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to detect FIFO support", t);
			}
		}

		_FIFO_SUPPORTED = fifoSupport;
	}

}