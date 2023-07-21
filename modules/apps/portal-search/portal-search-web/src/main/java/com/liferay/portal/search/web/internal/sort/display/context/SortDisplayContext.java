/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.sort.display.context;

import java.util.List;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public class SortDisplayContext {

	public String getParameterName() {
		return _parameterName;
	}

	public String getParameterValue() {
		return _parameterValue;
	}

	public List<SortTermDisplayContext> getSortTermDisplayContexts() {
		return _sortTermDisplayContexts;
	}

	public boolean isAnySelected() {
		return _anySelected;
	}

	public boolean isRenderNothing() {
		return _renderNothing;
	}

	public void setAnySelected(boolean anySelected) {
		_anySelected = anySelected;
	}

	public void setParameterName(String parameterName) {
		_parameterName = parameterName;
	}

	public void setParameterValue(String parameterValue) {
		_parameterValue = parameterValue;
	}

	public void setRenderNothing(boolean renderNothing) {
		_renderNothing = renderNothing;
	}

	public void setSortTermDisplayContexts(
		List<SortTermDisplayContext> sortTermDisplayContexts) {

		_sortTermDisplayContexts = sortTermDisplayContexts;
	}

	private boolean _anySelected;
	private String _parameterName;
	private String _parameterValue;
	private boolean _renderNothing;
	private List<SortTermDisplayContext> _sortTermDisplayContexts;

}