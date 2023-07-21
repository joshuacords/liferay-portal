/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.content.search.web.internal.portlet;

import com.liferay.commerce.product.constants.CPField;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.content.search.web.internal.configuration.CPPriceRangeFacetsPortletInstanceConfiguration;
import com.liferay.commerce.search.facet.SerializableFacet;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.json.JSONObjectImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.RangeFacet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import java.util.Optional;

import javax.portlet.RenderRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(
	property = "javax.portlet.name=" + CPPortletKeys.CP_PRICE_RANGE_FACETS,
	service = PortletSharedSearchContributor.class
)
public class CPPriceRangeFacetsPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		RenderRequest renderRequest =
			portletSharedSearchSettings.getRenderRequest();

		try {
			SearchContext searchContext =
				portletSharedSearchSettings.getSearchContext();

			Facet facet = getFacet(renderRequest, searchContext);

			Optional<String[]> parameterValuesOptional =
				portletSharedSearchSettings.getParameterValues71(
					facet.getFieldName());

			SerializableFacet serializableFacet = new SerializableFacet(
				facet.getFieldName(), searchContext);

			if (parameterValuesOptional.isPresent()) {
				serializableFacet.select(parameterValuesOptional.get());

				searchContext.setAttribute(
					facet.getFieldName(), parameterValuesOptional.get());
			}

			portletSharedSearchSettings.addFacet(facet);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected Facet getFacet(
			RenderRequest renderRequest, SearchContext searchContext)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		CPPriceRangeFacetsPortletInstanceConfiguration
			cpPriceRangeFacetsPortletInstanceConfiguration =
				portletDisplay.getPortletInstanceConfiguration(
					CPPriceRangeFacetsPortletInstanceConfiguration.class);

		Facet facet = new RangeFacet(searchContext);

		FacetConfiguration facetConfiguration = new FacetConfiguration();

		JSONObject jsonObject = new JSONObjectImpl();

		String rangesJSONArrayString =
			cpPriceRangeFacetsPortletInstanceConfiguration.
				rangesJSONArrayString();

		rangesJSONArrayString = StringUtil.replace(
			rangesJSONArrayString, new String[] {"\\,", StringPool.STAR},
			new String[] {StringPool.COMMA, String.valueOf(Double.MAX_VALUE)});

		jsonObject.put(
			"ranges", JSONFactoryUtil.createJSONArray(rangesJSONArrayString));

		facetConfiguration.setDataJSONObject(jsonObject);

		facet.setFacetConfiguration(facetConfiguration);

		facet.setFieldName(CPField.BASE_PRICE);

		return facet;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPPriceRangeFacetsPortletSharedSearchContributor.class);

}