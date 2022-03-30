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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.distributed.messaging.internal.configuration.DistributedMessagingConfiguration;
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.SalesforceConstants;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.PostalAddressWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.util.DataRegionUtil;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.petra.content.ContentUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.lock.model.Lock;
import com.liferay.portal.lock.service.LockLocalService;

import java.net.URL;

import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.distributed.messaging.internal.configuration.DistributedMessagingConfiguration",
	immediate = true, property = "topic.pattern=dossiera.provisioning.create",
	service = DossieraCreateMessageSubscriber.class
)
public class DossieraCreateMessageSubscriber extends BaseMessageSubscriber {

	@Activate
	protected void activate(Map<String, Object> properties) {
		_distributedMessagingConfiguration =
			ConfigurableUtil.createConfigurable(
				DistributedMessagingConfiguration.class, properties);
	}

	protected ProductPurchase addEWSAProductPurchase(
			ProductPurchase ewsaProductPurchase,
			ProductPurchase productPurchase, JSONObject jsonObject,
			String accountName)
		throws Exception {

		ProductPurchase newProductPurchase = new ProductPurchase();

		newProductPurchase.setAccountKey(productPurchase.getAccountKey());
		newProductPurchase.setProductKey(productPurchase.getProductKey());
		newProductPurchase.setStartDate(ewsaProductPurchase.getStartDate());

		if ((productPurchase.getStartDate() != null) &&
			(productPurchase.getOriginalEndDate() != null)) {

			newProductPurchase.setOriginalEndDate(
				ewsaProductPurchase.getOriginalEndDate());
			newProductPurchase.setEndDate(ewsaProductPurchase.getEndDate());
		}
		else {
			newProductPurchase.setPerpetual(true);
		}

		newProductPurchase.setQuantity(productPurchase.getQuantity());

		ExternalLink externalLink = getSalesforceOpportunityExternalLink(
			jsonObject);

		if (externalLink != null) {
			newProductPurchase.setExternalLinks(
				new ExternalLink[] {externalLink});
		}

		Map<String, String> properties = productPurchase.getProperties();

		properties.put("productType", SalesforceConstants.PRODUCT_TYPE_RENEWAL);

		newProductPurchase.setProperties(properties);

		Set<String> renewedEWSAAccountNames =
			_renewedEWSAAccountNamesThreadLocal.get();

		renewedEWSAAccountNames.add(accountName);

		try {
			newProductPurchase = _productPurchaseWebService.addProductPurchase(
				StringPool.BLANK, StringPool.BLANK,
				newProductPurchase.getAccountKey(), newProductPurchase);
		}
		catch (Exception exception) {
			_handleProductPurchaseError(productPurchase, exception);
		}

		return newProductPurchase;
	}

	protected void checkWarnings(
			String accountKey, Account account, Account partnerAccount,
			boolean analyticsCloud, boolean partnerFirstLineSupport,
			List<Contact> inactiveContacts, List<Contact> missingContacts,
			Set<ProductPurchase> productPurchases,
			String salesforceOpportunityTypeName, int salesforceOpportunityType,
			JSONObject jsonObject)
		throws Exception {

		if (Validator.isNull(accountKey) &&
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS)) {

			_logWarning(
				"The opportunity type is " + salesforceOpportunityTypeName +
					" and the project does not exist.");
		}

		if (Validator.isNotNull(accountKey) &&
			((salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS) ||
			 (salesforceOpportunityType ==
				 SalesforceConstants.
					 OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS))) {

			_logWarning(
				"The opportunity type is " + salesforceOpportunityTypeName +
					" and the project already exists.");
		}

		FilterQuery filterQuery = new FilterQuery();

		ContactRole supportAdministratorContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR);

		filterQuery.addLambdaEquals(
			false, "accountKeysContactRoleKeys",
			accountKey + "_" + supportAdministratorContactRole.getKey());

		ContactRole supportRequesterContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_REQUESTER);

		filterQuery.addLambdaEquals(
			false, "accountKeysContactRoleKeys",
			accountKey + "_" + supportRequesterContactRole.getKey());

		List<Account> liferayIncAccounts = _accountWebService.search(
			"Liferay, Inc.", null, 1, 1, null);

		Account liferayIncAccount = liferayIncAccounts.get(0);

		filterQuery.addLambdaContains(
			true, "accountKeysContactRoleKeys",
			liferayIncAccount.getKey() + "_", true);

		long curSupportSeatCount = _contactWebService.searchCount(
			StringPool.BLANK, filterQuery);

		int maxSupportSeatCount = _accountReader.getMaxSupportSeatCount(
			account);

		if ((maxSupportSeatCount >= 0) &&
			(curSupportSeatCount > maxSupportSeatCount)) {

			_logWarning(
				StringBundler.concat(
					"The maximum allowed support seats is ",
					maxSupportSeatCount, " but there are ", curSupportSeatCount,
					" support seats taken."));
		}

		if (!inactiveContacts.isEmpty()) {
			StringBundler sb = new StringBundler(
				(2 * inactiveContacts.size()) + 2);

			sb.append("The following inactive contact(s) cannot be assigned ");
			sb.append("to the account:<br />");

			for (Contact contact : inactiveContacts) {
				sb.append(contact.getEmailAddress());
				sb.append("<br />");
			}

			_logWarning(sb.toString());
		}

		if (!missingContacts.isEmpty()) {
			StringBundler sb = new StringBundler(
				(2 * missingContacts.size()) + 2);

			sb.append("The following missing contact(s) cannot be assigned ");
			sb.append("to the account:<br />");

			for (Contact contact : missingContacts) {
				sb.append(contact.getEmailAddress());
				sb.append("<br />");
			}

			_logWarning(sb.toString());
		}

		Team curPartnerTeam = _accountReader.getPartnerTeam(account);

		String curPartnerAccountKey = StringPool.BLANK;

		if (curPartnerTeam != null) {
			curPartnerAccountKey = curPartnerTeam.getAccountKey();
		}

		String partnerAccountKey = StringPool.BLANK;

		if (partnerAccount != null) {
			partnerAccountKey = partnerAccount.getKey();
		}

		if (!curPartnerAccountKey.equals(partnerAccountKey)) {
			StringBundler sb = new StringBundler(5);

			sb.append("The partner account (");

			if (partnerAccount != null) {
				sb.append(partnerAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(") is different from the existing partner account (");

			if (curPartnerTeam != null) {
				Account curPartnerAccount = _accountWebService.getAccount(
					curPartnerTeam.getAccountKey());

				sb.append(curPartnerAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(").");

			_logWarning(sb.toString());
		}

		Team curFLSTeam = _accountReader.getFirstLineSupportTeam(account);

		String curFLSAccountKey = StringPool.BLANK;

		if (curFLSTeam != null) {
			curFLSAccountKey = curFLSTeam.getAccountKey();
		}

		if (((curFLSTeam == null) && partnerFirstLineSupport) ||
			((curFLSTeam != null) &&
			 (!partnerFirstLineSupport ||
			  !curFLSAccountKey.equals(partnerAccountKey)))) {

			StringBundler sb = new StringBundler(5);

			sb.append("The FLS partner account (");

			if ((partnerAccount != null) && partnerFirstLineSupport) {
				sb.append(partnerAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(") is different from the existing FLS partner account (");

			if (curFLSTeam != null) {
				Account curFLSAccount = _accountWebService.getAccount(
					curFLSTeam.getAccountKey());

				sb.append(curFLSAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(").");

			_logWarning(sb.toString());
		}

		Set<String> inactiveProvisionedProducts = new HashSet<>();

		for (ProductPurchase productPurchase : productPurchases) {
			Map<String, String> properties = productPurchase.getProperties();

			if (properties != null) {
				String productType = properties.get("productType");

				if ((productType == null) ||
					!ArrayUtil.contains(
						SalesforceConstants.PRODUCT_TYPES_RENEWAL,
						productType)) {

					continue;
				}
			}

			Product product = productPurchase.getProduct();

			FilterQuery filterQuery2 = new FilterQuery();

			filterQuery2.addEquals(true, "accountKey", accountKey);
			filterQuery2.addEquals(true, "productKey", product.getKey());
			filterQuery2.addEquals(false, "endDate", (String)null);
			filterQuery2.addLessThanEquals(false, "endDate", new Date());

			int productConsumptionCount =
				(int)_productConsumptionWebService.searchCount(filterQuery2);

			String productName = product.getName();

			if ((productPurchase.getQuantity() != null) &&
				(productConsumptionCount > productPurchase.getQuantity())) {

				_logWarning(
					StringBundler.concat(
						"The new purchase quantity of ", productName, " is ",
						productPurchase.getQuantity(),
						" which is lower than the current provisioned amount ",
						"of ", productConsumptionCount, "."));
			}

			if ((productName.contains(ProductConstants.NAME_DXP) &&
				 !productName.contains(ProductConstants.NAME_DXP_CLOUD)) ||
				productName.contains(ProductConstants.NAME_COMMERCE) ||
				productName.contains(ProductConstants.NAME_PORTAL)) {

				FilterQuery filterQuery3 = new FilterQuery();

				filterQuery3.addEquals(true, "accountKey", accountKey);
				filterQuery3.addEquals(true, "productKey", product.getKey());
				filterQuery3.addEquals(false, "endDate", (String)null);
				filterQuery3.addGreaterThanEquals(false, "endDate", new Date());

				productConsumptionCount =
					(int)_productConsumptionWebService.searchCount(
						filterQuery3);

				if (productConsumptionCount == 0) {
					inactiveProvisionedProducts.add(productName);
				}
			}
		}

		boolean renewal = jsonObject.getBoolean("_renewal");

		if (renewal) {
			if (analyticsCloud) {
				FilterQuery filterQuery2 = new FilterQuery();

				ContactRole analyticsCloudOwnerRole =
					_contactRoleWebService.fetchContactRole(
						ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
						ContactRoleConstants.NAME_ANALYTICS_CLOUD_OWNER);

				filterQuery2.addLambdaEquals(
					true, "accountKeysContactRoleKeys",
					accountKey + "_" + analyticsCloudOwnerRole.getKey());

				long curAnalyticsCloudOwnerCount =
					_contactWebService.searchCount(
						StringPool.BLANK, filterQuery2);

				if (curAnalyticsCloudOwnerCount == 0) {
					_logWarning(
						StringBundler.concat(
							"An Analytics Cloud subscription was renewed and ",
							"there are no contacts with the Analytics Cloud ",
							"Owner role. The customer does not have access to ",
							"Analytics Cloud."));
				}
			}

			FilterQuery filterQuery2 = new FilterQuery();

			filterQuery2.addLambdaEquals(
				true, "customerAccountKeys", accountKey);

			long curContactsCount = _contactWebService.searchCount(
				StringPool.BLANK, filterQuery2);

			if (curContactsCount == 0) {
				_logWarning(
					StringBundler.concat(
						"An opportunity was processed with Renewal order type ",
						"and there are no contacts. The customer does not ",
						"have access to Help Center."));
			}

			if (curSupportSeatCount == 0) {
				_logWarning(
					StringBundler.concat(
						"An opportunity was processed with Renewal order type ",
						"and there are no contacts with a requester role. The ",
						"customer does not have access to support tickets."));
			}
		}

		for (String inactiveProvisionedProduct : inactiveProvisionedProducts) {
			_logWarning(
				StringBundler.concat(
					"The Renewal order ", inactiveProvisionedProduct,
					" has no activation keys."));
		}
	}

	protected Account createAccount(
			Account parentAccount, Contact[] contacts,
			ExternalLink[] externalLinks, Account.Language language,
			Account.Region region, PostalAddress postalAddress,
			ProductPurchase[] productPurchases, Team[] partnerTeams,
			JSONObject jsonObject)
		throws Exception {

		Account account = new Account();

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");
		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		String name = accountJSONObject.getString("_name");

		if (projectJSONObject != null) {
			String projectName = projectJSONObject.getString("_name");

			account.setName(projectName);
			account.setCode(_getCode(name, projectName));

			if (parentAccount != null) {
				account.setParentAccountKey(parentAccount.getKey());
			}

			String liferayVersion = projectJSONObject.getString(
				"_liferayVersion");

			if (Validator.isNotNull(liferayVersion) &&
				liferayVersion.contains("DXP")) {

				Map<String, String> properties = new HashMap<>();

				properties.put("liferayVersion", liferayVersion);

				account.setProperties(properties);
			}
		}
		else {
			account.setName(name);
			account.setCode(_getCode(name, null));
		}

		name = account.getName();

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "name", name);

		List<Account> duplicateAccounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 0, 1, null);

		if (!duplicateAccounts.isEmpty()) {
			_logWarning("Account name must be unique");
		}

		if (name.contains(StringPool.PIPE)) {
			_logWarning("Account name must not contain the | character");
		}

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		if (ownerJSONObject != null) {
			account.setContactEmailAddress(
				ownerJSONObject.getString("_emailAddress"));
		}

		account.setContacts(contacts);
		account.setExternalLinks(externalLinks);
		account.setPostalAddresses(new PostalAddress[] {postalAddress});
		account.setProductPurchases(productPurchases);
		account.setRegion(region);
		account.setDataRegion(
			DataRegionUtil.getDataRegion(
				region, postalAddress.getAddressCountry()));
		account.setLanguage(language);

		String productFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (!productFamily.equals("P")) {
			account.setTier(Account.Tier.T4);
		}

		account.setAssignedTeams(partnerTeams);

		return _accountWebService.addAccount(
			StringPool.BLANK, StringPool.BLANK, account);
	}

	protected void createAccountNote(JSONObject jsonObject, Account account)
		throws Exception {

		if (account.getProductPurchases() == null) {
			return;
		}

		Note note = new Note();

		note.setContent(getNoteContent(jsonObject, account));
		note.setFormat(Note.Format.PLAIN);
		note.setStatus(Note.Status.APPROVED);
		note.setType(Note.Type.SALES);

		_noteWebService.addNote(
			StringPool.BLANK, StringPool.BLANK, account.getKey(), note);
	}

	protected Account createParentAccount(JSONObject jsonObject)
		throws Exception {

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		String dossieraAccountKey = accountJSONObject.getString(
			"_dossieraAccountKey");

		String name = accountJSONObject.getString("_name");

		Account parentAccount = fetchAccount(dossieraAccountKey);

		if (parentAccount != null) {
			String parentAccountName = parentAccount.getName();

			if (!name.equals(parentAccountName)) {
				parentAccount.setName(name);

				return _accountWebService.updateAccount(
					StringPool.BLANK, StringPool.BLANK, parentAccount.getKey(),
					parentAccount);
			}

			return parentAccount;
		}

		parentAccount = new Account();

		parentAccount.setName(name);
		parentAccount.setCode(_getCode(name, null));

		ExternalLink dossieraExternalLink = new ExternalLink();

		dossieraExternalLink.setDomain(ExternalLinkDomain.DOSSIERA);
		dossieraExternalLink.setEntityName(
			ExternalLinkEntityName.DOSSIERA_ACCOUNT);
		dossieraExternalLink.setEntityId(dossieraAccountKey);

		String salesforceAccountKey = jsonObject.getString(
			"_salesforceAccountKey");

		ExternalLink salesforceExternalLink = new ExternalLink();

		salesforceExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
		salesforceExternalLink.setEntityName(
			ExternalLinkEntityName.SALESFORCE_ACCOUNT);
		salesforceExternalLink.setEntityId(salesforceAccountKey);

		parentAccount.setExternalLinks(
			new ExternalLink[] {dossieraExternalLink, salesforceExternalLink});

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "name", name);

		List<Account> duplicateAccounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 0, 1, null);

		if (!duplicateAccounts.isEmpty()) {
			_logWarning("Parent Account name must be unique");
		}

		if (name.contains(StringPool.PIPE)) {
			_logWarning("Parent Account name must not contain the | character");
		}

		return _accountWebService.addAccount(
			StringPool.BLANK, StringPool.BLANK, parentAccount);
	}

	protected void createZendeskTicket(
			Account account, PostalAddress postalAddress,
			Set<ProductPurchase> productPurchases,
			String salesforceOpportunityTypeName,
			String salesforceOpportunityKey,
			String salesforceOpportunityOwnerEmailAddress)
		throws Exception {

		ZendeskTicket zendeskTicket = new ZendeskTicket();

		Map<Long, String> customFields = new HashMap<>();

		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldProvisioningComponentId(),
			"opportunity_invoiced");
		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldOpportunityOwnerId(),
			salesforceOpportunityOwnerEmailAddress);
		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldPrimaryAddressCountryId(),
			postalAddress.getAddressCountry());
		customFields.put(
			_distributedMessagingConfiguration.zendeskCustomFieldProductId(),
			"Provisioning Request");

		String region = "provisioning_" + account.getRegionAsString();

		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldSupportRegionId(),
			StringUtil.replace(
				StringUtil.toLowerCase(region), CharPool.SPACE,
				CharPool.UNDERLINE));

		zendeskTicket.setCustomFields(customFields);

		StringBundler sb = new StringBundler(
			15 + (productPurchases.size() * 9));

		String accountName = account.getName();

		sb.append("Account Name: ");
		sb.append(accountName);
		sb.append("<br />Account Code: ");
		sb.append(account.getCode());
		sb.append("<br />Opportunity Type: ");
		sb.append(salesforceOpportunityTypeName);
		sb.append("<br />Date Created: ");
		sb.append(account.getDateCreated());
		sb.append("<br />Provisioning Account Link: <a href='");

		Group group = _groupLocalService.getFriendlyURLGroup(
			_portal.getDefaultCompanyId(), "/control_panel");

		Map<String, String[]> params = new LinkedHashMap<>();

		params.put(
			StringPool.UNDERLINE + ProvisioningPortletKeys.ACCOUNTS +
				"_mvcRenderCommandName",
			new String[] {"/accounts/view_account"});
		params.put(
			StringPool.UNDERLINE + ProvisioningPortletKeys.ACCOUNTS +
				"_accountKey",
			new String[] {account.getKey()});

		sb.append(
			_portal.getControlPanelFullURL(
				group.getGroupId(), ProvisioningPortletKeys.ACCOUNTS, params));

		sb.append("'>Provisioning Account</a><br />Salesforce Opportunity ");
		sb.append("Link: <a href='https://login.salesforce.com/");
		sb.append(salesforceOpportunityKey);
		sb.append("'>Salesforce Opportunity</a>");

		StringBundler subjectSB = new StringBundler(
			6 + (productPurchases.size() * 2));

		List<String> warningMessages = _warningMessagesThreadLocal.get();

		if (!warningMessages.isEmpty()) {
			sb.append("<br /><br />Warnings: ");

			for (String warningMessage : warningMessages) {
				sb.append("<br />");
				sb.append(warningMessage);
			}

			subjectSB = subjectSB.append("[Warning] ");
		}

		List<Note> pinnedNotes = _noteWebService.getNotes(
			account.getKey(), Note.Type.GENERAL.toString(), 1, StringPool.BLANK,
			1, 1000);

		if (!pinnedNotes.isEmpty()) {
			sb.append("<br /><br />Pinned Notes:");

			for (Note pinnedNote : pinnedNotes) {
				sb.append("<br /><br />");
				sb.append(pinnedNote.getDateCreated());
				sb.append("<br />");
				sb.append(pinnedNote.getContent());
			}
		}

		subjectSB.append(salesforceOpportunityTypeName);
		subjectSB.append(": ");

		Set<String> renewedAccountNames =
			_renewedEWSAAccountNamesThreadLocal.get();

		if (!renewedAccountNames.isEmpty()) {
			sb.append("<br /><br />EWSA Accounts renewed:");

			for (String renewedAccountName : renewedAccountNames) {
				sb.append("<br />");
				sb.append(renewedAccountName);
			}
		}

		Set<String> productTypes = new HashSet<>();

		sb.append("<br /><br />Products Purchased in This Opportunity:");

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			sb.append("<br /><br />");

			Map<String, String> properties = productPurchase.getProperties();

			if (properties != null) {
				String productType = properties.get("productType");

				if (productType != null) {
					sb.append(productType);
					sb.append(StringPool.SPACE);

					if (!productTypes.contains(productType)) {
						if (!productTypes.isEmpty()) {
							subjectSB.append(", ");
						}

						subjectSB.append(productType);

						productTypes.add(productType);
					}
				}
			}

			sb.append(product.getName());

			if (productPurchase.getQuantity() != null) {
				sb.append(" (");
				sb.append(productPurchase.getQuantity());
				sb.append(")");
			}

			sb.append("<br />Start Date - End Date: ");
			sb.append(getDateRange(productPurchase));
		}

		if (!productTypes.isEmpty()) {
			subjectSB.append(StringPool.SPACE);
		}

		subjectSB.append("Subscription(s) for ");

		int maxLength = 150 - subjectSB.length();

		if (accountName.length() > maxLength) {
			subjectSB.append(
				accountName.substring(0, maxLength - accountName.length()));
		}
		else {
			subjectSB.append(accountName);
		}

		if (account.getRegion() == Account.Region.UNITED_STATES) {
			sb.append(
				StringBundler.concat(
					"<br /><br />US Provisioning - Working with 2019 H2 ",
					"Pricing Promotions: <a href='https://grow.liferay.com",
					"/people/US+Provisioning+-+Working+with+2019+H2+Pricing",
					"+Promotions'>Grow Link</a>"));
		}

		zendeskTicket.setDescription(sb.toString());

		if (Validator.isNotNull(salesforceOpportunityOwnerEmailAddress)) {
			zendeskTicket.setEmailCCs(
				new String[] {salesforceOpportunityOwnerEmailAddress});
		}

		zendeskTicket.setRequesterId(
			_distributedMessagingConfiguration.
				provisioningZendeskRequesterId());
		zendeskTicket.setSubject(subjectSB.toString());
		zendeskTicket.setZendeskOrganizationId(
			_distributedMessagingConfiguration.
				provisioningZendeskOrganizationId());

		_zendeskTicketWebService.createZendeskTicket(zendeskTicket);
	}

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Parsing message: " + jsonObject.toString());
		}

		if (!hasOpportunityProductFamily(jsonObject)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skipping message because product family is invalid");
			}

			return;
		}

		_productPurchaseExceptionsThreadLocal.set(new ArrayList<>());
		_renewedEWSAAccountNamesThreadLocal.set(new HashSet<>());
		_warningMessagesThreadLocal.set(new ArrayList<>());

		String salesforceOpportunityStageName = jsonObject.getString(
			"_salesforceOpportunityStageName");

		String salesforceOpportunityTypeName = jsonObject.getString(
			"_salesforceOpportunityType");

		int salesforceOpportunityType = getSalesforceOpportunityType(
			salesforceOpportunityTypeName);

		if (!_isValidOpportunity(
				salesforceOpportunityStageName, salesforceOpportunityType)) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skipping message because opportunity is not closed won " +
						"or a renewal that is closed lost");
			}

			return;
		}

		Set<ProductPurchase> productPurchases = parseProductPurchases(
			jsonObject);

		if (productPurchases.isEmpty()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Skipping message because purchases is empty");
			}

			return;
		}

		List<Contact> activeContacts = new ArrayList<>();
		List<Contact> inactiveContacts = new ArrayList<>();
		List<Contact> missingContacts = new ArrayList<>();

		String accountKey = getAccountKey(jsonObject);

		if ((salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS) ||
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS) ||
			(salesforceOpportunityType ==
				SalesforceConstants.
					OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS)) {

			List<Contact> contacts = parseContacts(
				jsonObject, accountKey, salesforceOpportunityType);

			for (Contact contact : contacts) {
				Integer status =
					_contactIdentityProvider.fetchContactStatusByEmailAddress(
						contact.getEmailAddress());

				if (status == null) {
					missingContacts.add(contact);
				}
				else if (status == WorkflowConstants.STATUS_APPROVED) {
					activeContacts.add(contact);
				}
				else {
					inactiveContacts.add(contact);
				}
			}
		}

		boolean analyticsCloud = hasAnalyticsCloud(productPurchases);

		PostalAddress postalAddress = parseAddress(jsonObject);

		Account.Language language = getLanguage(
			jsonObject, postalAddress.getAddressCountry());

		String languageId = _getLanguageId(language);

		String soldBy = jsonObject.getString("_salesforceOpportunitySoldBy");

		Account.Region region = getSupportRegion(
			soldBy, postalAddress.getAddressCountry());

		Account account = null;

		Account partnerAccount = parsePartnerAccount(jsonObject);

		boolean partnerFirstLineSupport = jsonObject.getBoolean(
			"_partnerFirstLineSupport");

		String salesforceOpportunityKey = jsonObject.getString(
			"_salesforceOpportunityKey");

		if (isProvisionMessage(salesforceOpportunityKey, jsonObject)) {
			Account parentAccount = null;

			JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

			if (projectJSONObject != null) {
				parentAccount = createParentAccount(jsonObject);
			}

			if (Validator.isNotNull(accountKey)) {
				account = updateAccount(
					accountKey, parentAccount, activeContacts, region,
					postalAddress, productPurchases, jsonObject);

				List<Exception> productPurchaseExceptions =
					_productPurchaseExceptionsThreadLocal.get();

				if (!productPurchaseExceptions.isEmpty()) {
					handleError(
						"dossiera.provisioning.create", jsonObject.toString(),
						productPurchaseExceptions.toArray(new Exception[0]));
				}
			}
			else {
				ExternalLink[] externalLinks = parseExternalLinks(jsonObject);

				Team[] partnerTeams = parsePartnerTeams(
					partnerAccount, partnerFirstLineSupport);

				account = createAccount(
					parentAccount, activeContacts.toArray(new Contact[0]),
					externalLinks, language, region, postalAddress,
					productPurchases.toArray(new ProductPurchase[0]),
					partnerTeams, jsonObject);
			}

			createAccountNote(jsonObject, account);

			for (Contact contact : missingContacts) {
				sendUserCreationEmail(
					contact, account, analyticsCloud, languageId);
			}
		}
		else {
			account = _accountWebService.fetchAccount(accountKey);

			if (account == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Account is null, skipping zendesk ticket creation");
				}

				return;
			}
		}

		checkWarnings(
			accountKey, account, partnerAccount, analyticsCloud,
			partnerFirstLineSupport, inactiveContacts, missingContacts,
			productPurchases, salesforceOpportunityTypeName,
			salesforceOpportunityType, jsonObject);

		String salesforceOpportunityProductFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (!salesforceOpportunityProductFamily.equals("P")) {
			String salesforceOpportunityOwnerEmailAddress = StringPool.BLANK;

			JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

			if (ownerJSONObject != null) {
				salesforceOpportunityOwnerEmailAddress =
					ownerJSONObject.getString("_emailAddress");
			}

			createZendeskTicket(
				account, postalAddress, productPurchases,
				salesforceOpportunityTypeName, salesforceOpportunityKey,
				salesforceOpportunityOwnerEmailAddress);
		}
	}

	protected Account fetchAccount(String dossieraAccountKey) throws Exception {
		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_ACCOUNT, dossieraAccountKey, 1, 1);

		if (!accounts.isEmpty()) {
			return accounts.get(0);
		}

		return null;
	}

	protected String getAccountKey(JSONObject jsonObject) throws Exception {
		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		if (projectJSONObject != null) {
			String dossieraProjectKey = projectJSONObject.getString(
				"_dossieraProjectKey");

			List<Account> accounts = _accountWebService.getAccounts(
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_PROJECT, dossieraProjectKey, 1,
				1);

			if (!accounts.isEmpty()) {
				Account account = accounts.get(0);

				return account.getKey();
			}
		}
		else {
			JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

			String dossieraAccountKey = accountJSONObject.getString(
				"_dossieraAccountKey");

			Account account = fetchAccount(dossieraAccountKey);

			if (account != null) {
				return account.getKey();
			}
		}

		return null;
	}

	protected String getContactFullName(Contact contact) {
		StringBundler sb = new StringBundler(5);

		if (Validator.isNotNull(contact.getFirstName())) {
			sb.append(contact.getFirstName());
		}

		if (Validator.isNotNull(contact.getMiddleName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getMiddleName());
		}

		if (Validator.isNotNull(contact.getLastName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getLastName());
		}

		return sb.toString();
	}

	protected String getDateRange(ProductPurchase productPurchase) {
		if ((productPurchase.getStartDate() != null) &&
			(productPurchase.getOriginalEndDate() != null)) {

			Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"yyyy/MM/dd");

			StringBundler sb = new StringBundler(4);

			sb.append(dateFormat.format(productPurchase.getStartDate()));
			sb.append(" - ");
			sb.append(dateFormat.format(productPurchase.getOriginalEndDate()));
			sb.append(" (UTC)");

			return sb.toString();
		}

		return "Perpetual";
	}

	protected Account.Language getLanguage(
		JSONObject jsonObject, String country) {

		String soldBy = jsonObject.getString("_salesforceOpportunitySoldBy");

		if (Validator.isNull(soldBy)) {
			_logWarning(
				"Sold by field is empty. Defaulting support language to " +
					"English.");

			return Account.Language.ENGLISH;
		}

		if (soldBy.equals("Liferay Africa") ||
			soldBy.equals("Liferay Australia") ||
			soldBy.equals("Liferay Canada") ||
			soldBy.equals("Liferay France") ||
			soldBy.equals("Liferay Germany") ||
			soldBy.equals("Liferay Hungary") ||
			soldBy.equals("Liferay India") ||
			soldBy.equals("Liferay International") ||
			soldBy.equals("Liferay Italy") ||
			soldBy.equals("Liferay Middle East") ||
			soldBy.equals("Liferay Netherlands") ||
			soldBy.equals("Liferay Nordic") ||
			soldBy.equals("Liferay Singapore") || soldBy.equals("Liferay UK") ||
			soldBy.equals("Liferay US")) {

			return Account.Language.ENGLISH;
		}
		else if (soldBy.equals("Liferay Brazil")) {
			if (Validator.isNotNull(country) && country.equals("Brazil")) {
				return Account.Language.PORTUGUESE;
			}

			return Account.Language.SPANISH;
		}
		else if (soldBy.equals("Liferay China")) {
			if (Validator.isNotNull(country) && country.equals("China")) {
				return Account.Language.CHINESE;
			}

			return Account.Language.ENGLISH;
		}
		else if (soldBy.equals("Liferay Japan")) {
			return Account.Language.JAPANESE;
		}
		else if (soldBy.equals("Liferay Spain")) {
			if (Validator.isNotNull(country) &&
				(country.equals("Cyprus") || country.equals("Greece") ||
				 country.equals("Italy") || country.equals("Portugal"))) {

				return Account.Language.ENGLISH;
			}

			return Account.Language.SPANISH;
		}

		_logWarning(
			StringBundler.concat(
				"Unable to find matching support language for ", soldBy,
				" and ", country, ". Defaulting support language to English."));

		return Account.Language.ENGLISH;
	}

	protected String getNoteContent(JSONObject jsonObject, Account account)
		throws PortalException {

		ProductPurchase[] productPurchases = account.getProductPurchases();

		Map<String, Map<String, Integer>> subscriptionsMap = new TreeMap<>();

		for (ProductPurchase productPurchase : productPurchases) {
			String key = getDateRange(productPurchase);

			Map<String, Integer> productsMap = subscriptionsMap.get(key);

			if (productsMap == null) {
				productsMap = new TreeMap<>();

				subscriptionsMap.put(key, productsMap);
			}

			String productName = getNotesProductName(account, productPurchase);

			int quantity = GetterUtil.getInteger(productsMap.get(productName));

			quantity += productPurchase.getQuantity();

			productsMap.put(productName, quantity);
		}

		StringBundler sb = new StringBundler();

		for (Map.Entry<String, Map<String, Integer>> entry :
				subscriptionsMap.entrySet()) {

			String dateRange = entry.getKey();
			Map<String, Integer> productsMap = entry.getValue();

			sb.append("Subscriptions:");
			sb.append(StringPool.NEW_LINE);

			for (Map.Entry<String, Integer> productsEntry :
					productsMap.entrySet()) {

				sb.append(StringPool.TAB);
				sb.append(productsEntry.getKey());
				sb.append(" (");
				sb.append(productsEntry.getValue());
				sb.append(")");
				sb.append(StringPool.NEW_LINE);
			}

			sb.append("Dates: ");
			sb.append(dateRange);
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.NEW_LINE);
		}

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		sb.append("Owner: ");
		sb.append(ownerJSONObject.getString("_firstName"));
		sb.append(StringPool.SPACE);
		sb.append(ownerJSONObject.getString("_lastName"));
		sb.append(StringPool.NEW_LINE);

		sb.append("SFDC: https://login.salesforce.com/");
		sb.append(jsonObject.getString("_salesforceOpportunityKey"));

		return sb.toString();
	}

	protected String getNotesProductName(
			Account account, ProductPurchase productPurchase)
		throws PortalException {

		Map<String, String> properties = productPurchase.getProperties();

		String productType = properties.get("productType");

		if ((productType != null) &&
			productType.equals(SalesforceConstants.PRODUCT_TYPE_RENEWAL)) {

			return productType;
		}

		StringBundler sb = new StringBundler(8);

		if (Validator.isNotNull(productType)) {
			sb.append(productType);
			sb.append(StringPool.SPACE);
		}

		Product product = productPurchase.getProduct();

		String productName = product.getName();

		if (!ArrayUtil.contains(
				ProductConstants.NAMES_SUBSCRIPTION, productName)) {

			ProductPurchase slaProductPurchase =
				_accountReader.getSLAProductPurchase(account);

			if (slaProductPurchase != null) {
				Product slaProduct = slaProductPurchase.getProduct();

				if (slaProduct != null) {
					sb.append(
						StringUtil.removeSubstring(
							slaProduct.getName(), " Subscription"));

					sb.append(StringPool.SPACE);
				}
			}
		}

		sb.append(productName);

		sb.append(StringPool.SPACE);

		if (!productName.contains("Sizing") &&
			Validator.isNotNull(properties.get("sizing"))) {

			sb.append("Sizing ");
			sb.append(properties.get("sizing"));
		}

		return sb.toString();
	}

	protected PostalAddress getPostalAddress(JSONObject jsonObject) {
		PostalAddress postalAddress = new PostalAddress();

		String city = jsonObject.getString("_city");

		city = ModelHintsUtil.trimString(Address.class.getName(), "city", city);

		postalAddress.setAddressLocality(city);

		String countryName = jsonObject.getString("_country");

		if (Validator.isNotNull(countryName)) {
			postalAddress.setAddressCountry(countryName);

			String regionName = jsonObject.getString("_region");

			postalAddress.setAddressRegion(regionName);
		}

		String street = jsonObject.getString("_street");

		String street1 = street;

		String street2 = StringPool.BLANK;
		String street3 = StringPool.BLANK;

		int maxLength = ModelHintsUtil.getMaxLength(
			Address.class.getName(), "street1");

		if (street1.length() > maxLength) {
			street1 = street1.substring(0, maxLength);

			street2 = street.substring(maxLength);

			if (street2.length() > maxLength) {
				street2 = street2.substring(0, maxLength);

				street3 = street.substring(maxLength * 2);

				if (street3.length() > maxLength) {
					street3 = street3.substring(0, maxLength);
				}
			}
		}

		postalAddress.setStreetAddressLine1(street1);
		postalAddress.setStreetAddressLine2(street2);
		postalAddress.setStreetAddressLine3(street3);

		postalAddress.setPostalCode(jsonObject.getString("_postalCode"));

		return postalAddress;
	}

	protected String getProvisioningEmailAddress(String accountRegion) {
		if (accountRegion.equals(Account.Region.AUSTRALIA.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressAustralia();
		}
		else if (accountRegion.equals(Account.Region.BRAZIL.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressBrazil();
		}
		else if (accountRegion.equals(Account.Region.CHINA.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressChina();
		}
		else if (accountRegion.equals(Account.Region.HUNGARY.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressHungary();
		}
		else if (accountRegion.equals(Account.Region.INDIA.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressIndia();
		}
		else if (accountRegion.equals(Account.Region.JAPAN.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressJapan();
		}
		else if (accountRegion.equals(Account.Region.SPAIN.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressSpain();
		}
		else if (accountRegion.equals(
					Account.Region.UNITED_STATES.toString())) {

			return _distributedMessagingConfiguration.
				provisioningEmailAddressUS();
		}

		return _distributedMessagingConfiguration.
			provisioningEmailAddressGlobal();
	}

	protected ExternalLink getSalesforceOpportunityExternalLink(
		JSONObject jsonObject) {

		String salesforceOpportunityKey = jsonObject.getString(
			"_salesforceOpportunityKey");

		ExternalLink externalLink = null;

		if (Validator.isNotNull(salesforceOpportunityKey)) {
			externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.SALESFORCE);
			externalLink.setEntityName(
				ExternalLinkEntityName.SALESFORCE_OPPORTUNITY);
			externalLink.setEntityId(salesforceOpportunityKey);
		}

		return externalLink;
	}

	protected int getSalesforceOpportunityType(
		String salesforceOpportunityTypeName) {

		if (StringUtil.equalsIgnoreCase(
				salesforceOpportunityTypeName, "Existing Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "New Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "Renewal")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName,
					"New Project Existing Business")) {

			return SalesforceConstants.
				OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS;
		}
		else {
			return 0;
		}
	}

	protected Account.Region getSupportRegion(
		String soldBy, String countryName) {

		if (Validator.isNull(soldBy)) {
			_logWarning(
				"Sold by field is empty. Defaulting support region to global.");

			return Account.Region.GLOBAL;
		}

		if (soldBy.equals("Liferay Africa") ||
			soldBy.equals("Liferay France") ||
			soldBy.equals("Liferay Germany") ||
			soldBy.equals("Liferay Hungary") ||
			soldBy.equals("Liferay International") ||
			soldBy.equals("Liferay Italy") ||
			soldBy.equals("Liferay Middle East") ||
			soldBy.equals("Liferay Netherlands") ||
			soldBy.equals("Liferay Nordic") || soldBy.equals("Liferay UK")) {

			return Account.Region.HUNGARY;
		}
		else if (soldBy.equals("Liferay Australia")) {
			return Account.Region.AUSTRALIA;
		}
		else if (soldBy.equals("Liferay Brazil")) {
			return Account.Region.BRAZIL;
		}
		else if (soldBy.equals("Liferay Canada") ||
				 soldBy.equals("Liferay US")) {

			return Account.Region.UNITED_STATES;
		}
		else if (soldBy.equals("Liferay China") ||
				 soldBy.equals("Liferay Singapore")) {

			return Account.Region.CHINA;
		}
		else if (soldBy.equals("Liferay India")) {
			return Account.Region.INDIA;
		}
		else if (soldBy.equals("Liferay Japan")) {
			return Account.Region.JAPAN;
		}
		else if (soldBy.equals("Liferay Spain")) {
			if (Validator.isNotNull(countryName) &&
				(countryName.equals("Cypress") ||
				 countryName.equals("Greece") || countryName.equals("Italy"))) {

				return Account.Region.HUNGARY;
			}

			return Account.Region.SPAIN;
		}

		_logWarning(
			StringBundler.concat(
				"Unable to find matching support region for ", soldBy, " and ",
				countryName, ". Defaulting support region to global."));

		return Account.Region.GLOBAL;
	}

	@Override
	protected void handleError(
			String routingKey, String message, Exception[] exceptions)
		throws PortalException {

		ZendeskTicket zendeskTicket = new ZendeskTicket();

		Map<Long, String> customFields = new HashMap<>();

		customFields.put(
			_distributedMessagingConfiguration.zendeskCustomFieldProductId(),
			"Provisioning Request");

		zendeskTicket.setCustomFields(customFields);

		StringBundler sb = new StringBundler(6 + exceptions.length);

		sb.append("An unexpected error occurred.<br />Routing Key: ");
		sb.append(routingKey);
		sb.append("<br />Message:<br /><pre>");
		sb.append(message);
		sb.append("</pre><br />Error:<br /><pre>");

		for (Exception exception : exceptions) {
			sb.append(StackTraceUtil.getStackTrace(exception));
		}

		sb.append("</pre>");

		_log.error("Creating error Zendesk ticket: " + sb.toString());

		zendeskTicket.setDescription(sb.toString());

		zendeskTicket.setGroupId(
			_distributedMessagingConfiguration.provisioningZendeskGroupId());
		zendeskTicket.setRequesterId(
			_distributedMessagingConfiguration.
				provisioningZendeskRequesterId());
		zendeskTicket.setSubject("Auto-Provisioning Error");
		zendeskTicket.setZendeskOrganizationId(
			_distributedMessagingConfiguration.
				provisioningZendeskOrganizationId());

		zendeskTicketWebService.createZendeskTicket(zendeskTicket);
	}

	protected boolean hasAnalyticsCloud(Set<ProductPurchase> productPurchases) {
		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			String name = product.getName();

			if (name.equals(ProductConstants.NAME_ANALYTICS_CLOUD_BUSINESS) ||
				name.equals(ProductConstants.NAME_ANALYTICS_CLOUD_ENTERPRISE)) {

				return true;
			}
		}

		return false;
	}

	protected boolean hasOpportunityProductFamily(JSONObject jsonObject) {
		String salesforceOpportunityProductFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (Validator.isNull(salesforceOpportunityProductFamily)) {
			return false;
		}

		for (String productFamilyToken : _PRODUCT_FAMILY_TOKENS) {
			if (salesforceOpportunityProductFamily.contains(
					productFamilyToken)) {

				return true;
			}
		}

		return false;
	}

	protected boolean isParseMessage(Message message) {
		String salesforceOpportunityKey = _getSalesforceOpportunityKey(message);

		if (Validator.isNull(salesforceOpportunityKey)) {
			return false;
		}

		String owner = null;

		ClusterNode localClusterNode =
			ClusterExecutorUtil.getLocalClusterNode();

		if (localClusterNode != null) {
			owner = localClusterNode.getClusterNodeId();
		}

		Lock lock = _lockLocalService.lock(
			Message.class.getName(), salesforceOpportunityKey, owner);

		if (!lock.isNew()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Message is locked by another cluster node");
			}

			return false;
		}

		return true;
	}

	protected boolean isProvisionMessage(
			String salesforceOpportunityKey, JSONObject jsonObject)
		throws Exception {

		if (Validator.isNull(salesforceOpportunityKey)) {
			return false;
		}

		Account account = null;

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		if (projectJSONObject != null) {
			List<Account> accounts = _accountWebService.getAccounts(
				ExternalLinkDomain.SALESFORCE,
				ExternalLinkEntityName.SALESFORCE_PROJECT,
				projectJSONObject.getString("_salesforceProjectKey"), 1, 1);

			if (!accounts.isEmpty()) {
				account = accounts.get(0);
			}
		}
		else {
			JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

			List<Account> accounts = _accountWebService.getAccounts(
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_ACCOUNT,
				accountJSONObject.getString("_dossieraAccountKey"), 1, 1);

			if (!accounts.isEmpty()) {
				account = accounts.get(0);
			}
		}

		if (account == null) {
			return true;
		}

		FilterQuery filterQuery = new FilterQuery();

		StringBundler sb = new StringBundler(5);

		sb.append(ExternalLinkDomain.SALESFORCE);
		sb.append(StringPool.UNDERLINE);
		sb.append(ExternalLinkEntityName.SALESFORCE_OPPORTUNITY);
		sb.append(StringPool.UNDERLINE);
		sb.append(salesforceOpportunityKey);

		filterQuery.addLambdaEquals(
			true, "externalLinkEntityIds", sb.toString());

		filterQuery.addEquals(true, "accountKey", account.getKey());

		long productPurchaseCount = _productPurchaseWebService.searchCount(
			filterQuery);

		if (productPurchaseCount > 0) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Product purchase already exists with opportunity key ",
						salesforceOpportunityKey, " and account key ",
						account.getKey()));
			}

			_logWarning(
				"The opportunity was not provisioned automatically since " +
					"subscriptions with this opportunity key and account key " +
						"already exist in the system.");

			return false;
		}

		return true;
	}

	protected PostalAddress parseAddress(JSONObject jsonObject) {
		JSONObject billingAddressJSONObject = jsonObject.getJSONObject(
			"_billingAddress");
		JSONObject shippingAddressJSONObject = jsonObject.getJSONObject(
			"_shippingAddress");

		PostalAddress postalAddress = null;

		if (shippingAddressJSONObject != null) {
			postalAddress = getPostalAddress(shippingAddressJSONObject);
		}
		else if (billingAddressJSONObject != null) {
			postalAddress = getPostalAddress(billingAddressJSONObject);
		}
		else {
			postalAddress = new PostalAddress();
		}

		if (Validator.isNull(postalAddress.getAddressLocality())) {
			postalAddress.setAddressLocality("N/A");
		}

		if (Validator.isNull(postalAddress.getStreetAddressLine1())) {
			postalAddress.setStreetAddressLine1("N/A");
		}

		if (Validator.isNull(postalAddress.getPostalCode())) {
			postalAddress.setPostalCode("N/A");
		}

		postalAddress.setPrimary(true);

		return postalAddress;
	}

	protected List<Contact> parseContacts(
			JSONObject jsonObject, String accountKey,
			int salesforceOpportunityType)
		throws Exception {

		List<Contact> contacts = new ArrayList<>();

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		if (ownerJSONObject != null) {
			Contact contact = new Contact();

			contact.setFirstName(ownerJSONObject.getString("_firstName"));
			contact.setLastName(ownerJSONObject.getString("_lastName"));

			String ownerEmailAddress = ownerJSONObject.getString(
				"_emailAddress");

			contact.setEmailAddress(ownerEmailAddress);

			ContactRole salesContactRole =
				_contactRoleWebService.fetchContactRole(
					ContactRole.Type.ACCOUNT_WORKER.toString(),
					ContactRoleConstants.NAME_LIFERAY_SALES);

			ContactRole secondaryContactRole =
				_contactRoleWebService.fetchContactRole(
					ContactRole.Type.ACCOUNT_WORKER.toString(),
					ContactRoleConstants.NAME_SECONDARY_CONTACT);

			if (Validator.isNotNull(accountKey)) {
				FilterQuery filterQuery = new FilterQuery();

				filterQuery.addLambdaEquals(
					true, "accountKeysContactRoleKeys",
					accountKey + "_" + secondaryContactRole.getKey());

				List<Contact> secondaryContacts = _contactWebService.search(
					StringPool.BLANK, filterQuery, 1, 1, StringPool.BLANK);

				if (!secondaryContacts.isEmpty()) {
					Contact secondaryContact = secondaryContacts.get(0);

					if (!ownerEmailAddress.equals(
							secondaryContact.getEmailAddress())) {

						_accountWebService.unassignContactRolesByEmailAddress(
							StringPool.BLANK, StringPool.BLANK, accountKey,
							secondaryContact.getEmailAddress(),
							new String[] {secondaryContactRole.getKey()});
					}
				}
			}

			contact.setContactRoles(
				new ContactRole[] {salesContactRole, secondaryContactRole});

			contacts.add(contact);
		}

		if (salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS) {

			return contacts;
		}

		JSONArray contactsJSONArray = jsonObject.getJSONArray("_contacts");

		if (contactsJSONArray == null) {
			return contacts;
		}

		for (int i = 0; i < contactsJSONArray.length(); i++) {
			JSONObject contactJSONObject = contactsJSONArray.getJSONObject(i);

			Contact contact = new Contact();

			contact.setFirstName(contactJSONObject.getString("_firstName"));
			contact.setLastName(contactJSONObject.getString("_lastName"));
			contact.setEmailAddress(
				contactJSONObject.getString("_emailAddress"));

			String contactRoleName = null;

			if (_distributedMessagingConfiguration.customerPortal2Enabled()) {
				contactRoleName =
					ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR;
			}
			else {
				contactRoleName = ContactRoleConstants.NAME_SUPPORT_USER;
			}

			ContactRole contactRole = _contactRoleWebService.fetchContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(), contactRoleName);

			contact.setContactRoles(new ContactRole[] {contactRole});

			contacts.add(contact);
		}

		return contacts;
	}

	protected ExternalLink[] parseExternalLinks(JSONObject jsonObject) {
		String salesforceAccountKey = jsonObject.getString(
			"_salesforceAccountKey");

		ExternalLink accountExternalLink = new ExternalLink();

		accountExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
		accountExternalLink.setEntityName(
			ExternalLinkEntityName.SALESFORCE_ACCOUNT);
		accountExternalLink.setEntityId(salesforceAccountKey);

		ExternalLink dossieraExternalLink = new ExternalLink();

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		if (projectJSONObject != null) {
			String dossieraProjectKey = projectJSONObject.getString(
				"_dossieraProjectKey");

			dossieraExternalLink.setDomain(ExternalLinkDomain.DOSSIERA);
			dossieraExternalLink.setEntityName(
				ExternalLinkEntityName.DOSSIERA_PROJECT);
			dossieraExternalLink.setEntityId(dossieraProjectKey);

			String salesforceProjectKey = projectJSONObject.getString(
				"_salesforceProjectKey");

			ExternalLink projectExternalLink = new ExternalLink();

			projectExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
			projectExternalLink.setEntityName(
				ExternalLinkEntityName.SALESFORCE_PROJECT);
			projectExternalLink.setEntityId(salesforceProjectKey);

			return new ExternalLink[] {
				accountExternalLink, dossieraExternalLink, projectExternalLink
			};
		}

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		String dossieraAccountKey = accountJSONObject.getString(
			"_dossieraAccountKey");

		dossieraExternalLink.setDomain(ExternalLinkDomain.DOSSIERA);
		dossieraExternalLink.setEntityName(
			ExternalLinkEntityName.DOSSIERA_ACCOUNT);
		dossieraExternalLink.setEntityId(dossieraAccountKey);

		return new ExternalLink[] {accountExternalLink, dossieraExternalLink};
	}

	protected Account parsePartnerAccount(JSONObject jsonObject)
		throws Exception {

		JSONObject partnerAccountJSONObject = jsonObject.getJSONObject(
			"_partnerAccount");

		if (partnerAccountJSONObject == null) {
			return null;
		}

		String dossieraAccountKey = partnerAccountJSONObject.getString(
			"_dossieraAccountKey");

		if (Validator.isNull(dossieraAccountKey)) {
			return null;
		}

		return fetchAccount(dossieraAccountKey);
	}

	protected Team[] parsePartnerTeams(
			Account partnerAccount, boolean partnerFirstLineSupport)
		throws Exception {

		TeamRole partnerTeamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(), TeamRoleConstants.NAME_PARTNER);

		if ((partnerAccount == null) || (partnerTeamRole == null)) {
			return new Team[0];
		}

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", partnerAccount.getKey());
		filterQuery.addEquals(true, "system", true);

		List<Team> partnerDefaultTeams = _teamWebService.search(
			StringPool.BLANK, filterQuery, 1, 1, StringPool.BLANK);

		if (partnerDefaultTeams.isEmpty()) {
			return new Team[0];
		}

		Team partnerDefaultTeam = partnerDefaultTeams.get(0);

		partnerDefaultTeam.setTeamRoles(new TeamRole[] {partnerTeamRole});

		TeamRole flsTeamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(),
			TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

		if ((flsTeamRole == null) || !partnerFirstLineSupport) {
			return new Team[] {partnerDefaultTeam};
		}

		FilterQuery filterQuery2 = new FilterQuery();

		filterQuery2.addEquals(true, "accountKey", partnerAccount.getKey());
		filterQuery2.addContains(true, "name", "FLS");

		List<Team> partnerFLSTeams = _teamWebService.search(
			StringPool.BLANK, filterQuery2, 1, 1, StringPool.BLANK);

		if (!partnerFLSTeams.isEmpty()) {
			Team partnerFLSTeam = partnerFLSTeams.get(0);

			partnerFLSTeam.setTeamRoles(new TeamRole[] {flsTeamRole});

			return new Team[] {partnerDefaultTeam, partnerFLSTeam};
		}

		partnerDefaultTeam.setTeamRoles(
			new TeamRole[] {partnerTeamRole, flsTeamRole});

		return new Team[] {partnerDefaultTeam};
	}

	protected Set<ProductPurchase> parseProductPurchases(JSONObject jsonObject)
		throws Exception {

		JSONArray bundledProductsJSONArray = jsonObject.getJSONArray(
			"_bundledProducts");

		if (bundledProductsJSONArray == null) {
			return Collections.emptySet();
		}

		Map<ProductPurchase, Integer> productPurchasesMap = new HashMap<>();

		ExternalLink externalLink = getSalesforceOpportunityExternalLink(
			jsonObject);

		for (int i = 0; i < bundledProductsJSONArray.length(); i++) {
			JSONObject bundledProductJSONObject =
				bundledProductsJSONArray.getJSONObject(i);

			JSONArray purchasedProductsJSONArray =
				bundledProductJSONObject.getJSONArray("_purchasedProducts");

			for (int j = 0; j < purchasedProductsJSONArray.length(); j++) {
				JSONObject purchasedProductJSONObject =
					purchasedProductsJSONArray.getJSONObject(j);

				String productName = purchasedProductJSONObject.getString(
					"_name");

				if (ArrayUtil.contains(_IGNORE_PRODUCT_NAMES, productName)) {
					continue;
				}

				Product product = _getProduct(productName);

				if (product == null) {
					continue;
				}

				ProductPurchase productPurchase = new ProductPurchase();

				Date startDate = _portal.getDate(
					purchasedProductJSONObject.getInt("_startMonth") - 1,
					purchasedProductJSONObject.getInt("_startDay"),
					purchasedProductJSONObject.getInt("_startYear"));

				Date originalEndDate = _portal.getDate(
					purchasedProductJSONObject.getInt("_endMonth") - 1,
					purchasedProductJSONObject.getInt("_endDay"),
					purchasedProductJSONObject.getInt("_endYear"));

				if ((startDate != null) && (originalEndDate != null)) {
					productPurchase.setStartDate(startDate);

					productPurchase.setOriginalEndDate(originalEndDate);

					Calendar calendar = Calendar.getInstance();

					calendar.setTime(originalEndDate);

					calendar.add(Calendar.DATE, 30);

					productPurchase.setEndDate(calendar.getTime());
				}
				else {
					productPurchase.setPerpetual(true);
				}

				productPurchase.setProduct(product);

				Map<String, String> properties = new HashMap<>();

				String environment = purchasedProductJSONObject.getString(
					"_environment");

				if (Validator.isNotNull(environment)) {
					properties.put("environment", environment);
				}

				String productType = purchasedProductJSONObject.getString(
					"_productType");

				if (Validator.isNotNull(productType)) {
					properties.put("productType", productType);
				}

				String sizing = purchasedProductJSONObject.getString("_sizing");

				if (Validator.isNotNull(sizing) &&
					sizing.startsWith("Sizing ")) {

					String sizingValue = StringUtil.extractDigits(
						sizing.substring(7));

					properties.put("sizing", sizingValue);
				}

				if (!properties.isEmpty()) {
					productPurchase.setProperties(properties);
				}

				if (externalLink != null) {
					productPurchase.setExternalLinks(
						new ExternalLink[] {externalLink});
				}

				int quantity = GetterUtil.getInteger(
					productPurchasesMap.get(productPurchase));

				if (ArrayUtil.contains(
						ProductConstants.NAMES_SUBSCRIPTION,
						product.getName())) {

					quantity = 1;
				}
				else {
					quantity += purchasedProductJSONObject.getInt("_quantity");
				}

				productPurchasesMap.put(productPurchase, quantity);
			}
		}

		Set<ProductPurchase> productPurchases = new HashSet<>();

		for (Map.Entry<ProductPurchase, Integer> entry :
				productPurchasesMap.entrySet()) {

			ProductPurchase productPurchase = entry.getKey();

			productPurchase.setQuantity(entry.getValue());

			productPurchases.add(productPurchase);
		}

		return productPurchases;
	}

	@Override
	protected void postParseMessage(Message message) {
		String salesforceOpportunityKey = _getSalesforceOpportunityKey(message);

		if (Validator.isNull(salesforceOpportunityKey)) {
			return;
		}

		_lockLocalService.unlock(
			Message.class.getName(), salesforceOpportunityKey);
	}

	protected void sendUserCreationEmail(
		Contact contact, Account account, boolean analyticsCloud,
		String languageId) {

		String body = _getEmailTemplate(
			"email_provisioning_create_account_body_" + languageId + ".tmpl",
			"email_provisioning_create_account_body.tmpl");

		String provisioningEmailAddress = getProvisioningEmailAddress(
			account.getRegionAsString());

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		StringBundler sb = new StringBundler(2);

		if (analyticsCloud) {
			sb.append("analytics-cloud-");
		}

		sb.append("help-center-all-of-our-downloads-and-our-support-system");

		String subscriptionServices = LanguageUtil.get(
			resourceBundle, sb.toString());
		String subject = LanguageUtil.get(
			resourceBundle, "welcome-to-liferay-support");

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(_portal.getDefaultCompanyId());
		subscriptionSender.setContextAttributes(
			"[$ACCOUNT_ENTRY_NAME$]", account.getName(),
			"[$SUBSCRIPTION_SERVICES$]", subscriptionServices, "[$TO_NAME$]",
			getContactFullName(contact));
		subscriptionSender.setFrom(
			provisioningEmailAddress, "Liferay Provisioning");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("provisioning");
		subscriptionSender.setReplyToAddress(provisioningEmailAddress);
		subscriptionSender.setSubject(subject);

		subscriptionSender.addRuntimeSubscribers(
			contact.getEmailAddress(), getContactFullName(contact));
		subscriptionSender.addRuntimeSubscribers(
			provisioningEmailAddress, getContactFullName(contact));

		subscriptionSender.flushNotificationsAsync();
	}

	protected Account updateAccount(
			String accountKey, Account parentAccount, List<Contact> contacts,
			Account.Region region, PostalAddress postalAddress,
			Set<ProductPurchase> productPurchases, JSONObject jsonObject)
		throws Exception {

		Account account = _accountWebService.getAccount(accountKey);

		if (Validator.isNull(account.getContactEmailAddress()) ||
			Validator.isNull(account.getRegion()) || (parentAccount != null)) {

			if (parentAccount != null) {
				account.setParentAccountKey(parentAccount.getKey());
			}

			if (Validator.isNull(account.getContactEmailAddress())) {
				JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

				if (ownerJSONObject != null) {
					account.setContactEmailAddress(
						ownerJSONObject.getString("_emailAddress"));
				}
			}

			if (Validator.isNull(account.getRegion())) {
				account.setRegion(region);
			}

			_accountWebService.updateAccount(
				StringPool.BLANK, StringPool.BLANK, accountKey, account);
		}

		PostalAddress primaryPostalAddress = null;

		PostalAddress[] postalAddresses = account.getPostalAddresses();

		for (PostalAddress curPostalAddress : postalAddresses) {
			if (curPostalAddress.getPrimary()) {
				primaryPostalAddress = curPostalAddress;

				break;
			}
		}

		if (primaryPostalAddress != null) {
			_postalAddressWebService.updatePostalAddress(
				StringPool.BLANK, StringPool.BLANK,
				primaryPostalAddress.getId(), postalAddress);
		}
		else {
			_postalAddressWebService.addPostalAddress(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				postalAddress);
		}

		for (Contact contact : contacts) {
			ContactRole[] contactRoles = contact.getContactRoles();

			String[] contactRoleKeys = new String[contactRoles.length];

			for (int i = 0; i < contactRoles.length; i++) {
				ContactRole contactRole = contactRoles[i];

				contactRoleKeys[i] = contactRole.getKey();
			}

			_accountWebService.assignContactRolesByEmailAddress(
				StringPool.BLANK, StringPool.BLANK, accountKey,
				contact.getEmailAddress(), contactRoleKeys);
		}

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", accountKey);
		filterQuery.addEquals(true, "state", "Active");

		List<ProductPurchase> prevActiveProductPurchases =
			_productPurchaseWebService.search(
				filterQuery, 1, 1000, StringPool.BLANK);

		Date newStartDate = new Date();

		ProductPurchase ewsaProductPurchase = null;

		boolean renewal = false;

		for (ProductPurchase productPurchase : productPurchases) {
			Map<String, String> properties = productPurchase.getProperties();

			if (!renewal &&
				ArrayUtil.contains(
					SalesforceConstants.PRODUCT_TYPES_RENEWAL,
					properties.get("productType"))) {

				renewal = true;
			}

			if (renewal) {
				Product product = productPurchase.getProduct();

				if (ewsaProductPurchase == null) {
					String name = product.getName();

					if (name.equals(ProductConstants.NAME_DXP_EWSA) ||
						name.equals(ProductConstants.NAME_PORTAL_EWSA)) {

						ewsaProductPurchase = productPurchase;
					}
				}

				if (ArrayUtil.contains(
						ProductConstants.NAMES_PARTNERSHIP,
						product.getName()) ||
					ArrayUtil.contains(
						ProductConstants.NAMES_SUBSCRIPTION,
						product.getName())) {

					newStartDate = productPurchase.getStartDate();
				}
			}

			try {
				_productPurchaseWebService.addProductPurchase(
					StringPool.BLANK, StringPool.BLANK, accountKey,
					productPurchase);
			}
			catch (Exception exception) {
				_handleProductPurchaseError(productPurchase, exception);
			}
		}

		if (renewal) {
			FilterQuery filterQuery2 = new FilterQuery();

			filterQuery2.addEquals(
				true, "parentAccountKey", account.getParentAccountKey());

			List<Account> siblingAccounts = _accountWebService.search(
				StringPool.BLANK, filterQuery2, 1, 1000, null);

			for (Account siblingAccount : siblingAccounts) {
				FilterQuery filterQuery3 = new FilterQuery();

				filterQuery3.addEquals(
					true, "accountKey", siblingAccount.getKey());
				filterQuery3.addEquals(true, "state", "Active");

				List<ProductPurchase> activeProductPurchases =
					_productPurchaseWebService.search(
						filterQuery3, 1, 1000, StringPool.BLANK);

				for (ProductPurchase productPurchase : activeProductPurchases) {
					if ((ewsaProductPurchase != null) &&
						(!accountKey.equals(siblingAccount.getKey()) ||
						 !_containsProduct(
							 productPurchases,
							 productPurchase.getProductKey()))) {

						addEWSAProductPurchase(
							ewsaProductPurchase, productPurchase, jsonObject,
							siblingAccount.getName());

						newStartDate = ewsaProductPurchase.getStartDate();
					}

					if ((((ewsaProductPurchase != null) &&
						  !accountKey.equals(siblingAccount.getKey())) ||
						 prevActiveProductPurchases.contains(
							 productPurchase)) &&
						newStartDate.before(productPurchase.getEndDate())) {

						if (newStartDate.before(
								productPurchase.getOriginalEndDate())) {

							productPurchase.setEndDate(
								productPurchase.getOriginalEndDate());
						}
						else {
							productPurchase.setEndDate(newStartDate);
						}

						try {
							_productPurchaseWebService.updateProductPurchase(
								StringPool.BLANK, StringPool.BLANK,
								productPurchase.getKey(), productPurchase);
						}
						catch (Exception exception) {
							_handleProductPurchaseError(
								productPurchase, exception);
						}
					}
				}

				if ((ewsaProductPurchase != null) &&
					prevActiveProductPurchases.isEmpty()) {

					FilterQuery filterQuery4 = new FilterQuery();

					filterQuery4.addEquals(
						true, "accountKey", siblingAccount.getKey());
					filterQuery4.addEquals(true, "state", "Expired");

					List<ProductPurchaseView> expiredProductPurchaseViews =
						_productPurchaseViewWebService.search(
							StringPool.BLANK, filterQuery4, 1, 1000,
							StringPool.BLANK);

					for (ProductPurchaseView expiredProductPurchaseView :
							expiredProductPurchaseViews) {

						Product product =
							expiredProductPurchaseView.getProduct();

						if (accountKey.equals(siblingAccount.getKey()) &&
							_containsProduct(
								productPurchases, product.getKey())) {

							continue;
						}

						ProductPurchase[] expiredProductPurchases =
							expiredProductPurchaseView.getProductPurchases();

						if (ArrayUtil.isEmpty(expiredProductPurchases)) {
							continue;
						}

						Date latestEndDate = null;
						ProductPurchase latestProductPurchase = null;

						for (ProductPurchase productPurchase :
								expiredProductPurchases) {

							if ((latestEndDate == null) ||
								latestEndDate.after(
									productPurchase.getEndDate())) {

								latestEndDate = productPurchase.getEndDate();
								latestProductPurchase = productPurchase;
							}
						}

						addEWSAProductPurchase(
							ewsaProductPurchase, latestProductPurchase,
							jsonObject, siblingAccount.getName());
					}
				}
			}
		}

		return _accountWebService.getAccount(accountKey);
	}

	private static boolean _containsProduct(
		Set<ProductPurchase> productPurchases, String productKey) {

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			if ((product != null) && productKey.equals(product.getKey())) {
				return true;
			}
		}

		return false;
	}

	private static String _getEmailTemplate(
		String templateName, String defaultTemplateName) {

		ClassLoader classLoader =
			DossieraCreateMessageSubscriber.class.getClassLoader();

		String templateDirName =
			"com/liferay/osb/provisioning/distributed/messaging/internal" +
				"/dependencies/";

		URL url = classLoader.getResource(templateDirName + templateName);

		if (url != null) {
			return ContentUtil.get(
				DossieraCreateMessageSubscriber.class.getClassLoader(),
				templateDirName + templateName);
		}

		return ContentUtil.get(
			DossieraCreateMessageSubscriber.class.getClassLoader(),
			templateDirName + defaultTemplateName);
	}

	private String _getCode(String parentAccountName, String accountName)
		throws Exception {

		String code = StringUtil.extractChars(parentAccountName);

		if (code.length() > 6) {
			code = code.substring(0, 6);
		}

		if (accountName != null) {
			code += StringUtil.extractChars(accountName);
		}

		if (code.length() > 12) {
			code = code.substring(0, 12);
		}

		code = StringUtil.toUpperCase(code);

		if (!_isDuplicateCode(code)) {
			return code;
		}

		int i = 1;

		while (true) {
			String tempCode = code + i;

			if (!_isDuplicateCode(tempCode)) {
				return tempCode;
			}

			i++;
		}
	}

	private String _getLanguageId(Account.Language accountLanguage) {
		String language = accountLanguage.toString();

		if (language.equals(Account.Language.CHINESE.toString())) {
			return "zh_CN";
		}
		else if (language.equals(Account.Language.ENGLISH.toString())) {
			return "en_US";
		}
		else if (language.equals(Account.Language.JAPANESE.toString())) {
			return "ja_JP";
		}
		else if (language.equals(Account.Language.PORTUGUESE.toString())) {
			return "pt_BR";
		}
		else if (language.equals(Account.Language.SPANISH.toString())) {
			return "es_ES";
		}
		else {
			return StringPool.BLANK;
		}
	}

	private Product _getProduct(String productName) throws Exception {
		List<Product> products = _productWebService.getProducts(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_PRODUCT, productName, 1, 1);

		if (!products.isEmpty()) {
			return products.get(0);
		}

		return null;
	}

	private String _getSalesforceOpportunityKey(Message message) {
		try {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				(String)message.getPayload());

			return jsonObject.getString("_salesforceOpportunityKey");
		}
		catch (JSONException jsonException1) {
			try {
				JSONArray jsonArray = jsonFactory.createJSONArray(
					(String)message.getPayload());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					return jsonObject.getString("_salesforceOpportunityKey");
				}
			}
			catch (JSONException jsonException2) {
			}
		}

		return StringPool.BLANK;
	}

	private void _handleProductPurchaseError(
			ProductPurchase productPurchase, Exception exception)
		throws PortalException {

		Product product = productPurchase.getProduct();

		_logWarning(
			"Failed to add or update product purchase for " +
				product.getName());

		List<Exception> exceptions =
			_productPurchaseExceptionsThreadLocal.get();

		exceptions.add(exception);
	}

	private boolean _isDuplicateCode(String code) throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "code", code);

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 1, 1, null);

		if (!accounts.isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean _isValidOpportunity(
		String salesforceOpportunityStageName, int salesforceOpportunityType) {

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_LOST) &&
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_WON) &&
			(salesforceOpportunityType !=
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		return false;
	}

	private void _logWarning(String s) {
		List<String> warningMessages = _warningMessagesThreadLocal.get();

		warningMessages.add(s);
	}

	private static final String[] _IGNORE_PRODUCT_NAMES = {
		"Management Tools (LCS)"
	};

	private static final String[] _PRODUCT_FAMILY_TOKENS = {"E", "P", "S"};

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraCreateMessageSubscriber.class);

	private static final ThreadLocal<List<Exception>>
		_productPurchaseExceptionsThreadLocal = new CentralizedThreadLocal<>(
			DossieraCreateMessageSubscriber.class +
				"._productPurchaseExceptionsThreadLocal");
	private static final ThreadLocal<Set<String>>
		_renewedEWSAAccountNamesThreadLocal = new CentralizedThreadLocal<>(
			DossieraCreateMessageSubscriber.class +
				"._renewedAccountNamesThreadLocal");
	private static final ThreadLocal<ArrayList<String>>
		_warningMessagesThreadLocal = new CentralizedThreadLocal<>(
			DossieraCreateMessageSubscriber.class +
				"._warningMessagesThreadLocal");

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	private volatile DistributedMessagingConfiguration
		_distributedMessagingConfiguration;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LockLocalService _lockLocalService;

	@Reference
	private NoteWebService _noteWebService;

	@Reference
	private Portal _portal;

	@Reference
	private PostalAddressWebService _postalAddressWebService;

	@Reference
	private ProductConsumptionWebService _productConsumptionWebService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}