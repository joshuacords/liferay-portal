/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.builder;

import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Andrew Betts
 */
public class AttributeValidator {

	public void addAlwaysValidAttribute(String name) {
		_alwaysValidAttributeNames.add(StringUtil.toLowerCase(name));
	}

	public void addValidAttributeValues(String name, String... values) {
		Set<String> lowerCaseValues = Collections.newSetFromMap(
			new ConcurrentHashMap<>());

		for (String value : values) {
			lowerCaseValues.add(StringUtil.toLowerCase(value));
		}

		_validAttributeValues.put(
			StringUtil.toLowerCase(name), lowerCaseValues);
	}

	public boolean isValidAttribute(String name, String value) {
		name = StringUtil.toLowerCase(name);

		if (_alwaysValidAttributeNames.contains(name)) {
			return true;
		}

		Set<String> validAttributeValues = _validAttributeValues.get(name);

		if (validAttributeValues == null) {
			return false;
		}

		value = StringUtil.toLowerCase(value);

		if (validAttributeValues.contains(value)) {
			return true;
		}

		return false;
	}

	private final Set<String> _alwaysValidAttributeNames =
		Collections.newSetFromMap(new ConcurrentHashMap<>());
	private final Map<String, Set<String>> _validAttributeValues =
		new ConcurrentHashMap<>();

}