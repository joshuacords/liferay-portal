<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

Team team = (Team)request.getAttribute(TaprootWebKeys.TEAM);

long teamId = BeanParamUtil.getLong(team, request, "teamId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/teams_admin/edit_team", "tabs1", "details", "teamId", teamId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("contact-roles"));

						if (team != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/teams_admin/edit_team", "tabs1", "contact-roles", "teamId", teamId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "contact-roles"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (team != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/teams_admin/edit_team", "tabs1", "external-links", "teamId", teamId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>