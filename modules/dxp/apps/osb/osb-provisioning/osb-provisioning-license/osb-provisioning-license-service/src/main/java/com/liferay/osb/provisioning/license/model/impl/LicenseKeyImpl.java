/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model.impl;

import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@JSON(strict = true)
public class LicenseKeyImpl extends LicenseKeyBaseImpl {

	public LicenseKeyImpl() {
	}

	public LicenseEntry fetchLicenseEntry() throws PortalException {
		return LicenseEntryLocalServiceUtil.fetchLicenseEntry(
			getLicenseEntryId());
	}

	public LicenseEntry getLicenseEntry() throws PortalException {
		return LicenseEntryLocalServiceUtil.getLicenseEntry(
			getLicenseEntryId());
	}

	@JSON
	@Override
	public String getProductEntryName() {
		return getProductName();
	}

	@JSON
	@Override
	public String getProductVersionLabel() {
		return getProductVersion();
	}

	public boolean isExpired() {
		Date now = new Date();

		if (now.after(getExpirationDate())) {
			return true;
		}

		return false;
	}

}