/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.starter;

import com.liferay.commerce.starter.CommerceRegionsStarter;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = "commerce.region.starter.key=" + SaudiArabiaCommerceRegionsStarter.SAUDI_ARABIA_NUMERIC_ISO_CODE,
	service = CommerceRegionsStarter.class
)
public class SaudiArabiaCommerceRegionsStarter
	extends BaseCommerceRegionsStarter {

	public static final int SAUDI_ARABIA_NUMERIC_ISO_CODE = 682;

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "country.saudi-arabia");
	}

	@Override
	protected int getCountryIsoCode() {
		return SAUDI_ARABIA_NUMERIC_ISO_CODE;
	}

	@Override
	protected String getFilePath() {
		return _FILEPATH;
	}

	private static final String _FILEPATH =
		"com/liferay/commerce/internal/saudi-arabia.json";

}