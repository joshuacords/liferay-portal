/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.field.type.v1_0;

import com.liferay.data.engine.field.type.BaseFieldType;
import com.liferay.data.engine.field.type.FieldType;
import com.liferay.data.engine.spi.dto.SPIDataDefinitionField;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcela Cunha
 */
@Component(
	immediate = true,
	property = {
		"data.engine.field.type.data.domain=date",
		"data.engine.field.type.description=date-field-type-description",
		"data.engine.field.type.display.order:Integer=5",
		"data.engine.field.type.group=basic",
		"data.engine.field.type.icon=calendar",
		"data.engine.field.type.js.module=dynamic-data-mapping-form-field-type/DatePicker/DatePicker.es",
		"data.engine.field.type.label=date-field-type-label"
	},
	service = FieldType.class
)
public class DateFieldType extends BaseFieldType {

	@Override
	public String getName() {
		return "date";
	}

	@Override
	protected void includeContext(
		Map<String, Object> context, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		SPIDataDefinitionField spiDataDefinitionField) {

		List<Integer> years = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.YEAR, -4);

		for (int i = 0; i < 5; i++) {
			years.add(calendar.get(Calendar.YEAR));

			calendar.add(Calendar.YEAR, 1);
		}

		context.put("years", years);
	}

}