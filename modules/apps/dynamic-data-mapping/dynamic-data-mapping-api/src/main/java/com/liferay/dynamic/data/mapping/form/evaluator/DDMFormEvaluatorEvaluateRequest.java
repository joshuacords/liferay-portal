/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.evaluator;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;

import java.util.Locale;

/**
 * @author Leonardo Barros
 */
public final class DDMFormEvaluatorEvaluateRequest {

	public long getCompanyId() {
		return _companyId;
	}

	public DDMForm getDDMForm() {
		return _ddmForm;
	}

	public DDMFormValues getDDMFormValues() {
		return _ddmFormValues;
	}

	public long getGroupId() {
		return _groupId;
	}

	public Locale getLocale() {
		return _locale;
	}

	public long getUserId() {
		return _userId;
	}

	public static class Builder {

		public static Builder newBuilder(
			DDMForm ddmForm, DDMFormValues ddmFormValues, Locale locale) {

			return new Builder(ddmForm, ddmFormValues, locale);
		}

		public DDMFormEvaluatorEvaluateRequest build() {
			return _ddmFormEvaluatorEvaluateRequest;
		}

		public Builder withCompanyId(long companyId) {
			_ddmFormEvaluatorEvaluateRequest._companyId = companyId;

			return this;
		}

		public Builder withGroupId(long groupId) {
			_ddmFormEvaluatorEvaluateRequest._groupId = groupId;

			return this;
		}

		public Builder withUserId(long userId) {
			_ddmFormEvaluatorEvaluateRequest._userId = userId;

			return this;
		}

		private Builder(
			DDMForm ddmForm, DDMFormValues ddmFormValues, Locale locale) {

			_ddmFormEvaluatorEvaluateRequest._ddmForm = ddmForm;
			_ddmFormEvaluatorEvaluateRequest._ddmFormValues = ddmFormValues;
			_ddmFormEvaluatorEvaluateRequest._locale = locale;
		}

		private final DDMFormEvaluatorEvaluateRequest
			_ddmFormEvaluatorEvaluateRequest =
				new DDMFormEvaluatorEvaluateRequest();

	}

	private DDMFormEvaluatorEvaluateRequest() {
	}

	private long _companyId;
	private DDMForm _ddmForm;
	private DDMFormValues _ddmFormValues;
	private long _groupId;
	private Locale _locale;
	private long _userId;

}