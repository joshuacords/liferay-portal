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
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPCreationMenu" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList" %><%@
page import="com.liferay.osb.koroneiki.root.constants.RootWebKeys" %><%@
page import="com.liferay.osb.koroneiki.root.exception.ExternalLinkDomainException" %><%@
page import="com.liferay.osb.koroneiki.root.exception.ExternalLinkEntityIdException" %><%@
page import="com.liferay.osb.koroneiki.root.exception.ExternalLinkEntityNameException" %><%@
page import="com.liferay.osb.koroneiki.root.model.ExternalLink" %><%@
page import="com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys" %><%@
page import="com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants" %><%@
page import="com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountException" %><%@
page import="com.liferay.osb.koroneiki.taproot.model.Account" %><%@
page import="com.liferay.osb.koroneiki.taproot.service.AccountLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.trunk.constants.TrunkWebKeys" %><%@
page import="com.liferay.osb.koroneiki.trunk.exception.NoSuchProductEntryException" %><%@
page import="com.liferay.osb.koroneiki.trunk.exception.ProductEntryNameException" %><%@
page import="com.liferay.osb.koroneiki.trunk.exception.ProductPurchaseEndDateException" %><%@
page import="com.liferay.osb.koroneiki.trunk.exception.ProductPurchaseQuantityException" %><%@
page import="com.liferay.osb.koroneiki.trunk.exception.RequiredProductEntryException" %><%@
page import="com.liferay.osb.koroneiki.trunk.exception.RequiredProductPurchaseException" %><%@
page import="com.liferay.osb.koroneiki.trunk.model.ProductConsumption" %><%@
page import="com.liferay.osb.koroneiki.trunk.model.ProductEntry" %><%@
page import="com.liferay.osb.koroneiki.trunk.model.ProductField" %><%@
page import="com.liferay.osb.koroneiki.trunk.model.ProductPurchase" %><%@
page import="com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalServiceUtil" %><%@
page import="com.liferay.osb.koroneiki.trunk.web.internal.display.context.ProductConsumptionsDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.trunk.web.internal.display.context.ProductEntriesDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.trunk.web.internal.display.context.ProductPurchasesDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.trunk.web.internal.display.context.ViewProductConsumptionsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.trunk.web.internal.display.context.ViewProductEntriesManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.koroneiki.trunk.web.internal.display.context.ViewProductPurchasesManagementToolbarDisplayContext" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
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