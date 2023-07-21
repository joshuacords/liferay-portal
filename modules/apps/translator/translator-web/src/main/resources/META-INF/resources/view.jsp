<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Translation translation = (Translation)request.getAttribute(TranslatorConfiguration.TRANSLATOR_TRANSLATION);

Map<String, String> languageIdsMap = TranslatorUtil.getLanguageIdsMap(locale, translatorConfiguration);

if (translation == null) {
	String translationId = translatorConfiguration.translationId();

	String[] fromAndTolanguageIds = TranslatorUtil.getFromAndToLanguageIds(translationId, languageIdsMap);

	if (fromAndTolanguageIds != null) {
		String fromLanguageId = fromAndTolanguageIds[0];
		String toLanguageId = fromAndTolanguageIds[1];

		translation = new Translation(fromLanguageId, toLanguageId, StringPool.BLANK, StringPool.BLANK);
	}
}
%>

<c:choose>
	<c:when test="<%= translation == null %>">
		<div class="alert alert-danger">
			<liferay-ui:message key="please-configure-valid-default-languages" />
		</div>
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="portletURL" />

		<aui:form accept-charset="UTF-8" action="<%= portletURL %>" method="post" name="fm">
			<liferay-ui:error exception="<%= MicrosoftTranslatorException.class %>">

				<%
				MicrosoftTranslatorException mte = (MicrosoftTranslatorException)errorException;
				%>

				<liferay-ui:message key="<%= mte.getMessage() %>" />
			</liferay-ui:error>

			<c:if test="<%= Validator.isNotNull(translation.getToText()) %>">
				<%= HtmlUtil.escape(translation.getToText()) %>
			</c:if>

			<aui:fieldset>
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" cssClass="lfr-textarea-container" label="" name="text" type="textarea" value="<%= translation.getFromText() %>" wrap="soft" />

				<aui:select label="language-from" name="fromLanguageId">

					<%
					for (Map.Entry<String, String> entry : languageIdsMap.entrySet()) {
						String languageId = entry.getKey();
					%>

						<aui:option label="<%= entry.getValue() %>" selected="<%= languageId.equals(translation.getFromLanguageId()) %>" value="<%= languageId %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select label="language-to" name="toLanguageId">

					<%
					for (Map.Entry<String, String> entry : languageIdsMap.entrySet()) {
						String languageId = entry.getKey();
					%>

						<aui:option label="<%= entry.getValue() %>" selected="<%= languageId.equals(translation.getToLanguageId()) %>" value="<%= languageId %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>

			<aui:button-row>
				<aui:button type="submit" value="translate" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>