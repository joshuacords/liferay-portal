/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Leonardo Barros
 */
public class EqualsFunction
	implements DDMExpressionFunction.Function2<Object, Object, Boolean> {

	public static final String NAME = "equals";

	@Override
	public Boolean apply(Object object1, Object object2) {
		return Objects.equals(_getValue(object1), _getValue(object2));
	}

	@Override
	public String getName() {
		return NAME;
	}

	private Object _getValue(Object object) {
		if (object instanceof BigDecimal || object instanceof JSONObject) {
			return object.toString();
		}
		else if (object instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray)object;

			String[] strings = ArrayUtil.toStringArray(jsonArray);

			Arrays.sort(strings);

			return StringUtil.merge(strings);
		}

		return object;
	}

}