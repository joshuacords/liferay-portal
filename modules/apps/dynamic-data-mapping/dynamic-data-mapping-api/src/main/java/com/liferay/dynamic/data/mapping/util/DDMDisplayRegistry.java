/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util;

import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Eduardo García
 */
@Component(immediate = true, service = DDMDisplayRegistry.class)
public class DDMDisplayRegistry {

	public DDMDisplay getDDMDisplay(String portletId) {
		return _getDDMDisplay(portletId);
	}

	public List<DDMDisplay> getDDMDisplays() {
		return _getDDMDisplays();
	}

	public String[] getPortletIds() {
		return _getPortletIds();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setDDMDisplay(DDMDisplay ddmDisplay) {
		_ddmDisplays.put(ddmDisplay.getPortletId(), ddmDisplay);
	}

	protected void unsetDDMDisplay(DDMDisplay ddmDisplay) {
		_ddmDisplays.remove(ddmDisplay.getPortletId());
	}

	private DDMDisplay _getDDMDisplay(String portletId) {
		return _ddmDisplays.get(portletId);
	}

	private List<DDMDisplay> _getDDMDisplays() {
		return ListUtil.fromMapValues(_ddmDisplays);
	}

	private String[] _getPortletIds() {
		Set<String> portletIds = _ddmDisplays.keySet();

		return portletIds.toArray(new String[0]);
	}

	private final Map<String, DDMDisplay> _ddmDisplays =
		new ConcurrentHashMap<>();

}