<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/polls/init.jsp" %>

<liferay-util:include page="/polls/navigation_bar.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/polls/management_bar.jsp" servletContext="<%= application %>" />

<div class="container-fluid container-fluid-max-xl main-content-body">
	<aui:form method="post" name="fm">
		<liferay-ui:error exception="<%= DuplicateVoteException.class %>" message="you-may-only-vote-once" />
		<liferay-ui:error exception="<%= NoSuchChoiceException.class %>" message="please-select-an-option" />

		<liferay-ui:search-container
			cssClass="table-nowrap"
			id="<%= pollsDisplayContext.getSearchContainerId() %>"
			searchContainer="<%= pollsDisplayContext.getSearch() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.polls.model.PollsQuestion"
				modelVar="question"
			>

				<%
				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("mvcRenderCommandName", "/polls/view_question");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("questionId", String.valueOf(question.getQuestionId()));
				%>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200 table-title"
					href="<%= rowURL %>"
					name="title"
					value="<%= HtmlUtil.escape(question.getTitle(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand-smaller"
					href="<%= rowURL %>"
					name="num-of-votes"
					value="<%= String.valueOf(PollsVoteLocalServiceUtil.getQuestionVotesCount(question.getQuestionId())) %>"
				/>

				<c:choose>
					<c:when test="<%= question.getLastVoteDate() != null %>">
						<liferay-ui:search-container-column-date
							cssClass="table-cell-expand-smaller table-cell-ws-nowrap"
							href="<%= rowURL %>"
							name="last-vote-date"
							value="<%= question.getLastVoteDate() %>"
						/>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smaller table-cell-ws-nowrap"
							href="<%= rowURL %>"
							name="last-vote-date"
							value='<%= LanguageUtil.get(request, "never") %>'
						/>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="<%= question.getExpirationDate() != null %>">
						<c:choose>
							<c:when test="<%= question.getExpirationDate().before(new Date()) %>">
								<liferay-ui:search-container-column-text
									cssClass="table-cell-expand-smaller table-cell-ws-nowrap"
									href="<%= rowURL %>"
									name="expiration-date"
									value='<%= LanguageUtil.get(request, "expired") %>'
								/>
							</c:when>
							<c:otherwise>
								<liferay-ui:search-container-column-date
									cssClass="table-cell-expand-smaller table-cell-ws-nowrap"
									href="<%= rowURL %>"
									name="expiration-date"
									value="<%= question.getExpirationDate() %>"
								/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smaller table-cell-ws-nowrap"
							href="<%= rowURL %>"
							name="expiration-date"
							value='<%= LanguageUtil.get(request, "never") %>'
						/>
					</c:otherwise>
				</c:choose>

				<liferay-ui:search-container-column-jsp
					path="/polls/question_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="list"
				markupView="lexicon"
				searchContainer="<%= searchContainer %>"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>