<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String namespace = AUIUtil.getNamespace(liferayPortletRequest, liferayPortletResponse);

String protocol = HttpUtil.getProtocol(request);

String bootstrapRequire = (String)request.getAttribute("liferay-map:map:bootstrapRequire");
boolean geolocation = GetterUtil.getBoolean(request.getAttribute("liferay-map:map:geolocation"));
double latitude = (Double)request.getAttribute("liferay-map:map:latitude");
double longitude = (Double)request.getAttribute("liferay-map:map:longitude");
String name = (String)request.getAttribute("liferay-map:map:name");
String points = (String)request.getAttribute("liferay-map:map:points");

name = namespace + name;
%>

<liferay-util:html-top
	outputKey="js_maps_google_skip_map_loading"
>
	<script>
		Liferay.namespace('Maps').onGMapsReady = function(event) {
			Liferay.Maps.gmapsReady = true;

			Liferay.fire('gmapsReady');
		};
	</script>

	<%
	String apiURL = protocol + "://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places&callback=Liferay.Maps.onGMapsReady";

	if (Validator.isNotNull(googleMapDisplayContext.getGoogleMapsAPIKey())) {
		apiURL += "&key=" + googleMapDisplayContext.getGoogleMapsAPIKey();
	}
	%>

	<script src="<%= apiURL %>" type="text/javascript"></script>
</liferay-util:html-top>

<aui:script require="<%= bootstrapRequire %>">
	var MapControls = Liferay.MapBase.CONTROLS;

	var mapConfig = {
		boundingBox: '#<%= HtmlUtil.escapeJS(name) %>Map',

		<c:if test="<%= geolocation %>">
			<c:choose>
				<c:when test="<%= BrowserSnifferUtil.isMobile(request) %>">
					controls: [MapControls.HOME, MapControls.SEARCH],
				</c:when>
				<c:otherwise>
					controls: [
						MapControls.HOME,
						MapControls.PAN,
						MapControls.SEARCH,
						MapControls.TYPE,
						MapControls.ZOOM
					],
				</c:otherwise>
			</c:choose>
		</c:if>

		<c:if test="<%= Validator.isNotNull(points) %>">
			data: <%= points %>,
		</c:if>

		geolocation: <%= geolocation %>,

		<c:if test="<%= (latitude != 0) && (longitude != 0) %>">
			position: {
				location: {
					lat: <%= latitude %>,
					lng: <%= longitude %>
				}
			}
		</c:if>
	};

	var createMap = function() {
		var map = new MapGoogleMaps.default(mapConfig);

		Liferay.MapBase.register(
			'<%= HtmlUtil.escapeJS(name) %>',
			map,
			'<%= portletDisplay.getId() %>'
		);
	};

	if (Liferay.Maps.gmapsReady) {
		createMap();
	}
	else {
		Liferay.once('gmapsReady', createMap);
	}
</aui:script>