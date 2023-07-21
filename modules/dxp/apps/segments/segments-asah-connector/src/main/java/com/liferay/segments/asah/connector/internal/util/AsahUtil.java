/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Eduardo García
 */
public class AsahUtil {

	public static String getAsahFaroBackendDataSourceId(long companyId) {
		return PrefsPropsUtil.getString(
			companyId, "liferayAnalyticsDataSourceId");
	}

	public static String getAsahFaroBackendSecuritySignature(long companyId) {
		return PrefsPropsUtil.getString(
			companyId, "liferayAnalyticsFaroBackendSecuritySignature");
	}

	public static String getAsahFaroBackendURL(long companyId) {
		return PrefsPropsUtil.getString(
			companyId, "liferayAnalyticsFaroBackendURL");
	}

	public static String getAsahProjectId(long companyId) {
		return PrefsPropsUtil.getString(companyId, "liferayAnalyticsProjectId");
	}

	public static boolean isAnalyticsEnabled(long companyId) {
		if (Validator.isNull(getAsahFaroBackendDataSourceId(companyId)) ||
			Validator.isNull(getAsahFaroBackendSecuritySignature(companyId)) ||
			Validator.isNull(getAsahFaroBackendURL(companyId))) {

			return false;
		}

		return true;
	}

	public static boolean isAnalyticsEnabled(long companyId, long groupId) {
		if (!isAnalyticsEnabled(companyId)) {
			return false;
		}

		if (PrefsPropsUtil.getBoolean(
				companyId, "liferayAnalyticsEnableAllGroupIds")) {

			return true;
		}

		String[] liferayAnalyticsGroupIds = PrefsPropsUtil.getStringArray(
			companyId, "liferayAnalyticsGroupIds", StringPool.COMMA);

		if (ArrayUtil.contains(
				liferayAnalyticsGroupIds, String.valueOf(groupId))) {

			return true;
		}

		return false;
	}

	public static boolean isSkipAsahEvent(long companyId, long groupId) {
		if (!isAnalyticsEnabled(companyId, groupId)) {
			return true;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if ((serviceContext != null) &&
			!GetterUtil.getBoolean(
				serviceContext.getAttribute("updateAsah"), true)) {

			return true;
		}

		return false;
	}

	private AsahUtil() {
	}

}