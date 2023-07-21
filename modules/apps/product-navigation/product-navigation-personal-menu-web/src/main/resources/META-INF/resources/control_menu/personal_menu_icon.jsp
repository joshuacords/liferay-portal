<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<style type="text/css">
	#impersonate-user-icon {
		color: #272833;
	}

	#impersonate-user-icon .lexicon-icon {
		margin-top: -.125rem;
	}

	#impersonate-user-sticker {
		bottom: -.4rem;
		font-size: .6rem;
		height: 1.2rem;
		right: -0.4rem;
		width: 1.2rem;
	}

	#personal-menu-icon-wrapper .lexicon-icon {
		margin-top: -.25rem;
	}
</style>

<li class="control-menu-nav-item">
	<span class="user-avatar-link">
		<liferay-util:buffer
			var="userAvatar"
		>
			<span class="sticker">
				<span class="inline-item" id="personal-menu-icon-wrapper">
					<liferay-ui:user-portrait
						user="<%= user %>"
					/>
				</span>

				<c:if test="<%= themeDisplay.isImpersonated() %>">
					<span class="sticker sticker-bottom-right sticker-circle sticker-outside sticker-user-icon" id="impersonate-user-sticker">
						<aui:icon id="impersonate-user-icon" image="user" markupView="lexicon" />
					</span>
				</c:if>
			</span>
		</liferay-util:buffer>

		<liferay-product-navigation:personal-menu
			expanded="<%= true %>"
			label="<%= userAvatar %>"
		/>

		<%
		int notificationsCount = GetterUtil.getInteger(request.getAttribute(PersonalMenuWebKeys.NOTIFICATIONS_COUNT));
		%>

		<c:if test="<%= notificationsCount > 0 %>">

			<%
			String notificationsURL = PersonalApplicationURLUtil.getPersonalApplicationURL(request, PortletProviderUtil.getPortletId(UserNotificationEvent.class.getName(), PortletProvider.Action.VIEW));
			%>

			<aui:a href="<%= (notificationsURL != null) ? notificationsURL : null %>">
				<span class="badge badge-danger panel-notifications-count">
					<span class="badge-item badge-item-expand"><%= notificationsCount %></span>
				</span>
			</aui:a>
		</c:if>
	</span>
</li>