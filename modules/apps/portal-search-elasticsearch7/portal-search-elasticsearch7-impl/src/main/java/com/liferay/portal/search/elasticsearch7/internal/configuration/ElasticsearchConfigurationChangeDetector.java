/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.configuration;

/**
 * @author Joshua Cords
 */
public class ElasticsearchConfigurationChangeDetector {

	public boolean isContextChanged() {
		return _contextChanged;
	}

	public void setContextChanged(boolean contextChanged) {
		_contextChanged = contextChanged;
	}

	private boolean _contextChanged;

}