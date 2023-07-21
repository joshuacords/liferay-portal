/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model.impl;

/**
 * @author Amos Fong
 */
public class CommonLicenseKeyImpl extends CommonLicenseKeyBaseImpl {

	public String getFileDir() {
		return "osb_common_license_keys/" + getCommonLicenseKeyId();
	}

	public String getFilePath() {
		return getFileDir() + "/" + getFileName();
	}

}