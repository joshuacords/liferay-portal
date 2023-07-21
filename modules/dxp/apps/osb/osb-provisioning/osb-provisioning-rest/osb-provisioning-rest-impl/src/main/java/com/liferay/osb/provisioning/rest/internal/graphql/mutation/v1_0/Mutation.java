/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.graphql.mutation.v1_0;

import com.liferay.osb.provisioning.rest.dto.v1_0.AppLicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.resource.v1_0.AppLicenseKeyResource;
import com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class Mutation {

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

	@GraphQLField(description = "Generates an app license key.")
	public AppLicenseKey createAppLicenseKey(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("appLicenseKey") AppLicenseKey appLicenseKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			appLicenseKeyResource -> appLicenseKeyResource.postAppLicenseKey(
				agentName, agentUID, appLicenseKey));
	}

	@GraphQLField(description = "Activates app license keys.")
	public boolean updateAppLicenseKeyActivate(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("appLicenseKeyIds") Long[] appLicenseKeyIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			appLicenseKeyResource ->
				appLicenseKeyResource.putAppLicenseKeyActivate(
					agentName, agentUID, appLicenseKeyIds));

		return true;
	}

	@GraphQLField(description = "Deactivates app license keys.")
	public boolean updateAppLicenseKeyDeactivate(
			@GraphQLName("agentName") String agentName,
			@GraphQLName("agentUID") String agentUID,
			@GraphQLName("appLicenseKeyIds") Long[] appLicenseKeyIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			appLicenseKeyResource ->
				appLicenseKeyResource.putAppLicenseKeyDeactivate(
					agentName, agentUID, appLicenseKeyIds));

		return true;
	}

	@GraphQLField(description = "Generates license keys for an account.")
	public java.util.Collection<LicenseKey>
			createAccountAccountKeyLicenseKeysPage(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("licenseKeys") LicenseKey[] licenseKeys)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> {
				Page paginationPage =
					licenseKeyResource.postAccountAccountKeyLicenseKeysPage(
						accountKey, licenseKeys);

				return paginationPage.getItems();
			});
	}

	@GraphQLField(description = "Activates license keys.")
	public boolean updateLicenseKeyActivate(
			@GraphQLName("licenseKeyIds") Long[] licenseKeyIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.putLicenseKeyActivate(
				licenseKeyIds));

		return true;
	}

	@GraphQLField(description = "Deactivates license keys.")
	public boolean updateLicenseKeyDeactivate(
			@GraphQLName("licenseKeyIds") Long[] licenseKeyIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.putLicenseKeyDeactivate(
				licenseKeyIds));

		return true;
	}

	@GraphQLField(description = "Extends license keys.")
	public java.util.Collection<LicenseKey> createLicenseKeysExtendPage(
			@GraphQLName("licenseKeys") LicenseKey[] licenseKeys)
		throws Exception {

		return _applyComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> {
				Page paginationPage =
					licenseKeyResource.postLicenseKeysExtendPage(licenseKeys);

				return paginationPage.getItems();
			});
	}

	@GraphQLField(description = "Unsubscribes from license keys.")
	public boolean deleteLicenseKeySubscription(
			@GraphQLName("licenseKeyIds") Long[] licenseKeyIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource ->
				licenseKeyResource.deleteLicenseKeySubscription(licenseKeyIds));

		return true;
	}

	@GraphQLField(description = "Subscribes to license keys.")
	public boolean updateLicenseKeySubscription(
			@GraphQLName("licenseKeyIds") Long[] licenseKeyIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects,
			this::_populateResourceContext,
			licenseKeyResource -> licenseKeyResource.putLicenseKeySubscription(
				licenseKeyIds));

		return true;
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}