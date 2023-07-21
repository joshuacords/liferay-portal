<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/gallery/init.jsp" %>

<div class="gallery-root" id="<%= galleryId %>"></div>

<aui:script require="commerce-frontend-js/components/gallery/entry as gallery">
	gallery.default('<%= galleryId %>', '<%= galleryId %>', {
		images: <%= jsonSerializer.serializeDeep(images) %>,
		portletId: '<%= portletDisplay.getRootPortletId() %>'
	});
</aui:script>