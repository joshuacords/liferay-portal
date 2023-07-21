<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceApplicationAdminDisplayContext commerceApplicationAdminDisplayContext = (CommerceApplicationAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceApplicationBrand commerceApplicationBrand = commerceApplicationAdminDisplayContext.getCommerceApplicationBrand();
%>

<portlet:actionURL name="editCommerceApplicationBrand" var="editCommerceApplicationBrandActionURL" />

<div class="container-fluid-1280 entry-body">
	<aui:form action="<%= editCommerceApplicationBrandActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceApplicationBrand == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceApplicationBrandId" type="hidden" value="<%= commerceApplicationAdminDisplayContext.getCommerceApplicationBrandId() %>" />

		<aui:model-context bean="<%= commerceApplicationBrand %>" model="<%= CommerceApplicationBrand.class %>" />

		<div class="lfr-form-content">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<div class="row">
						<div class="col-md-6">
							<aui:input autoFocus="<%= true %>" name="name" />
						</div>

						<div class="col-md-5">
							<div align="middle">
								<c:if test="<%= commerceApplicationBrand != null %>">

									<%
									long logoId = commerceApplicationBrand.getLogoId();
									%>

									<liferay-ui:logo-selector
										currentLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=" + logoId + "&t=" + WebServerServletTokenUtil.getToken(logoId) %>'
										defaultLogo="<%= logoId == 0 %>"
										defaultLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=0" %>'
										logoDisplaySelector=".organization-logo"
										tempImageFileName="<%= String.valueOf(themeDisplay.getScopeGroupId()) %>"
									/>
								</c:if>
							</div>
						</div>
					</div>
				</aui:fieldset>

				<aui:fieldset>
					<aui:button-row>
						<aui:button cssClass="btn-lg" type="submit" value="save" />

						<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
					</aui:button-row>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</aui:form>
</div>