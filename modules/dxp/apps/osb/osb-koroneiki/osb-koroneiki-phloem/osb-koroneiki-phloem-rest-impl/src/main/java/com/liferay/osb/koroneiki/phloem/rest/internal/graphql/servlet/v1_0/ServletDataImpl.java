/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.graphql.servlet.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.osb.koroneiki.phloem.rest.internal.graphql.query.v1_0.Query;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AuditEntryResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactAccountViewResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.CountryRegionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.CountryResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.NoteResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.PostalAddressResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Amos Fong
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Mutation.setAuditEntryResourceComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects);
		Mutation.setContactResourceComponentServiceObjects(
			_contactResourceComponentServiceObjects);
		Mutation.setContactRoleResourceComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects);
		Mutation.setEntitlementDefinitionResourceComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects);
		Mutation.setExternalLinkResourceComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects);
		Mutation.setNoteResourceComponentServiceObjects(
			_noteResourceComponentServiceObjects);
		Mutation.setPostalAddressResourceComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects);
		Mutation.setProductResourceComponentServiceObjects(
			_productResourceComponentServiceObjects);
		Mutation.setProductConsumptionResourceComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects);
		Mutation.setProductPurchaseResourceComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects);
		Mutation.setTeamResourceComponentServiceObjects(
			_teamResourceComponentServiceObjects);
		Mutation.setTeamRoleResourceComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects);

		Query.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Query.setAuditEntryResourceComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects);
		Query.setContactResourceComponentServiceObjects(
			_contactResourceComponentServiceObjects);
		Query.setContactAccountViewResourceComponentServiceObjects(
			_contactAccountViewResourceComponentServiceObjects);
		Query.setContactRoleResourceComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects);
		Query.setCountryResourceComponentServiceObjects(
			_countryResourceComponentServiceObjects);
		Query.setCountryRegionResourceComponentServiceObjects(
			_countryRegionResourceComponentServiceObjects);
		Query.setEntitlementDefinitionResourceComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects);
		Query.setExternalLinkResourceComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects);
		Query.setNoteResourceComponentServiceObjects(
			_noteResourceComponentServiceObjects);
		Query.setPostalAddressResourceComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects);
		Query.setProductResourceComponentServiceObjects(
			_productResourceComponentServiceObjects);
		Query.setProductConsumptionResourceComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects);
		Query.setProductPurchaseResourceComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects);
		Query.setProductPurchaseViewResourceComponentServiceObjects(
			_productPurchaseViewResourceComponentServiceObjects);
		Query.setTeamResourceComponentServiceObjects(
			_teamResourceComponentServiceObjects);
		Query.setTeamRoleResourceComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/koroneiki-rest-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AuditEntryResource>
		_auditEntryResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ContactResource>
		_contactResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<EntitlementDefinitionResource>
		_entitlementDefinitionResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ExternalLinkResource>
		_externalLinkResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<NoteResource>
		_noteResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<PostalAddressResource>
		_postalAddressResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductConsumptionResource>
		_productConsumptionResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductPurchaseResource>
		_productPurchaseResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TeamResource>
		_teamResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TeamRoleResource>
		_teamRoleResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ContactAccountViewResource>
		_contactAccountViewResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CountryResource>
		_countryResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CountryRegionResource>
		_countryRegionResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductPurchaseViewResource>
		_productPurchaseViewResourceComponentServiceObjects;

}