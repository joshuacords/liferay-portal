<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);

renderResponse.setTitle(koroneikiAccount.getName());
%>

<liferay-util:include page="/accounts_admin/edit_account_tabs.jsp" servletContext="<%= application %>" />

<clay:management-toolbar
	creationMenu='<%=
		new JSPCreationMenu(pageContext) {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/edit_account_note", "redirect", PortalUtil.getCurrentURL(request), "accountId", koroneikiAccount.getAccountId());
						dropdownItem.setLabel(LanguageUtil.get(request, "add"));
					});
			}
		}
	%>'
	selectable="<%= false %>"
	showSearch="<%= false %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="no-notes-were-found"
		headerNames="name,type"
		iteratorURL="<%= currentURLObj %>"
		total="<%= AccountNoteLocalServiceUtil.getAccountNotesCount(koroneikiAccount.getAccountId()) %>"
	>
		<liferay-ui:search-container-results
			results="<%= AccountNoteLocalServiceUtil.getAccountNotes(koroneikiAccount.getAccountId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.AccountNote"
			escapedModel="<%= true %>"
			keyProperty="accountNoteId"
			modelVar="accountNote"
		>
			<liferay-portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts_admin/edit_account_note" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountNoteId" value="<%= String.valueOf(accountNote.getAccountNoteId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="created-by"
				value="<%= accountNote.getCreatorName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="last-modified-by"
				value="<%= accountNote.getModifierName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				property="type"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				property="priority"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="content"
				value="<%= StringUtil.shorten(accountNote.getContent(), 100) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label label-sm label-<%= StringUtil.lowerCase(accountNote.getStatus()) %>"><%= StringUtil.lowerCase(accountNote.getStatus()) %></span>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/accounts_admin/account_note_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>