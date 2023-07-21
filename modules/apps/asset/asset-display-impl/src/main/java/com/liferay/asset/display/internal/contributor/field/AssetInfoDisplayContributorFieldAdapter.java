/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.internal.contributor.field;

import com.liferay.asset.display.contributor.AssetDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorFieldType;

import java.util.Locale;

/**
 * @author Jürgen Kappler
 */
public class AssetInfoDisplayContributorFieldAdapter
	implements InfoDisplayContributorField {

	public AssetInfoDisplayContributorFieldAdapter(
		AssetDisplayContributorField assetDisplayContributorField) {

		_assetDisplayContributorField = assetDisplayContributorField;
	}

	@Override
	public String getKey() {
		return _assetDisplayContributorField.getKey();
	}

	@Override
	public String getLabel(Locale locale) {
		return _assetDisplayContributorField.getLabel(locale);
	}

	@Override
	public InfoDisplayContributorFieldType getType() {
		return InfoDisplayContributorFieldType.parse(
			_assetDisplayContributorField.getType());
	}

	@Override
	public Object getValue(Object model, Locale locale) {
		return _assetDisplayContributorField.getValue(model, locale);
	}

	private final AssetDisplayContributorField _assetDisplayContributorField;

}