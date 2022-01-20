<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs = ParamUtil.getString(request, "tabs", "common-license-keys");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs.equals("common-license-keys"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs", "common-license-keys");
						navigationItem.setLabel(LanguageUtil.get(request, "common-license-keys"));
					});
				add(
					navigationItem -> {
						navigationItem.setActive(tabs.equals("rabbitmq"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs", "rabbitmq");
						navigationItem.setLabel(LanguageUtil.get(request, "rabbitmq"));
					});
			}
		}
	%>'
/>

<div class="container-fluid home">
	<c:choose>
		<c:when test='<%= tabs.equals("common-license-keys") %>'>
			<liferay-util:include page="/admin/view_common_license_keys.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<portlet:actionURL name="/admin/debug_rabbitmq" var="debugRabbitMQURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:actionURL>

			<aui:form action="<%= debugRabbitMQURL %>" cssClass="container-fluid container-fluid-max-xl" method="post">
				<aui:fieldset-group>
					<aui:fieldset>
						<aui:input name="routingKey" type="text" value="dossiera.provisioning.create" />

						<aui:input name="message" type="textarea" />

						<aui:input name="properties" type="textarea" />

						<aui:button type="submit" value="submit" />
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>
		</c:otherwise>
	</c:choose>
</div>