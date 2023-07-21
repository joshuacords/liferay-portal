/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator.internal.function;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionParameterAccessor;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * @author Rafael Praxedes
 */
public class DefaultDDMExpressionParameterAccessor
	implements DDMExpressionParameterAccessor {

	@Override
	public long getCompanyId() {
		return _getCompanyIdSupplier.get();
	}

	@Override
	public long getGroupId() {
		return _getGroupIdSupplier.get();
	}

	@Override
	public Locale getLocale() {
		return _getLocaleSupplier.get();
	}

	@Override
	public long getUserId() {
		return _getUserIdSupplier.get();
	}

	protected void setGetCompanyIdSupplier(Supplier<Long> supplier) {
		_getCompanyIdSupplier = supplier;
	}

	protected void setGetGroupIdSupplier(Supplier<Long> supplier) {
		_getGroupIdSupplier = supplier;
	}

	protected void setGetUserIdSupplier(Supplier<Long> supplier) {
		_getUserIdSupplier = supplier;
	}

	private Supplier<Long> _getCompanyIdSupplier = () -> 0L;
	private Supplier<Long> _getGroupIdSupplier = () -> 0L;
	private final Supplier<Locale> _getLocaleSupplier = () -> new Locale(
		"pt", "BR");
	private Supplier<Long> _getUserIdSupplier = () -> 0L;

}