/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upload;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Pei-Jung Lan
 */
public class UploadServletRequestConfigurationHelperUtil {

	public static long getMaxSize() {
		return _uploadServletRequestConfigurationHelper.getMaxSize();
	}

	public static long getMaxTries() {
		return _uploadServletRequestConfigurationHelper.getMaxTries();
	}

	public static String getTempDir() {
		return _uploadServletRequestConfigurationHelper.getTempDir();
	}

	private static volatile UploadServletRequestConfigurationHelper
		_uploadServletRequestConfigurationHelper =
			ServiceProxyFactory.newServiceTrackedInstance(
				UploadServletRequestConfigurationHelper.class,
				UploadServletRequestConfigurationHelperUtil.class,
				"_uploadServletRequestConfigurationHelper", false);

}