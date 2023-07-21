/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.display.contributor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.HashUtil;

import java.util.Objects;

/**
 * @author Jürgen Kappler
 */
public class InfoDisplayField {

	public InfoDisplayField(String key, String label) {
		this(key, label, _TYPE);
	}

	public InfoDisplayField(String key, String label, String type) {
		_key = key;
		_label = label;
		_type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InfoDisplayField)) {
			return false;
		}

		InfoDisplayField infoDisplayField = (InfoDisplayField)obj;

		if (Objects.equals(_key, infoDisplayField._key) &&
			Objects.equals(_label, infoDisplayField._label) &&
			Objects.equals(_type, infoDisplayField._type)) {

			return true;
		}

		return false;
	}

	public String getKey() {
		return _key;
	}

	public String getLabel() {
		return _label;
	}

	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _key);

		hash = HashUtil.hash(hash, _label);

		return HashUtil.hash(hash, _type);
	}

	public JSONObject toJSONObject() {
		return JSONUtil.put(
			"key", getKey()
		).put(
			"label", getLabel()
		).put(
			"type", getType()
		);
	}

	private static final String _TYPE = "text";

	private final String _key;
	private final String _label;
	private final String _type;

}