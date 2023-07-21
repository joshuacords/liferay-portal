<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<aui:script sandbox="<%= true %>">
	var onDestroyPortlet = function() {
		Liferay.detach('messagePosted', onMessagePosted);
		Liferay.detach('destroyPortlet', onDestroyPortlet);
	};

	Liferay.on('destroyPortlet', onDestroyPortlet);

	var onMessagePosted = function(event) {
		if (window.Analytics) {
			Analytics.send('posted', 'Comment', {
				className: event.className,
				classPK: event.classPK,
				commentId: event.commentId,
				text: event.text
			});
		}
	};

	Liferay.on('messagePosted', onMessagePosted);
</aui:script>