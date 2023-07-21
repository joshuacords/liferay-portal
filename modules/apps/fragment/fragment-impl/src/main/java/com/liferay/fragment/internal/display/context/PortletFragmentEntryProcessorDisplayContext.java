/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.display.context;

/**
 * @author Pavel Savinov
 */
public class PortletFragmentEntryProcessorDisplayContext {

	public PortletFragmentEntryProcessorDisplayContext(
		String defaultPreferences, String instanceId, String portletName) {

		_defaultPreferences = defaultPreferences;
		_instanceId = instanceId;
		_portletName = portletName;
	}

	public String getDefaultPreferences() {
		return _defaultPreferences;
	}

	public String getInstanceId() {
		return _instanceId;
	}

	public String getPortletName() {
		return _portletName;
	}

	private final String _defaultPreferences;
	private final String _instanceId;
	private final String _portletName;

}