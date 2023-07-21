/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.contributor.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorFieldType;

import java.util.Locale;

/**
 * @author     Pavel Savinov
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 *             com.liferay.info.display.contributor.field.ExpandoInfoDisplayContributorField}
 */
@Deprecated
public class ExpandoInfoDisplayContributorField
	extends com.liferay.info.display.contributor.field.
				ExpandoInfoDisplayContributorField
	implements InfoDisplayContributorField {

	public ExpandoInfoDisplayContributorField(
		String attributeName, ExpandoBridge expandoBridge) {

		super(attributeName, expandoBridge);
	}

	@Override
	public String getKey() {
		return super.getKey();
	}

	@Override
	public String getLabel(Locale locale) {
		return super.getLabel(locale);
	}

	@Override
	public InfoDisplayContributorFieldType getType() {
		return super.getType();
	}

	@Override
	public Object getValue(Object model, Locale locale) {
		return super.getValue(model, locale);
	}

}