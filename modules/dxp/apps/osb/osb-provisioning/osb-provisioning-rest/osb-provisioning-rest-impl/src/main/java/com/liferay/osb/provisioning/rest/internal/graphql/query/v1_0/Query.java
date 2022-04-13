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

package com.liferay.osb.provisioning.rest.internal.graphql.query.v1_0;

import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKeyGenerateForm;
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

	public static void setLicenseKeyResourceComponentServiceObjects(
		ComponentServiceObjects<LicenseKeyResource>
			licenseKeyResourceComponentServiceObjects) {

		_licenseKeyResourceComponentServiceObjects =
			licenseKeyResourceComponentServiceObjects;
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
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductGroupProductGroupNameGenerateForm(accountKey: ___, productGroupName: ___){allowPermanentLicenses, subscriptionTerms, versions}}"}' -u 'test@liferay.com:test'
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
		protected Map<String, Map> actions;

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