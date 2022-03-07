<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem" %><%@
page import="com.liferay.osb.provisioning.constants.ProductTypeConstants" %><%@
page import="com.liferay.osb.provisioning.constants.ProvisioningActionKeys" %><%@
page import="com.liferay.osb.provisioning.constants.ProvisioningPortletKeys" %><%@
page import="com.liferay.osb.provisioning.constants.ProvisioningWebKeys" %><%@
page import="com.liferay.osb.provisioning.exception.AccountCodeException" %><%@
page import="com.liferay.osb.provisioning.exception.ContactEmailAddressException" %><%@
page import="com.liferay.osb.provisioning.exception.ContactNameException" %><%@
page import="com.liferay.osb.provisioning.exception.ContactRequiredException" %><%@
page import="com.liferay.osb.provisioning.exception.DuplicateContactRoleException" %><%@
page import="com.liferay.osb.provisioning.exception.DuplicateDossieraKeyException" %><%@
page import="com.liferay.osb.provisioning.exception.MultipleDossieraKeysException" %><%@
page import="com.liferay.osb.provisioning.exception.ProductBundleNameException" %><%@
page import="com.liferay.osb.provisioning.exception.ProductPurchaseQuantityException" %><%@
page import="com.liferay.osb.provisioning.exception.RequiredProductException" %><%@
page import="com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants" %><%@
page import="com.liferay.osb.provisioning.license.exception.DuplicateCommonLicenseKeyException" %><%@
page import="com.liferay.osb.provisioning.license.helper.constants.LicenseType" %><%@
page import="com.liferay.osb.provisioning.license.helper.constants.ProductId" %><%@
page import="com.liferay.osb.provisioning.license.model.CommonLicenseKey" %><%@
page import="com.liferay.osb.provisioning.license.model.LicenseKey" %><%@
page import="com.liferay.osb.provisioning.license.service.CommonLicenseKeyLocalServiceUtil" %><%@
page import="com.liferay.osb.provisioning.model.ProductBundle" %><%@
page import="com.liferay.osb.provisioning.rest.dto.v1_0.ProductGroup" %><%@
page import="com.liferay.osb.provisioning.service.ProductBundleLocalServiceUtil" %><%@
page import="com.liferay.osb.provisioning.web.internal.dao.search.ProductPurchaseResultRowSplitter" %><%@
page import="com.liferay.osb.provisioning.web.internal.dao.search.ProductPurchaseViewResultRowSplitter" %><%@
page import="com.liferay.osb.provisioning.web.internal.dao.search.ProductResultRowSplitter" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AccountSearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AddLicenseKeyDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AssignProductBundleProductsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AssignProductPurchaseProductsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AssignTeamContactsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AuditEntryDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ContactSearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.DownloadLicenseKeysDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.EditLicenseKeyDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.EditProductPurchasesDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ExtendLicenseKeysDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.LicenseKeyDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.LicenseKeySearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.MoveLicenseKeyDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseViewDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductSearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.TeamSearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountContactsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountLicenseKeysDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountLiferayWorkersDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountRelatedAccountsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountTeamsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewContactDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewLicenseKeysManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewProductPurchasesManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewProductsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewSubscriptionDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewTeamDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.permission.ProductBundlePermissionChecker" %><%@
page import="com.liferay.osb.provisioning.web.internal.util.ProvisioningWebComponentProvider" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchContactException" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.json.JSONUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.vulcan.util.TransformUtil" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Format mediumDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.MEDIUM, locale, timeZone);
%>

<aui:script>
	window.ProvisioningConstants = {
		accountsPortletNamespace:
			'<%= PortalUtil.getPortletNamespace(ProvisioningPortletKeys.ACCOUNTS) %>',
		contactRole: {
			administrator: '<%= ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR %>'
		},
		licenseType: {
			cluster: '<%= LicenseType.CLUSTER %>',
			developer: '<%= LicenseType.DEVELOPER %>',
			developerCluster: '<%= LicenseType.DEVELOPER_CLUSTER %>',
			noServerIdTypes: [
				'<%= LicenseType.DEVELOPER %>',
				'<%= LicenseType.DEVELOPER_CLUSTER %>',
				'<%= LicenseType.ELASTIC %>',
				'<%= LicenseType.ENTERPRISE %>',
				'<%= LicenseType.OEM %>',
				'<%= LicenseType.VIRTUAL_CLUSTER %>'
			],
			production: '<%= LicenseType.PRODUCTION %>',
			restrictedExpirationDateTypes: [
				'<%= LicenseType.ENTERPRISE %>',
				'<%= LicenseType.LIMITED %>',
				'<%= LicenseType.OEM %>',
				'<%= LicenseType.VIRTUAL_CLUSTER %>'
			],
			virtualCluster: '<%= LicenseType.VIRTUAL_CLUSTER %>'
		},
		namespace: '${renderResponse.namespace}',
		noteFormat: {
			html: '<%= Note.Format.HTML %>',
			plaintext: '<%= Note.Format.PLAIN %>'
		},
		noteStatus: {
			approved: '<%= Note.Status.APPROVED %>',
			archived: '<%= Note.Status.ARCHIVED %>'
		},
		noteType: {
			general: '<%= Note.Type.GENERAL %>',
			sales: '<%= Note.Type.SALES %>'
		},
		productId: {
			commerce: '<%= ProductId.COMMERCE %>',
			portal: '<%= ProductId.PORTAL %>'
		},
		productPurchaseStatus: {
			approved: '<%= ProductPurchase.Status.APPROVED %>',
			cancelled: '<%= ProductPurchase.Status.CANCELLED %>'
		}
	};
</aui:script>