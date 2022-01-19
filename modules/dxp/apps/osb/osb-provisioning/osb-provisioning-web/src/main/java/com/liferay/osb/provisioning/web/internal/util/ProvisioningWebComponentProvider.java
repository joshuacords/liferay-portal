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

package com.liferay.osb.provisioning.web.internal.util;

import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.CountryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.web.internal.configuration.ProvisioningWebConfiguration;
import com.liferay.osb.provisioning.web.internal.display.context.AccountSearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.AddLicenseKeyDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.AssignProductBundleProductsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.AssignProductPurchaseProductsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.AssignTeamContactsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ContactSearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.EditLicenseKeyDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.EditProductPurchasesDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ExtendLicenseKeysDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.LicenseKeySearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.MoveLicenseKeyDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ProductSearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.TeamSearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountContactsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountLicenseKeysDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountLiferayWorkersDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountRelatedAccountsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountTeamsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountsManagementToolbarDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewContactDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewLicenseKeysManagementToolbarDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewSubscriptionDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewTeamDisplayContext;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.web.internal.configuration.ProvisioningWebConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = {}
)
public class ProvisioningWebComponentProvider {

	public static AccountSearchDisplayContext getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getAccountSearchDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static AddLicenseKeyDisplayContext getAddLicenseKeyDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getAddLicenseKeyDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static AssignProductBundleProductsDisplayContext
			getAssignProductBundleProductsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider.
			_getAssignProductBundleProductsDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static AssignProductPurchaseProductsDisplayContext
			getAssignProductPurchaseProductsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider.
			_getAssignProductPurchaseProductsDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static AssignTeamContactsDisplayContext
			getAssignTeamContactsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			AssignTeamContactsDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ContactSearchDisplayContext getContactSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getContactSearchDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static EditLicenseKeyDisplayContext getEditLicenseKeyDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider.
			_getEditLicenseKeyDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static EditProductPurchasesDisplayContext
			getEditProductPurchasesDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			EditProductPurchasesDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ExtendLicenseKeysDisplayContext
			getExtendLicenseKeysDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider.
			_getExtendLicenseKeysDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static LicenseKeySearchDisplayContext
		getLicenseKeySearchDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getLicenseKeySearchDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static MoveLicenseKeyDisplayContext getMoveLicenseKeyDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider.
			_getMoveLicenseKeyDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static ProductSearchDisplayContext getProductSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getProductSearchDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static ProvisioningWebComponentProvider
		getProvisioningWebComponentProvider() {

		return _provisioningWebComponentProvider;
	}

	public static TeamSearchDisplayContext getTeamSearchDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getTeamSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest);
	}

	public static ViewAccountContactsDisplayContext
			getViewAccountContactsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountContactsDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ViewAccountDisplayContext getViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	public static ViewAccountLicenseKeysDisplayContext
			getViewAccountLicenseKeysDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountLicenseKeysDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ViewAccountLiferayWorkersDisplayContext
			getViewAccountLiferayWorkersDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountLiferayWorkersDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ViewAccountRelatedAccountsDisplayContext
			getViewAccountRelatedAccountsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountRelatedAccountsDisplayContext.class, renderRequest,
			renderResponse, httpServletRequest);
	}

	public static ViewAccountsManagementToolbarDisplayContext
		getViewAccountsManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest httpServletRequest,
			SearchContainer searchContainer) {

		return _provisioningWebComponentProvider.
			_getViewAccountsManagementToolbarDisplayContext(
				liferayPortletRequest, liferayPortletResponse,
				httpServletRequest, searchContainer);
	}

	public static ViewAccountTeamsDisplayContext
			getViewAccountTeamsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewAccountTeamsDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	public static ViewContactDisplayContext getViewContactDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewContactDisplayContext(
			ViewContactDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	public static ViewLicenseKeysManagementToolbarDisplayContext
		getViewLicenseKeysManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest httpServletRequest,
			SearchContainer searchContainer) {

		return _provisioningWebComponentProvider.
			_getViewLicenseKeysManagementToolbarDisplayContext(
				liferayPortletRequest, liferayPortletResponse,
				httpServletRequest, searchContainer);
	}

	public static ViewSubscriptionDisplayContext
			getViewSubscriptionDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewSubscriptionDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	public static ViewTeamDisplayContext getViewTeamDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			ViewTeamDisplayContext.class, renderRequest, renderResponse,
			httpServletRequest);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_provisioningWebConfiguration = ConfigurableUtil.createConfigurable(
			ProvisioningWebConfiguration.class, properties);

		_provisioningWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_provisioningWebComponentProvider = null;
	}

	private AccountSearchDisplayContext _getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new AccountSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountWebService, _contactIdentityProvider, _countryWebService,
			_productWebService, _teamRoleWebService, _userLocalService);
	}

	private AddLicenseKeyDisplayContext _getAddLicenseKeyDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new AddLicenseKeyDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_licenseEntryLocalService, _licenseKeyPermission,
			_productConsumptionWebService, _productWebService,
			_productPurchaseViewWebService, _provisioningWebConfiguration);
	}

	private AssignProductBundleProductsDisplayContext
		_getAssignProductBundleProductsDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest) {

		return new AssignProductBundleProductsDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_productWebService);
	}

	private AssignProductPurchaseProductsDisplayContext
		_getAssignProductPurchaseProductsDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest) {

		return new AssignProductPurchaseProductsDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_productBundleLocalService, _productWebService);
	}

	private ContactSearchDisplayContext _getContactSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new ContactSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_accountWebService, _contactWebService);
	}

	private EditLicenseKeyDisplayContext _getEditLicenseKeyDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return new EditLicenseKeyDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_licenseKeyLocalService, _licenseKeyPermission,
			_productPurchaseViewWebService);
	}

	private ExtendLicenseKeysDisplayContext _getExtendLicenseKeysDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return new ExtendLicenseKeysDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_licenseKeyPermission, _productPurchaseViewWebService);
	}

	private LicenseKeySearchDisplayContext _getLicenseKeySearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new LicenseKeySearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_contactIdentityProvider, _licenseEntryLocalService,
			_licenseKeyLocalService, _licenseKeyPermission, _productWebService,
			_userLocalService);
	}

	private MoveLicenseKeyDisplayContext _getMoveLicenseKeyDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new MoveLicenseKeyDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_productConsumptionWebService, _productPurchaseViewWebService);
	}

	private ProductSearchDisplayContext _getProductSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new ProductSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_productWebService);
	}

	private TeamSearchDisplayContext _getTeamSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new TeamSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest,
			_accountWebService, _teamRoleWebService, _teamWebService);
	}

	private <T extends ViewAccountDisplayContext> T
			_getViewAccountDisplayContext(
				Class<T> clazz, RenderRequest renderRequest,
				RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		T viewAccountDisplayContext = (T)httpServletRequest.getAttribute(
			clazz.getName());

		if (viewAccountDisplayContext != null) {
			return viewAccountDisplayContext;
		}

		viewAccountDisplayContext = clazz.newInstance();

		viewAccountDisplayContext.init(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountEntryWebService, _accountWebService, _auditEntryWebService,
			_contactRoleWebService, _contactWebService, _countryWebService,
			_externalLinkWebService, _licenseKeyLocalService,
			_licenseKeyPermission, _noteWebService,
			_productConsumptionWebService, _productPurchaseViewWebService,
			_productWebService, _teamRoleWebService, _teamWebService,
			_userLocalService);

		httpServletRequest.setAttribute(
			clazz.getName(), viewAccountDisplayContext);

		return viewAccountDisplayContext;
	}

	private ViewAccountsManagementToolbarDisplayContext
		_getViewAccountsManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest httpServletRequest,
			SearchContainer searchContainer) {

		return new ViewAccountsManagementToolbarDisplayContext(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer, _accountWebService, _teamWebService);
	}

	private <T extends ViewContactDisplayContext> T
			_getViewContactDisplayContext(
				Class<T> clazz, RenderRequest renderRequest,
				RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		T viewContactDisplayContext = (T)httpServletRequest.getAttribute(
			clazz.getName());

		if (viewContactDisplayContext != null) {
			return viewContactDisplayContext;
		}

		viewContactDisplayContext = clazz.newInstance();

		viewContactDisplayContext.init(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountWebService, _contactRoleWebService, _contactWebService);

		httpServletRequest.setAttribute(
			clazz.getName(), viewContactDisplayContext);

		return viewContactDisplayContext;
	}

	private ViewLicenseKeysManagementToolbarDisplayContext
		_getViewLicenseKeysManagementToolbarDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest httpServletRequest,
			SearchContainer searchContainer) {

		return new ViewLicenseKeysManagementToolbarDisplayContext(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer, _licenseEntryLocalService, _productWebService);
	}

	private static ProvisioningWebComponentProvider
		_provisioningWebComponentProvider;

	@Reference
	private AccountEntryWebService _accountEntryWebService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private AuditEntryWebService _auditEntryWebService;

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private CountryWebService _countryWebService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private LicenseKeyPermission _licenseKeyPermission;

	@Reference
	private NoteWebService _noteWebService;

	@Reference
	private ProductBundleLocalService _productBundleLocalService;

	@Reference
	private ProductConsumptionWebService _productConsumptionWebService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductWebService _productWebService;

	private volatile ProvisioningWebConfiguration _provisioningWebConfiguration;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private UserLocalService _userLocalService;

}