/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.internal.servlet.taglib.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.taglib.internal.servlet.ServletContextUtil;
import com.liferay.asset.util.AssetEntryUsageRecorder;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class AssetEntryUsagesTaglibUtil {

	public static void recordAssetEntryUsage(String className, long classPK) {
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			className, classPK);

		try {
			Map<String, AssetEntryUsageRecorder> assetEntryUsageRecorders =
				ServletContextUtil.getAssetEntryUsageRecorders();

			AssetEntryUsageRecorder assetEntryUsageRecorder =
				assetEntryUsageRecorders.get(assetEntry.getClassName());

			if (assetEntryUsageRecorder != null) {
				assetEntryUsageRecorder.record(assetEntry);
			}
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Unable to check asset entry usages for class name ",
						className, " and class PK ", classPK),
					portalException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryUsagesTaglibUtil.class);

}