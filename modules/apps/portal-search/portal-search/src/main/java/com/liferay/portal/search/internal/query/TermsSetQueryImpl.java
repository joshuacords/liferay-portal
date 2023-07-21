/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.query;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.search.query.QueryVisitor;
import com.liferay.portal.search.query.TermsSetQuery;
import com.liferay.portal.search.script.Script;

import java.util.Collections;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class TermsSetQueryImpl extends BaseQueryImpl implements TermsSetQuery {

	public TermsSetQueryImpl(String fieldName, List<Object> values) {
		_fieldName = fieldName;
		_values = values;
	}

	@Override
	public <T> T accept(QueryVisitor<T> queryVisitor) {
		return queryVisitor.visit(this);
	}

	public String getFieldName() {
		return _fieldName;
	}

	public String getMinimumShouldMatchField() {
		return _minimumShouldMatchField;
	}

	public Script getMinimumShouldMatchScript() {
		return _minimumShouldMatchScript;
	}

	public List<Object> getValues() {
		return Collections.unmodifiableList(_values);
	}

	public Boolean isCached() {
		return _cached;
	}

	public void setCached(Boolean cached) {
		_cached = cached;
	}

	public String setMinimumShouldMatchField(String minimumShouldMatchField) {
		return _minimumShouldMatchField = minimumShouldMatchField;
	}

	public void setMinimumShouldMatchScript(Script minimumShouldMatchScript) {
		_minimumShouldMatchScript = minimumShouldMatchScript;
	}

	@Override
	public String toString() {
		return StringBundler.concat(
			"{(", _fieldName, "=", _values, "), (minimum_should_match_field=",
			_minimumShouldMatchField, ")}");
	}

	private static final long serialVersionUID = 1L;

	private Boolean _cached = Boolean.TRUE;
	private final String _fieldName;
	private String _minimumShouldMatchField;
	private Script _minimumShouldMatchScript;
	private final List<Object> _values;

}