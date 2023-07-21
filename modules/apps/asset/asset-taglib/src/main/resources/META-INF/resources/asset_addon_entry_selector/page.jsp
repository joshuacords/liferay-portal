<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset_addon_entry_selector/init.jsp" %>

<div class="lfr-asset-addon-entry-selector" id="<%= namespace + id %>assetAddonEntrySelector">
	<aui:input name="<%= hiddenInput %>" type="hidden" value='<%= ListUtil.toString(selectedAssetAddonEntries, "key") %>' />

	<ul class="list-inline list-unstyled row selected-entries">

		<%
		for (AssetAddonEntry assetAddonEntry : selectedAssetAddonEntries) {
		%>

			<li class="col-md-4 list-entry" data-key="<%= assetAddonEntry.getKey() %>" data-label="<%= assetAddonEntry.getLabel(locale) %>">
				<div class="card card-horizontal card-type-directory">
					<div class="card-body">
						<div class="card-row">
							<div class="autofit-col autofit-col-expand autofit-col-gutters">
								<section class="autofit-section">
									<h3 class="card-title" title="{label}">
										<span class="text-truncate-inline">
											<span class="text-truncate"><%= assetAddonEntry.getLabel(locale) %></span>
										</span>
									</h3>
								</section>
							</div>

							<div class="autofit-col">
								<a class="remove-button" href="javascript:;">
									<aui:icon image="times-circle" markupView="lexicon" />
								</a>
							</div>
						</div>
					</div>
				</div>
			</li>

		<%
		}
		%>

	</ul>

	<div class="button-holder">
		<aui:button cssClass="select-button" name='<%= id + "selectButton" %>' value="select" />
	</div>
</div>

<aui:script use="liferay-asset-addon-entry-selector">
	var assetAddonEntries = [];

	<%
	for (AssetAddonEntry assetAddonEntry : assetAddonEntries) {
	%>

		assetAddonEntries.push({
			icon: '<%= assetAddonEntry.getIcon() %>',
			key: '<%= assetAddonEntry.getKey() %>',
			label: '<%= assetAddonEntry.getLabel(locale) %>'
		});

	<%
	}
	%>

	var selectedAssetAddonEntries = [];

	<%
	for (AssetAddonEntry assetAddonEntry : selectedAssetAddonEntries) {
	%>

		selectedAssetAddonEntries.push('<%= assetAddonEntry.getKey() %>');

	<%
	}
	%>

	new Liferay.AssetAddonEntrySelector({
		assetAddonEntries: assetAddonEntries,
		dialogTitle: '<liferay-ui:message key="<%= title %>" />',
		rootNode: '#<%= namespace + id %>assetAddonEntrySelector',
		selectedAssetAddonEntries: selectedAssetAddonEntries
	});
</aui:script>