<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/nav_bar_search/init.jsp" %>

<div class="collapse navbar-collapse <%= searchResults ? "open" : StringPool.BLANK %>" id="<%= id %>NavbarSearchCollapse">
	<div class="navbar-search <%= cssClass %>" id="<%= id %>" <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
		<c:if test="<%= Validator.isNotNull(file) %>">
			<liferay-ui:search-form
				page="<%= file %>"
				searchContainer="<%= searchContainer %>"
			/>
		</c:if>

		<aui:script use="aui-base,event-outside">
			var Util = Liferay.Util;

			var advancedSearchResults = <%= searchResults %>;

			var toggleSearchMenu = function(event) {
				if ((event.type === 'click') || event.isKeyInSet('ENTER', 'SPACE')) {
					var btnNavbar = event.currentTarget;

					var navbar = btnNavbar.ancestor('.navbar');

					var navbarCollapse = A.one('#<%= id %>NavbarSearchCollapse');

					var handles = Liferay.Data['<%= id %>Handle'];

					var navbarWillOpen = !navbarCollapse.hasClass('open');

					if (advancedSearchResults) {
						navbarWillOpen = true;

						advancedSearchResults = false;
					}

					if (!navbarWillOpen && handles && handles.length) {
						(new A.EventHandle(handles)).detach();

						handles = null;
					}
					else {
						handles = handles || [];

						var closeNavBar = function() {
							var handles = Liferay.Data['<%= id %>Handle'];

							(new A.EventHandle(handles)).detach();

							Liferay.Data['<%= id %>Handle'] = null;

							navbarCollapse.removeClass('open');

							if (navbar) {
								navbar.all('.navbar-btn, .nav').show();
							}

							btnNavbar.focus();
						};

						var handleMouseOutside = navbarCollapse.on(
							'mousedownoutside',
							function(event) {
								if (!btnNavbar.contains(event.target)) {
									closeNavBar();
								}
							}
						);

						var handleEscape = A.getDoc().on('key', closeNavBar, 'down:27');

						handles.push(handleEscape, handleMouseOutside);
					}

					navbarCollapse.toggleClass('open', navbarWillOpen);

					if (navbar && (Util.isPhone() || Util.isTablet())) {
						navbar.all('.navbar-btn, .nav').hide();
					}

					if (navbarWillOpen) {
						Liferay.Util.focusFormField(navbarCollapse.one('input.search-query'));
					}

					Liferay.Data['<%= id %>Handle'] = handles;
				}
			};

			var navbarButton = A.one('#<%= id %>NavbarBtn');

			navbarButton.on(['click', 'keypress'], toggleSearchMenu);

			if (advancedSearchResults) {
				toggleSearchMenu(
					{
						currentTarget: navbarButton,
						type: 'click'
					}
				);
			}
		</aui:script>