/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.internal.type;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeRegistry;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = SiteNavigationMenuItemTypeRegistry.class)
public class SiteNavigationMenuItemTypeRegistryImpl
	implements SiteNavigationMenuItemTypeRegistry {

	@Override
	public SiteNavigationMenuItemType getSiteNavigationMenuItemType(
		SiteNavigationMenuItem siteNavigationMenuItem) {

		return getSiteNavigationMenuItemType(siteNavigationMenuItem.getType());
	}

	@Override
	public SiteNavigationMenuItemType getSiteNavigationMenuItemType(
		String type) {

		return _siteNavigationMenuItemTypes.get(type);
	}

	@Override
	public List<SiteNavigationMenuItemType> getSiteNavigationMenuItemTypes() {
		return ListUtil.fromMapValues(_siteNavigationMenuItemTypes);
	}

	@Override
	public String[] getTypes() {
		Set<String> types = _siteNavigationMenuItemTypes.keySet();

		return types.toArray(new String[0]);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	public void registerSiteNavigationMenuItemType(
		SiteNavigationMenuItemType siteNavigationMenuItemType) {

		_siteNavigationMenuItemTypes.put(
			siteNavigationMenuItemType.getType(), siteNavigationMenuItemType);
	}

	public void unregisterSiteNavigationMenuItemType(
		SiteNavigationMenuItemType siteNavigationMenuItemType) {

		_siteNavigationMenuItemTypes.remove(
			siteNavigationMenuItemType.getType());
	}

	private final Map<String, SiteNavigationMenuItemType>
		_siteNavigationMenuItemTypes = new ConcurrentHashMap<>();

}