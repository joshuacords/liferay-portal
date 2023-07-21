/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Country;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.CountryRegion;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.CountryRegionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.CountryRegionResource;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/country-region.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {CountryRegionResource.class, NestedFieldSupport.class}
)
public class CountryRegionResourceImpl
	extends BaseCountryRegionResourceImpl implements NestedFieldSupport {

	@NestedField(parentClass = Country.class, value = "countryRegions")
	public List<CountryRegion> getCountryNestedFieldRegions(
			@NestedFieldId("name") String countryName)
		throws Exception {

		com.liferay.portal.kernel.model.Country country =
			_countryService.getCountryByName(countryName);

		return transform(
			_regionService.getRegions(country.getCountryId()),
			CountryRegionUtil::toCountryRegion);
	}

	@Override
	public Page<CountryRegion> getCountryRegionsPage() throws Exception {
		return Page.of(
			transform(
				_regionService.getRegions(),
				CountryRegionUtil::toCountryRegion));
	}

	@Reference
	private CountryService _countryService;

	@Reference
	private RegionService _regionService;

}