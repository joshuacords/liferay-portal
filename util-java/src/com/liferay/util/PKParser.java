/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PKParser {

	public PKParser(String pk) {
		if (pk.startsWith(StringPool.OPEN_CURLY_BRACE)) {
			pk = pk.substring(1);
		}

		if (pk.endsWith(StringPool.CLOSE_CURLY_BRACE)) {
			pk = pk.substring(0, pk.length() - 1);
		}

		String[] array = StringUtil.split(pk);

		for (String s : array) {
			String[] kvp = StringUtil.split(s, CharPool.EQUAL);

			String key = kvp[0].trim();
			String value = kvp[1].trim();

			_fields.put(key, value);
		}
	}

	public boolean getBoolean(String key) {
		return GetterUtil.getBoolean(getString(key));
	}

	public double getDouble(String key) {
		return GetterUtil.getDouble(getString(key));
	}

	public int getInteger(String key) {
		return GetterUtil.getInteger(getString(key));
	}

	public long getLong(String key) {
		return GetterUtil.getLong(getString(key));
	}

	public short getShort(String key) {
		return GetterUtil.getShort(getString(key));
	}

	public String getString(String key) {
		String value = _fields.get(key);

		if (value == null) {
			return StringPool.BLANK;
		}

		return value;
	}

	private final Map<String, String> _fields = new HashMap<>();

}