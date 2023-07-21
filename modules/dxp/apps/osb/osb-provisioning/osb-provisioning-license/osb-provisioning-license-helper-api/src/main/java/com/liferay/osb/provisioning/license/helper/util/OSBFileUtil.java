/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.helper.util;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;

import java.io.File;

/**
 * @author Amos Fong
 * @author Calvin Keum
 */
public class OSBFileUtil {

	public static File createTempFile(String fileName) throws Exception {
		cleanUpDir(new File(_TEMP_DIR), System.currentTimeMillis() - Time.DAY);

		DLStoreUtil.validate(fileName, false);

		return createFile(new File(_TEMP_DIR + fileName));
	}

	protected static void cleanUpDir(File dir, long timeInterval) {
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.lastModified() < timeInterval) {
				FileUtil.delete(file);
			}
		}
	}

	protected static File createFile(File file) throws Exception {
		file.createNewFile();

		return file;
	}

	private static final String _TEMP_DIR =
		PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/osb/temp/";

}