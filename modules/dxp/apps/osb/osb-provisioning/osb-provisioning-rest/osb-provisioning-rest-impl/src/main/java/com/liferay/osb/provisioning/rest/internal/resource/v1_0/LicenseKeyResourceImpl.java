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

package com.liferay.osb.provisioning.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.exception.LicenseKeyDateException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyProductPurchaseKeyException;
import com.liferay.osb.provisioning.license.exporter.LicenseKeyExporter;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKeyGenerateForm;
import com.liferay.osb.provisioning.rest.dto.v1_0.ProductGroup;
import com.liferay.osb.provisioning.rest.dto.v1_0.SubscriptionTerm;
import com.liferay.osb.provisioning.rest.dto.v1_0.Type;
import com.liferay.osb.provisioning.rest.dto.v1_0.Version;
import com.liferay.osb.provisioning.rest.dto.v1_0.util.LicenseKeyUtil;
import com.liferay.osb.provisioning.rest.internal.ProvisioningContactThreadLocal;
import com.liferay.osb.provisioning.rest.internal.odata.entity.v1_0.LicenseKeyEntityModel;
import com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/license-key.properties",
	scope = ServiceScope.PROTOTYPE, service = LicenseKeyResource.class
)
public class LicenseKeyResourceImpl
	extends BaseLicenseKeyResourceImpl implements EntityModelResource {

	@Override
	public Response getAccountAccountKeyLicenseKeyExport(
			String accountKey, Filter filter, Sort[] sorts)
		throws Exception {

		_checkAccountMembership(accountKey);

		Page<com.liferay.osb.provisioning.license.model.LicenseKey> page =
			SearchUtil.search(
				booleanQuery -> booleanQuery.addRequiredTerm(
					"accountKey", accountKey),
				filter,
				com.liferay.osb.provisioning.license.model.LicenseKey.class,
				StringPool.BLANK, null,
				queryConfig -> queryConfig.setSelectedFieldNames(
					Field.ENTRY_CLASS_PK),
				searchContext -> searchContext.setCompanyId(
					contextCompany.getCompanyId()),
				document -> _licenseKeyLocalService.getLicenseKey(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))),
				sorts);

		return Response.ok(
			_toCsv(page.getItems())
		).header(
			"content-disposition",
			"attachment; filename=\"activation-key-details.csv\""
		).type(
			ContentTypes.TEXT_CSV
		).build();
	}

	@Override
	public Page<LicenseKey> getAccountAccountKeyLicenseKeysPage(
			String accountKey, String search, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception {

		_checkAccountMembership(accountKey);

		return SearchUtil.search(
			booleanQuery -> booleanQuery.addRequiredTerm(
				"accountKey", accountKey),
			filter, com.liferay.osb.provisioning.license.model.LicenseKey.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> LicenseKeyUtil.toLicenseKey(
				_licenseKeyLocalService.getLicenseKey(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public LicenseKeyGenerateForm
			getAccountAccountKeyProductGroupProductGroupNameGenerateForm(
				String accountKey, String productGroupName)
		throws Exception {

		LicenseKeyGenerateForm licenseKeyGenerateForm =
			new LicenseKeyGenerateForm();

		Account account = _accountWebService.getAccount(accountKey);

		boolean allowPermanentLicenses = false;

		Map<String, String> properties = account.getProperties();

		if (properties != null) {
			allowPermanentLicenses = GetterUtil.getBoolean(
				properties.get("allowPermanentLicenses"), true);
		}

		licenseKeyGenerateForm.setAllowPermanentLicenses(
			allowPermanentLicenses);

		licenseKeyGenerateForm.setSubscriptionTerms(
			_getSubscriptionTerms(accountKey, productGroupName));
		licenseKeyGenerateForm.setVersions(
			_getProductVersions(productGroupName));

		return licenseKeyGenerateForm;
	}

	@Override
	public Response
			getAccountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey(
				String accountKey, String productGroupName,
				String productVersion)
		throws Exception {

		_checkAccountMembership(accountKey);

		_checkAccountSelfProvisioningPermission(accountKey);

		if (!_hasActiveProduct(accountKey, productGroupName)) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		String fileName = _licenseKeyExporter.getFileName(
			productGroupName, productVersion, "development");

		String licenseXML = StringUtil.read(
			LicenseKeyResourceImpl.class.getResourceAsStream(
				"/dependencies/" + fileName));

		return Response.ok(
			licenseXML.getBytes()
		).header(
			"content-disposition", "attachment; filename=\"" + fileName + "\""
		).type(
			ContentTypes.TEXT_XML
		).build();
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Response getLicenseKeyDownload(Long licenseKeyId) throws Exception {
		com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
			_licenseKeyLocalService.getLicenseKey(licenseKeyId);

		_checkAccountMembership(licenseKey.getAccountKey());

		if (licenseKey.getLicenseVersion() >= 2) {
			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion(),
				licenseKey.getName());

			String licenseXML = _licenseKeyExporter.toXML(
				licenseKey.getKey(), licenseKey.getAccountName(),
				licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(),
				licenseKey.getHostName(), licenseKey.getIpAddresses(),
				licenseKey.getMacAddresses(), licenseKey.getServerId(),
				licenseKey.getStartDate(), licenseKey.getExpirationDate(),
				licenseKey.getCreateDate());

			return Response.ok(
				licenseXML.getBytes()
			).header(
				"content-disposition",
				"attachment; filename=\"" + fileName + "\""
			).type(
				ContentTypes.TEXT_XML
			).build();
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	@Override
	public Response getLicenseKeyDownload(Long[] licenseKeyIds)
		throws Exception {

		if (ArrayUtil.isEmpty(licenseKeyIds)) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		List<com.liferay.osb.provisioning.license.model.LicenseKey>
			licenseKeys = new ArrayList<>();

		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			if (!licenseKey.getActive()) {
				continue;
			}

			_checkAccountMembership(licenseKey.getAccountKey());

			licenseKeys.add(licenseKey);
		}

		if (_isAggregateVersion1(licenseKeys)) {
			String[] hostNames = new String[licenseKeys.size()];
			String[] ipAddresses = new String[licenseKeys.size()];
			String[] macAddresses = new String[licenseKeys.size()];
			String[] serverIds = new String[licenseKeys.size()];

			for (int i = 0; i < licenseKeys.size(); i++) {
				com.liferay.osb.provisioning.license.model.LicenseKey
					licenseKey = licenseKeys.get(i);

				hostNames[i] = licenseKey.getHostName();
				ipAddresses[i] = licenseKey.getIpAddresses();
				macAddresses[i] = licenseKey.getMacAddresses();
				serverIds[i] = licenseKey.getServerId();
			}

			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				licenseKeys.get(0);

			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion(),
				licenseKey.getName());

			String licenseXML = _licenseKeyExporter.toXML(
				licenseKey.getAccountName(), licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(), hostNames,
				ipAddresses, macAddresses, serverIds, licenseKey.getStartDate(),
				licenseKey.getExpirationDate(), licenseKey.getCreateDate());

			return Response.ok(
				licenseXML.getBytes()
			).header(
				"content-disposition",
				"attachment; filename=\"" + fileName + "\""
			).type(
				ContentTypes.TEXT_XML
			).build();
		}

		if (!_isAggregateVersion2(licenseKeys)) {
			throw new Exception(
				"The specified activation keys cannot be aggregated together");
		}

		Set<String> names = new HashSet<>();
		Set<String> productNames = new HashSet<>();

		String[] licenseXMLs = new String[licenseKeys.size()];

		for (int i = 0; i < licenseKeys.size(); i++) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				licenseKeys.get(i);

			names.add(licenseKey.getName());
			productNames.add(licenseKey.getProductName());

			licenseXMLs[i] = _licenseKeyExporter.toXML(
				licenseKey.getKey(), licenseKey.getAccountName(),
				licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(),
				licenseKey.getHostName(), licenseKey.getIpAddresses(),
				licenseKey.getMacAddresses(), licenseKey.getServerId(),
				licenseKey.getStartDate(), licenseKey.getExpirationDate(),
				licenseKey.getCreateDate());
		}

		String fileName = _licenseKeyExporter.getFileName(
			ArrayUtil.toStringArray(productNames),
			ArrayUtil.toStringArray(names));

		String licenseXML = _licenseKeyExporter.aggregateXMLs(licenseXMLs);

		return Response.ok(
			licenseXML.getBytes()
		).header(
			"content-disposition", "attachment; filename=\"" + fileName + "\""
		).type(
			ContentTypes.TEXT_XML
		).build();
	}

	@Override
	public Response getLicenseKeyExport(Long[] licenseKeyIds) throws Exception {
		if (ArrayUtil.isEmpty(licenseKeyIds)) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		List<com.liferay.osb.provisioning.license.model.LicenseKey>
			licenseKeys = new ArrayList<>();

		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_checkAccountMembership(licenseKey.getAccountKey());

			licenseKeys.add(licenseKey);
		}

		return Response.ok(
			_toCsv(licenseKeys)
		).header(
			"content-disposition",
			"attachment; filename=\"activation-key-details.csv\""
		).type(
			ContentTypes.TEXT_CSV
		).build();
	}

	@Override
	public Page<LicenseKey> postAccountAccountKeyLicenseKeysPage(
			String accountKey, LicenseKey[] licenseKeys)
		throws Exception {

		_checkAccountAdminContactRole(accountKey);

		List<LicenseKey> curLicenseKeys = new ArrayList<>();

		for (LicenseKey licenseKey : licenseKeys) {
			LicenseKey.LicenseEntryType licenseEntryType =
				licenseKey.getLicenseEntryType();
			LicenseKey.Sizing sizing = licenseKey.getSizing();

			com.liferay.osb.provisioning.license.model.LicenseKey
				curLicenseKey = _licenseKeyLocalService.addLicenseKey(
					contextUser.getUserId(), licenseEntryType.getValue(),
					licenseKey.getProductKey(), accountKey,
					licenseKey.getProductPurchaseKey(),
					licenseKey.getProductVersion(), licenseKey.getName(),
					licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
					sizing.getValue(), licenseKey.getDescription(),
					licenseKey.getHostName(), licenseKey.getIpAddresses(),
					licenseKey.getMacAddresses(), licenseKey.getStartDate(),
					licenseKey.getExpirationDate(),
					licenseKey.getComplimentary(), licenseKey.getActive());

			curLicenseKeys.add(LicenseKeyUtil.toLicenseKey(curLicenseKey));
		}

		return Page.of(curLicenseKeys);
	}

	@Override
	public Page<LicenseKey> postLicenseKeysExtendPage(LicenseKey[] licenseKeys)
		throws Exception {

		for (LicenseKey licenseKey : licenseKeys) {
			com.liferay.osb.provisioning.license.model.LicenseKey
				curLicenseKey = _licenseKeyLocalService.getLicenseKey(
					licenseKey.getId());

			_checkAccountAdminContactRole(curLicenseKey.getAccountKey());

			_validate(
				licenseKey.getProductPurchaseKey(), licenseKey.getStartDate(),
				licenseKey.getExpirationDate());
		}

		List<LicenseKey> curLicenseKeys = new ArrayList<>();

		for (LicenseKey licenseKey : licenseKeys) {
			com.liferay.osb.provisioning.license.model.LicenseKey
				curLicenseKey = _licenseKeyLocalService.extendLicenseKey(
					contextUser.getUserId(), licenseKey.getId(),
					licenseKey.getProductPurchaseKey(),
					licenseKey.getStartDate(), licenseKey.getExpirationDate());

			curLicenseKeys.add(LicenseKeyUtil.toLicenseKey(curLicenseKey));
		}

		return Page.of(curLicenseKeys);
	}

	@Override
	public void putLicenseKeyActivate(Long[] licenseKeyIds) throws Exception {
		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_checkAccountAdminContactRole(licenseKey.getAccountKey());
		}

		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_licenseKeyLocalService.updateLicenseKey(
				contextUser.getUserId(), licenseKeyId,
				licenseKey.getProductPurchaseKey(),
				licenseKey.getComplimentary(), true);
		}
	}

	@Override
	public void putLicenseKeyDeactivate(Long[] licenseKeyIds) throws Exception {
		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_checkAccountAdminContactRole(licenseKey.getAccountKey());
		}

		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_licenseKeyLocalService.updateLicenseKey(
				contextUser.getUserId(), licenseKeyId,
				licenseKey.getProductPurchaseKey(),
				licenseKey.getComplimentary(), false);
		}
	}

	private void _checkAccountAdminContactRole(String accountKey)
		throws Exception {

		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountCustomerContactRoles(
					accountKey, contact.getEmailAddress(), 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(
						ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

					return;
				}
			}
		}
		else if (_isOmniAdmin()) {
			return;
		}

		throw new PrincipalException();
	}

	private void _checkAccountMembership(String accountKey)
		throws PrincipalException {

		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			for (Account account : contact.getAccounts()) {
				if (accountKey.equals(account.getKey())) {
					return;
				}
			}
		}
		else if (_isOmniAdmin()) {
			return;
		}

		throw new PrincipalException();
	}

	private void _checkAccountSelfProvisioningPermission(String accountKey)
		throws Exception {

		Account account = _accountWebService.getAccount(accountKey);

		Map<String, String> properties = account.getProperties();

		if (properties == null) {
			return;
		}

		boolean selfProvisioning = GetterUtil.getBoolean(
			properties.get("allowSelfProvisioning"), true);

		if (!selfProvisioning) {
			throw new PrincipalException();
		}
	}

	private String _formatCsvFields(Object... objects) {
		StringBundler sb = new StringBundler(4 * objects.length);

		for (int i = 0; i < objects.length; i++) {
			sb.append(StringPool.QUOTE);
			sb.append(objects[i]);
			sb.append(StringPool.QUOTE);

			if (i < (objects.length - 1)) {
				sb.append(StringPool.COMMA);
			}
		}

		sb.append(StringPool.NEW_LINE);

		return sb.toString();
	}

	private Version[] _getProductVersions(String productGroupName) {
		Set<Version> versions = new HashSet<>();

		String[] productVersions = ProductVersion.getProductGroupVersions(
			productGroupName);

		for (String productVersion : productVersions) {
			Set<Type> types = new HashSet<>();

			Version version = new Version();

			version.setLabel(productVersion);

			List<LicenseEntry> licenseEntries =
				_licenseEntryLocalService.getLicenseEntriesByNameVersion(
					"%" + productGroupName + "%", productVersion);

			for (LicenseEntry licenseEntry : licenseEntries) {
				Type type = new Type();

				type.setLicenseEntryDisplayName(licenseEntry.getDisplayName());
				type.setLicenseEntryName(licenseEntry.getName());
				type.setLicenseEntryType(licenseEntry.getType());
				type.setProductKey(licenseEntry.getProductKey());

				types.add(type);
			}

			if (!types.isEmpty()) {
				version.setTypes(types.toArray(new Type[0]));

				versions.add(version);
			}
		}

		return versions.toArray(new Version[0]);
	}

	private SubscriptionTerm[] _getSubscriptionTerms(
			String accountKey, String productGroupName)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", accountKey);
		filterQuery.addEquals(true, "property_licenses", "true");

		if (productGroupName.equals(ProductGroup.Name.COMMERCE.toString())) {
			filterQuery.addContains(true, "name", "Commerce Subscription");
		}
		else if (productGroupName.equals(ProductGroup.Name.DXP.toString())) {
			filterQuery.addStartsWith(true, "name", "DXP");
			filterQuery.addContains(true, "name", "DXP Cloud", true);
		}
		else if (productGroupName.equals(ProductGroup.Name.PORTAL.toString())) {
			filterQuery.addContains(
				true, "name", "Early  Access Program", true);
			filterQuery.addContains(true, "name", "Portal");
		}

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.search(
				StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		if (productPurchaseViews.isEmpty()) {
			return new SubscriptionTerm[0];
		}

		List<SubscriptionTerm> subscriptionTerms = new ArrayList<>();

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			SubscriptionTerm subscriptionTerm = new SubscriptionTerm();

			if (productPurchaseView.getProductConsumptions() != null) {
				Map<String, List<ProductConsumption>> productConsumptionsMap =
					new HashMap<>();

				for (ProductConsumption productConsumption :
						productPurchaseView.getProductConsumptions()) {

					List<ProductConsumption> productConsumptions =
						productConsumptionsMap.get(
							productConsumption.getProductPurchaseKey());

					if (productConsumptions == null) {
						productConsumptions = new ArrayList<>();

						productConsumptionsMap.put(
							productConsumption.getProductPurchaseKey(),
							productConsumptions);
					}

					productConsumptions.add(productConsumption);
				}

				if (ArrayUtil.isNotEmpty(
						productPurchaseView.getProductPurchases())) {

					for (ProductPurchase productPurchase :
							productPurchaseView.getProductPurchases()) {

						Map<String, String> properties =
							productPurchase.getProperties();

						int sizing = 0;

						if (properties != null) {
							sizing = GetterUtil.getInteger(
								properties.get("sizing"));
						}

						int provisionedCount = 0;

						List<ProductConsumption> productConsumptions =
							productConsumptionsMap.get(
								productPurchase.getKey());

						if (productConsumptions != null) {
							provisionedCount = productConsumptions.size();
						}

						subscriptionTerm.setEndDate(
							productPurchase.getEndDate());
						subscriptionTerm.setInstanceSize(sizing);
						subscriptionTerm.setPerpetual(
							productPurchase.getPerpetual());
						subscriptionTerm.setProductKey(
							productPurchase.getProductKey());
						subscriptionTerm.setProductPurchaseKey(
							productPurchase.getKey());
						subscriptionTerm.setProvisionedCount(provisionedCount);
						subscriptionTerm.setQuantity(
							productPurchase.getQuantity());
						subscriptionTerm.setStartDate(
							productPurchase.getStartDate());

						subscriptionTerms.add(subscriptionTerm);
					}
				}
			}
		}

		return subscriptionTerms.toArray(new SubscriptionTerm[0]);
	}

	private boolean _hasActiveProduct(
			String accountKey, String productGroupName)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", accountKey);
		filterQuery.addEquals(true, "property_type", "primary");
		filterQuery.addEquals(true, "state", "active");

		if (productGroupName.equals(ProductConstants.GROUP_NAME_COMMERCE)) {
			filterQuery.addContains(false, "name", "Commerce for DXP Cloud");
			filterQuery.addContains(false, "name", "Commerce Subscription");
		}

		if (productGroupName.equals(ProductConstants.GROUP_NAME_DXP)) {
			filterQuery.addContains(false, "name", "DXP");
		}

		if (productGroupName.equals(ProductConstants.GROUP_NAME_PORTAL)) {
			filterQuery.addContains(false, "name", "Portal");
		}

		filterQuery.addContains(false, "name", "Partnership");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.search(
				StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			Product curProduct = productPurchaseView.getProduct();

			String curProductName = curProduct.getName();

			if (productGroupName.equals(ProductConstants.GROUP_NAME_COMMERCE) &&
				(curProductName.startsWith(
					ProductConstants.NAME_COMMERCE_FOR_DXP_CLOUD) ||
				 curProductName.startsWith(
					 ProductConstants.NAME_COMMERCE_SUBSCRIPTION))) {

				return true;
			}

			if (productGroupName.equals(ProductConstants.GROUP_NAME_DXP) &&
				(curProductName.startsWith(ProductConstants.NAME_DXP) ||
				 curProductName.contains(ProductConstants.NAME_DXP_CLOUD))) {

				return true;
			}

			if (productGroupName.equals(ProductConstants.GROUP_NAME_PORTAL) &&
				curProductName.contains(ProductConstants.NAME_PORTAL)) {

				return true;
			}

			if (ArrayUtil.contains(
					ProductConstants.NAMES_PARTNERSHIP, curProductName) &&
				(productGroupName.equals(
					ProductConstants.GROUP_NAME_COMMERCE) ||
				 productGroupName.equals(ProductConstants.GROUP_NAME_DXP) ||
				 productGroupName.equals(ProductConstants.GROUP_NAME_PORTAL))) {

				return true;
			}
		}

		return false;
	}

	private boolean _isAggregateVersion1(
			List<com.liferay.osb.provisioning.license.model.LicenseKey>
				licenseKeys)
		throws Exception {

		if (licenseKeys.isEmpty() || (licenseKeys.size() <= 1)) {
			return false;
		}

		com.liferay.osb.provisioning.license.model.LicenseKey firstLicenseKey =
			licenseKeys.get(0);

		int licenseVersion = firstLicenseKey.getLicenseVersion();
		String productVersion = firstLicenseKey.getProductVersion();
		Date startDate = firstLicenseKey.getStartDate();
		Date expirationDate = firstLicenseKey.getExpirationDate();

		for (com.liferay.osb.provisioning.license.model.LicenseKey licenseKey :
				licenseKeys) {

			int curLicenseVersion = licenseKey.getLicenseVersion();

			if ((curLicenseVersion < 4) ||
				(curLicenseVersion != licenseVersion)) {

				return false;
			}

			String curProductVersion = licenseKey.getProductVersion();

			if (!curProductVersion.equals(productVersion)) {
				return false;
			}

			String curLicenseEntryType = licenseKey.getLicenseEntryType();

			if (!curLicenseEntryType.equals(LicenseType.PRODUCTION)) {
				return false;
			}

			if (!DateUtil.equals(startDate, licenseKey.getStartDate())) {
				return false;
			}

			if (!DateUtil.equals(
					expirationDate, licenseKey.getExpirationDate())) {

				return false;
			}
		}

		return true;
	}

	private boolean _isAggregateVersion2(
			List<com.liferay.osb.provisioning.license.model.LicenseKey>
				licenseKeys)
		throws Exception {

		if (licenseKeys.isEmpty() || (licenseKeys.size() <= 1)) {
			return false;
		}

		for (com.liferay.osb.provisioning.license.model.LicenseKey licenseKey :
				licenseKeys) {

			if (licenseKey.getLicenseVersion() <= 5) {
				return false;
			}
		}

		return true;
	}

	private boolean _isOmniAdmin() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		return false;
	}

	private String _toCsv(
			Collection<com.liferay.osb.provisioning.license.model.LicenseKey>
				licenseKeys)
		throws Exception {

		StringBundler sb = new StringBundler(6 + licenseKeys.size());

		sb.append("Project Name,Account Key,Project State,Support Region,");
		sb.append("Product Version,Product Name,License Key Id,IP Addresses,");
		sb.append("MAC Addresses,Host Name,Instance Sizing,");
		sb.append("License Start Date,License Expiration Date,License Status,");
		sb.append("Max Servers,Complimentary");
		sb.append(StringPool.NEW_LINE);

		for (com.liferay.osb.provisioning.license.model.LicenseKey licenseKey :
				licenseKeys) {

			Account account = _accountWebService.getAccount(
				licenseKey.getAccountKey());

			String status = "Active";

			if (!licenseKey.getActive()) {
				status = "Inactive";
			}

			String formattedCsvFields = _formatCsvFields(
				licenseKey.getAccountName(), licenseKey.getAccountKey(),
				_accountReader.getSubscriptionState(account),
				account.getRegionAsString(),
				licenseKey.getProductVersionLabel(),
				licenseKey.getProductName(), licenseKey.getLicenseKeyId(),
				licenseKey.getIpAddresses(), licenseKey.getMacAddresses(),
				licenseKey.getHostName(), licenseKey.getSizing(),
				licenseKey.getStartDate(), licenseKey.getExpirationDate(),
				status, licenseKey.getMaxServers(),
				licenseKey.getComplimentary());

			sb.append(formattedCsvFields);
		}

		return sb.toString();
	}

	private void _validate(
			String productPurchaseKey, Date startDate, Date expirationDate)
		throws PortalException {

		if (Validator.isNull(productPurchaseKey)) {
			throw new LicenseKeyProductPurchaseKeyException(
				"Invalid product purchase key");
		}

		if ((startDate == null) || (expirationDate == null) ||
			expirationDate.before(startDate)) {

			throw new LicenseKeyDateException(
				"Invalid start date or expiration date");
		}
	}

	private static final EntityModel _entityModel = new LicenseKeyEntityModel();

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductWebService _productWebService;

}