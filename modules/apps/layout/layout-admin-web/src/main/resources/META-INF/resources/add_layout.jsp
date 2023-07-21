<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long sourcePlid = ParamUtil.getLong(request, "sourcePlid");

List<SiteNavigationMenu> autoSiteNavigationMenus = layoutsAdminDisplayContext.getAutoSiteNavigationMenus();
%>

<div class="container-fluid-1280 pt-2">
	<liferay-frontend:edit-form
		action="<%= (sourcePlid <= 0) ? layoutsAdminDisplayContext.getAddLayoutURL() : layoutsAdminDisplayContext.getCopyLayoutURL(sourcePlid) %>"
		method="post"
		name="fm"
		onSubmit="event.preventDefault();"
	>
		<liferay-frontend:edit-form-body>
			<aui:input label="name" name="name" required="<%= true %>" />

			<c:choose>
				<c:when test="<%= autoSiteNavigationMenus.size() > 1 %>">
					<div class="h3 sheet-subtitle"><liferay-ui:message key="navigation-menus" /></div>

					<liferay-ui:message key="add-this-page-to-the-following-menus" />

					<div class="auto-site-navigation-menus container my-3">
						<div class="row">

							<%
							for (SiteNavigationMenu autoSiteNavigationMenu : autoSiteNavigationMenus) {
							%>

								<div class="col-6">
									<aui:input id='<%= "menu_" + autoSiteNavigationMenu.getSiteNavigationMenuId() %>' label="<%= HtmlUtil.escape(autoSiteNavigationMenu.getName()) %>" name="TypeSettingsProperties--siteNavigationMenuId--" type="checkbox" value="<%= autoSiteNavigationMenu.getSiteNavigationMenuId() %>" />
								</div>

							<%
							}
							%>

						</div>
					</div>
				</c:when>
				<c:when test="<%= autoSiteNavigationMenus.size() == 1 %>">

					<%
					SiteNavigationMenu autoSiteNavigationMenu = autoSiteNavigationMenus.get(0);
					%>

					<div class="auto-site-navigation-menus container mt-3">
						<div class="row">
							<aui:input id='<%= "menu_" + autoSiteNavigationMenu.getSiteNavigationMenuId() %>' label='<%= LanguageUtil.format(request, "add-this-page-to-x", HtmlUtil.escape(autoSiteNavigationMenu.getName())) %>' name="TypeSettingsProperties--siteNavigationMenuId--" type="checkbox" value="<%= autoSiteNavigationMenu.getSiteNavigationMenuId() %>" />
						</div>
					</div>
				</c:when>
			</c:choose>

			<c:if test="<%= layoutsAdminDisplayContext.hasRequiredVocabularies() %>">
				<aui:fieldset cssClass="mb-4">
					<div class="h3 sheet-subtitle"><liferay-ui:message key="categorization" /></div>

					<c:choose>
						<c:when test="<%= layoutsAdminDisplayContext.isShowCategorization() %>">
							<liferay-asset:asset-categories-selector
								className="<%= Layout.class.getName() %>"
								classPK="<%= 0 %>"
								showOnlyRequiredVocabularies="<%= true %>"
							/>
						</c:when>
						<c:otherwise>
							<div class="alert alert-warning text-justify">
								<liferay-ui:message key="pages-have-required-vocabularies.-you-need-to-create-at-least-one-category-in-all-required-vocabularies-in-order-to-create-a-page" />
							</div>
						</c:otherwise>
					</c:choose>
				</aui:fieldset>
			</c:if>
		</liferay-frontend:edit-form-body>

		<liferay-frontend:edit-form-footer>
			<aui:button id="addButton" type="submit" value="add" />

			<aui:button type="cancel" value="cancel" />
		</liferay-frontend:edit-form-footer>
	</liferay-frontend:edit-form>
</div>

<aui:script use="liferay-alert">
	var addButton = document.getElementById('<portlet:namespace />addButton');

	var form = document.<portlet:namespace />fm;

	form.addEventListener('submit', function(event) {
		event.preventDefault();
		event.stopPropagation();

		if (addButton.disabled) {
			return;
		}

		addButton.disabled = true;

		var formData = new FormData();

		formData.append('p_auth', Liferay.authToken);

		formActionURL = new URL(form.action);

		formActionURL.searchParams.delete('p_auth');

		form.action = formActionURL;

		Array.prototype.slice
			.call(form.querySelectorAll('input'))
			.forEach(function(input) {
				if (input.type == 'checkbox' && !input.checked) {
					return;
				}

				if (input.name && input.value) {
					formData.append(input.name, input.value);
				}
			});

		Liferay.Util.fetch(form.action, {
			body: formData,
			method: 'POST'
		})
			.then(function(response) {
				return response.json();
			})
			.then(function(response) {
				if (response.redirectURL) {
					var redirectURL = new URL(
						response.redirectURL,
						window.location.origin
					);

					redirectURL.searchParams.set('p_p_state', 'normal');

					Liferay.fire('closeWindow', {
						id: '<portlet:namespace />addLayoutDialog',
						redirect: redirectURL.toString()
					});
				}
				else {
					new Liferay.Alert({
						delay: {
							hide: 3000,
							show: 0
						},
						duration: 500,
						icon: 'exclamation-circle',
						message: response.errorMessage,
						type: 'danger'
					}).render();

					addButton.disabled = false;
				}
			});
	});
</aui:script>