/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.internal.field;

import com.liferay.asset.info.display.field.AssetEntryInfoDisplayFieldProvider;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorFieldTracker;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = AssetEntryInfoDisplayFieldProvider.class)
public class AssetEntryInfoDisplayFieldProviderImpl
	implements AssetEntryInfoDisplayFieldProvider {

	public Map<String, Object> getAssetEntryInfoDisplayFieldsValues(
			String className, long classPK, Locale locale)
		throws PortalException {

		Map<String, Object> infoDisplayFieldsValues = new HashMap<>();

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		try {
			AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
				className, classPK);

			List<InfoDisplayContributorField> infoDisplayContributorFields =
				_infoDisplayContributorFieldTracker.
					getInfoDisplayContributorFields(AssetEntry.class.getName());

			for (InfoDisplayContributorField infoDisplayContributorField :
					infoDisplayContributorFields) {

				Object fieldValue = infoDisplayContributorField.getValue(
					assetEntry, locale);

				infoDisplayFieldsValues.putIfAbsent(
					infoDisplayContributorField.getKey(), fieldValue);
			}
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return infoDisplayFieldsValues;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryInfoDisplayFieldProviderImpl.class);

	@Reference
	private InfoDisplayContributorFieldTracker
		_infoDisplayContributorFieldTracker;

}