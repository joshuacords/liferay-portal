/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.web.internal.util;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.util.comparator.DataProviderInstanceModifiedDateComparator;
import com.liferay.dynamic.data.mapping.util.comparator.DataProviderInstanceNameComparator;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Leonardo Barros
 */
public class DDMDataProviderPortletUtil {

	public static OrderByComparator<DDMDataProviderInstance>
		getDDMDataProviderOrderByComparator(
			String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<DDMDataProviderInstance> orderByComparator = null;

		if (orderByCol.equals("modified-date")) {
			orderByComparator = new DataProviderInstanceModifiedDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new DataProviderInstanceNameComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

	public static Set<String> getDDMFormFieldNamesByType(
		DDMForm ddmForm, String type) {

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		Collection<DDMFormField> ddmFormFields = ddmFormFieldsMap.values();

		Stream<DDMFormField> ddmFormFieldStream = ddmFormFields.stream();

		return ddmFormFieldStream.filter(
			ddmFormField -> Objects.equals(ddmFormField.getType(), type)
		).map(
			DDMFormField::getName
		).collect(
			Collectors.toSet()
		);
	}

}