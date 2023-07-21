<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String[] names = StringUtil.split(ParamUtil.getString(request, "names"));
String param = ParamUtil.getString(request, "param");
String url = ParamUtil.getString(request, "url");
String[] values = StringUtil.split(ParamUtil.getString(request, "values"));

String paramValue = ParamUtil.getString(request, param);
%>

<ul class="mb-3 mb-lg-4 nav nav-underline">

	<%
	for (int i = 0; i < names.length; i++) {
		StringBundler sb = new StringBundler();

		sb.append(url);
		sb.append("&");
		sb.append(renderResponse.getNamespace());
		sb.append(param);
		sb.append("=");
		sb.append(values[i]);
	%>

		<li class="nav-item">
			<a class="nav-link <%= (((i == 0) && Validator.isNull(paramValue)) || paramValue.equals(values[i])) ? "active" : "" %>" href="<%= sb.toString() %>">
				<%= LanguageUtil.get(request, names[i]) %>
			</a>
		</li>

	<%
	}
	%>

</ul>