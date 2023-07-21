<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNotNull(youTubeDisplayContext.getURL()) %>">
		<c:choose>
			<c:when test="<%= youTubeDisplayContext.isShowThumbnail() %>">
				<aui:a href="<%= youTubeDisplayContext.getWatchURL() %>" rel="external" title='<%= HtmlUtil.escapeAttribute(LanguageUtil.get(request, "watch-this-video-at-youtube")) %>'>
					<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="youtube-video" />" height="<%= youTubeDisplayContext.getHeight() %>" src="<%= youTubeDisplayContext.getImageURL() %>" width="<%= youTubeDisplayContext.getWidth() %>" />
				</aui:a>
			</c:when>
			<c:otherwise>
				<iframe allowfullscreen frameborder="0" id="<portlet:namespace />iframe" src="<%= youTubeDisplayContext.getEmbedURL() %>" wmode="Opaque" /></iframe>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />resizeIFrame() {
		var iframe = document.getElementById('<portlet:namespace />iframe');

		if (iframe != null) {
			var displayContextWidth = <%= youTubeDisplayContext.getWidth() %>;

			var parentWidth = iframe.parentElement.offsetWidth;

			if (displayContextWidth > parentWidth) {
				displayContextWidth = parentWidth;
			}

			iframe.setAttribute('height', <%= youTubeDisplayContext.getHeight() %>);
			iframe.setAttribute('width', displayContextWidth);
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />addDragAndDropListener',
		function() {
			if (!Liferay.Layout) {
				setTimeout(function() {
					<portlet:namespace />addDragAndDropListener();
				}, 5000);
			}
			else {
				Liferay.Layout.on(['drag:end', 'drag:start'], function() {
					AUI().debounce(<portlet:namespace />resizeIFrame(), 500);
				});
			}
		},
		['aui-debounce']
	);

	Liferay.on('allPortletsReady', function() {
		<portlet:namespace />addDragAndDropListener();
	});

	Liferay.on('portletReady', function() {
		<portlet:namespace />resizeIFrame();
	});
</aui:script>

<aui:script use="event">
	A.on('windowresize', function() {
		<portlet:namespace />resizeIFrame();
	});
</aui:script>