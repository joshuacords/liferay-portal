<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/blogs/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

BlogsEntry blogsEntry = (BlogsEntry)request.getAttribute("view_entry_related.jsp-blogs_entry");

BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration = BlogsPortletInstanceConfigurationUtil.getBlogsPortletInstanceConfiguration(themeDisplay);
%>

<c:if test="<%= blogsEntry != null %>">
	<div class="col-lg-6">
		<div class="card">

			<%
			String imageURL = blogsEntry.getCoverImageURL(themeDisplay);

			if (Validator.isNull(imageURL)) {
				imageURL = blogsEntry.getSmallImageURL(themeDisplay);
			}
			%>

			<c:if test="<%= Validator.isNotNull(imageURL) %>">
				<div class="card-header">
					<div class="aspect-ratio aspect-ratio-8-to-3">
						<img alt="thumbnail" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<%= HtmlUtil.escape(imageURL) %>" />
					</div>
				</div>
			</c:if>

			<div class="card-body widget-topbar">
				<div class="autofit-row card-title">
					<div class="autofit-col autofit-col-expand">
						<portlet:renderURL var="blogsEntryURL">
							<portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="urlTitle" value="<%= blogsEntry.getUrlTitle() %>" />
						</portlet:renderURL>

						<liferay-util:html-top
							outputKey="blogs_previous_entry_link"
						>
							<link href="<%= blogsEntryURL.toString() %>" rel="prev" />
						</liferay-util:html-top>

						<h3 class="title"><a class="title-link" href="<%= blogsEntryURL %>">
							<%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, blogsEntry)) %></a>
						</h3>
					</div>
				</div>

				<div class="autofit-row widget-metadata">
					<div class="autofit-col inline-item-before">

						<%
						User blogsEntryUser = UserLocalServiceUtil.fetchUser(blogsEntry.getUserId());

						String blogsEntryUserURL = StringPool.BLANK;

						if ((blogsEntryUser != null) && !blogsEntryUser.isDefaultUser() && !user.isDefaultUser()) {
							blogsEntryUserURL = blogsEntryUser.getDisplayURL(themeDisplay);
						}
						%>

						<liferay-ui:user-portrait
							user="<%= blogsEntryUser %>"
						/>
					</div>

					<div class="autofit-col autofit-col-expand">
						<div class="autofit-row">
							<div class="autofit-col autofit-col-expand">
								<div class="text-truncate-inline">
									<a class="text-truncate username" href="<%= blogsEntryUserURL %>"><%= HtmlUtil.escape(blogsEntry.getUserName()) %></a>
								</div>

								<div class="text-secondary">
									<%= DateUtil.getDate(blogsEntry.getStatusDate(), "dd MMM", locale) %>

									<c:if test="<%= blogsPortletInstanceConfiguration.enableReadingTime() %>">
										- <liferay-reading-time:reading-time displayStyle="descriptive" model="<%= blogsEntry %>" />
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="card-footer">
				<div class="card-row">

					<%
					request.setAttribute("entry_toolbar.jsp-entry", blogsEntry);
					%>

					<liferay-util:include page="/blogs/entry_toolbar.jsp" servletContext="<%= application %>">
						<liferay-util:param name="showOnlyIcons" value="<%= Boolean.TRUE.toString() %>" />
					</liferay-util:include>
				</div>
			</div>
		</div>
	</div>
</c:if>