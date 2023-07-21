/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountSearchTerms extends AccountDisplayTerms {

	public AccountSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public FilterQuery getAdvancedSearchFilter(
			String[] subscriptionProductKeys, String createdByUuid,
			String flsTeamRoleKey, String partnerTeamRoleKey)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		if (!ArrayUtil.isEmpty(subscriptionStates)) {
			filterQuery.addFilterQuery(
				andOperator,
				_getSubscriptionStateFilter(subscriptionProductKeys));
		}

		if (!ArrayUtil.isEmpty(receivesFLS)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean receivesFLSValue : receivesFLS) {
				nestedFilterQuery.addLambdaContains(
					false, "assignedTeamKeyTeamRoleKeys", "_" + flsTeamRoleKey,
					!receivesFLSValue);
			}

			filterQuery.addFilterQuery(andOperator, nestedFilterQuery);
		}

		if (!ArrayUtil.isEmpty(activeSLAs)) {
			filterQuery.addLambdaEquals(
				andOperator, "entitlements", activeSLAs);
		}

		if (!ArrayUtil.isEmpty(partners)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean partner : partners) {
				nestedFilterQuery.addLambdaEquals(
					false, "entitlements", EntitlementConstants.PARTNER,
					!partner);
			}

			filterQuery.addFilterQuery(andOperator, nestedFilterQuery);
		}

		if (Validator.isNotNull(externalAccountKey)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			nestedFilterQuery.addLambdaContains(
				false, "externalLinkEntityIds", externalAccountKey);
			nestedFilterQuery.addLambdaContains(
				false, "productPurchaseExternalLinkEntityIds",
				externalAccountKey);

			filterQuery.addFilterQuery(andOperator, nestedFilterQuery);
		}

		if (!ArrayUtil.isEmpty(internals)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean internal : internals) {
				nestedFilterQuery.addEquals(false, "internal", internal);
			}

			filterQuery.addFilterQuery(andOperator, nestedFilterQuery);
		}

		if (!ArrayUtil.isEmpty(providesFLS)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean providesFLSValue : providesFLS) {
				nestedFilterQuery.addLambdaContains(
					false, "teamsAssignedToAccountKeyTeamRoleKeys",
					"_" + flsTeamRoleKey, !providesFLSValue);
			}

			filterQuery.addFilterQuery(andOperator, nestedFilterQuery);
		}

		if (Validator.isNotNull(flsTeamKey)) {
			filterQuery.addLambdaEquals(
				andOperator, "assignedTeamKeyTeamRoleKeys",
				flsTeamKey + "_" + flsTeamRoleKey);
		}

		if (Validator.isNotNull(partnerTeamKey)) {
			filterQuery.addLambdaEquals(
				andOperator, "assignedTeamKeyTeamRoleKeys",
				partnerTeamKey + "_" + partnerTeamRoleKey);
		}

		if (Validator.isNotNull(code)) {
			filterQuery.addContains(andOperator, "code", code);
		}

		if (Validator.isNotNull(createdByUuid)) {
			filterQuery.addEquals(andOperator, "creatorUuid", createdByUuid);
		}

		if (Validator.isNotNull(createDateGT)) {
			filterQuery.addGreaterThan(
				andOperator, "dateCreated", _dateFormat.parse(createDateGT));
		}

		if (Validator.isNotNull(createDateLT)) {
			filterQuery.addLessThan(
				andOperator, "dateCreated", _dateFormat.parse(createDateLT));
		}

		if (Validator.isNotNull(modifiedDateGT)) {
			filterQuery.addGreaterThan(
				andOperator, "dateModified", _dateFormat.parse(modifiedDateGT));
		}

		if (Validator.isNotNull(modifiedDateLT)) {
			filterQuery.addLessThan(
				andOperator, "dateModified", _dateFormat.parse(modifiedDateLT));
		}

		if (Validator.isNotNull(notes)) {
			filterQuery.addLambdaContains(
				andOperator, "generalNoteContent", notes);
		}

		if (Validator.isNotNull(name)) {
			filterQuery.addContains(andOperator, "name", name);
		}

		if (Validator.isNotNull(parentAccountKey)) {
			filterQuery.addEquals(
				andOperator, "parentAccountKey", parentAccountKey);
		}

		if (Validator.isNotNull(countryName)) {
			filterQuery.addLambdaEquals(
				andOperator, "postalAddressCountries", countryName);
		}

		if (!ArrayUtil.isEmpty(regions)) {
			filterQuery.addEquals(andOperator, "region", regions);
		}

		if (Validator.isNotNull(salesInfo)) {
			filterQuery.addLambdaContains(
				andOperator, "salesNoteContent", salesInfo);
		}

		if (!ArrayUtil.isEmpty(tiers)) {
			filterQuery.addEquals(andOperator, "tier", tiers);
		}

		if (Validator.isNotNull(workerContactEmailAddress)) {
			filterQuery.addLambdaEquals(
				andOperator, "workerContactEmailAddresses",
				workerContactEmailAddress);
		}

		return filterQuery;
	}

	public FilterQuery getBasicSearchFilter(String[] subscriptionProductKeys) {
		FilterQuery filterQuery = new FilterQuery();

		if (!ArrayUtil.isEmpty(subscriptionStates)) {
			filterQuery.addFilterQuery(
				andOperator,
				_getSubscriptionStateFilter(subscriptionProductKeys));
		}

		if (parent) {
			filterQuery.addEquals(andOperator, "parent", true);
		}

		return filterQuery;
	}

	public boolean hasSearchTerms() {
		if (isAdvancedSearch()) {
			if (!ArrayUtil.isEmpty(activeSLAs) || Validator.isNotNull(code) ||
				Validator.isNotNull(countryName) ||
				Validator.isNotNull(createDateGT) ||
				Validator.isNotNull(createDateLT) ||
				Validator.isNotNull(createdByEmailAddress) ||
				Validator.isNotNull(externalAccountKey) ||
				Validator.isNotNull(flsTeamKey) ||
				!ArrayUtil.isEmpty(internals) ||
				Validator.isNotNull(modifiedDateGT) ||
				Validator.isNotNull(modifiedDateLT) ||
				Validator.isNotNull(name) || Validator.isNotNull(notes) ||
				Validator.isNotNull(parentAccountKey) ||
				!ArrayUtil.isEmpty(partners) ||
				Validator.isNotNull(partnerTeamKey) ||
				!ArrayUtil.isEmpty(providesFLS) ||
				!ArrayUtil.isEmpty(receivesFLS) ||
				!ArrayUtil.isEmpty(regions) || Validator.isNotNull(salesInfo) ||
				!ArrayUtil.isEmpty(subscriptionStates) ||
				!ArrayUtil.isEmpty(tiers) ||
				Validator.isNotNull(workerContactEmailAddress)) {

				return true;
			}
		}
		else {
			if (Validator.isNotNull(keywords)) {
				return true;
			}
		}

		return false;
	}

	private FilterQuery _getSubscriptionStateFilter(
		String[] subscriptionProductKeys) {

		FilterQuery filterQuery = new FilterQuery();

		for (String subscriptionState : subscriptionStates) {
			if (subscriptionState.equals(
					ProductPurchaseConstants.STATE_ACTIVE)) {

				filterQuery.addLambdaEquals(
					false, "activeProductKeys", subscriptionProductKeys);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_CANCELLED)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					true, "activeProductKeys", subscriptionProductKeys, true);
				nestedFilterQuery.addLambdaEquals(
					true, "expiredProductKeys", subscriptionProductKeys, true);
				nestedFilterQuery.addLambdaEquals(
					true, "unactivatedProductKeys", subscriptionProductKeys,
					true);

				nestedFilterQuery.addLambdaEquals(
					true, "cancelledProductKeys", subscriptionProductKeys);

				filterQuery.addFilterQuery(false, nestedFilterQuery);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_EXPIRED)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					true, "activeProductKeys", subscriptionProductKeys, true);
				nestedFilterQuery.addLambdaEquals(
					true, "unactivatedProductKeys", subscriptionProductKeys,
					true);

				nestedFilterQuery.addLambdaEquals(
					true, "expiredProductKeys", subscriptionProductKeys);

				filterQuery.addFilterQuery(false, nestedFilterQuery);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_NOT_AVAILABLE)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					true, "activeProductKeys", subscriptionProductKeys, true);
				nestedFilterQuery.addLambdaEquals(
					true, "cancelledProductKeys", subscriptionProductKeys,
					true);
				nestedFilterQuery.addLambdaEquals(
					true, "expiredProductKeys", subscriptionProductKeys, true);
				nestedFilterQuery.addLambdaEquals(
					true, "unactivatedProductKeys", subscriptionProductKeys,
					true);

				filterQuery.addFilterQuery(false, nestedFilterQuery);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_UNACTIVATED)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					true, "activeProductKeys", subscriptionProductKeys, true);

				nestedFilterQuery.addLambdaEquals(
					true, "unactivatedProductKeys", subscriptionProductKeys);

				filterQuery.addFilterQuery(false, nestedFilterQuery);
			}
		}

		return filterQuery;
	}

	private final DateFormat _dateFormat =
		DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}