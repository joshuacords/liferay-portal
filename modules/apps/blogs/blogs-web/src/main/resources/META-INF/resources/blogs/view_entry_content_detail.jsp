<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/blogs/init.jsp" %>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("view_entry_content.jsp-searchContainer");

BlogsEntry entry = (BlogsEntry)request.getAttribute("view_entry_content.jsp-entry");

BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration = BlogsPortletInstanceConfigurationUtil.getBlogsPortletInstanceConfiguration(themeDisplay);
%>

<c:choose>
	<c:when test="<%= entry.isVisible() || (entry.getUserId() == user.getUserId()) || BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="viewEntryURL">
			<portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />

			<c:choose>
				<c:when test="<%= Validator.isNotNull(entry.getUrlTitle()) %>">
					<portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
				</c:when>
				<c:otherwise>
					<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
				</c:otherwise>
			</c:choose>
		</portlet:renderURL>

		<div class="container widget-mode-detail-header">
			<liferay-asset:asset-categories-available
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= entry.getEntryId() %>"
			>
				<div class="row">
					<div class="categories col-md-8 mx-auto widget-metadata">
						<liferay-asset:asset-categories-summary
							className="<%= BlogsEntry.class.getName() %>"
							classPK="<%= entry.getEntryId() %>"
							displayStyle="simple-category"
							portletURL="<%= renderResponse.createRenderURL() %>"
						/>
					</div>
				</div>
			</liferay-asset:asset-categories-available>

			<div class="row">
				<div class="col-md-8 mx-auto">
					<div class="autofit-row">
						<div class="autofit-col autofit-col-expand">
							<h3 class="title"><%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, entry)) %></h3>

							<%
							String subtitle = entry.getSubtitle();
							%>

							<c:if test="<%= Validator.isNotNull(subtitle) %>">
								<h4 class="sub-title"><%= HtmlUtil.escape(subtitle) %></h4>
							</c:if>
						</div>

						<div class="autofit-col visible-interaction">
							<div class="dropdown dropdown-action">
								<c:if test="<%= BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE) %>">
									<portlet:renderURL var="editEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
										<portlet:param name="mvcRenderCommandName" value="/blogs/edit_entry" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
									</portlet:renderURL>

									<a href="<%= editEntryURL.toString() %>">
										<span class="hide-accessible"><liferay-ui:message key="edit-entry" /></span>

										<clay:icon
											symbol="pencil"
										/>
									</a>
								</c:if>
							</div>
						</div>
					</div>

					<div class="autofit-row widget-metadata">
						<div class="autofit-col inline-item-before">

							<%
							User entryUser = UserLocalServiceUtil.fetchUser(entry.getUserId());

							String entryUserURL = StringPool.BLANK;

							if ((entryUser != null) && !entryUser.isDefaultUser() && !user.isDefaultUser()) {
								entryUserURL = entryUser.getDisplayURL(themeDisplay);
							}
							%>

							<liferay-ui:user-portrait
								cssClass="sticker-lg"
								user="<%= entryUser %>"
							/>
						</div>

						<div class="autofit-col autofit-col-expand">
							<div class="autofit-row">
								<div class="autofit-col autofit-col-expand">
									<div class="text-truncate-inline">
										<a class="text-truncate username" href="<%= entryUserURL %>"><%= HtmlUtil.escape(entry.getUserName()) %></a>
									</div>

									<div>
										<span class="hide-accessible"><liferay-ui:message key="published-date" /></span><liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - entry.getStatusDate().getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />

										<c:if test="<%= blogsPortletInstanceConfiguration.enableReadingTime() %>">
											- <liferay-reading-time:reading-time displayStyle="descriptive" model="<%= entry %>" />
										</c:if>

										<c:if test="<%= blogsPortletInstanceConfiguration.enableViewCount() %>">

											<%
											AssetEntry assetEntry = BlogsEntryAssetEntryUtil.getAssetEntry(request, entry);
											%>

											- <liferay-ui:message arguments="<%= assetEntry.getViewCount() %>" key='<%= (assetEntry.getViewCount() == 1) ? "x-view" : "x-views" %>' />
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%
		String coverImageURL = entry.getCoverImageURL(themeDisplay);
		%>

		<liferay-util:include page="/blogs/entry_cover_image_caption.jsp" servletContext="<%= application %>">
			<liferay-util:param name="coverImageCaption" value="<%= entry.getCoverImageCaption() %>" />
			<liferay-util:param name="coverImageURL" value="<%= coverImageURL %>" />
		</liferay-util:include>

		<!-- text resume -->

		<div class="container widget-mode-detail-header" id="<portlet:namespace /><%= entry.getEntryId() %>">
			<c:if test="<%= Validator.isNotNull(coverImageURL) %>">

				<%
				String coverImageCaption = entry.getCoverImageCaption();
				%>

				<c:if test="<%= Validator.isNotNull(coverImageCaption) %>">
					<div class="row">
						<div class="col-md-8 mx-auto">
							<div class="cover-image-caption">
								<small><%= entry.getCoverImageCaption() %></small>
							</div>
						</div>
					</div>
				</c:if>
			</c:if>

			<div class="row">
				<div class="col-md-8 mx-auto widget-mode-detail-text">
					<%= entry.getContent() %>
				</div>
			</div>

			<liferay-expando:custom-attributes-available
				className="<%= BlogsEntry.class.getName() %>"
			>
				<div class="row">
					<div class="col-md-8 mx-auto widget-mode-detail">
						<liferay-expando:custom-attribute-list
							className="<%= BlogsEntry.class.getName() %>"
							classPK="<%= entry.getEntryId() %>"
							editable="<%= false %>"
							label="<%= true %>"
						/>
					</div>
				</div>
			</liferay-expando:custom-attributes-available>

			<liferay-asset:asset-tags-available
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= entry.getEntryId() %>"
			>
				<div class="row">
					<div class="col-md-8 mx-auto widget-mode-detail">
						<div class="entry-tags">
							<liferay-asset:asset-tags-summary
								className="<%= BlogsEntry.class.getName() %>"
								classPK="<%= entry.getEntryId() %>"
								portletURL="<%= renderResponse.createRenderURL() %>"
							/>
						</div>
					</div>
				</div>
			</liferay-asset:asset-tags-available>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-8 mx-auto widget-mode-detail">

					<%
					request.setAttribute("entry_toolbar.jsp-entry", entry);
					%>

					<liferay-util:include page="/blogs/entry_toolbar.jsp" servletContext="<%= application %>">
						<liferay-util:param name="showFlags" value="<%= Boolean.TRUE.toString() %>" />
					</liferay-util:include>
				</div>
			</div>

			<c:if test="<%= blogsPortletInstanceConfiguration.enableRelatedAssets() %>">
				<div class="row">
					<div class="col-md-8 mx-auto widget-mode-detail">

						<%
						AssetEntry assetEntry = BlogsEntryAssetEntryUtil.getAssetEntry(request, entry);
						%>

						<div class="entry-links">
							<liferay-asset:asset-links
								assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
								className="<%= BlogsEntry.class.getName() %>"
								classPK="<%= entry.getEntryId() %>"
							/>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>

		<%
		if (searchContainer != null) {
			searchContainer.setTotal(searchContainer.getTotal() - 1);
		}
		%>

	</c:otherwise>
</c:choose>