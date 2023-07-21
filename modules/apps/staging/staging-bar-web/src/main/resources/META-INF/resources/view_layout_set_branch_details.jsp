<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutRevision layoutRevision = (LayoutRevision)request.getAttribute(WebKeys.LAYOUT_REVISION);
LayoutSetBranch layoutSetBranch = (LayoutSetBranch)request.getAttribute(StagingProcessesWebKeys.LAYOUT_SET_BRANCH);
List<LayoutSetBranch> layoutSetBranches = (List<LayoutSetBranch>)request.getAttribute(StagingProcessesWebKeys.LAYOUT_SET_BRANCHES);
%>

<c:if test="<%= (layoutSetBranches != null) && !layoutSetBranches.isEmpty() %>">
	<div class="control-menu-label staging-variation-label">
		<liferay-util:buffer
			var="sitePagesVariationsHelpIcon"
		>
			<liferay-ui:icon-help message="pages-variations-help" />
		</liferay-util:buffer>

		<liferay-ui:message arguments="<%= sitePagesVariationsHelpIcon %>" key="site-pages-variation-x" />
	</div>

	<div class="dropdown">
		<a class="dropdown-toggle layout-set-branch-selector staging-variation-selector" data-toggle="dropdown" href="#1">
			<liferay-ui:message key="<%= HtmlUtil.escape(layoutSetBranchDisplayContext.getLayoutSetBranchDisplayName(layoutSetBranch)) %>" localizeKey="<%= false %>" />

			<aui:icon image="caret-double-l" markupView="lexicon" />
		</a>

		<ul class="dropdown-menu">

			<%
			for (LayoutSetBranch curLayoutSetBranch : layoutSetBranches) {
				boolean selected = (group.isStagingGroup() || group.isStagedRemotely()) && (curLayoutSetBranch.getLayoutSetBranchId() == layoutRevision.getLayoutSetBranchId());
			%>

				<portlet:actionURL name="selectLayoutSetBranch" var="curLayoutSetBranchURL">
					<portlet:param name="redirect" value="<%= (String)request.getAttribute(StagingProcessesWebKeys.STAGING_URL) %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(curLayoutSetBranch.getGroupId()) %>" />
					<portlet:param name="privateLayout" value="<%= String.valueOf(layout.isPrivateLayout()) %>" />
					<portlet:param name="layoutSetBranchId" value="<%= String.valueOf(curLayoutSetBranch.getLayoutSetBranchId()) %>" />
				</portlet:actionURL>

				<li>
					<a class="<%= selected ? "disabled" : StringPool.BLANK %>" href="<%= selected ? "javascript:;" : "javascript:submitForm(document.hrefFm, '" + HtmlUtil.escapeJS(curLayoutSetBranchURL) + "');" %>">
						<liferay-ui:message key="<%= HtmlUtil.escape(layoutSetBranchDisplayContext.getLayoutSetBranchDisplayName(curLayoutSetBranch)) %>" localizeKey="<%= false %>" />
					</a>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>