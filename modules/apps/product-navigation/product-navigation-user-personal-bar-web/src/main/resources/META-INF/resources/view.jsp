<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
		<span class="user-avatar-link">
			<liferay-util:buffer
				var="userAvatar"
			>
				<span class="sticker sticker-lg">
					<span class="inline-item">
						<liferay-ui:user-portrait
							cssClass="sticker-lg"
							user="<%= user %>"
						/>
					</span>

					<c:if test="<%= themeDisplay.isImpersonated() %>">
						<span class="sticker sticker-bottom-right sticker-circle sticker-outside sticker-sm sticker-user-icon">
							<aui:icon image="user" markupView="lexicon" />
						</span>
					</c:if>
				</span>
			</liferay-util:buffer>

			<liferay-product-navigation:personal-menu
				expanded="<%= true %>"
				label="<%= userAvatar %>"
			/>

			<%
			int notificationsCount = GetterUtil.getInteger(request.getAttribute(ProductNavigationUserPersonalBarWebKeys.NOTIFICATIONS_COUNT));
			%>

			<c:if test="<%= notificationsCount > 0 %>">

				<%
				String notificaitonsPortletId = PortletProviderUtil.getPortletId(UserNotificationEvent.class.getName(), PortletProvider.Action.VIEW);

				String notificationsURL = PersonalApplicationURLUtil.getPersonalApplicationURL(request, notificaitonsPortletId);
				%>

				<aui:a href="<%= (notificationsURL != null) ? notificationsURL : null %>">
					<span class="badge badge-danger panel-notifications-count">
						<span class="badge-item badge-item-expand"><%= notificationsCount %></span>
					</span>
				</aui:a>
			</c:if>
		</span>
	</c:when>
	<c:otherwise>

		<%
		Map<String, Object> anchorData = new HashMap<String, Object>();

		anchorData.put("redirect", String.valueOf(PortalUtil.isLoginRedirectRequired(request)));
		%>

		<span class="sign-in text-default" role="presentation">
			<aui:icon cssClass="sign-in text-default" data="<%= anchorData %>" image="user" label="sign-in" markupView="lexicon" url="<%= themeDisplay.getURLSignIn() %>" />
		</span>
	</c:otherwise>
</c:choose>