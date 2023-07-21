/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Igor Beslic
 */
public class Attribute {

	public Attribute(String name, byte[] bytes) {
		setAttributeId(name);
		setValue(bytes);
	}

	public Attribute(String name, String value) {
		setAttributeId(name);
		setValue(value);
	}

	public String getAttributeId() {
		return _attributeId;
	}

	public byte[] getBytes() {
		return _bytes;
	}

	public String getValue() {
		return _value;
	}

	public boolean isBinary() {
		if (_bytes != null) {
			return true;
		}

		return false;
	}

	public void setAttributeId(String attributeId) {
		_attributeId = attributeId;
	}

	public void setValue(byte[] bytes) {
		_bytes = bytes;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _attributeId;
	private byte[] _bytes;
	private String _value;

}