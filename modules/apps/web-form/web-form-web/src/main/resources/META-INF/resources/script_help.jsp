<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<p>
	<liferay-ui:message key="enter-javascript-code-that-returns-true-or-false-to-validate-the-field.-the-following-implicit-variables-are-available"></liferay-ui:message>
</p>

<ul>
	<li>
		<strong>currentFieldValue</strong>: <liferay-ui:message key="the-value-being-validated" />
	</li>
	<li>
		<strong>fieldsMap</strong>: <liferay-ui:message key="the-array-of-all-form-values-indexed-by-name" />
	</li>
</ul>