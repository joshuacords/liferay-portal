/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.sort.display.context;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.search.web.internal.sort.portlet.SortPortletPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class SortDisplayBuilder {

	public SortDisplayBuilder(SortPortletPreferences sortPortletPreferences) {
		_sortPortletPreferences = sortPortletPreferences;
	}

	public SortDisplayContext build() {
		SortDisplayContext sortDisplayContext = new SortDisplayContext();

		List<SortTermDisplayContext> sortTermDisplayContexts =
			buildTermDisplayContexts();

		sortDisplayContext.setAnySelected(
			isAnySelected(sortTermDisplayContexts));

		sortDisplayContext.setParameterName(_parameterName);
		sortDisplayContext.setParameterValue(getParameterValue());
		sortDisplayContext.setRenderNothing(isRenderNothing());
		sortDisplayContext.setSortTermDisplayContexts(sortTermDisplayContexts);

		return sortDisplayContext;
	}

	public SortDisplayBuilder parameterName(String parameterName) {
		_parameterName = parameterName;

		return this;
	}

	public SortDisplayBuilder parameterValues(String... parameterValues) {
		if (parameterValues == null) {
			_selectedFields = Collections.emptyList();

			return this;
		}

		_selectedFields = Arrays.asList(parameterValues);

		return this;
	}

	public SortDisplayBuilder renderNothing(boolean renderNothing) {
		_renderNothing = renderNothing;

		return this;
	}

	protected SortTermDisplayContext buildTermDisplayContext(
		String label, String field) {

		SortTermDisplayContext sortTermDisplayContext =
			new SortTermDisplayContext();

		sortTermDisplayContext.setLabel(label);
		sortTermDisplayContext.setField(field);
		sortTermDisplayContext.setSelected(_selectedFields.contains(field));

		return sortTermDisplayContext;
	}

	protected List<SortTermDisplayContext> buildTermDisplayContexts() {
		List<SortTermDisplayContext> sortTermDisplayContexts =
			new ArrayList<>();

		JSONArray fieldsJSONArray =
			_sortPortletPreferences.getFieldsJSONArray();

		for (int i = 0; i < fieldsJSONArray.length(); i++) {
			JSONObject jsonObject = fieldsJSONArray.getJSONObject(i);

			sortTermDisplayContexts.add(
				buildTermDisplayContext(
					jsonObject.getString("label"),
					jsonObject.getString("field")));
		}

		return sortTermDisplayContexts;
	}

	protected String getParameterValue() {
		if (!_selectedFields.isEmpty()) {
			return _selectedFields.get(_selectedFields.size() - 1);
		}

		return null;
	}

	protected boolean isAnySelected(
		List<SortTermDisplayContext> sortTermDisplayContexts) {

		for (SortTermDisplayContext sortTermDisplayContext :
				sortTermDisplayContexts) {

			if (sortTermDisplayContext.isSelected()) {
				return true;
			}
		}

		if (!sortTermDisplayContexts.isEmpty()) {
			SortTermDisplayContext sortTermDisplayContext =
				sortTermDisplayContexts.get(0);

			sortTermDisplayContext.setSelected(true);

			return true;
		}

		return false;
	}

	protected boolean isRenderNothing() {
		JSONArray jsonArray = _sortPortletPreferences.getFieldsJSONArray();

		if (jsonArray == null) {
			return true;
		}

		if (jsonArray.length() == 0) {
			return true;
		}

		if (_renderNothing) {
			return true;
		}

		return false;
	}

	private String _parameterName;
	private boolean _renderNothing;
	private List<String> _selectedFields = Collections.emptyList();
	private final SortPortletPreferences _sortPortletPreferences;

}