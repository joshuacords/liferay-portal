<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.search.tuning.rankings.web.internal.display.context.RankingResultContentDisplayBuilder" %><%@
page import="com.liferay.portal.search.tuning.rankings.web.internal.display.context.RankingResultContentDisplayContext" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
RankingResultContentDisplayBuilder rankingResultContentDisplayBuilder = new RankingResultContentDisplayBuilder();

rankingResultContentDisplayBuilder.setAssetEntryId(ParamUtil.getLong(request, "assetEntryId"));
rankingResultContentDisplayBuilder.setEntryClassName(ParamUtil.getString(request, "entryClassName"));
rankingResultContentDisplayBuilder.setEntryClassPK(ParamUtil.getLong(request, "entryClassPK"));
rankingResultContentDisplayBuilder.setLocale(locale);
rankingResultContentDisplayBuilder.setPermissionChecker(permissionChecker);
rankingResultContentDisplayBuilder.setPortal(PortalUtil.getPortal());
rankingResultContentDisplayBuilder.setRenderRequest(renderRequest);
rankingResultContentDisplayBuilder.setRenderResponse(renderResponse);

RankingResultContentDisplayContext rankingResultContentDisplayContext = rankingResultContentDisplayBuilder.build();
%>

<div class="container container-no-gutters-sm-down container-view">
	<c:choose>
		<c:when test="<%= rankingResultContentDisplayContext.isVisible() %>">
			<div class="result-rankings-view-content-container sheet sheet-lg">
				<div class="autofit-row">
					<div class="autofit-col autofit-col-expand">
						<div class="sheet-title">
							<%= rankingResultContentDisplayContext.getHeaderTitle() %>
						</div>
					</div>

					<div class="autofit-col visible-interaction">
						<c:if test="<%= rankingResultContentDisplayContext.hasEditPermission() %>">
							<div class="asset-actions lfr-meta-actions">
								<liferay-ui:icon
									cssClass="visible-interaction"
									icon="pencil"
									label="<%= false %>"
									markupView="lexicon"
									message='<%= LanguageUtil.format(request, "edit-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(rankingResultContentDisplayContext.getIconEditTarget())}, false) %>'
									method="get"
									url="<%= rankingResultContentDisplayContext.getIconURLString() %>"
								/>
							</div>
						</c:if>
					</div>
				</div>

				<liferay-asset:asset-display
					assetEntry="<%= rankingResultContentDisplayContext.getAssetEntry() %>"
					assetRenderer="<%= rankingResultContentDisplayContext.getAssetRenderer() %>"
					assetRendererFactory="<%= rankingResultContentDisplayContext.getAssetRendererFactory() %>"
				/>
			</div>
		</c:when>
		<c:otherwise>
			<div class="alert alert-danger">
				<liferay-ui:message key="you-do-not-have-permission-to-access-the-requested-resource" />
			</div>
		</c:otherwise>
	</c:choose>
</div>