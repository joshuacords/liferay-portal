/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class FilterQuery {

	public void addContains(boolean required, String field, String value) {
		addContains(required, field, value, false);
	}

	public void addContains(
		boolean required, String field, String value, boolean negate) {

		StringBundler sb = new StringBundler(6);

		if (negate) {
			sb.append("not ");
		}

		sb.append("contains(");
		sb.append(field);
		sb.append(", '");
		sb.append(_escape(value));
		sb.append("')");

		_addFilter(required, sb.toString());
	}

	public void addEquals(boolean required, String field, boolean value) {
		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" eq ");
		sb.append(value);

		_addFilter(required, sb.toString());
	}

	public void addEquals(boolean required, String field, String value) {
		addEquals(required, field, value, false);
	}

	public void addEquals(
		boolean required, String field, String value, boolean negate) {

		StringBundler sb = new StringBundler(5);

		sb.append(field);

		if (negate) {
			sb.append(" ne ");
		}
		else {
			sb.append(" eq ");
		}

		if (value != null) {
			sb.append("'");
		}

		sb.append(_escape(value));

		if (value != null) {
			sb.append("'");
		}

		_addFilter(required, sb.toString());
	}

	public void addEquals(boolean required, String field, String[] values) {
		StringBundler sb = new StringBundler(4);

		for (int i = 0; i < values.length; i++) {
			sb.append(field);
			sb.append(" eq '");
			sb.append(_escape(values[i]));
			sb.append("'");

			if ((i + 1) < values.length) {
				sb.append(" or ");
			}
		}

		_addFilter(required, sb.toString());
	}

	public void addFilterQuery(boolean required, FilterQuery filterQuery) {
		_addFilter(required, filterQuery.toString());
	}

	public void addGreaterThan(boolean required, String field, Date dateValue) {
		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" gt ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(required, sb.toString());
	}

	public void addGreaterThanEquals(
		boolean required, String field, Date dateValue) {

		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" ge ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(required, sb.toString());
	}

	public void addLambdaContains(
		boolean required, String field, String value) {

		addLambdaContains(required, field, value, false);
	}

	public void addLambdaContains(
		boolean required, String field, String value, boolean negate) {

		StringBundler sb = new StringBundler(5);

		if (negate) {
			sb.append("not ");
		}

		sb.append(field);
		sb.append("/any(s:contains(s, '");
		sb.append(_escape(value));
		sb.append("'))");

		_addFilter(required, sb.toString());
	}

	public void addLambdaEquals(boolean required, String field, String value) {
		addLambdaEquals(required, field, value, false);
	}

	public void addLambdaEquals(
		boolean required, String field, String value, boolean negate) {

		StringBundler sb = new StringBundler(5);

		if (negate) {
			sb.append("not ");
		}

		sb.append(field);
		sb.append("/any(s:s eq '");
		sb.append(_escape(value));
		sb.append("')");

		_addFilter(required, sb.toString());
	}

	public void addLambdaEquals(
		boolean required, String field, String[] values) {

		addLambdaEquals(required, field, values, false);
	}

	public void addLambdaEquals(
		boolean required, String field, String[] values, boolean negate) {

		StringBundler sb = new StringBundler();

		if (negate) {
			sb.append("not ");
		}

		sb.append(field);
		sb.append("/any(s:");

		for (int i = 0; i < values.length; i++) {
			sb.append("s eq '");
			sb.append(_escape(values[i]));
			sb.append("'");

			if ((i + 1) < values.length) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		_addFilter(required, sb.toString());
	}

	public void addLessThan(boolean required, String field, Date dateValue) {
		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" lt ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(required, sb.toString());
	}

	public void addLessThanEquals(
		boolean required, String field, Date dateValue) {

		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" le ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(required, sb.toString());
	}

	public void addStartsWith(boolean required, String field, String value) {
		StringBundler sb = new StringBundler(5);

		sb.append("startswith(");
		sb.append(field);
		sb.append(", '");
		sb.append(_escape(value));
		sb.append("')");

		_addFilter(required, sb.toString());
	}

	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBundler sb = new StringBundler(7);

		if (!_filters.isEmpty()) {
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringUtil.merge(_filters, " or "));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (!_requiredFilters.isEmpty()) {
				sb.append(" and ");
			}
		}

		if (!_requiredFilters.isEmpty()) {
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringUtil.merge(_requiredFilters, " and "));
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		_toString = sb.toString();

		return _toString;
	}

	private void _addFilter(boolean required, String filter) {
		if (required) {
			_requiredFilters.add(filter);
		}
		else {
			_filters.add(filter);
		}

		_toString = null;
	}

	private String _escape(String value) {
		if (value == null) {
			return "null";
		}

		return value.replaceAll(
			StringPool.APOSTROPHE, StringPool.DOUBLE_APOSTROPHE);
	}

	private final List<String> _filters = new ArrayList<>();
	private final DateFormat _isoDateFormat = DateUtil.getISO8601Format();
	private final List<String> _requiredFilters = new ArrayList<>();
	private String _toString;

}