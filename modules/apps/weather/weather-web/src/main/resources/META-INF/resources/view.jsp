<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNotNull(apiKey) %>">
		<form name="<portlet:namespace />fm" onSubmit="submitForm(document.<portlet:namespace />fm, 'http://openweathermap.org/find?q=', false); return false;" target="_blank">
			<table class="lfr-table">

				<%
				for (String zip : zips) {
					Weather weather = WeatherUtil.getWeather(zip, apiKey);
				%>

					<c:if test="<%= weather != null %>">
						<tr>
							<td>
								<a href="http://www.openweathermap.org/city/<%= HtmlUtil.escapeURL(weather.getCityId()) %>" style="font-size: xx-small; font-weight: bold;" target="_blank"><%= HtmlUtil.escape(weather.getZip()) %></a>
							</td>
							<td align="right">
								<span style="font-size: xx-small;">
									<c:if test="<%= fahrenheit %>">
										<%= weather.getCurrentTemp() %> &deg;F
									</c:if>

									<c:if test="<%= !fahrenheit %>">
										<%= Math.round((.5555555555 * (weather.getCurrentTemp() + 459.67)) - 273.15) %> &deg;C
									</c:if>
								</span>
							</td>
							<td align="right">
								<img alt="" src="<%= weather.getIconURL() %>" />
							</td>
						</tr>
					</c:if>

				<%
				}
				%>

			</table>

			<br />

			<liferay-ui:message key="city-or-zip-code" />

			<input name="q" size="23" type="text" />

			<input type="submit" value="<liferay-ui:message key="search" />" />
		</form>

		<br />

		<liferay-ui:message key="powered-by" /> <a href="http://www.openweathermap.org" target="_blank">Open Weather Map</a>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.q);
			</aui:script>
		</c:if>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>