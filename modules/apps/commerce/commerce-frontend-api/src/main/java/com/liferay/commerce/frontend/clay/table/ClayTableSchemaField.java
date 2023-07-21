/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.table;

/**
 * @author Marco Leo
 */
public class ClayTableSchemaField {

	public String getActionId() {
		return _actionId;
	}

	public String getContentRenderer() {
		return _contentRenderer;
	}

	public String getContentRendererModuleUrl() {
		return _contentRendererModuleUrl;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public String getLabel() {
		return _label;
	}

	public SortingOrder getSortingOrder() {
		return _sortingOrder;
	}

	public boolean isExpand() {
		return _expand;
	}

	public boolean isSortable() {
		return _sortable;
	}

	public void setActionId(String actionId) {
		_actionId = actionId;
	}

	public void setContentRenderer(String contentRenderer) {
		_contentRenderer = contentRenderer;
	}

	public void setContentRendererModuleUrl(String contentRendererModuleUrl) {
		_contentRendererModuleUrl = contentRendererModuleUrl;
	}

	public void setExpand(boolean expand) {
		_expand = expand;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setSortable(boolean sortable) {
		_sortable = sortable;
	}

	public void setSortingOrder(SortingOrder sortingOrder) {
		_sortingOrder = sortingOrder;
	}

	public enum SortingOrder {

		ASC, DESC

	}

	private String _actionId;
	private String _contentRenderer;
	private String _contentRendererModuleUrl;
	private boolean _expand;
	private String _fieldName;
	private String _label;
	private boolean _sortable;
	private SortingOrder _sortingOrder;

}