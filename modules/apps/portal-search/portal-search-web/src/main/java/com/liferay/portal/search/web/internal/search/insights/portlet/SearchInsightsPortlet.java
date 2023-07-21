/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.search.insights.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.permission.PortletPermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.web.internal.search.insights.constants.SearchInsightsPortletKeys;
import com.liferay.portal.search.web.internal.search.insights.display.context.SearchInsightsDisplayContext;
import com.liferay.portal.search.web.internal.util.SearchPortletPermissionUtil;
import com.liferay.portal.search.web.internal.util.SearchStringUtil;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchRequest;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchResponse;

import java.io.IOException;

import java.util.Optional;
import java.util.ResourceBundle;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-search-insights",
		"com.liferay.portlet.display-category=category.search",
		"com.liferay.portlet.icon=/icons/search.png",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.restore-current-view=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Search Insights",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/search/insights/view.jsp",
		"javax.portlet.name=" + SearchInsightsPortletKeys.SEARCH_INSIGHTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class SearchInsightsPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletSharedSearchResponse portletSharedSearchResponse =
			portletSharedSearchRequest.search(renderRequest);

		SearchInsightsPortletPreferences searchInsightsPortletPreferences =
			new SearchInsightsPortletPreferencesImpl(
				portletSharedSearchResponse.getPortletPreferences(
					renderRequest));

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			buildDisplayContext(
				portletSharedSearchResponse, searchInsightsPortletPreferences,
				renderRequest));

		if (!SearchPortletPermissionUtil.containsConfiguration(
				portletPermission, renderRequest, portal)) {

			renderRequest.setAttribute(
				WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		}

		super.render(renderRequest, renderResponse);
	}

	protected SearchInsightsDisplayContext buildDisplayContext(
		PortletSharedSearchResponse portletSharedSearchResponse,
		SearchInsightsPortletPreferences searchInsightsPortletPreferences,
		RenderRequest renderRequest) {

		SearchInsightsDisplayContext searchInsightsDisplayContext =
			new SearchInsightsDisplayContext();

		SearchResponse searchResponse =
			portletSharedSearchResponse.getFederatedSearchResponse(
				searchInsightsPortletPreferences.
					getFederatedSearchKeyOptional());

		if (isCompanyAdmin() && isRequestStringPresent(searchResponse)) {
			searchInsightsDisplayContext.setRequestString(
				buildRequestString(searchResponse));

			searchInsightsDisplayContext.setResponseString(
				buildResponseString(searchResponse));
		}
		else {
			searchInsightsDisplayContext.setHelpMessage(
				getHelpMessage(renderRequest));
		}

		return searchInsightsDisplayContext;
	}

	protected String buildRequestString(SearchResponse searchResponse) {
		Optional<String> optional = SearchStringUtil.maybe(
			searchResponse.getRequestString());

		return optional.orElse(StringPool.BLANK);
	}

	protected String buildResponseString(SearchResponse searchResponse) {
		Optional<String> responseString = SearchStringUtil.maybe(
			searchResponse.getResponseString());

		return responseString.orElse(StringPool.BLANK);
	}

	protected String getHelpMessage(RenderRequest renderRequest) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", renderRequest.getLocale(), getClass());

		return language.get(resourceBundle, "search-insights-help");
	}

	protected boolean isCompanyAdmin() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		return permissionChecker.isCompanyAdmin();
	}

	protected boolean isRequestStringPresent(SearchResponse searchResponse) {
		Optional<String> requestString = SearchStringUtil.maybe(
			searchResponse.getRequestString());

		return requestString.isPresent();
	}

	@Reference
	protected Language language;

	@Reference
	protected Portal portal;

	@Reference
	protected PortletPermission portletPermission;

	@Reference
	protected PortletSharedSearchRequest portletSharedSearchRequest;

}