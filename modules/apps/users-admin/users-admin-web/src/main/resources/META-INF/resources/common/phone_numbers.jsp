<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String className = (String)request.getAttribute("contact_information.jsp-className");
long classPK = (long)request.getAttribute("contact_information.jsp-classPK");

String emptyResultsMessage = ParamUtil.getString(request, "emptyResultsMessage");

List<Phone> phones = PhoneServiceUtil.getPhones(className, classPK);
%>

<h3 class="autofit-row sheet-subtitle">
	<span class="autofit-col autofit-col-expand">
		<span class="heading-text"><liferay-ui:message key="phone-numbers" /></span>
	</span>
	<span class="autofit-col">
		<span class="heading-end">

			<%
			PortletURL editURL = liferayPortletResponse.createRenderURL();

			editURL.setParameter("mvcPath", "/common/edit_phone_number.jsp");
			editURL.setParameter("redirect", currentURL);
			editURL.setParameter("className", className);
			editURL.setParameter("classPK", String.valueOf(classPK));
			%>

			<liferay-ui:icon
				label="<%= true %>"
				linkCssClass="add-phone-number-link btn btn-secondary btn-sm"
				message="add"
				url="<%= editURL.toString() %>"
			/>
		</span>
	</span>
</h3>

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	cssClass="lfr-search-container-wrapper"
	curParam="phoneNumberCur"
	deltaParam="phoneNumbersDelta"
	emptyResultsMessage="<%= emptyResultsMessage %>"
	headerNames="phone-number,type,extension,"
	id="phonesSearchContainer"
	iteratorURL="<%= currentURLObj %>"
	total="<%= phones.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= phones.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Phone"
		escapedModel="<%= true %>"
		keyProperty="phoneId"
		modelVar="phone"
	>
		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand"
			name="phone-number"
			property="number"
		/>

		<%
		ListType phoneListType = ListTypeServiceUtil.getListType(phone.getTypeId());

		String phoneTypeKey = phoneListType.getName();
		%>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand-small"
			name="type"
			value="<%= LanguageUtil.get(request, phoneTypeKey) %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand-small"
			name="extension"
			property="extension"
		/>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-expand-smaller"
		>
			<c:if test="<%= phone.isPrimary() %>">
				<span class="label label-primary">
					<span class="label-item label-item-expand"><%= StringUtil.toUpperCase(LanguageUtil.get(request, "primary"), locale) %></span>
				</span>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action-column"
			path="/common/phone_number_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>