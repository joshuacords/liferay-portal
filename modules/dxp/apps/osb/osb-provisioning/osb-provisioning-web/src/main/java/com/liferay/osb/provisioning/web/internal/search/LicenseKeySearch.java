/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.osb.provisioning.web.internal.display.context.LicenseKeyDisplay;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Kyle Bischof
 */
public class LicenseKeySearch extends SearchContainer<LicenseKeyDisplay> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-licenses-were-found";

	public LicenseKeySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new LicenseKeyDisplayTerms(portletRequest),
			new LicenseKeySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, Collections.emptyList(),
			EMPTY_RESULTS_MESSAGE);

		LicenseKeyDisplayTerms displayTerms =
			(LicenseKeyDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACCOUNT_KEY, displayTerms.getAccountKey());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACCOUNT_NAME, displayTerms.getAccountName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACTIVE_LICENSES,
			ArrayUtil.toStringArray(displayTerms.getActiveLicenses()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_GT,
			displayTerms.getCreateDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_LT,
			displayTerms.getCreateDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATOR_EMAIL_ADDRESS,
			displayTerms.getCreatorEmailAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_GT,
			displayTerms.getExpirationDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRATION_DATE_LT,
			displayTerms.getExpirationDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.HOST_NAME, displayTerms.getHostName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.IP_ADDRESS, displayTerms.getIpAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.KEY, displayTerms.getKey());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MAC_ADDRESS, displayTerms.getMacAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_GT,
			displayTerms.getModifiedDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_LT,
			displayTerms.getModifiedDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_EMAIL_ADDRESS,
			displayTerms.getModifiedEmailAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.OWNER, displayTerms.getOwner());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCT_PURCHASE_KEY,
			displayTerms.getProductPurchaseKey());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCT_VERSIONS,
			displayTerms.getProductVersions());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCTS, displayTerms.getProducts());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.SERVER_ID, displayTerms.getServerId());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_GT,
			displayTerms.getStartDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_LT,
			displayTerms.getStartDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.TYPES, displayTerms.getTypes());
	}

}