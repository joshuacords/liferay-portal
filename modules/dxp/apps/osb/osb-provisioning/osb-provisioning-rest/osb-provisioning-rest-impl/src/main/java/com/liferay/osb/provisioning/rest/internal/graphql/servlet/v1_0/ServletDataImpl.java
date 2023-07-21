/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.graphql.servlet.v1_0;

import com.liferay.osb.provisioning.rest.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.osb.provisioning.rest.internal.graphql.query.v1_0.Query;
import com.liferay.osb.provisioning.rest.internal.resource.v1_0.AppLicenseKeyResourceImpl;
import com.liferay.osb.provisioning.rest.internal.resource.v1_0.LicenseKeyResourceImpl;
import com.liferay.osb.provisioning.rest.resource.v1_0.AppLicenseKeyResource;
import com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Kyle Bischof
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAppLicenseKeyResourceComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects);
		Mutation.setLicenseKeyResourceComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects);

		Query.setAppLicenseKeyResourceComponentServiceObjects(
			_appLicenseKeyResourceComponentServiceObjects);
		Query.setLicenseKeyResourceComponentServiceObjects(
			_licenseKeyResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "Liferay.Provisioning.REST";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/provisioning-rest-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createAppLicenseKey",
						new ObjectValuePair<>(
							AppLicenseKeyResourceImpl.class,
							"postAppLicenseKey"));
					put(
						"mutation#updateAppLicenseKeyActivate",
						new ObjectValuePair<>(
							AppLicenseKeyResourceImpl.class,
							"putAppLicenseKeyActivate"));
					put(
						"mutation#updateAppLicenseKeyDeactivate",
						new ObjectValuePair<>(
							AppLicenseKeyResourceImpl.class,
							"putAppLicenseKeyDeactivate"));
					put(
						"mutation#createAccountAccountKeyLicenseKeysPage",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"postAccountAccountKeyLicenseKeysPage"));
					put(
						"mutation#updateLicenseKeyActivate",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"putLicenseKeyActivate"));
					put(
						"mutation#updateLicenseKeyDeactivate",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"putLicenseKeyDeactivate"));
					put(
						"mutation#createLicenseKeysExtendPage",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"postLicenseKeysExtendPage"));
					put(
						"mutation#deleteLicenseKeySubscription",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"deleteLicenseKeySubscription"));
					put(
						"mutation#updateLicenseKeySubscription",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"putLicenseKeySubscription"));

					put(
						"query#appLicenseKeys",
						new ObjectValuePair<>(
							AppLicenseKeyResourceImpl.class,
							"getAppLicenseKeysPage"));
					put(
						"query#appLicenseKey",
						new ObjectValuePair<>(
							AppLicenseKeyResourceImpl.class,
							"getAppLicenseKey"));
					put(
						"query#appLicenseKeyDownload",
						new ObjectValuePair<>(
							AppLicenseKeyResourceImpl.class,
							"getAppLicenseKeyDownload"));
					put(
						"query#accountAccountKeyLicenseKeys",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getAccountAccountKeyLicenseKeysPage"));
					put(
						"query#accountAccountKeyLicenseKeyExport",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getAccountAccountKeyLicenseKeyExport"));
					put(
						"query#accountAccountKeyProductGroupProductGroupNameGenerateForm",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getAccountAccountKeyProductGroupProductGroupNameGenerateForm"));
					put(
						"query#accountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getAccountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey"));
					put(
						"query#accountAccountKeyProductProductKeyUsage",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getAccountAccountKeyProductProductKeyUsage"));
					put(
						"query#licenseKeyDownload",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getLicenseKeyDownload"));
					put(
						"query#licenseKeyDownloadZip",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getLicenseKeyDownloadZip"));
					put(
						"query#licenseKeyExport",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getLicenseKeyExport"));
					put(
						"query#licenseKeySubscription",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getLicenseKeySubscription"));
					put(
						"query#licenseKeyDownload",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getLicenseKeyDownload"));
					put(
						"query#productGroupProductGroupNameDevelopmentLicenseKey",
						new ObjectValuePair<>(
							LicenseKeyResourceImpl.class,
							"getProductGroupProductGroupNameDevelopmentLicenseKey"));

					put(
						"query#AppLicenseKey.download",
						new ObjectValuePair<>(
							AppLicenseKeyResourceImpl.class,
							"getAppLicenseKeyDownload"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AppLicenseKeyResource>
		_appLicenseKeyResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<LicenseKeyResource>
		_licenseKeyResourceComponentServiceObjects;

}