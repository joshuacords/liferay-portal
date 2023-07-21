/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.repository.cmis.search;

/**
 * @author Mika Koivisto
 */
public class CMISInFolderExpression implements CMISCriterion {

	public CMISInFolderExpression(String objectId) {
		_objectId = objectId;
	}

	@Override
	public String toQueryFragment() {
		return "IN_FOLDER('".concat(
			_objectId
		).concat(
			"')"
		);
	}

	private final String _objectId;

}