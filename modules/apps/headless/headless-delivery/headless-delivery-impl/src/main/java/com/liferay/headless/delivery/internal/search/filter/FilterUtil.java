/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.search.filter;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.headless.delivery.internal.dynamic.data.mapping.DDMStructureField;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.util.LocaleUtil;

/**
 * @author Javier de Arcos
 */
public class FilterUtil {

	public static Filter processFilter(DDMIndexer ddmIndexer, Filter filter)
		throws Exception {

		if (ddmIndexer.isLegacyDDMIndexFieldsEnabled() ||
			!(filter instanceof TermFilter)) {

			return filter;
		}

		TermFilter termFilter = (TermFilter)filter;

		String termFilterField = termFilter.getField();

		if (!termFilterField.startsWith(DDMIndexer.DDM_FIELD_PREFIX)) {
			return filter;
		}

		DDMStructureField ddmStructureField = DDMStructureField.from(
			termFilterField);

		return ddmIndexer.createFieldValueQueryFilter(
			ddmStructureField.getDDMStructureFieldName(), termFilter.getValue(),
			LocaleUtil.fromLanguageId(ddmStructureField.getLocale()));
	}

}