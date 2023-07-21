<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);

long accountId = BeanParamUtil.getLong(koroneikiAccount, request, "accountId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "details", "accountId", accountId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("child-accounts"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "child-accounts", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "child-accounts"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("customer-contacts"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "customer-contacts", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "customer-contacts"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("worker-contacts"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "worker-contacts", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "worker-contacts"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("teams"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "teams", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "teams"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("assigned-teams"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "assigned-teams", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "assigned-teams"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("addresses"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "addresses", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "addresses"));
					});
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("purchases"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "purchases", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "purchases"));
					});
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("consumptions"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "consumptions", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "consumptions"));
					});
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("entitlements"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "entitlements", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "entitlements"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("notes"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "notes", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "notes"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (koroneikiAccount != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account", "tabs1", "external-links", "accountId", accountId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>