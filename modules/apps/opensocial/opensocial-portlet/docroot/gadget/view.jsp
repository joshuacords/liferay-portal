<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Gadget gadget = (Gadget)renderRequest.getAttribute(WebKeys.GADGET);
String view = (String)renderRequest.getAttribute(WebKeys.VIEW);

String ownerId = ShindigUtil.getOwnerId(layout);
long moduleId = ShindigUtil.getModuleId(renderResponse.getNamespace());

long gadgetId = gadget.getGadgetId();

String gadgetKey = StringPool.BLANK;

if (gadgetId > 0) {
	gadgetKey = GadgetConstants.toPublishedGadgetKey(gadgetId);
}
else {
	gadgetKey = GadgetConstants.toAdhocGadgetKey(moduleId);
}

GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

ModulePrefs modulePrefs = gadgetSpec.getModulePrefs();

Map<String, Feature> features = modulePrefs.getFeatures();

boolean requiresPubsub = features.containsKey("pubsub-2");

String secureToken = ShindigUtil.createSecurityToken(ownerId, themeDisplay.getUserId(), gadgetKey, PortalUtil.getPortalURL(themeDisplay), gadget.getUrl(), moduleId, currentURL);

String userPrefsKey = ShindigUtil.getColumnUserPrefs(renderResponse.getNamespace(), themeDisplay);

JSONObject userPrefsJSONObject = ExpandoValueServiceUtil.getJSONData(themeDisplay.getCompanyId(), Layout.class.getName(), ShindigUtil.getTableOpenSocial(), userPrefsKey, themeDisplay.getPlid());

String userPrefsJSON = "{}";

if (userPrefsJSONObject != null) {
	userPrefsJSON = String.valueOf(userPrefsJSONObject);

	userPrefsJSON = StringUtil.replace(userPrefsJSON, CharPool.BACK_SLASH, StringPool.DOUBLE_BACK_SLASH);
}
%>

<div class="gadgets-gadget-chrome" id="<portlet:namespace />gadget"></div>

<aui:script use="liferay-open-social-gadget">
	new Liferay.OpenSocial.Gadget(
		{
			appId: '<%= gadget.getUrl() %>',
			baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
			checksum: '<%= gadgetSpec.getChecksum() %>',
			country: '<%= locale.getCountry() %>',
			debug: <%= PortletPropsValues.SHINDIG_JS_DEBUG %>,
			height: <%= modulePrefs.getHeight() %>,
			language: '<%= _getLanguage(locale) %>',
			moduleId: '<%= moduleId %>',
			nocache: <%= PortletPropsValues.SHINDIG_NO_CACHE %>,
			portletId: '<%= portletDisplay.getId() %>',
			pubsubURILoadTimeout: <%= PortletPropsValues.PUBSUB_URI_LOAD_TIMEOUT %>,
			requiresPubsub: <%= requiresPubsub %>,
			scrolling: <%= modulePrefs.getScrolling() %>,
			secureToken: '<%= secureToken %>',
			serverBase: '<%= PortalUtil.getPathContext(renderRequest) %>/gadgets/',
			specUrl: '<%= gadget.getUrl() %>',
			store: new Liferay.OpenSocial.Store.Expando(
				{
					userPrefsKey: '<%= userPrefsKey %>'
				}
			),
			userPrefs: JSON.parse('<%= userPrefsJSON %>'),
			view: '<%= view %>',
			viewParams: '<%= ParamUtil.getString(renderRequest, "viewParams") %>'
		}
	).render('#<portlet:namespace />gadget');
</aui:script>

<%!
private String _getLanguage(Locale locale) {
	String language = locale.getLanguage();

	// See http://docs.opensocial.org/display/OSREF/Gadgets+XML+Reference

	if (language.equals(LocaleUtil.CHINESE.getLanguage())) {
		language = language + StringPool.DASH + locale.getCountry();
	}

	return language;
}
%>