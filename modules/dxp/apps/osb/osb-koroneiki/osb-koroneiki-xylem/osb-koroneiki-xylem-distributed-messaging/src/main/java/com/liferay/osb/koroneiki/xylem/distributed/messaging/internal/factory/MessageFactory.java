/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.factory;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.AccountUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.ContactRoleUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.ContactUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.EntitlementUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.ProductConsumptionUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.ProductPurchaseUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.ProductUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.TeamRoleUtil;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util.TeamUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = MessageFactory.class)
public class MessageFactory {

	public Message create(Account account) throws Exception {
		JSONObject jsonObject = JSONUtil.put("account", toJSONObject(account));

		return new Message(jsonObject.toString());
	}

	public Message create(
			Account account, Contact contact, ContactRole contactRole)
		throws Exception {

		JSONObject jsonObject = JSONUtil.put(
			"account", toJSONObject(account)
		).put(
			"contact", toJSONObject(contact)
		).put(
			"contactRole", toJSONObject(contactRole)
		);

		return new Message(jsonObject.toString());
	}

	public Message create(Account account, Team team, TeamRole teamRole)
		throws Exception {

		JSONObject jsonObject = JSONUtil.put(
			"account", toJSONObject(account)
		).put(
			"team", toJSONObject(team)
		).put(
			"teamRole", toJSONObject(teamRole)
		);

		return new Message(jsonObject.toString());
	}

	public Message create(Contact contact) throws Exception {
		JSONObject jsonObject = JSONUtil.put("contact", toJSONObject(contact));

		return new Message(jsonObject.toString());
	}

	public Message create(ContactRole contactRole) throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"contactRole", toJSONObject(contactRole));

		return new Message(jsonObject.toString());
	}

	public Message create(Entitlement entitlement, Account account)
		throws Exception {

		JSONObject jsonObject = JSONUtil.put(
			"account", toJSONObject(account)
		).put(
			"entitlement", toJSONObject(entitlement)
		);

		return new Message(jsonObject.toString());
	}

	public Message create(Entitlement entitlement, Contact contact)
		throws Exception {

		JSONObject jsonObject = JSONUtil.put(
			"contact", toJSONObject(contact)
		).put(
			"entitlement", toJSONObject(entitlement)
		);

		return new Message(jsonObject.toString());
	}

	public Message create(ProductConsumption productConsumption)
		throws Exception {

		JSONObject jsonObject = JSONUtil.put(
			"productConsumption", toJSONObject(productConsumption));

		return new Message(jsonObject.toString());
	}

	public Message create(ProductEntry productEntry) throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"product", toJSONObject(productEntry));

		return new Message(jsonObject.toString());
	}

	public Message create(ProductPurchase productPurchase) throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"productPurchase", toJSONObject(productPurchase));

		return new Message(jsonObject.toString());
	}

	public Message create(Team team) throws Exception {
		JSONObject jsonObject = JSONUtil.put("team", toJSONObject(team));

		return new Message(jsonObject.toString());
	}

	public Message create(Team team, Contact contact, ContactRole contactRole)
		throws Exception {

		JSONObject jsonObject = JSONUtil.put(
			"contact", toJSONObject(contact)
		).put(
			"contactRole", toJSONObject(contactRole)
		).put(
			"team", toJSONObject(team)
		);

		return new Message(jsonObject.toString());
	}

	public Message create(TeamRole teamRole) throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"teamRole", toJSONObject(teamRole));

		return new Message(jsonObject.toString());
	}

	protected JSONObject toJSONObject(Account account) throws Exception {
		Account currentAccount = _accountLocalService.fetchAccount(
			account.getAccountId());

		if (currentAccount != null) {
			account = currentAccount;
		}

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account
			dtoAccount = AccountUtil.toAccount(account);

		return _jsonFactory.createJSONObject(dtoAccount.toString());
	}

	protected JSONObject toJSONObject(Contact contact) throws Exception {
		Contact currentContact = _contactLocalService.fetchContact(
			contact.getContactId());

		if (currentContact != null) {
			contact = currentContact;
		}

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact
			dtoContact = ContactUtil.toContact(contact);

		return _jsonFactory.createJSONObject(dtoContact.toString());
	}

	protected JSONObject toJSONObject(ContactRole contactRole)
		throws Exception {

		ContactRole currentContactRole =
			_contactRoleLocalService.fetchContactRole(
				contactRole.getContactRoleId());

		if (currentContactRole != null) {
			contactRole = currentContactRole;
		}

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole
			dtoContactRole = ContactRoleUtil.toContactRole(contactRole);

		return _jsonFactory.createJSONObject(dtoContactRole.toString());
	}

	protected JSONObject toJSONObject(Entitlement entitlement)
		throws Exception {

		Entitlement currentEntitlement =
			_entitlementLocalService.fetchEntitlement(
				entitlement.getEntitlementId());

		if (currentEntitlement != null) {
			entitlement = currentEntitlement;
		}

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement
			dtoEntitlement = EntitlementUtil.toEntitlement(entitlement);

		return _jsonFactory.createJSONObject(dtoEntitlement.toString());
	}

	protected JSONObject toJSONObject(ProductConsumption productConsumption)
		throws Exception {

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption
			dtoProductConsumption = ProductConsumptionUtil.toProductConsumption(
				productConsumption);

		return _jsonFactory.createJSONObject(dtoProductConsumption.toString());
	}

	protected JSONObject toJSONObject(ProductEntry productEntry)
		throws Exception {

		Product dtoProduct = ProductUtil.toProduct(productEntry);

		return _jsonFactory.createJSONObject(dtoProduct.toString());
	}

	protected JSONObject toJSONObject(ProductPurchase productPurchase)
		throws Exception {

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase
			dtoProductPurchase = ProductPurchaseUtil.toProductPurchase(
				productPurchase);

		return _jsonFactory.createJSONObject(dtoProductPurchase.toString());
	}

	protected JSONObject toJSONObject(Team team) throws Exception {
		Team currentTeam = _teamLocalService.fetchTeam(team.getTeamId());

		if (currentTeam != null) {
			team = currentTeam;
		}

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team dtoTeam =
			TeamUtil.toTeam(team);

		return _jsonFactory.createJSONObject(dtoTeam.toString());
	}

	protected JSONObject toJSONObject(TeamRole teamRole) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole
			dtoTeamRole = TeamRoleUtil.toTeamRole(teamRole);

		return _jsonFactory.createJSONObject(dtoTeamRole.toString());
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private EntitlementLocalService _entitlementLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

}