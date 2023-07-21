/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseKeyDisplayTerms extends DisplayTerms {

	public static final String ACCOUNT_KEY = "accountKey";

	public static final String ACCOUNT_NAME = "accountName";

	public static final String ACTIVE_LICENSES = "activeLicenses";

	public static final String CREATE_DATE_GT = "createDateGT";

	public static final String CREATE_DATE_LT = "createDateLT";

	public static final String CREATOR_EMAIL_ADDRESS = "creatorEmailAddress";

	public static final String EXPIRATION_DATE_GT = "expirationDateGT";

	public static final String EXPIRATION_DATE_LT = "expirationDateLT";

	public static final String HOST_NAME = "hostName";

	public static final String IP_ADDRESS = "ipAddress";

	public static final String KEY = "key";

	public static final String MAC_ADDRESS = "macAddress";

	public static final String MODIFIED_DATE_GT = "modifiedDateGT";

	public static final String MODIFIED_DATE_LT = "modifiedDateLT";

	public static final String MODIFIED_EMAIL_ADDRESS = "modifiedEmailAddress";

	public static final String OWNER = "owner";

	public static final String PRODUCT_PURCHASE_KEY = "productPurchaseKey";

	public static final String PRODUCT_VERSIONS = "productVersions";

	public static final String PRODUCTS = "products";

	public static final String SERVER_ID = "serverId";

	public static final String START_DATE_GT = "startDateGT";

	public static final String START_DATE_LT = "startDateLT";

	public static final String TYPES = "types";

	public LicenseKeyDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		if (Validator.isNull(keywords)) {
			keywords = ParamUtil.getString(
				portletRequest, "licenseKeySearchKeywords");
		}

		accountKey = ParamUtil.getString(portletRequest, ACCOUNT_KEY);
		accountName = ParamUtil.getString(portletRequest, ACCOUNT_NAME);
		activeLicenses = ParamUtil.getBooleanValues(
			portletRequest, ACTIVE_LICENSES);
		createDateGT = ParamUtil.getString(portletRequest, CREATE_DATE_GT);
		createDateLT = ParamUtil.getString(portletRequest, CREATE_DATE_LT);
		creatorEmailAddress = ParamUtil.getString(
			portletRequest, CREATOR_EMAIL_ADDRESS);
		expirationDateGT = ParamUtil.getString(
			portletRequest, EXPIRATION_DATE_GT);
		expirationDateLT = ParamUtil.getString(
			portletRequest, EXPIRATION_DATE_LT);
		hostName = ParamUtil.getString(portletRequest, HOST_NAME);
		ipAddress = ParamUtil.getString(portletRequest, IP_ADDRESS);
		key = ParamUtil.getString(portletRequest, KEY);
		macAddress = ParamUtil.getString(portletRequest, MAC_ADDRESS);
		modifiedDateGT = ParamUtil.getString(portletRequest, MODIFIED_DATE_GT);
		modifiedDateLT = ParamUtil.getString(portletRequest, MODIFIED_DATE_LT);
		modifiedEmailAddress = ParamUtil.getString(
			portletRequest, MODIFIED_EMAIL_ADDRESS);
		owner = ParamUtil.getString(portletRequest, OWNER);
		productPurchaseKey = ParamUtil.getString(
			portletRequest, PRODUCT_PURCHASE_KEY);
		products = ParamUtil.getStringValues(portletRequest, PRODUCTS);
		productVersions = ParamUtil.getStringValues(
			portletRequest, PRODUCT_VERSIONS);
		serverId = ParamUtil.getString(portletRequest, SERVER_ID);
		startDateGT = ParamUtil.getString(portletRequest, START_DATE_GT);
		startDateLT = ParamUtil.getString(portletRequest, START_DATE_LT);
		types = ParamUtil.getStringValues(portletRequest, TYPES);
	}

	public String getAccountKey() {
		if (Validator.isNotNull(accountKey)) {
			return accountKey;
		}

		return null;
	}

	public String getAccountName() {
		if (Validator.isNotNull(accountName)) {
			return accountName;
		}

		return null;
	}

	public boolean[] getActiveLicenses() {
		return activeLicenses;
	}

	public String getCreateDateGT() {
		if (Validator.isNotNull(createDateGT)) {
			return createDateGT;
		}

		return null;
	}

	public String getCreateDateLT() {
		if (Validator.isNotNull(createDateLT)) {
			return createDateLT;
		}

		return null;
	}

	public String getCreatorEmailAddress() {
		if (Validator.isNotNull(creatorEmailAddress)) {
			return creatorEmailAddress;
		}

		return null;
	}

	public List<DisplayTerm> getDisplayTermsList() {
		return new ArrayList<>(
			Arrays.asList(
				new DisplayTerm("account-key", ACCOUNT_KEY, accountKey),
				new DisplayTerm(
					"product-purchase-key", PRODUCT_PURCHASE_KEY,
					productPurchaseKey),
				new DisplayTerm("account-name", ACCOUNT_NAME, accountName),
				new DisplayTerm("owner", OWNER, owner),
				new DisplayTerm("host-name", HOST_NAME, hostName),
				new DisplayTerm("ip-address", IP_ADDRESS, ipAddress),
				new DisplayTerm("mac-address", MAC_ADDRESS, macAddress),
				new DisplayTerm("server-id", SERVER_ID, serverId),
				new DisplayTerm("key", KEY, key),
				new DisplayTerm(
					"created-by", CREATOR_EMAIL_ADDRESS, creatorEmailAddress),
				new DisplayTerm(
					"last-edited-by", MODIFIED_EMAIL_ADDRESS,
					modifiedEmailAddress),
				new DisplayTerm(
					"active", ACTIVE_LICENSES,
					StringUtil.merge(activeLicenses)),
				new DisplayTerm("license-type", TYPES, StringUtil.merge(types)),
				new DisplayTerm(
					"product", PRODUCTS, StringUtil.merge(products)),
				new DisplayTerm(
					"product-version", PRODUCT_VERSIONS,
					StringUtil.merge(productVersions)),
				new DisplayTerm("created-before", CREATE_DATE_LT, createDateLT),
				new DisplayTerm("created-after", CREATE_DATE_GT, createDateGT),
				new DisplayTerm(
					"modified-before", MODIFIED_DATE_LT, modifiedDateLT),
				new DisplayTerm(
					"modified-after", MODIFIED_DATE_GT, modifiedDateGT),
				new DisplayTerm("started-before", START_DATE_LT, startDateLT),
				new DisplayTerm("started-after", START_DATE_GT, startDateGT),
				new DisplayTerm(
					"expires-before", EXPIRATION_DATE_LT, expirationDateLT),
				new DisplayTerm(
					"expires-after", EXPIRATION_DATE_GT, expirationDateGT)));
	}

	public String getExpirationDateGT() {
		if (Validator.isNotNull(expirationDateGT)) {
			return expirationDateGT;
		}

		return null;
	}

	public String getExpirationDateLT() {
		if (Validator.isNotNull(expirationDateLT)) {
			return expirationDateLT;
		}

		return null;
	}

	public String getHostName() {
		if (Validator.isNotNull(hostName)) {
			return hostName;
		}

		return null;
	}

	public String getIpAddress() {
		if (Validator.isNotNull(ipAddress)) {
			return ipAddress;
		}

		return null;
	}

	public String getKey() {
		if (Validator.isNotNull(key)) {
			return key;
		}

		return null;
	}

	@Override
	public String getKeywords() {
		if (Validator.isNotNull(keywords)) {
			return keywords;
		}

		return null;
	}

	public String getMacAddress() {
		if (Validator.isNotNull(macAddress)) {
			return macAddress;
		}

		return null;
	}

	public String getModifiedDateGT() {
		if (Validator.isNotNull(modifiedDateGT)) {
			return modifiedDateGT;
		}

		return null;
	}

	public String getModifiedDateLT() {
		if (Validator.isNotNull(modifiedDateLT)) {
			return modifiedDateLT;
		}

		return null;
	}

	public String getModifiedEmailAddress() {
		if (Validator.isNotNull(modifiedEmailAddress)) {
			return modifiedEmailAddress;
		}

		return null;
	}

	public String getOwner() {
		if (Validator.isNotNull(owner)) {
			return owner;
		}

		return null;
	}

	public String getProductPurchaseKey() {
		if (Validator.isNotNull(productPurchaseKey)) {
			return productPurchaseKey;
		}

		return null;
	}

	public String[] getProducts() {
		return products;
	}

	public String[] getProductVersions() {
		return productVersions;
	}

	public String getServerId() {
		if (Validator.isNotNull(serverId)) {
			return serverId;
		}

		return null;
	}

	public String getStartDateGT() {
		if (Validator.isNotNull(startDateGT)) {
			return startDateGT;
		}

		return null;
	}

	public String getStartDateLT() {
		if (Validator.isNotNull(startDateLT)) {
			return startDateLT;
		}

		return null;
	}

	public String[] getTypes() {
		return types;
	}

	protected String accountKey;
	protected String accountName;
	protected boolean[] activeLicenses;
	protected String createDateGT;
	protected String createDateLT;
	protected String creatorEmailAddress;
	protected String expirationDateGT;
	protected String expirationDateLT;
	protected String hostName;
	protected String ipAddress;
	protected String key;
	protected String macAddress;
	protected String modifiedDateGT;
	protected String modifiedDateLT;
	protected String modifiedEmailAddress;
	protected String owner;
	protected String productPurchaseKey;
	protected String[] products;
	protected String[] productVersions;
	protected String serverId;
	protected String startDateGT;
	protected String startDateLT;
	protected String[] types;

}