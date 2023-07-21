/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.resource.v1_0;

import com.liferay.osb.provisioning.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.provisioning.license.exporter.LicenseKeyExporter;
import com.liferay.osb.provisioning.license.helper.constants.ProductId;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.AppLicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.util.AppLicenseKeyUtil;
import com.liferay.osb.provisioning.rest.internal.odata.entity.v1_0.AppLicenseKeyEntityModel;
import com.liferay.osb.provisioning.rest.resource.v1_0.AppLicenseKeyResource;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/app-license-key.properties",
	scope = ServiceScope.PROTOTYPE, service = AppLicenseKeyResource.class
)
public class AppLicenseKeyResourceImpl
	extends BaseAppLicenseKeyResourceImpl implements EntityModelResource {

	@Override
	public AppLicenseKey getAppLicenseKey(Long appLicenseKeyId)
		throws Exception {

		_checkPermission();

		LicenseKey licenseKey = _licenseKeyLocalService.getLicenseKey(
			appLicenseKeyId);

		if (!_isApp(licenseKey)) {
			throw new NoSuchLicenseKeyException();
		}

		return AppLicenseKeyUtil.toAppLicenseKey(licenseKey);
	}

	@Override
	public Response getAppLicenseKeyDownload(Long appLicenseKeyId)
		throws Exception {

		_checkPermission();

		LicenseKey licenseKey = _licenseKeyLocalService.getLicenseKey(
			appLicenseKeyId);

		if (!_isApp(licenseKey)) {
			throw new NoSuchLicenseKeyException();
		}

		String fileName = _licenseKeyExporter.getFileName(
			licenseKey.getProductName(), licenseKey.getProductVersion(),
			licenseKey.getName());

		String licenseXML = _licenseKeyExporter.toXML(
			licenseKey.getKey(), licenseKey.getAccountName(),
			licenseKey.getLicenseEntryName(), licenseKey.getLicenseEntryType(),
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
			"content-disposition", "attachment; filename=\"" + fileName + "\""
		).type(
			ContentTypes.TEXT_XML
		).build();
	}

	@Override
	public Page<AppLicenseKey> getAppLicenseKeysPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		_checkPermission();

		return SearchUtil.search(
			booleanQuery -> booleanQuery.addTerm(
				"productId", ProductId.PORTAL, false,
				BooleanClauseOccur.MUST_NOT),
			filter, LicenseKey.class, search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> AppLicenseKeyUtil.toAppLicenseKey(
				_licenseKeyLocalService.getLicenseKey(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public AppLicenseKey postAppLicenseKey(
			String agentName, String agentUID, AppLicenseKey appLicenseKey)
		throws Exception {

		_checkPermission();

		String description = appLicenseKey.getDescription();

		if (Validator.isNull(description)) {
			description = appLicenseKey.getOwner();
		}

		LicenseKey licenseKey = _licenseKeyLocalService.addLicenseKey(
			agentName, agentUID, appLicenseKey.getOrderId(),
			appLicenseKey.getLicenseTypeAsString(),
			appLicenseKey.getProductName(), appLicenseKey.getProductId(),
			appLicenseKey.getProductVersion(), appLicenseKey.getOwner(), 0,
			description, appLicenseKey.getHostName(),
			appLicenseKey.getIpAddresses(), appLicenseKey.getMacAddresses(),
			StringPool.BLANK, appLicenseKey.getStartDate(),
			appLicenseKey.getExpirationDate());

		return AppLicenseKeyUtil.toAppLicenseKey(licenseKey);
	}

	@Override
	public void putAppLicenseKeyActivate(
			String agentName, String agentUID, Long[] appLicenseKeyIds)
		throws Exception {

		_checkPermission();

		for (long appLicenseKeyId : appLicenseKeyIds) {
			_licenseKeyLocalService.updateLicenseKey(
				agentName, agentUID, appLicenseKeyId, true);
		}
	}

	@Override
	public void putAppLicenseKeyDeactivate(
			String agentName, String agentUID, Long[] appLicenseKeyIds)
		throws Exception {

		_checkPermission();

		for (long appLicenseKeyId : appLicenseKeyIds) {
			_licenseKeyLocalService.updateLicenseKey(
				agentName, agentUID, appLicenseKeyId, false);
		}
	}

	private boolean _checkPermission() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		return false;
	}

	private boolean _isApp(LicenseKey licenseKey) {
		String productId = licenseKey.getProductId();

		if (productId.equals(ProductId.PORTAL)) {
			return false;
		}

		return true;
	}

	private static final EntityModel _entityModel =
		new AppLicenseKeyEntityModel();

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

}