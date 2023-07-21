<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/text-localizer" prefix="liferay-text-localizer" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPCreationMenu" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note" %><%@
page import="com.liferay.osb.koroneiki.phytohormone.model.Entitlement" %><%@
page import="com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition" %><%@
page import="com.liferay.osb.koroneiki.root.constants.RootWebKeys" %><%@
page import="com.liferay.osb.koroneiki.root.exception.ExternalLinkDomainException" %><%@
page import="com.liferay.osb.koroneiki.root.exception.ExternalLinkEntityIdException" %><%@
page import="com.liferay.osb.koroneiki.root.exception.ExternalLinkEntityNameException" %><%@
page import="com.liferay.osb.koroneiki.root.model.ExternalLink" %><%@
page import="com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys" %><%@
page import="com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.AccountCodeException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.AccountNameException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.AccountParentException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.ContactEmailAddressException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.ContactRoleNameException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.ContactRoleTypeException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.NoSuchContactException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.NoSuchContactRoleException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamRoleException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.RequiredAccountException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.RequiredContactRoleException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.TeamNameException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.TeamRoleNameException" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.TeamRoleTypeException" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.Account" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.AccountField" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.AccountNote" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.Contact" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.ContactRole" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.Team" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.TeamRole" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.AccountFieldLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.AccountLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.ContactLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.TeamLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.AccountsDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.ContactRolesDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.ContactsDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.TeamRolesDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.TeamsDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.ViewAccountsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.ViewContactRolesManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.ViewContactsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.ViewTeamRolesManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.taproot.web.internal.display.context.ViewTeamsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.trunk.constants.TrunkPortletKeys" %><%@
page import="com.liferay.osb.koroneiki.trunk.model.ProductEntry" %><%@
page import="com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalServiceUtil" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.exception.AddressCityException" %><%@
page import="com.liferay.portal.kernel.exception.AddressStreetException" %><%@
page import="com.liferay.portal.kernel.exception.AddressZipException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchCountryException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchListTypeException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchRegionException" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Address" %><%@
page import="com.liferay.portal.kernel.model.ListType" %><%@
page import="com.liferay.portal.kernel.model.ListTypeConstants" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.service.AddressLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Format mediumDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.MEDIUM, locale, timeZone);
%>