/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.util;

import com.liferay.asset.list.service.AssetListEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class AssetListTypeSettingsSanitizerUtil {

	public static UnicodeProperties sanitize(
			long assetListEntryId, long segmentsEntryId,
			UnicodeProperties unicodeProperties)
		throws PortalException {

		if (unicodeProperties == null) {
			return new UnicodeProperties();
		}

		boolean updated = false;

		String[] values = StringUtil.split(
			unicodeProperties.getProperty("classNameIds"));

		List<String> validValues = new ArrayList<>(values.length);

		for (String value : values) {
			long classNameId = GetterUtil.getLong(value);

			if (ClassNameLocalServiceUtil.fetchClassName(classNameId) != null) {
				validValues.add(String.valueOf(classNameId));

				continue;
			}

			updated = true;
		}

		if (validValues.isEmpty()) {
			unicodeProperties.remove("classNameIds");
		}
		else {
			unicodeProperties.setProperty(
				"classNameIds", StringUtil.merge(validValues));
		}

		long classNameId = GetterUtil.getLong(
			unicodeProperties.getProperty("anyAssetType"));

		if (classNameId > 0) {
			if (ClassNameLocalServiceUtil.fetchClassName(classNameId) == null) {
				unicodeProperties.setProperty("anyAssetType", "true");

				updated = true;
			}
		}
		else if (!GetterUtil.getBoolean(
					unicodeProperties.getProperty("anyAssetType"), true)) {

			if (validValues.size() == 1) {
				unicodeProperties.setProperty(
					"anyAssetType", validValues.get(0));
			}
			else {
				unicodeProperties.setProperty("anyAssetType", "true");
			}

			updated = true;
		}

		if (!updated) {
			return unicodeProperties;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Removing missing class name id %d from asset list %d " +
						"with segment entry id %d)",
					classNameId, assetListEntryId, segmentsEntryId));
		}

		AssetListEntryLocalServiceUtil.updateAssetListEntryTypeSettings(
			assetListEntryId, segmentsEntryId, unicodeProperties.toString());

		return unicodeProperties;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetListTypeSettingsSanitizerUtil.class);

}