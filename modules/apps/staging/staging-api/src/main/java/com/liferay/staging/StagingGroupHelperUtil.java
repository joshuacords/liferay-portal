/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.staging;

/**
 * @author Máté Thurzó
 */
public class StagingGroupHelperUtil {

	public static StagingGroupHelper getStagingGroupHelper() {
		if (_stagingGroupHelper != null) {
			return _stagingGroupHelper;
		}

		throw new NullPointerException("StagingGroupHelper is null");
	}

	public static void setStagingGroupHelper(
		StagingGroupHelper stagingGroupHelper) {

		if (_stagingGroupHelper != null) {
			stagingGroupHelper = _stagingGroupHelper;

			return;
		}

		_stagingGroupHelper = stagingGroupHelper;
	}

	private static StagingGroupHelper _stagingGroupHelper;

}