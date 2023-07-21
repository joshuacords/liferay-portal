/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Country;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.CountryUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.CountryResource;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/country.properties",
	scope = ServiceScope.PROTOTYPE, service = CountryResource.class
)
public class CountryResourceImpl extends BaseCountryResourceImpl {

	@Override
	public Page<Country> getCountriesPage() throws Exception {
		return Page.of(
			transform(_countryService.getCountries(), CountryUtil::toCountry));
	}

	@Reference
	private CountryService _countryService;

}