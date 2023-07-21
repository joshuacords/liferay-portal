/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.web.internal.search;

import com.liferay.dynamic.data.mapping.constants.DDMPortletKeys;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.util.comparator.DDMFormInstanceRecordIdComparator;
import com.liferay.dynamic.data.mapping.util.comparator.DDMFormInstanceRecordModifiedDateComparator;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Leonardo Barros
 */
public class FormInstanceRecordSearch
	extends SearchContainer<DDMFormInstanceRecord> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-entries-were-found";

	public static Map<String, String> orderableHeaders =
		new HashMap<String, String>() {
			{
				put("modified-date", "modified-date");
			}
		};

	public static OrderByComparator<DDMFormInstanceRecord>
		getDDMFormInstanceRecordOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<DDMFormInstanceRecord> orderByComparator = null;

		if (orderByCol.equals("modified-date")) {
			orderByComparator = new DDMFormInstanceRecordModifiedDateComparator(
				orderByAsc);
		}
		else {
			orderByComparator = new DDMFormInstanceRecordIdComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public FormInstanceRecordSearch(
		PortletRequest portletRequest, PortletURL iteratorURL,
		List<String> headerNames) {

		super(
			portletRequest, new DisplayTerms(portletRequest), null,
			DEFAULT_CUR_PARAM, DEFAULT_DELTA, iteratorURL, headerNames,
			EMPTY_RESULTS_MESSAGE);

		try {
			String keywords = ParamUtil.getString(portletRequest, "keywords");

			if (Validator.isNotNull(keywords)) {
				setSearch(true);
			}

			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
					"view-entries-order-by-col", orderByCol);
				preferences.setValue(
					DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
					"view-entries-order-by-type", orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
					"view-entries-order-by-col", "id");
				orderByType = preferences.getValue(
					DDMPortletKeys.DYNAMIC_DATA_MAPPING_FORM_ADMIN,
					"view-entries-order-by-type", "asc");
			}

			OrderByComparator<DDMFormInstanceRecord> orderByComparator =
				getDDMFormInstanceRecordOrderByComparator(
					orderByCol, orderByType);

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FormInstanceRecordSearch.class);

}