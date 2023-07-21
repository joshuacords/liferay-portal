/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.model.table;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Iván Zaera Avellón
 */
public class Field {

	public Field(String fieldName, String label) {
		this(fieldName, label, StringPool.BLANK);
	}

	public Field(String fieldName, String label, String contentRenderer) {
		this(fieldName, label, contentRenderer, null);
	}

	public Field(
		String fieldName, String label, String contentRenderer,
		SortingOrder sortingOrder) {

		_fieldName = fieldName;
		_label = label;
		_contentRenderer = contentRenderer;
		_sortingOrder = sortingOrder;

		if (contentRenderer != null) {
			_sortable = true;
		}
		else {
			_sortable = false;
		}
	}

	public void addContentRendererMapping(String type, String contentRenderer) {
		_fieldsMap.put(type, contentRenderer);
	}

	public void addCustomProperty(String name, Object value) {
		_customProperties.put(name, value);
	}

	public void addFieldMapping(String field, String mapping) {
		_fieldsMap.put(field, mapping);
	}

	public String getFieldName() {
		return _fieldName;
	}

	public boolean isEscaping() {
		return _escaping;
	}

	public void setEscaping(boolean escaping) {
		_escaping = escaping;
	}

	public Map<String, ?> toMap() {
		Map<String, Object> map = HashMapBuilder.<String, Object>put(
			"contentRenderer", _contentRenderer
		).put(
			"contentRendererMap", _contentRendererMap
		).build();

		map.putAll(_customProperties);
		map.put("fieldName", _fieldName);
		map.put("fieldsMap", _fieldsMap);
		map.put("label", _label);
		map.put("sortable", _sortable);

		if (_sortingOrder != null) {
			map.put("sortingOrder", _sortingOrder.getValue());
		}

		return map;
	}

	private final String _contentRenderer;
	private final Map<String, String> _contentRendererMap = new HashMap<>();
	private final Map<String, Object> _customProperties = new HashMap<>();
	private boolean _escaping = true;
	private final String _fieldName;
	private final Map<String, String> _fieldsMap = new HashMap<>();
	private final String _label;
	private final boolean _sortable;
	private final SortingOrder _sortingOrder;

}