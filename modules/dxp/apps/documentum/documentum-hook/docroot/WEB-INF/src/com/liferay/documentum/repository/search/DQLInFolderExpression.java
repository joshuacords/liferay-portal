/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.search;

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Mika Koivisto
 */
public class DQLInFolderExpression implements DQLCriterion {

	public DQLInFolderExpression(String objectId, boolean decend) {
		_objectId = objectId;
		_decend = decend;
	}

	@Override
	public String toQueryFragment() {
		StringBundler sb = new StringBundler(5);

		sb.append("FOLDER(ID('");
		sb.append(_objectId);
		sb.append("')");

		if (_decend) {
			sb.append(", DESCEND");
		}

		sb.append(")");

		return sb.toString();
	}

	private final boolean _decend;
	private final String _objectId;

}