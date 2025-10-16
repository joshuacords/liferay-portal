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
		UnicodeProperties unicodeProperties) {

		if (unicodeProperties == null) {
			return new UnicodeProperties();
		}

		if (_sanitizeClassNameIds(
				assetListEntryId, segmentsEntryId, unicodeProperties)) {

			try {
				AssetListEntryLocalServiceUtil.updateAssetListEntryTypeSettings(
					assetListEntryId, segmentsEntryId,
					unicodeProperties.toString());
			}
			catch (PortalException portalException) {
				_log.error(
					String.format(
						"Unable to persist sanitized type settings for asset " +
							"list id %d with segments entry id %d",
						assetListEntryId, segmentsEntryId),
					portalException);
			}
		}

		return unicodeProperties;
	}

	private static boolean _sanitizeClassNameIds(
		long assetListEntryId, long segmentsEntryId,
		UnicodeProperties unicodeProperties) {

		String[] values = StringUtil.split(
			unicodeProperties.getProperty("classNameIds"));

		if (values.length == 0) {
			return false;
		}

		List<String> validValues = new ArrayList<>(values.length);

		boolean updated = false;

		for (String value : values) {
			long classNameId = GetterUtil.getLong(value);

			if (ClassNameLocalServiceUtil.fetchClassName(classNameId) != null) {
				validValues.add(String.valueOf(classNameId));

				continue;
			}

			updated = true;

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Removed missing class name id %d from asset list %d " +
							"with segment entry id %d)",
						classNameId, assetListEntryId, segmentsEntryId));
			}
		}

		if (!updated) {
			return false;
		}

		unicodeProperties.setProperty(
			"classNameIds", StringUtil.merge(validValues));

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetListTypeSettingsSanitizerUtil.class);

}