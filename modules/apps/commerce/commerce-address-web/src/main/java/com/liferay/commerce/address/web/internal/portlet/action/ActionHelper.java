/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.address.web.internal.portlet.action;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = ActionHelper.class)
public class ActionHelper {

	public List<CommerceCountry> getCommerceCountries(
			PortletRequest portletRequest)
		throws PortalException {

		List<CommerceCountry> commerceCountries = new ArrayList<>();

		long[] commerceCountryIds = ParamUtil.getLongValues(
			portletRequest, "rowIds");

		for (long commerceCountryId : commerceCountryIds) {
			commerceCountries.add(
				_commerceCountryService.getCommerceCountry(commerceCountryId));
		}

		return commerceCountries;
	}

	public CommerceCountry getCommerceCountry(RenderRequest renderRequest)
		throws PortalException {

		long commerceCountryId = ParamUtil.getLong(
			renderRequest, "commerceCountryId");

		if (commerceCountryId > 0) {
			return _commerceCountryService.getCommerceCountry(
				commerceCountryId);
		}

		return null;
	}

	public CommerceRegion getCommerceRegion(RenderRequest renderRequest)
		throws PortalException {

		long commerceRegionId = ParamUtil.getLong(
			renderRequest, "commerceRegionId");

		if (commerceRegionId > 0) {
			return _commerceRegionService.getCommerceRegion(commerceRegionId);
		}

		return null;
	}

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private CommerceRegionService _commerceRegionService;

}