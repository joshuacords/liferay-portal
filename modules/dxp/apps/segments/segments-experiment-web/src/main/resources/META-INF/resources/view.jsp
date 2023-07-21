<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="lfr-segments-experiment-sidebar" id="segmentsExperimentSidebar">
	<div class="sidebar-header">
		<h1 class="sr-only"><liferay-ui:message key="ab-test-panel" /></h1>

		<span><liferay-ui:message key="ab-test" /></span>

		<aui:icon aria-label='<%= LanguageUtil.get(request, "close") %>' cssClass="icon-monospaced sidenav-close" image="times" markupView="lexicon" url="javascript:;" />
	</div>

	<div class="sidebar-body">
		<c:if test="<%= GetterUtil.getBoolean(request.getAttribute(SegmentsExperimentWebKeys.SEGMENTS_EXPERIMENT_PANEL_STATE_OPEN)) %>">
			<liferay-util:include page="/segments_experiment_panel.jsp" servletContext="<%= application %>" />
		</c:if>
	</div>
</div>

<aui:script>
	var segmentsExperimentPanelToggle = document.getElementById(
		'<portlet:namespace />segmentsExperimentPanelToggleId'
	);

	var sidenavInstance = Liferay.SideNavigation.initialize(
		segmentsExperimentPanelToggle
	);

	sidenavInstance.on('open.lexicon.sidenav', function(event) {
		Liferay.Util.Session.set(
			'com.liferay.segments.experiment.web_panelState',
			'open'
		);
	});

	sidenavInstance.on('closed.lexicon.sidenav', function(event) {
		Liferay.Util.Session.set(
			'com.liferay.segments.experiment.web_panelState',
			'closed'
		);
	});

	Liferay.once('screenLoad', function() {
		Liferay.SideNavigation.destroy(segmentsExperimentPanelToggle);
	});
</aui:script>