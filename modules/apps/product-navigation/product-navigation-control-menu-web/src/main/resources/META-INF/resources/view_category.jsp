<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletCategory portletCategory = (PortletCategory)request.getAttribute(WebKeys.PORTLET_CATEGORY);

int portletCategoryIndex = GetterUtil.getInteger((String)request.getAttribute(WebKeys.PORTLET_CATEGORY_INDEX));

String oldCategoryPath = (String)request.getAttribute(WebKeys.PORTLET_CATEGORY_PATH);

String newCategoryPath = LanguageUtil.get(request, portletCategory.getName());

Pattern pattern = Pattern.compile(".*");

Matcher matcher = pattern.matcher(newCategoryPath);

StringBundler sb = new StringBundler();

while (matcher.find()) {
	sb.append(matcher.group());
}

newCategoryPath = sb.toString();

if (Validator.isNotNull(oldCategoryPath)) {
	newCategoryPath = oldCategoryPath + ":" + newCategoryPath;
}

List<PortletCategory> categories = ListUtil.fromCollection(portletCategory.getCategories());

categories = ListUtil.sort(categories, new PortletCategoryComparator(locale));

List<Portlet> portlets = new ArrayList<Portlet>();

for (String portletId : portletCategory.getPortletIds()) {
	Portlet portlet = PortletLocalServiceUtil.getPortletById(user.getCompanyId(), portletId);

	if ((portlet != null) && PortletPermissionUtil.contains(permissionChecker, layout, portlet, ActionKeys.ADD_TO_PAGE)) {
		portlets.add(portlet);
	}
}

String externalPortletCategory = null;

for (String portletId : PortletCategoryUtil.getFirstChildPortletIds(portletCategory)) {
	Portlet portlet = PortletLocalServiceUtil.getPortletById(user.getCompanyId(), portletId);

	if (portlet == null) {
		continue;
	}

	PortletApp portletApp = portlet.getPortletApp();

	if (portletApp.isWARFile() && Validator.isNull(externalPortletCategory)) {
		PortletConfig curPortletConfig = PortletConfigFactoryUtil.create(portlet, application);

		ResourceBundle portletResourceBundle = curPortletConfig.getResourceBundle(locale);

		externalPortletCategory = ResourceBundleUtil.getString(portletResourceBundle, portletCategory.getName());
	}
}

portlets = ListUtil.sort(portlets, new PortletTitleComparator(application, locale));
%>

<c:if test="<%= !categories.isEmpty() || !portlets.isEmpty() %>">

	<%
	String panelId = renderResponse.getNamespace() + "portletCategory" + portletCategoryIndex;
	%>

	<input id="<portlet:namespace />portletCategory<%= portletCategoryIndex %>CategoryPath" type="hidden" value="<%= StringUtil.replace(newCategoryPath, ':', '-') %>" />

	<div class="lfr-content-category panel-page-category">
		<a class="collapse-icon collapse-icon-middle collapsed list-group-heading panel-header panel-header-link" data-toggle="collapse" href="#<%= panelId %>">
			<%= Validator.isNotNull(externalPortletCategory) ? externalPortletCategory : LanguageUtil.get(request, portletCategory.getName()) %>

			<aui:icon cssClass="collapse-icon-closed" image="angle-right" markupView="lexicon" />

			<aui:icon cssClass="collapse-icon-open" image="angle-down" markupView="lexicon" />
		</a>

		<div class="collapse list-group-panel" id="<%= panelId %>">
			<div class="list-group-item">
				<ul class="nav nav-equal-height nav-stacked">

					<%
					for (PortletCategory category : categories) {
						portletCategoryIndex++;

						request.setAttribute(WebKeys.PORTLET_CATEGORY, category);
						request.setAttribute(WebKeys.PORTLET_CATEGORY_INDEX, String.valueOf(portletCategoryIndex));
						request.setAttribute(WebKeys.PORTLET_CATEGORY_PATH, newCategoryPath);
					%>

						<liferay-util:include page="/view_category.jsp" servletContext="<%= application %>" />

					<%
						request.setAttribute(WebKeys.PORTLET_CATEGORY_PATH, oldCategoryPath);
					}

					Set<String> layoutDecodedPortletNames = null;

					for (Portlet portlet : portlets) {
						sb.setIndex(0);

						sb.append(newCategoryPath);
						sb.append(":");

						matcher = pattern.matcher(PortalUtil.getPortletTitle(portlet, application, locale));

						while (matcher.find()) {
							sb.append(matcher.group());
						}

						boolean portletInstanceable = portlet.isInstanceable();

						boolean portletUsed = false;

						if (layoutDecodedPortletNames != null) {
							portletUsed = layoutDecodedPortletNames.contains(portlet.getPortletId());
						}
						else {
							layoutDecodedPortletNames = new HashSet<>();

							for (Portlet layoutPortlet : layoutTypePortlet.getPortlets()) {
								String decodedPortletName = PortletIdCodec.decodePortletName(layoutPortlet.getPortletId());

								layoutDecodedPortletNames.add(decodedPortletName);

								if (decodedPortletName.equals(portlet.getPortletId())) {
									portletUsed = true;
								}
							}
						}

						boolean portletLocked = !portletInstanceable && portletUsed;

						if (portletInstanceable && layout.isTypePanel()) {
							continue;
						}
					%>

						<c:choose>
							<c:when test="<%= layout.isTypePortlet() %>">

								<%
								Map<String, Object> data = new HashMap<String, Object>();

								data.put("draggable", true);
								data.put("id", renderResponse.getNamespace() + "portletItem" + portlet.getPortletId());
								data.put("instanceable", portletInstanceable);
								data.put("plid", plid);
								data.put("portlet-id", portlet.getPortletId());
								data.put("search", StringUtil.replace(sb.toString(), ':', '-'));
								data.put("title", PortalUtil.getPortletTitle(portlet, application, locale));

								String cssClass = "drag-content-item";

								if (portletLocked) {
									cssClass += " lfr-portlet-used";
								}
								%>

								<li class="lfr-content-item" role="presentation">
									<aui:icon cssClass="<%= cssClass %>" data="<%= data %>" image='<%= portletInstanceable ? "grid" : "live" %>' label="<%= PortalUtil.getPortletTitle(portlet, application, locale) %>" markupView="lexicon" />

									<%
									data.remove("draggable");
									%>

									<a class="add-content-item <%= portletLocked ? "lfr-portlet-used" : StringPool.BLANK %> sr-only sr-only-focusable" href="javascript:;" <%= AUIUtil.buildData(data) %>>
										<liferay-ui:message key="add" />

										<span class="sr-only"><%= PortalUtil.getPortletTitle(portlet, application, locale) %></span>
									</a>
								</li>

								<%
								List<PortletItem> portletItems = PortletItemLocalServiceUtil.getPortletItems(themeDisplay.getScopeGroupId(), portlet.getPortletId(), com.liferay.portal.kernel.model.PortletPreferences.class.getName());
								%>

								<c:if test="<%= ListUtil.isNotEmpty(portletItems) %>">
									<ul class="nav nav-equal-height nav-stacked">

										<%
										for (PortletItem portletItem : portletItems) {
											sb.setIndex(0);

											sb.append(newCategoryPath);
											sb.append(":");
											sb.append(PortalUtil.getPortletTitle(portlet, application, locale));
											sb.append(":");

											matcher = pattern.matcher(HtmlUtil.escape(portletItem.getName()));

											while (matcher.find()) {
												sb.append(matcher.group());
											}

											Map<String, Object> portletItemData = new HashMap<String, Object>();

											portletItemData.put("draggable", true);
											portletItemData.put("id", renderResponse.getNamespace() + "portletItem" + portletItem.getPortletItemId());
											portletItemData.put("instanceable", portletInstanceable);
											portletItemData.put("plid", plid);
											portletItemData.put("portlet-id", portlet.getPortletId());
											portletItemData.put("portlet-item-id", portletItem.getPortletItemId());
											portletItemData.put("search", StringUtil.replace(sb.toString(), ':', '-'));
											portletItemData.put("title", HtmlUtil.escape(portletItem.getName()));
										%>

											<li class="lfr-archived-setup lfr-content-item" role="presentation">
												<aui:icon cssClass="<%= cssClass %>" data="<%= portletItemData %>" image='<%= portletInstanceable ? "grid" : "live" %>' label="<%= HtmlUtil.escape(portletItem.getName()) %>" markupView="lexicon" />

												<%
												portletItemData.remove("draggable");
												%>

												<span <%= AUIUtil.buildData(portletItemData) %> class='add-content-item <%= portletLocked ? "lfr-portlet-used" : StringPool.BLANK %>'>
													<liferay-ui:message key="add" />
												</span>
											</li>

										<%
										}
										%>

									</ul>
								</c:if>
							</c:when>
							<c:otherwise>
								<div>
									<a href="<liferay-portlet:renderURL portletName="<%= portlet.getRootPortletId() %>" windowState="<%= WindowState.MAXIMIZED.toString() %>"></liferay-portlet:renderURL>"><%= PortalUtil.getPortletTitle(portlet, application, locale) %></a>
								</div>
							</c:otherwise>
						</c:choose>

					<%
					}
					%>

				</ul>
			</div>
		</div>
	</div>
</c:if>