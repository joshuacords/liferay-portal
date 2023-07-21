/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.render;

import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Locale;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseDDMFormFieldValueRenderer
	implements DDMFormFieldValueRenderer {

	@Override
	public String render(DDMFormFieldValue ddmFormFieldValue, Locale locale) {
		ValueAccessor valueAccessor = getValueAcessor(locale);

		return valueAccessor.get(ddmFormFieldValue);
	}

	@Override
	public String render(
		List<DDMFormFieldValue> ddmFormFieldValues, Locale locale) {

		ValueAccessor valueAccessor = getValueAcessor(locale);

		return ListUtil.toString(
			ddmFormFieldValues, valueAccessor, StringPool.COMMA_AND_SPACE);
	}

	protected abstract ValueAccessor getValueAcessor(Locale locale);

}