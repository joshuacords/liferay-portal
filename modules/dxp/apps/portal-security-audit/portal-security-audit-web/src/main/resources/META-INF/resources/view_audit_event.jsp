<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String referrer = (String)request.getAttribute(WebKeys.REFERER);

long auditEventId = ParamUtil.getLong(request, "auditEventId");

AuditEvent auditEvent = null;

String eventTypeAction = StringPool.BLANK;

if (auditEventId > 0) {
	auditEvent = AuditEventManagerUtil.fetchAuditEvent(auditEventId);

	if (auditEvent != null) {
		auditEvent = auditEvent.toEscapedModel();

		eventTypeAction = (String)PortalClassInvoker.invoke(new MethodKey(ClassResolverUtil.resolve("com.liferay.portal.kernel.security.permission.ResourceActionsUtil", PortalClassLoaderUtil.getClassLoader()), "getAction", HttpServletRequest.class, String.class), request, auditEvent.getEventType());
	}
}
%>

<liferay-ui:header
	backURL='<%= Validator.isNotNull(referrer) ? referrer : "javascript:history.go(-1);" %>'
	escapeXml="<%= false %>"
	localizeTitle="<%= auditEvent == null %>"
	title='<%= (auditEvent == null) ? "audit-event" : auditEvent.getEventType() + " (" + eventTypeAction + ")" %>'
/>

<aui:fieldset>
	<c:choose>
		<c:when test="<%= auditEvent == null %>">
			<div class="portlet-msg-error">
				<liferay-ui:message key="the-event-could-not-be-found" />
			</div>
		</c:when>
		<c:otherwise>
			<aui:col columnWidth="350">
				<aui:field-wrapper label="event-id">
					<%= auditEvent.getAuditEventId() %>
				</aui:field-wrapper>

				<aui:field-wrapper label="create-date">
					<%= dateFormatDateTime.format(auditEvent.getCreateDate()) %>
				</aui:field-wrapper>

				<aui:field-wrapper label="resource-id">
					<%= auditEvent.getClassPK() %>
				</aui:field-wrapper>

				<aui:field-wrapper label="resource-name">
					<%= auditEvent.getClassName() %>

					(<%= (String)PortalClassInvoker.invoke(new MethodKey(ClassResolverUtil.resolve("com.liferay.portal.kernel.security.permission.ResourceActionsUtil", PortalClassLoaderUtil.getClassLoader()), "getModelResource", HttpServletRequest.class, String.class), request, auditEvent.getClassName()) %>)
				</aui:field-wrapper>

				<aui:field-wrapper label="resource-action">
					<%= auditEvent.getEventType() %>

					(<%= eventTypeAction %>)
				</aui:field-wrapper>
			</aui:col>

			<aui:col>
				<aui:field-wrapper label="user-id">
					<%= auditEvent.getUserId() %>
				</aui:field-wrapper>

				<aui:field-wrapper label="user-name">
					<%= auditEvent.getUserName() %>
				</aui:field-wrapper>

				<aui:field-wrapper label="client-host">
					<%= Validator.isNotNull(auditEvent.getClientHost()) ? auditEvent.getClientHost() : LanguageUtil.get(request, "none") %>
				</aui:field-wrapper>

				<aui:field-wrapper label="client-ip">
					<%= Validator.isNotNull(auditEvent.getClientIP()) ? auditEvent.getClientIP() : LanguageUtil.get(request, "none") %>
				</aui:field-wrapper>

				<aui:field-wrapper label="server-name">
					<%= Validator.isNotNull(auditEvent.getServerName()) ? auditEvent.getServerName() : LanguageUtil.get(request, "none") %>
				</aui:field-wrapper>

				<aui:field-wrapper label="additional-information">
					<%= Validator.isNotNull(auditEvent.getAdditionalInfo()) ? auditEvent.getAdditionalInfo() : LanguageUtil.get(request, "none") %>
				</aui:field-wrapper>
			</aui:col>
		</c:otherwise>
	</c:choose>
</aui:fieldset>