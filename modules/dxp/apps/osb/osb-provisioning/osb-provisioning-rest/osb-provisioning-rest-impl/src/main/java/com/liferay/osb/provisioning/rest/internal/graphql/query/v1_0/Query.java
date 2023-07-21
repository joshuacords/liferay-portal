/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.graphql.query.v1_0;

import com.liferay.osb.provisioning.rest.dto.v1_0.AppLicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKeyGenerateForm;
import com.liferay.osb.provisioning.rest.resource.v1_0.AppLicenseKeyResource;
import com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class Query {

	public static void setAppLicenseKeyResourceComponentServiceObjects(
		ComponentServiceObjects<AppLicenseKeyResource>
			appLicenseKeyResourceComponentServiceObjects) {

		_appLicenseKeyResourceComponentServiceObjects =
			appLicenseKeyResourceComponentServiceObjects;
	}

	public static void setLicenseKeyResourceComponentServiceObjects(
		ComponentServiceObjects<LicenseKeyResource>
			licenseKeyResourceComponentServiceObjects) {

		_licenseKeyResourceComponentServiceObjects =
			licenseKeyResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {appLicenseKeys(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the app license keys. Results can be paginated, filtered, searched, and sorted."
	)
	public AppLicenseKeyPage appLicenseKeys(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			appLicenseKeyResource -> new AppLicenseKeyPage(
				appLicenseKeyResource.getAppLicenseKeysPage(
					search,
					_filterBiFunction.apply(
						appLicenseKeyResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						appLicenseKeyResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {appLicenseKey(appLicenseKeyId: ___){active, complimentary, createDate, description, expirationDate, hostName, id, ipAddresses, key, licenseType, macAddresses, modifiedDate, modifiedUserName, modifiedUserUuid, orderId, owner, productId, productName, productVersion, startDate, userName, userUuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the app license key.")
	public AppLicenseKey appLicenseKey(
			@GraphQLName("appLicenseKeyId") Long appLicenseKeyId)
		throws Exception {

		return _applyComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			appLicenseKeyResource -> appLicenseKeyResource.getAppLicenseKey(
				appLicenseKeyId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {appLicenseKeyDownload(appLicenseKeyId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Downloads an XML of the app license key.")
	public Response appLicenseKeyDownload(
			@GraphQLName("appLicenseKeyId") Long appLicenseKeyId)
		throws Exception {

		return _applyComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			appLicenseKeyResource ->
				appLicenseKeyResource.getAppLicenseKeyDownload(
					appLicenseKeyId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyLicenseKeys(accountKey: ___, filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the account's license keys. Results can be paginated, filtered, searched, and sorted."
	)
	public LicenseKeyPage accountAccountKeyLicenseKeys(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> new LicenseKeyPage(
				licenseKeyResource.getAccountAccountKeyLicenseKeysPage(
					accountKey, search,
					_filterBiFunction.apply(licenseKeyResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(licenseKeyResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyLicenseKeyExport(accountKey: ___, filter: ___, sorts: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Exports a CSV file of the account's license key details. Results can be filtered and sorted."
	)
	public Response accountAccountKeyLicenseKeyExport(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("filter") String filterString,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource ->
				licenseKeyResource.getAccountAccountKeyLicenseKeyExport(
					accountKey,
					_filterBiFunction.apply(licenseKeyResource, filterString),
					_sortsBiFunction.apply(licenseKeyResource, sortsString)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductGroupProductGroupNameGenerateForm(accountKey: ___, productGroupName: ___){allowComplimentary, allowPermanentLicenses, subscriptionTerms, versions}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the license key generation options for the given account."
	)
	public LicenseKeyGenerateForm
			accountAccountKeyProductGroupProductGroupNameGenerateForm(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("productGroupName") String productGroupName)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource ->
				licenseKeyResource.
					getAccountAccountKeyProductGroupProductGroupNameGenerateForm(
						accountKey, productGroupName));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey(accountKey: ___, productGroupName: ___, productVersion: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Downloads the account's product development license key."
	)
	public Response
			accountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("productGroupName") String productGroupName,
				@GraphQLName("productVersion") String productVersion)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource ->
				licenseKeyResource.
					getAccountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey(
						accountKey, productGroupName, productVersion));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductProductKeyUsage(accountKey: ___, productKey: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Gets the max concurrent purchased count and consumed count per year for the previous, current and next year. Also includes the current usage at the time of invoking this API."
	)
	public Response accountAccountKeyProductProductKeyUsage(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("productKey") String productKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource ->
				licenseKeyResource.getAccountAccountKeyProductProductKeyUsage(
					accountKey, productKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {licenseKeyDownload(licenseKeyIds: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Downloads an aggregated license key of the specified license keys."
	)
	public Response licenseKeyDownload(
			@GraphQLName("licenseKeyIds") Long[] licenseKeyIds)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.getLicenseKeyDownload(
				licenseKeyIds));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {licenseKeyDownloadZip(licenseKeyIds: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Downloads a zip file that includes all of the specified license keys."
	)
	public Response licenseKeyDownloadZip(
			@GraphQLName("licenseKeyIds") Long[] licenseKeyIds)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.getLicenseKeyDownloadZip(
				licenseKeyIds));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {licenseKeyExport(licenseKeyIds: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Exports a CSV file of the license key details."
	)
	public Response licenseKeyExport(
			@GraphQLName("licenseKeyIds") Long[] licenseKeyIds)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.getLicenseKeyExport(
				licenseKeyIds));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {licenseKeySubscription(licenseKeyId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Gets the subscriptions to the license keys.")
	public Boolean licenseKeySubscription(
			@GraphQLName("licenseKeyId") Long licenseKeyId)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.getLicenseKeySubscription(
				licenseKeyId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {licenseKeyDownload(licenseKeyId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Downloads the license key.")
	public Response licenseKeyDownload(
			@GraphQLName("licenseKeyId") Long licenseKeyId)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.getLicenseKeyDownload(
				licenseKeyId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productGroupProductGroupNameDevelopmentLicenseKey(accountKey: ___, productGroupName: ___, productVersion: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrives the account's product development license key download."
	)
	public Response productGroupProductGroupNameDevelopmentLicenseKey(
			@GraphQLName("productGroupName") String productGroupName,
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("productVersion") String productVersion)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource ->
				licenseKeyResource.
					getProductGroupProductGroupNameDevelopmentLicenseKey(
						productGroupName, accountKey, productVersion));
	}

	@GraphQLTypeExtension(AppLicenseKey.class)
	public class GetAppLicenseKeyDownloadTypeExtension {

		public GetAppLicenseKeyDownloadTypeExtension(
			AppLicenseKey appLicenseKey) {

			_appLicenseKey = appLicenseKey;
		}

		@GraphQLField(description = "Downloads an XML of the app license key.")
		public Response download() throws Exception {
			return _applyComponentServiceObjects(
				_appLicenseKeyResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				appLicenseKeyResource ->
					appLicenseKeyResource.getAppLicenseKeyDownload(
						_appLicenseKey.getId()));
		}

		private AppLicenseKey _appLicenseKey;

	}

	@GraphQLName("AppLicenseKeyPage")
	public class AppLicenseKeyPage {

		public AppLicenseKeyPage(Page appLicenseKeyPage) {
			actions = appLicenseKeyPage.getActions();

			items = appLicenseKeyPage.getItems();
			lastPage = appLicenseKeyPage.getLastPage();
			page = appLicenseKeyPage.getPage();
			pageSize = appLicenseKeyPage.getPageSize();
			totalCount = appLicenseKeyPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<AppLicenseKey> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("LicenseKeyPage")
	public class LicenseKeyPage {

		public LicenseKeyPage(Page licenseKeyPage) {
			actions = licenseKeyPage.getActions();

			items = licenseKeyPage.getItems();
			lastPage = licenseKeyPage.getLastPage();
			page = licenseKeyPage.getPage();
			pageSize = licenseKeyPage.getPageSize();
			totalCount = licenseKeyPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<LicenseKey> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			AppLicenseKeyResource appLicenseKeyResource)
		throws Exception {

		appLicenseKeyResource.setContextAcceptLanguage(_acceptLanguage);
		appLicenseKeyResource.setContextCompany(_company);
		appLicenseKeyResource.setContextHttpServletRequest(_httpServletRequest);
		appLicenseKeyResource.setContextHttpServletResponse(
			_httpServletResponse);
		appLicenseKeyResource.setContextUriInfo(_uriInfo);
		appLicenseKeyResource.setContextUser(_user);
		appLicenseKeyResource.setGroupLocalService(_groupLocalService);
		appLicenseKeyResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(LicenseKeyResource licenseKeyResource)
		throws Exception {

		licenseKeyResource.setContextAcceptLanguage(_acceptLanguage);
		licenseKeyResource.setContextCompany(_company);
		licenseKeyResource.setContextHttpServletRequest(_httpServletRequest);
		licenseKeyResource.setContextHttpServletResponse(_httpServletResponse);
		licenseKeyResource.setContextUriInfo(_uriInfo);
		licenseKeyResource.setContextUser(_user);
		licenseKeyResource.setGroupLocalService(_groupLocalService);
		licenseKeyResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<AppLicenseKeyResource>
		_appLicenseKeyResourceComponentServiceObjects;
	private static ComponentServiceObjects<LicenseKeyResource>
		_licenseKeyResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}