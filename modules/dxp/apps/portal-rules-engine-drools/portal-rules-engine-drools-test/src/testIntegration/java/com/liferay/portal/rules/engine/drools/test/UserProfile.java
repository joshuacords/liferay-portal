/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.rules.engine.drools.test;

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Michael C. Han
 */
public class UserProfile {

	public int getAge() {
		return _age;
	}

	public String getAgeGroup() {
		return _ageGroup;
	}

	public void setAge(int age) {
		_age = age;
	}

	public void setAgeGroup(String ageGroup) {
		_ageGroup = ageGroup;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{age=");
		sb.append(_age);
		sb.append(", _ageGroup=");
		sb.append(_ageGroup);
		sb.append("}");

		return sb.toString();
	}

	private int _age;
	private String _ageGroup;

}