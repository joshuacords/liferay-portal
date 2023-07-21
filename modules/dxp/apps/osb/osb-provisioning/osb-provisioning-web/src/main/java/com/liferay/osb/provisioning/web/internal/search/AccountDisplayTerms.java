/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountDisplayTerms extends DisplayTerms {

	public static final String ACTIVE_SLAS = "activeSLAs";

	public static final String CODE = "code";

	public static final String COUNTRY_NAME = "countryName";

	public static final String CREATE_DATE_GT = "createDateGT";

	public static final String CREATE_DATE_LT = "createDateLT";

	public static final String CREATED_BY_EMAIL_ADDRESS =
		"createdByEmailAddress";

	public static final String EXTERNAL_ACCOUNT_KEY = "externalAccountKey";

	public static final String FLS_TEAM_KEY = "flsTeamKey";

	public static final String INTERNALS = "internals";

	public static final String MODIFIED_DATE_GT = "modifiedDateGT";

	public static final String MODIFIED_DATE_LT = "modifiedDateLT";

	public static final String NAME = "name";

	public static final String NOTES = "notes";

	public static final String PARENT = "parent";

	public static final String PARENT_ACCOUNT_KEY = "parentAccountKey";

	public static final String PARTNER_TEAM_KEY = "partnerTeamKey";

	public static final String PARTNERS = "partners";

	public static final String PROVIDES_FLS = "providesFLS";

	public static final String RECEIVES_FLS = "receivesFLS";

	public static final String REGIONS = "regions";

	public static final String SALES_INFO = "salesInfo";

	public static final String SUBSCRIPTION_STATES = "subscriptionStates";

	public static final String TIERS = "tiers";

	public static final String WORKER_CONTACT_EMAIL_ADDRESS =
		"workerContactEmailAddress";

	public AccountDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		activeSLAs = ParamUtil.getStringValues(portletRequest, ACTIVE_SLAS);
		code = ParamUtil.getString(portletRequest, CODE);
		countryName = ParamUtil.getString(portletRequest, COUNTRY_NAME);
		createDateGT = ParamUtil.getString(portletRequest, CREATE_DATE_GT);
		createDateLT = ParamUtil.getString(portletRequest, CREATE_DATE_LT);
		createdByEmailAddress = ParamUtil.getString(
			portletRequest, CREATED_BY_EMAIL_ADDRESS);
		externalAccountKey = ParamUtil.getString(
			portletRequest, EXTERNAL_ACCOUNT_KEY);
		flsTeamKey = ParamUtil.getString(portletRequest, FLS_TEAM_KEY);
		internals = ParamUtil.getBooleanValues(portletRequest, INTERNALS);

		if (Validator.isNull(keywords)) {
			keywords = ParamUtil.getString(
				portletRequest, "accountSearchKeywords");
		}

		modifiedDateGT = ParamUtil.getString(portletRequest, MODIFIED_DATE_GT);
		modifiedDateLT = ParamUtil.getString(portletRequest, MODIFIED_DATE_LT);
		name = ParamUtil.getString(portletRequest, NAME);
		notes = ParamUtil.getString(portletRequest, NOTES);
		parent = ParamUtil.getBoolean(portletRequest, PARENT);
		parentAccountKey = ParamUtil.getString(
			portletRequest, PARENT_ACCOUNT_KEY);
		partners = ParamUtil.getBooleanValues(portletRequest, PARTNERS);
		partnerTeamKey = ParamUtil.getString(portletRequest, PARTNER_TEAM_KEY);
		providesFLS = ParamUtil.getBooleanValues(portletRequest, PROVIDES_FLS);
		receivesFLS = ParamUtil.getBooleanValues(portletRequest, RECEIVES_FLS);
		regions = ParamUtil.getStringValues(portletRequest, REGIONS);
		salesInfo = ParamUtil.getString(portletRequest, SALES_INFO);

		subscriptionStates = ParamUtil.getStringValues(
			portletRequest, SUBSCRIPTION_STATES);

		Map<String, String[]> parameterMap = portletRequest.getParameterMap();

		if (!isAdvancedSearch() && (parameterMap.size() == 1)) {
			subscriptionStates = ProductPurchaseConstants.STATES;
		}

		tiers = ParamUtil.getStringValues(portletRequest, TIERS);
		workerContactEmailAddress = ParamUtil.getString(
			portletRequest, WORKER_CONTACT_EMAIL_ADDRESS);
	}

	public String[] getActiveSLAs() {
		return activeSLAs;
	}

	public String getCode() {
		return code;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getCreateDateGT() {
		return createDateGT;
	}

	public String getCreateDateLT() {
		return createDateLT;
	}

	public String getCreatedByEmailAddress() {
		return createdByEmailAddress;
	}

	public List<DisplayTerm> getDisplayTermsList() {
		return new ArrayList<>(
			Arrays.asList(
				new DisplayTerm("account-name", NAME, name),
				new DisplayTerm("code", CODE, code),
				new DisplayTerm(
					"parent-account", PARENT_ACCOUNT_KEY, parentAccountKey),
				new DisplayTerm(
					"project-worker", WORKER_CONTACT_EMAIL_ADDRESS,
					workerContactEmailAddress),
				new DisplayTerm(
					"partner-reseller-si", PARTNER_TEAM_KEY, partnerTeamKey),
				new DisplayTerm("first-line-support", FLS_TEAM_KEY, flsTeamKey),
				new DisplayTerm("country", COUNTRY_NAME, countryName),
				new DisplayTerm(
					"external-account-key", EXTERNAL_ACCOUNT_KEY,
					externalAccountKey),
				new DisplayTerm("notes", NOTES, notes),
				new DisplayTerm("sales-info", SALES_INFO, salesInfo),
				new DisplayTerm(
					"partner", PARTNERS, StringUtil.merge(partners)),
				new DisplayTerm(
					"provides-fls", PROVIDES_FLS,
					StringUtil.merge(providesFLS)),
				new DisplayTerm(
					"receives-fls", RECEIVES_FLS,
					StringUtil.merge(receivesFLS)),
				new DisplayTerm(
					"internal", INTERNALS, StringUtil.merge(internals)),
				new DisplayTerm("tier", TIERS, StringUtil.merge(tiers)),
				new DisplayTerm(
					"subscription-state", SUBSCRIPTION_STATES,
					StringUtil.merge(subscriptionStates)),
				new DisplayTerm(
					"subscription-level", ACTIVE_SLAS,
					StringUtil.merge(activeSLAs)),
				new DisplayTerm(
					"support-region", REGIONS, StringUtil.merge(regions)),
				new DisplayTerm(
					"created-by", CREATED_BY_EMAIL_ADDRESS,
					createdByEmailAddress),
				new DisplayTerm("created-after", CREATE_DATE_GT, createDateGT),
				new DisplayTerm("created-before", CREATE_DATE_LT, createDateLT),
				new DisplayTerm(
					"modified-after", MODIFIED_DATE_GT, modifiedDateGT),
				new DisplayTerm(
					"modified-before", MODIFIED_DATE_LT, modifiedDateLT)));
	}

	public String getExternalAccountKey() {
		return externalAccountKey;
	}

	public String getFLSTeamKey() {
		return flsTeamKey;
	}

	public boolean[] getInternals() {
		return internals;
	}

	public String getModifiedDateGT() {
		return modifiedDateGT;
	}

	public String getModifiedDateLT() {
		return modifiedDateLT;
	}

	public String getName() {
		return name;
	}

	public String getNotes() {
		return notes;
	}

	public String getParentAccountKey() {
		return parentAccountKey;
	}

	public boolean[] getPartners() {
		return partners;
	}

	public String getPartnerTeamKey() {
		return partnerTeamKey;
	}

	public boolean[] getProvidesFLS() {
		return providesFLS;
	}

	public boolean[] getReceivesFLS() {
		return receivesFLS;
	}

	public String[] getRegions() {
		return regions;
	}

	public String getSalesInfo() {
		return salesInfo;
	}

	public String[] getSubscriptionStates() {
		return subscriptionStates;
	}

	public String[] getTiers() {
		return tiers;
	}

	public String getWorkerContactEmailAddress() {
		return workerContactEmailAddress;
	}

	public boolean isParent() {
		return parent;
	}

	protected String[] activeSLAs;
	protected String code;
	protected String countryName;
	protected String createDateGT;
	protected String createDateLT;
	protected String createdByEmailAddress;
	protected String externalAccountKey;
	protected String flsTeamKey;
	protected boolean[] internals;
	protected String modifiedDateGT;
	protected String modifiedDateLT;
	protected String name;
	protected String notes;
	protected boolean parent;
	protected String parentAccountKey;
	protected boolean[] partners;
	protected String partnerTeamKey;
	protected boolean[] providesFLS;
	protected boolean[] receivesFLS;
	protected String[] regions;
	protected String salesInfo;
	protected String[] subscriptionStates;
	protected String[] tiers;
	protected String workerContactEmailAddress;

}