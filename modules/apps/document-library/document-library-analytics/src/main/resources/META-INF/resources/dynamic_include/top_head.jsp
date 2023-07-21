<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<script data-senna-track="temporary" type="text/javascript">
	if (window.Analytics) {
		window.<%= DocumentLibraryAnalyticsConstants.JS_PREFIX %>isViewFileEntry = false;
	}
</script>

<aui:script sandbox="<%= true %>">
	var pathnameRegexp = /\/documents\/(\d+)\/(\d+)\/(.+?)\/([^&]+)/;

	function handleDownloadClick(event) {
		if (event.target.nodeName.toLowerCase() === 'a' && window.Analytics) {
			var anchor = event.target;
			var match = pathnameRegexp.exec(anchor.pathname);

			var fileEntryId =
				anchor.dataset.analyticsFileEntryId ||
				(anchor.parentElement &&
					anchor.parentElement.dataset.analyticsFileEntryId);

			if (fileEntryId && match) {
				var getParameterValue = function(parameterName) {
					var result = null;

					anchor.search
						.substr(1)
						.split('&')
						.forEach(function(item) {
							var tmp = item.split('=');

							if (tmp[0] === parameterName) {
								result = decodeURIComponent(tmp[1]);
							}
						});

					return result;
				};

				Analytics.send('documentDownloaded', 'Document', {
					groupId: match[1],
					fileEntryId: fileEntryId,
					preview: !!window.<%= DocumentLibraryAnalyticsConstants.JS_PREFIX %>isViewFileEntry,
					title: decodeURIComponent(
						match[3].replace(/\.[^.\\:*?"<>|\r\n]+$/, '')
					),
					version: getParameterValue('version')
				});
			}
		}
	}

	var onDestroyPortlet = function() {
		document.body.removeEventListener('click', handleDownloadClick);
	};

	Liferay.once('destroyPortlet', onDestroyPortlet);

	var onPortletReady = function() {
		document.body.addEventListener('click', handleDownloadClick);
	};

	Liferay.once('portletReady', onPortletReady);
</aui:script>