/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.search;

/**
 * @author Rebecca Dai
 */
public class DisplayTerm {

	public DisplayTerm(String label, String name, String value) {
		_label = label;
		_name = name;
		_value = value;
	}

	public String getLabel() {
		return _label;
	}

	public String getName() {
		return _name;
	}

	public String getValue() {
		return _value;
	}

	private final String _label;
	private final String _name;
	private final String _value;

}