/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;

import javax.portlet.PortletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseKeySearchTerms extends LicenseKeyDisplayTerms {

	public LicenseKeySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public Boolean getActive() {
		if (activeLicenses.length == 1) {
			return activeLicenses[0];
		}

		return null;
	}

	public Date getDate(String date) throws ParseException {
		if (Validator.isNotNull(date)) {
			return _dateFormat.parse(date);
		}

		return null;
	}

	public Long[] getLicenseEntryIds() {
		Long[] licenseEntryIds = new Long[types.length];

		for (int i = 0; i < types.length; i++) {
			licenseEntryIds[i] = Long.valueOf(types[i]);
		}

		return licenseEntryIds;
	}

	private final DateFormat _dateFormat =
		DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}