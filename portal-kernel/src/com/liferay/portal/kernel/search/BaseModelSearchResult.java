/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.BaseModel;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class BaseModelSearchResult<T extends BaseModel<T>>
	implements Serializable {

	public BaseModelSearchResult(List<T> baseModels, int length) {
		if (baseModels == null) {
			_baseModels = Collections.emptyList();
		}
		else {
			_baseModels = baseModels;
		}

		_length = length;
	}

	public List<T> getBaseModels() {
		return _baseModels;
	}

	public int getLength() {
		return _length;
	}

	@Override
	public String toString() {
		if (_baseModels.isEmpty()) {
			return "{baseModels={}, length=".concat(
				String.valueOf(_length)
			).concat(
				StringPool.CLOSE_BRACKET
			);
		}

		StringBundler sb = new StringBundler((2 * _baseModels.size()) + 3);

		sb.append("{baseModels={");

		for (T baseModel : _baseModels) {
			sb.append(baseModel);
			sb.append(StringPool.COMMA_AND_SPACE);
		}

		sb.setStringAt(StringPool.CLOSE_BRACKET, sb.index() - 1);

		sb.append(", length=");
		sb.append(_length);
		sb.append(StringPool.CLOSE_BRACKET);

		return sb.toString();
	}

	private final List<T> _baseModels;
	private final int _length;

}