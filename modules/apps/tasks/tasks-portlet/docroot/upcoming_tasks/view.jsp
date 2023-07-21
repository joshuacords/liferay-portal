<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="tasks-entries-container">
	<ul class="tasks-entries">

		<%
		List<TasksEntry> taskEntries = TasksEntryLocalServiceUtil.getTasksEntries(0, user.getUserId(), 0, 0, TasksEntryConstants.STATUS_OPEN, new long[0], new long[0], 0, 10);

		for (TasksEntry tasksEntry : taskEntries) {
			String taskHREF = null;

			if (TasksEntryPermission.contains(permissionChecker, tasksEntry, ActionKeys.UPDATE)) {
				LiferayPortletURL liferayPortletURL = liferayPortletResponse.createLiferayPortletURL(PortletKeys.TASKS, PortletRequest.RENDER_PHASE);

				liferayPortletURL.setParameter("mvcPath", "/tasks/view_task.jsp");
				liferayPortletURL.setParameter("tasksEntryId", String.valueOf(tasksEntry.getTasksEntryId()));
				liferayPortletURL.setWindowState(LiferayWindowState.POP_UP);

				taskHREF = liferayPortletURL.toString();
			}

			String cssClass = "tasks-title";

			if (tasksEntry.getPriority() == 1) {
				cssClass = cssClass.concat(" high");
			}
			else if (tasksEntry.getPriority() == 2) {
				cssClass = cssClass.concat(" normal");
			}
			else {
				cssClass = cssClass.concat(" low");
			}
		%>

			<li class="<%= cssClass %>">
				<c:choose>
					<c:when test="<%= Validator.isNotNull(taskHREF) %>">
						<a href="javascript:;" onClick="Liferay.Tasks.openTask('<%= taskHREF %>');">
							<i class="icon-circle"></i>

							<%= HtmlUtil.escape(tasksEntry.getTitle()) %>
						</a>
					</c:when>
					<c:otherwise>
						<span>
							<i class="icon-circle"></i>

							<%= HtmlUtil.escape(tasksEntry.getTitle()) %>
						</span>
					</c:otherwise>
				</c:choose>
			</li>

		<%
		}
		%>

	</ul>

	<div class="view-all-tasks">

		<%
		long tasksPlid = PortalUtil.getPlidFromPortletId(group.getGroupId(), PortletKeys.TASKS);

		PortletURL portletURL = null;

		if (tasksPlid != 0) {
			portletURL = PortletURLFactoryUtil.create(request, PortletKeys.TASKS, tasksPlid, PortletRequest.RENDER_PHASE);
		}
		%>

		<c:if test="<%= portletURL != null %>">
			<a href="<%= portletURL %>"><liferay-ui:message key="view-all-tasks" /></a>
		</c:if>
	</div>
</div>

<aui:script use="liferay-tasks">
	Liferay.Tasks.initUpcomingTasks(
		{
			upcomingTasksListURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/upcoming_tasks/view.jsp" /></portlet:renderURL>'
		}
	);
</aui:script>