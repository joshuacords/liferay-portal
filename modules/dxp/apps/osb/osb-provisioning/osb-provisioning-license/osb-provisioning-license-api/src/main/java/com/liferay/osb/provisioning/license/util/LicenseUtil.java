/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.util;

import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.util.comparator.LicenseKeyExpirationDateComparator;
import com.liferay.osb.provisioning.license.util.comparator.LicenseKeyStartDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Amos Fong
 */
public class LicenseUtil {

	public static OrderByComparator getLicenseKeyOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("expiration-date")) {
			orderByComparator = new LicenseKeyExpirationDateComparator(
				orderByAsc);
		}
		else {
			orderByComparator = new LicenseKeyStartDateComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static boolean isAggregate(List<LicenseKey> licenseKeys)
		throws PortalException {

		licenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = licenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!licenseKey.isActive()) {
				itr.remove();
			}
		}

		if (licenseKeys.isEmpty() || (licenseKeys.size() <= 1)) {
			return false;
		}

		LicenseKey firstLicenseKey = licenseKeys.get(0);

		int licenseVersion = firstLicenseKey.getLicenseVersion();
		String productVersion = firstLicenseKey.getProductVersion();
		Date startDate = firstLicenseKey.getStartDate();
		Date expirationDate = firstLicenseKey.getExpirationDate();

		for (LicenseKey licenseKey : licenseKeys) {
			int curLicenseVersion = licenseKey.getLicenseVersion();

			if ((curLicenseVersion < 3) ||
				(curLicenseVersion != licenseVersion)) {

				return false;
			}

			String curProductVersion = licenseKey.getProductVersion();

			if (!curProductVersion.equals(productVersion)) {
				return false;
			}

			String curLicenseEntryType = licenseKey.getLicenseEntryType();

			if (!curLicenseEntryType.equals(LicenseType.PRODUCTION)) {
				return false;
			}

			if (!DateUtil.equals(startDate, licenseKey.getStartDate())) {
				return false;
			}

			if (!DateUtil.equals(
					expirationDate, licenseKey.getExpirationDate())) {

				return false;
			}
		}

		return true;
	}

}