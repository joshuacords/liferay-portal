/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.test;

import com.liferay.asset.display.contributor.AssetDisplayContributorField;
import com.liferay.asset.kernel.model.AssetEntry;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = "model.class.name=com.liferay.asset.kernel.model.AssetEntry",
	service = AssetDisplayContributorField.class
)
public class AssetEntryAdapterAssetDisplayContributorField
	implements AssetDisplayContributorField<AssetEntry> {

	@Override
	public String getKey() {
		return "assetEntryAdapterKey";
	}

	@Override
	public String getLabel(Locale locale) {
		return "assetEntryAdapterLabel";
	}

	@Override
	public String getType() {
		return "text";
	}

	@Override
	public String getValue(AssetEntry assetEntry, Locale locale) {
		return "assetEntryAdapterValue";
	}

}