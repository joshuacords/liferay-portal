<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="server-admin-tabs">
	<aui:fieldset>
		<liferay-ui:panel-container
			extended="<%= true %>"
			id="adminExternalServicesPanelContainer"
			persistState="<%= true %>"
		>
			<liferay-ui:panel
				collapsible="<%= true %>"
				extended="<%= true %>"
				id="adminImageMagickConversionPanel"
				markupView="lexicon"
				persistState="<%= true %>"
				title="enabling-imagemagick-provides-document-preview-functionality"
			>
				<aui:input label="enabled" name="imageMagickEnabled" type="checkbox" value="<%= ImageMagickUtil.isEnabled() %>" />

				<aui:input cssClass="lfr-input-text-container" label="path" name="imageMagickPath" type="text" value="<%= ImageMagickUtil.getGlobalSearchPath() %>" />

				<aui:fieldset label="resource-limits">

					<%
					Properties resourceLimitsProperties = ImageMagickUtil.getResourceLimitsProperties();

					for (String label : ImageMagickResourceLimitConstants.PROPERTY_NAMES) {
					%>

						<aui:input cssClass="lfr-input-text-container" label="<%= label %>" name="<%= PropsKeys.IMAGEMAGICK_RESOURCE_LIMIT + label %>" type="text" value="<%= resourceLimitsProperties.getProperty(label) %>" />

					<%
					}
					%>

				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel
				collapsible="<%= true %>"
				extended="<%= true %>"
				id="adminXugglerPanel"
				markupView="lexicon"
				persistState="<%= true %>"
				title="enabling-xuggler-provides-video-conversion-functionality"
			>
				<liferay-ui:error exception="<%= XugglerInstallException.class %>" targetNode="#controlMenuAlertsContainer">

					<%
					XugglerInstallException xie = (XugglerInstallException)errorException;
					%>

					<liferay-ui:message arguments="<%= HtmlUtil.escape(xie.getMessage()) %>" key="an-unexpected-error-occurred-while-installing-xuggler-x" translateArguments="<%= false %>" />
				</liferay-ui:error>

				<c:choose>
					<c:when test="<%= XugglerUtil.isNativeLibraryInstalled() %>">
						<div class="alert alert-info">
							<liferay-ui:message key="xuggler-installed" />
						</div>

						<aui:input label="enabled" name="xugglerEnabled" type="checkbox" value="<%= XugglerUtil.isEnabled() %>" />
					</c:when>
					<c:when test="<%= XugglerUtil.isNativeLibraryCopied() %>">
						<div class="alert alert-info">
							<liferay-ui:message key="xuggler-has-been-installed-you-need-to-reboot-your-server-to-apply-changes" />
						</div>
					</c:when>
					<c:otherwise>

						<%
						String xugglerHelp = LanguageUtil.format(request, "xuggler-help", "http://www.xuggle.com/xuggler/downloads", false);

						String[] xugglerOptions = PropsUtil.getArray(PropsKeys.XUGGLER_JAR_OPTIONS);

						String bitmode = OSDetector.getBitmode();

						String guess = StringPool.BLANK;

						if (Validator.isNotNull(bitmode) && (bitmode.equals("32") || bitmode.equals("64"))) {
							if (OSDetector.isApple()) {
								guess = bitmode + "-mac";
							}
							else if (OSDetector.isLinux()) {
								guess = bitmode + "-linux";
							}
							else if (OSDetector.isWindows()) {
								guess = bitmode + "-win";
							}

							if (Validator.isNotNull(guess)) {
								boolean found = false;

								for (String xugglerOption : xugglerOptions) {
									if (xugglerOption.equals(guess)) {
										found = true;

										break;
									}
								}

								if (!found) {
									guess = StringPool.BLANK;
								}
							}
						}
						%>

						<div class="alert alert-info">
							<liferay-ui:message key="<%= xugglerHelp %>" />
						</div>

						<aui:select label="jar-file" name="jarName">
							<c:if test="<%= Validator.isNull(guess) %>">
								<aui:option label="unknown" value="" />
							</c:if>

							<%
							for (String xugglerOption : xugglerOptions) {
								String jarFile = PropsUtil.get(PropsKeys.XUGGLER_JAR_FILE, new Filter(xugglerOption));
								String jarName = PropsUtil.get(PropsKeys.XUGGLER_JAR_NAME, new Filter(xugglerOption));
							%>

								<aui:option label='<%= jarName + " (" + jarFile + ")" %>' selected="<%= xugglerOption.equals(guess) %>" value="<%= jarFile %>" />

							<%
							}
							%>

						</aui:select>

						<aui:button-row>
							<aui:button cssClass="save-server-button" data-cmd="installXuggler" name="installXugglerButton" value="install" />
						</aui:button-row>
					</c:otherwise>
				</c:choose>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:fieldset>
</div>

<aui:button-row>
	<aui:button cssClass="save-server-button" data-cmd="updateExternalServices" value="save" />
</aui:button-row>