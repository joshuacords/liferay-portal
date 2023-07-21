/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.gateway;

/**
 * @author Riccardo Ferrari
 */
public class CommerceMLJobState {

	public CommerceMLJobState() {
	}

	public String getApplicationId() {
		return _applicationId;
	}

	public String getState() {
		return _state;
	}

	public void setApplicationId(String applicationId) {
		_applicationId = applicationId;
	}

	public void setState(String state) {
		_state = state;
	}

	private String _applicationId;
	private String _state;

}