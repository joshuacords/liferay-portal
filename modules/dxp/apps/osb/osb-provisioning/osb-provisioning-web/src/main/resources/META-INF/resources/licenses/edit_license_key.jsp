<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");

EditLicenseKeyDisplayContext editLicenseKeyDisplayContext = ProvisioningWebComponentProvider.getEditLicenseKeyDisplayContext(renderRequest, renderResponse, request);

LicenseKey licenseKey = editLicenseKeyDisplayContext.getLicenseKey();

LicenseKeyDisplay licenseKeyDisplay = editLicenseKeyDisplayContext.getLicenseKeyDisplay();

String licenseProductPurchaseKey = StringPool.BLANK;

if (Validator.isNotNull(licenseKey.getProductPurchaseKey())) {
	licenseProductPurchaseKey = licenseKey.getProductPurchaseKey();
}

boolean hasManageLicenseKeysPermission = editLicenseKeyDisplayContext.hasManageLicenseKeysPermission();
%>

<div class="add-items edit-license">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= licenseKey.getOwner() %>"
	/>

	<aui:form action="<%= editLicenseKeyDisplayContext.getEditLicenseKeyURL() %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="editLicenseFm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="licenseKeyId" type="hidden" value="<%= licenseKeyDisplay.getLicenseKeyId() %>" />
		<aui:input name="clusterLicenseKeyId" type="hidden" value="0" />
		<aui:input name="productPurchaseKey" type="hidden" value="<%= licenseProductPurchaseKey %>" />
		<aui:input name="complimentary" type="hidden" value="<%= licenseKey.isComplimentary() %>" />
		<aui:input name="active" type="hidden" value="<%= licenseKey.isActive() %>" />
		<aui:input name="startDate" type="hidden" />
		<aui:input name="expirationDate" type="hidden" />

		<div class="add-items-sheet edit-license-sheet sheet">
			<aui:row>
				<aui:col md="4">
					<dl>
						<div>
							<dt>
								<liferay-ui:message key="product" />
							</dt>
							<dd>
								<a href="<%= editLicenseKeyDisplayContext.getAccountProductPurchasesURL() %>"><%= HtmlUtil.escape(licenseKeyDisplay.getProductName()) %></a>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="version" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getProductVersion()) %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="type" />
							</dt>
							<dd>
								<%= licenseKeyDisplay.getType() %>
							</dd>
						</div>
					</dl>

					<dl class="server-id-fields">
						<c:if test="<%= editLicenseKeyDisplayContext.showServerId() %>">
							<div class="server-id">
								<dt>
									<liferay-ui:message key="server-id" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getServerId()) %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showHostName() %>">
							<div class="host-name">
								<dt>
									<liferay-ui:message key="host-name" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getHostName()) %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showIpAddresses() %>">
							<div>
								<dt>
									<liferay-ui:message key="ip-addresses" />
								</dt>
								<dd>
									<%= licenseKeyDisplay.getIpAddresses() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showMacAddresses() %>">
							<div>
								<dt>
									<liferay-ui:message key="mac-addresses" />
								</dt>
								<dd>
									<c:choose>
										<c:when test="<%= licenseKey.getLicenseVersion() >= 3 %>">
											<%= licenseKeyDisplay.getMacAddresses() %>
										</c:when>
										<c:otherwise>
											<%= HtmlUtil.escape(editLicenseKeyDisplayContext.getClusterLicenseKeysDisplay()) %>
										</c:otherwise>
									</c:choose>
								</dd>
							</div>
						</c:if>
					</dl>
				</aui:col>

				<aui:col md="4">
					<dl>
						<c:if test="<%= !editLicenseKeyDisplayContext.showClusterLicenseKey() %>">
							<div class="owner">
								<dt>
									<liferay-ui:message key="owner" />
								</dt>
								<dd>
									<a href="<%= editLicenseKeyDisplayContext.getAccountURL() %>"><%= HtmlUtil.escape(licenseKeyDisplay.getOwner()) %></a>
								</dd>
							</div>

							<div class="description">
								<dt>
									<liferay-ui:message key="description" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getDescription()) %>
								</dd>
							</div>
						</c:if>
					</dl>

					<dl>
						<c:if test="<%= editLicenseKeyDisplayContext.showKey() %>">
							<div>
								<dt>
									<liferay-ui:message key="key" />
								</dt>
								<dd>
									<%= licenseKey.getKey() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showMaxClusterNodes() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-cluster-nodes" />
								</dt>
								<dd>
									<%= licenseKey.getMaxClusterNodes() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showMaximumConnections() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-connections" />
								</dt>
								<dd>
									<%= licenseKey.getMaxHttpSessions() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showMaximumConcurrentUsers() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-concurrent-users" />
								</dt>
								<dd>
									<%= licenseKeyDisplay.getMaxConcurrentUsersLabel() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showMaximumUsers() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-users" />
								</dt>
								<dd>
									<%= licenseKeyDisplay.getMaxUsersLabel() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showMaximumServers() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-servers" />
								</dt>
								<dd>
									<%= licenseKey.getMaxServers() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showComplimentary() %>">
							<div>
								<dt>
									<liferay-ui:message key="complimentary" />
								</dt>
								<dd>
									<%= licenseKeyDisplay.isComplimentaryLabel() %>
								</dd>
							</div>
						</c:if>
					</dl>
				</aui:col>

				<aui:col md="4">
					<dl>
						<c:if test="<%= !editLicenseKeyDisplayContext.showClusterLicenseKey() %>">
							<div>
								<dt>
									<liferay-ui:message key="status" />
								</dt>
								<dd>
									<span class="label <%= licenseKeyDisplay.getStatusStyle() %>"><%= licenseKeyDisplay.getStatus() %></span>
								</dd>
							</div>
						</c:if>

						<div>
							<dt>
								<liferay-ui:message key="start-date" />
							</dt>
							<dd>
								<%= licenseKeyDisplay.getStartDate() %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="expiration-date" />
							</dt>
							<dd>
								<%= licenseKeyDisplay.getExpirationDate() %>
							</dd>
						</div>
					</dl>

					<dl>
						<div>
							<dt>
								<liferay-ui:message key="created-by" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getUserName()) %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="create-date" />
							</dt>
							<dd>
								<%= licenseKeyDisplay.getCreateDate() %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="last-modified" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(editLicenseKeyDisplayContext.getLastModifiedUserNameDate()) %>
							</dd>
						</div>
					</dl>
				</aui:col>

				<c:if test="<%= editLicenseKeyDisplayContext.showClusterLicenseKey() %>">
					<aui:col cssClass="cluster-licenses" md="12">
						<aui:row>

							<%
							List<LicenseKey> clusterLicenseKeys = editLicenseKeyDisplayContext.getClusterLicenseKeys();

							for (int i = 0; i < clusterLicenseKeys.size(); i++) {
								LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);

								LicenseKeyDisplay clusterLicenseKeyDisplay = new LicenseKeyDisplay(renderRequest, renderResponse, clusterLicenseKey);
							%>

								<aui:col cssClass="license" md="12">
									<aui:row>
										<aui:col md="4">
											<dl class="server-id-fields">
												<div class="host-name">
													<dt>
														<liferay-ui:message key="host-name" />
													</dt>
													<dd>
														<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getHostName()) %>
													</dd>
												</div>

												<div>
													<dt>
														<liferay-ui:message key="ip-addresses" />
													</dt>
													<dd>
														<%= clusterLicenseKeyDisplay.getIpAddresses() %>
													</dd>
												</div>

												<div>
													<dt>
														<liferay-ui:message key="mac-addresses" />
													</dt>
													<dd>
														<%= clusterLicenseKeyDisplay.getMacAddresses() %>
													</dd>
												</div>
											</dl>
										</aui:col>

										<aui:col md="4">
											<dl>
												<div class="owner">
													<dt>
														<liferay-ui:message key="owner" />
													</dt>
													<dd>
														<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getOwner()) %>
													</dd>
												</div>

												<div class="description">
													<dt>
														<liferay-ui:message key="description" />
													</dt>
													<dd>
														<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getDescription()) %>
													</dd>
												</div>
											</dl>
										</aui:col>

										<aui:col md="4">
											<dl>
												<div>
													<dt>
														<liferay-ui:message key="status" />
													</dt>
													<dd>
														<span class="label <%= clusterLicenseKeyDisplay.getStatusStyle() %>">
															<%= clusterLicenseKeyDisplay.getStatus() %>
														</span>
													</dd>
												</div>
											</dl>
										</aui:col>

										<aui:col cssClass="edit-license-actions" md="12">
											<c:if test="<%= hasManageLicenseKeysPermission %>">
												<button class="btn btn-secondary btn-sm" onclick="<portlet:namespace />updateLicenseKeyProperties('<%= clusterLicenseKeyDisplay.getUpdateActiveConfirmMessage() %>', 'active', <%= !clusterLicenseKey.isActive() %>,'<%= clusterLicenseKey.getLicenseKeyId() %>');" type="button">
													<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getUpdateActiveLabel()) %>
												</button>
											</c:if>

											<portlet:resourceURL id="/licenses/download_license_key" var="downloadClusterLicenseKeyURL">
												<portlet:param name="licenseKeyId" value="<%= String.valueOf(clusterLicenseKey.getLicenseKeyId()) %>" />
											</portlet:resourceURL>

											<a class="btn btn-monospaced btn-secondary" href="<%= downloadClusterLicenseKeyURL %>" type="button">
												<clay:icon
													symbol="download"
												/>
											</a>
										</aui:col>
									</aui:row>
								</aui:col>

							<%
							}
							%>

						</aui:row>
					</aui:col>
				</c:if>

				<aui:col cssClass="edit-license-actions" md="12">
					<div>
						<c:if test="<%= hasManageLicenseKeysPermission %>">
							<span id="replaceLicense">
								<react:component
									data="<%= editLicenseKeyDisplayContext.getReplaceLicenseKeyData() %>"
									module="js/apps/ReplaceLicenseApp"
								/>
							</span>
						</c:if>

						<c:if test="<%= hasManageLicenseKeysPermission && editLicenseKeyDisplayContext.showExtend() %>">
							<a class="btn btn-secondary btn-sm" href="<%= editLicenseKeyDisplayContext.getExtendLicenseKeysURL() %>" type="button">
								<liferay-ui:message key="extend" />
							</a>
						</c:if>

						<c:if test="<%= hasManageLicenseKeysPermission && editLicenseKeyDisplayContext.showComplimentary() %>">
							<button class="btn btn-secondary btn-sm" onclick="<portlet:namespace />updateLicenseKeyProperties('<%= licenseKeyDisplay.getUpdateComplimentaryConfirmMessage() %>', 'complimentary', <%= !licenseKey.isComplimentary() %>);" type="button">
								<%= licenseKeyDisplay.getUpdateComplimentaryLabel() %>
							</button>
						</c:if>

						<c:if test="<%= hasManageLicenseKeysPermission && !editLicenseKeyDisplayContext.showClusterLicenseKey() %>">
							<button class="btn btn-secondary btn-sm" onclick="<portlet:namespace />updateLicenseKeyProperties('<%= licenseKeyDisplay.getUpdateActiveConfirmMessage() %>', 'active', <%= !licenseKey.isActive() %>);" type="button">
								<%= licenseKeyDisplay.getUpdateActiveLabel() %>
							</button>
						</c:if>
					</div>

					<div>
						<c:if test="<%= hasManageLicenseKeysPermission && !editLicenseKeyDisplayContext.showClusterLicenseKey() %>">
							<button class="btn btn-secondary btn-sm" onclick="<portlet:namespace />moveLicenseKey('<%= editLicenseKeyDisplayContext.getMoveLicenseKeyURL() %>');" type="button">
								<liferay-ui:message key="move" />

								<clay:icon
									symbol="move-folder"
								/>
							</button>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.showDownload() %>">
							<a class="btn btn-primary btn-sm" href="<%= editLicenseKeyDisplayContext.getDownloadLicenseKeyURL() %>" type="button">
								<liferay-ui:message key="download" />

								<clay:icon
									symbol="download"
								/>
							</a>
						</c:if>
					</div>
				</aui:col>
			</aui:row>
		</div>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />moveLicenseKey(url) {
		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					modal: true
				},
				eventName: 'moveLicenseKey',
				title: '<liferay-ui:message key="move-license" />',
				uri: url
			},
			function(event) {
				var productPurchaseKeyField = document.getElementById(
					'<portlet:namespace />productPurchaseKey'
				);

				if (productPurchaseKeyField) {
					productPurchaseKeyField.value = event.productpurchasekey;
				}

				var form = document.getElementById(
					'<portlet:namespace />editLicenseFm'
				);

				if (form) {
					form.submit();
				}
			}
		);
	}

	function <portlet:namespace />updateLicenseKeyProperties(
		confirmMessage,
		fieldName,
		value,
		clusterLicenseKeyId
	) {
		if (!confirm(confirmMessage)) {
			return;
		}

		var clusterLicenseKeyIdField = document.getElementById(
			'<portlet:namespace />clusterLicenseKeyId'
		);

		if (clusterLicenseKeyId && clusterLicenseKeyIdField) {
			clusterLicenseKeyIdField.value = clusterLicenseKeyId;
		}

		var field = document.getElementById('<portlet:namespace />' + fieldName);

		if (field) {
			field.value = value;
		}

		var form = document.getElementById('<portlet:namespace />editLicenseFm');

		if (form) {
			form.submit();
		}
	}
</aui:script>