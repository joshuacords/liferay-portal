/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.model.table;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Iván Zaera Avellón
 */
public class Schema {

	public void addField(Field field) {
		_fields.add(field);
	}

	public void addInputNameMapping(String type, String name) {
		_inputNamesMap.put(type, name);
	}

	public Collection<Field> getFields() {
		return _fields;
	}

	public void setInputName(String inputName) {
		_inputName = inputName;
	}

	public void setInputNameField(String inputNameField) {
		_inputNameField = inputNameField;
	}

	public void setInputValueField(String inputValueField) {
		_inputValueField = inputValueField;
	}

	public Map<String, ?> toMap() {
		return HashMapBuilder.<String, Object>put(
			"fields", _getFields()
		).put(
			"inputName", _inputName
		).put(
			"inputNameField", _inputNameField
		).put(
			"inputNamesMap", _inputNamesMap
		).put(
			"inputValueField", _inputValueField
		).build();
	}

	private List<Map<String, ?>> _getFields() {
		Stream<Field> stream = StreamSupport.stream(
			_fields.spliterator(), false);

		return stream.map(
			Field::toMap
		).collect(
			Collectors.toList()
		);
	}

	private final List<Field> _fields = new ArrayList<>();
	private String _inputName = StringPool.BLANK;
	private String _inputNameField = StringPool.BLANK;
	private final Map<String, String> _inputNamesMap = new HashMap<>();
	private String _inputValueField = StringPool.BLANK;

}