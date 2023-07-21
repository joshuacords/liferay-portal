<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error key="shutdownMinutes" message="please-enter-the-number-of-minutes" />

<aui:button-row>
	<c:choose>
		<c:when test="<%= ShutdownUtil.isInProcess() %>">
			<aui:button cssClass="save-server-button" data-cmd="shutdown" value="cancel-shutdown" />
		</c:when>
		<c:otherwise>
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input label="number-of-minutes" name="minutes" size="3" type="text">
						<aui:validator name="digits" />
						<aui:validator name="min">1</aui:validator>
						<aui:validator name="required" />
					</aui:input>

					<aui:input cssClass="lfr-textarea-container" label="custom-message" name="message" type="textarea" />
				</aui:fieldset>
			</aui:fieldset-group>

			<aui:button cssClass="save-server-button" data-cmd="shutdown" value="shutdown" />
		</c:otherwise>
	</c:choose>
</aui:button-row>