/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.internal.request.attributes.contributor;

import com.liferay.asset.display.page.constants.AssetDisplayPageWebKeys;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.info.display.contributor.InfoDisplayContributor;
import com.liferay.info.display.contributor.InfoDisplayContributorTracker;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.info.display.request.attributes.contributor.InfoDisplayRequestAttributesContributor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true, service = InfoDisplayRequestAttributesContributor.class
)
public class AssetInfoDisplayRequestAttributesContributor
	implements InfoDisplayRequestAttributesContributor {

	@Override
	public void addAttributes(HttpServletRequest httpServletRequest) {
		Object layoutAssetEntry = httpServletRequest.getAttribute(
			WebKeys.LAYOUT_ASSET_ENTRY);

		if (!(layoutAssetEntry instanceof AssetEntry)) {
			return;
		}

		AssetEntry assetEntry = (AssetEntry)layoutAssetEntry;

		if (assetEntry != null) {
			return;
		}

		long assetEntryId = ParamUtil.getLong(
			httpServletRequest, "assetEntryId");

		assetEntry = _assetEntryLocalService.fetchEntry(assetEntryId);

		if (assetEntry == null) {
			return;
		}

		InfoDisplayContributor infoDisplayContributor =
			_infoDisplayContributorTracker.getInfoDisplayContributor(
				assetEntry.getClassName());

		httpServletRequest.setAttribute(
			InfoDisplayWebKeys.INFO_DISPLAY_CONTRIBUTOR,
			infoDisplayContributor);

		try {
			InfoDisplayObjectProvider infoDisplayObjectProvider =
				infoDisplayContributor.getInfoDisplayObjectProvider(
					assetEntry.getClassPK());

			if (infoDisplayObjectProvider != null) {
				httpServletRequest.setAttribute(
					AssetDisplayPageWebKeys.INFO_DISPLAY_OBJECT_PROVIDER,
					infoDisplayObjectProvider);
			}
		}
		catch (Exception exception) {
			_log.error("Unable to get info display object provider", exception);
		}

		httpServletRequest.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, assetEntry);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetInfoDisplayRequestAttributesContributor.class);

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private InfoDisplayContributorTracker _infoDisplayContributorTracker;

}