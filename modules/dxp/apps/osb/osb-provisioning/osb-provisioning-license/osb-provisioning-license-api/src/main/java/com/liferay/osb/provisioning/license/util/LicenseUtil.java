/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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