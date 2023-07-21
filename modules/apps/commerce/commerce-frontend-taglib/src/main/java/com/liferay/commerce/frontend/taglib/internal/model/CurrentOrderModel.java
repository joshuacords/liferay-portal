/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.taglib.internal.model;

/**
 * @author Marco Leo
 */
public class CurrentOrderModel {

	public CurrentOrderModel(long id, String status) {
		_id = id;
		_status = status;
	}

	public long getId() {
		return _id;
	}

	public String getStatus() {
		return _status;
	}

	private final long _id;
	private final String _status;

}