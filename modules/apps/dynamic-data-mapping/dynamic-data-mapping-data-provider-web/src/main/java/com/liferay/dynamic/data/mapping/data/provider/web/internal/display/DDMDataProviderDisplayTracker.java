/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.web.internal.display;

import com.liferay.dynamic.data.mapping.data.provider.display.DDMDataProviderDisplay;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Lino Alves
 */
@Component(immediate = true, service = DDMDataProviderDisplayTracker.class)
public class DDMDataProviderDisplayTracker {

	public DDMDataProviderDisplay getDDMDataProviderDisplay(String portletId) {
		return _ddmDataProviderDisplay.get(portletId);
	}

	public List<DDMDataProviderDisplay> getDDMDataProviderDisplays() {
		return _getDDMDataProviderDisplays();
	}

	public String[] getPortletIds() {
		return _getPortletIds();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addDDMDataProviderDisplay(
		DDMDataProviderDisplay ddmDataProviderDisplay) {

		_ddmDataProviderDisplay.put(
			ddmDataProviderDisplay.getPortletId(), ddmDataProviderDisplay);
	}

	@Deactivate
	protected void deactivate() {
		_ddmDataProviderDisplay.clear();
	}

	protected void removeDDMDataProviderDisplay(
		DDMDataProviderDisplay ddmDataProviderDisplay) {

		_ddmDataProviderDisplay.remove(ddmDataProviderDisplay.getPortletId());
	}

	private List<DDMDataProviderDisplay> _getDDMDataProviderDisplays() {
		return ListUtil.fromMapValues(_ddmDataProviderDisplay);
	}

	private String[] _getPortletIds() {
		Set<String> portletIds = _ddmDataProviderDisplay.keySet();

		return portletIds.toArray(new String[0]);
	}

	private final Map<String, DDMDataProviderDisplay> _ddmDataProviderDisplay =
		new ConcurrentHashMap<>();

}