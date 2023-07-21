<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/captcha/init.jsp" %>

<%
String url = (String)request.getAttribute("liferay-captcha:captcha:url");
%>

<c:if test="<%= captchaEnabled %>">
	<div class="taglib-captcha">
		<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="text-to-identify" />" class="captcha" id="<portlet:namespace />captcha" src="<%= HtmlUtil.escapeAttribute(HttpUtil.addParameter(url, "t", String.valueOf(System.currentTimeMillis()))) %>" />

		<liferay-ui:icon
			cssClass="refresh"
			icon="reload"
			id="refreshCaptcha"
			label="<%= false %>"
			localizeMessage="<%= true %>"
			markupView="lexicon"
			message="refresh-captcha"
			url="javascript:;"
		/>

		<aui:input ignoreRequestValue="<%= true %>" label="text-verification" name="captchaText" size="10" type="text" value="">
			<aui:validator name="required" />
		</aui:input>
	</div>

	<aui:script>
		var hasEventAttached = false;

		function attachEvent() {
			var refreshCaptcha = document.getElementById(
				'<portlet:namespace />refreshCaptcha'
			);

			if (refreshCaptcha && !hasEventAttached) {
				hasEventAttached = true;
				refreshCaptcha.addEventListener('click', function() {
					var url = Liferay.Util.addParams(
						't=' + Date.now(),
						'<%= HtmlUtil.escapeJS(url) %>'
					);

					var captcha = document.getElementById(
						'<portlet:namespace />captcha'
					);

					if (captcha) {
						captcha.setAttribute('src', url);
					}
				});
			}
		}

		attachEvent();

		Liferay.on('<portlet:namespace />simplecaptcha_attachEvent', attachEvent);
	</aui:script>
</c:if>