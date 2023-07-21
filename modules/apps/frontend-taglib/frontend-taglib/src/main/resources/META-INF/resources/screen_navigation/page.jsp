<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/screen_navigation/init.jsp" %>

<%
String containerCssClass = (String)request.getAttribute("liferay-frontend:screen-navigation:containerCssClass");
String containerWrapperCssClass = (String)request.getAttribute("liferay-frontend:screen-navigation:containerWrapperCssClass");
String fullContainerCssClass = (String)request.getAttribute("liferay-frontend:screen-navigation:fullContainerCssClass");
String headerContainerCssClass = (String)request.getAttribute("liferay-frontend:screen-navigation:headerContainerCssClass");
String id = (String)request.getAttribute("liferay-frontend:screen-navigation:id");
boolean inverted = (boolean)request.getAttribute("liferay-frontend:screen-navigation:inverted");
String menubarCssClass = (String)request.getAttribute("liferay-frontend:screen-navigation:menubarCssClass");
String navCssClass = (String)request.getAttribute("liferay-frontend:screen-navigation:navCssClass");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-frontend:screen-navigation:portletURL");
ScreenNavigationCategory selectedScreenNavigationCategory = (ScreenNavigationCategory)request.getAttribute("liferay-frontend:screen-navigation:selectedScreenNavigationCategory");
ScreenNavigationEntry selectedScreenNavigationEntry = (ScreenNavigationEntry)request.getAttribute("liferay-frontend:screen-navigation:selectedScreenNavigationEntry");
List<ScreenNavigationCategory> screenNavigationCategories = (List<ScreenNavigationCategory>)request.getAttribute("liferay-frontend:screen-navigation:screenNavigationCategories");
List<ScreenNavigationEntry> screenNavigationEntries = (List<ScreenNavigationEntry>)request.getAttribute("liferay-frontend:screen-navigation:screenNavigationEntries");

LiferayPortletResponse finalLiferayPortletResponse = liferayPortletResponse;
%>

<c:if test="<%= ListUtil.isNotEmpty(screenNavigationCategories) && (screenNavigationCategories.size() > 1) %>">
	<div class="page-header">
		<div class="<%= headerContainerCssClass %>">
			<clay:navigation-bar
				inverted="<%= inverted %>"
				navigationItems='<%=
					new JSPNavigationItemList(pageContext) {
						{
							for (ScreenNavigationCategory screenNavigationCategory : screenNavigationCategories) {
								PortletURL screenNavigationCategoryURL = PortletURLUtil.clone(portletURL, finalLiferayPortletResponse);

								add(
									navigationItem -> {
										navigationItem.setActive((selectedScreenNavigationCategory != null) && Objects.equals(selectedScreenNavigationCategory.getCategoryKey(), screenNavigationCategory.getCategoryKey()));
										navigationItem.setHref(screenNavigationCategoryURL, "screenNavigationCategoryKey", screenNavigationCategory.getCategoryKey(), "screenNavigationEntryKey", StringPool.BLANK);
										navigationItem.setLabel(screenNavigationCategory.getLabel(themeDisplay.getLocale()));
									});
							}
						}
					}
				%>'
			/>
		</div>
	</div>
</c:if>

<c:if test="<%= (selectedScreenNavigationEntry != null) && ListUtil.isNotEmpty(screenNavigationEntries) %>">
	<div class="<%= containerWrapperCssClass %>">
		<div class="row">
			<c:if test="<%= screenNavigationEntries.size() > 1 %>">
				<div class="<%= navCssClass %>">
					<nav class="<%= menubarCssClass %>">
						<a aria-controls="<%= id %>" aria-expanded="false" class="menubar-toggler" data-toggle="collapse" href="#<%= id %>" role="button">
							<%= selectedScreenNavigationEntry.getLabel(locale) %>

							<aui:icon image="caret-bottom" markupView="lexicon" />
						</a>

						<div class="collapse menubar-collapse" id="<%= id %>">
							<ul class="nav nav-nested">

								<%
								for (ScreenNavigationEntry screenNavigationEntry : screenNavigationEntries) {
									PortletURL screenNavigationEntryURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

									screenNavigationEntryURL.setParameter("screenNavigationCategoryKey", screenNavigationEntry.getCategoryKey());
									screenNavigationEntryURL.setParameter("screenNavigationEntryKey", screenNavigationEntry.getEntryKey());
								%>

									<li class="nav-item">
										<a class="nav-link <%= Objects.equals(selectedScreenNavigationEntry.getEntryKey(), screenNavigationEntry.getEntryKey()) ? "active" : StringPool.BLANK %>" href="<%= screenNavigationEntryURL %>"><%= screenNavigationEntry.getLabel(themeDisplay.getLocale()) %></a>
									</li>

								<%
								}
								%>

							</ul>
						</div>
					</nav>
				</div>
			</c:if>

			<div class="<%= (screenNavigationEntries.size() > 1) ? containerCssClass : fullContainerCssClass %>">

				<%
				selectedScreenNavigationEntry.render(request, PipingServletResponse.createPipingServletResponse(pageContext));
				%>

			</div>
		</div>
	</div>
</c:if>