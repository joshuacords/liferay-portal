/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.field.type.v1_0;

import java.util.Objects;

/**
 * @author Marcela Cunha
 */
public class DataFieldOption {

	public DataFieldOption() {
	}

	public DataFieldOption(String label, String value) {
		_label = label;
		_value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataFieldOption)) {
			return false;
		}

		DataFieldOption dataFieldOption = (DataFieldOption)obj;

		if (Objects.equals(_value, dataFieldOption._value)) {
			return true;
		}

		return false;
	}

	public String getLabel() {
		return _label;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		if (_value != null) {
			return _value.hashCode();
		}

		return 0;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _label;
	private String _value;

}