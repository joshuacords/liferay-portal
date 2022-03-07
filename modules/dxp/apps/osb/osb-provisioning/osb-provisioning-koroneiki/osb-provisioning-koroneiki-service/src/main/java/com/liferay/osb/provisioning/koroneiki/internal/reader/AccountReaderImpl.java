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

package com.liferay.osb.provisioning.koroneiki.internal.reader;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(immediate = true, service = AccountReader.class)
public class AccountReaderImpl implements AccountReader {

	public List<Account> getAncestorAccounts(Account account) throws Exception {
		List<Account> ancestorAccounts = new ArrayList<>();

		if (Validator.isNotNull(account.getParentAccountKey())) {
			Account parentAccount = _accountWebService.fetchAccount(
				account.getParentAccountKey());

			if (parentAccount != null) {
				ancestorAccounts.add(parentAccount);

				ancestorAccounts.addAll(getAncestorAccounts(parentAccount));
			}
		}

		return ancestorAccounts;
	}

	public Team getFirstLineSupportTeam(Account account) throws Exception {
		return _getTeam(account, TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);
	}

	public int getMaxSupportSeatCount(Account account) {
		if (ArrayUtil.isEmpty(account.getProductPurchases())) {
			return 0;
		}

		ProductPurchase slaProductPurchase = getSLAProductPurchase(account);

		if (slaProductPurchase == null) {
			return 0;
		}

		Product product = slaProductPurchase.getProduct();

		String name = product.getName();

		if (!name.equals(ProductConstants.NAME_GOLD) &&
			!name.equals(ProductConstants.NAME_PLATINUM)) {

			return 0;
		}

		boolean managedServices = false;
		int supportSeatAddons = 0;
		int productionInstances = 0;

		for (ProductPurchase productPurchase : account.getProductPurchases()) {
			if (!_isActive(productPurchase)) {
				continue;
			}

			Product curProduct = productPurchase.getProduct();

			String curName = curProduct.getName();

			if (curName.equals(ProductConstants.NAME_ANALYTICS_CLOUD_BASIC) ||
				curName.equals(
					ProductConstants.NAME_ANALYTICS_CLOUD_BUSINESS) ||
				curName.equals(
					ProductConstants.NAME_ANALYTICS_CLOUD_ENTERPRISE)) {

				return -1;
			}

			if (curName.equals(
					ProductConstants.NAME_DESIGNATED_CONTACT_ADD_ON)) {

				supportSeatAddons += productPurchase.getQuantity();
			}
			else if (curName.equals(
						ProductConstants.
							NAME_MANAGED_SERVICES_DEVELOPER_SUPPORT) ||
					 curName.equals(
						 ProductConstants.NAME_MANAGED_SERVICES_STANDARD)) {

				managedServices = true;
			}
			else if (curName.equals(
						ProductConstants.
							NAME_DXP_CLOUD_SUBSCRIPTION_HA_PRODUCTION)) {

				productionInstances += 2 * productPurchase.getQuantity();
			}
			else if (curName.equals(ProductConstants.NAME_DXP_PRODUCTION) ||
					 curName.equals(
						 ProductConstants.
							 NAME_DXP_CLOUD_SUBSCRIPTION_STD_PRODUCTION) ||
					 curName.equals(
						 ProductConstants.NAME_DXP_CLOUD_INSTANCE_PRODUCTION) ||
					 curName.equals(ProductConstants.NAME_PORTAL_PRODUCTION)) {

				productionInstances += productPurchase.getQuantity();
			}
		}

		if (managedServices) {
			return 10 + supportSeatAddons;
		}

		if (productionInstances <= 0) {
			return 0;
		}

		int maxSupportSeatCount = 0;

		if (name.equals(ProductConstants.NAME_GOLD)) {
			if (productionInstances <= 4) {
				maxSupportSeatCount = 2;
			}
			else if (productionInstances <= 8) {
				maxSupportSeatCount = 4;
			}
			else if (productionInstances <= 12) {
				maxSupportSeatCount = 6;
			}
			else if (productionInstances <= 16) {
				maxSupportSeatCount = 8;
			}
			else if (productionInstances <= 20) {
				maxSupportSeatCount = 10;
			}
			else {
				maxSupportSeatCount = 12;
			}
		}
		else if (name.equals(ProductConstants.NAME_PLATINUM)) {
			if (productionInstances <= 4) {
				maxSupportSeatCount = 3;
			}
			else if (productionInstances <= 8) {
				maxSupportSeatCount = 6;
			}
			else if (productionInstances <= 12) {
				maxSupportSeatCount = 9;
			}
			else if (productionInstances <= 16) {
				maxSupportSeatCount = 12;
			}
			else if (productionInstances <= 20) {
				maxSupportSeatCount = 15;
			}
			else {
				maxSupportSeatCount = 18;
			}
		}

		maxSupportSeatCount += supportSeatAddons;

		return maxSupportSeatCount;
	}

	public Team getPartnerTeam(Account account) throws Exception {
		return _getTeam(account, TeamRoleConstants.NAME_PARTNER);
	}

	public ProductPurchase getSLAProductPurchase(Account account) {
		if (ArrayUtil.isEmpty(account.getProductPurchases())) {
			return null;
		}

		ProductPurchase slaProductPurchase = null;

		for (ProductPurchase productPurchase : account.getProductPurchases()) {
			if (!_isActive(productPurchase)) {
				continue;
			}

			Product product = productPurchase.getProduct();

			if (!ArrayUtil.contains(
					ProductConstants.NAMES_SUBSCRIPTION, product.getName())) {

				continue;
			}

			if (_isHigherSLA(slaProductPurchase, productPurchase)) {
				slaProductPurchase = productPurchase;
			}
		}

		return slaProductPurchase;
	}

	public String getSubscriptionState(Account account) {
		String state = StringPool.BLANK;

		if (ArrayUtil.isEmpty(account.getProductPurchases())) {
			return state;
		}

		for (ProductPurchase productPurchase : account.getProductPurchases()) {
			Product product = productPurchase.getProduct();

			if (!ArrayUtil.contains(
					ProductConstants.NAMES_PARTNERSHIP, product.getName()) &&
				!ArrayUtil.contains(
					ProductConstants.NAMES_SUBSCRIPTION, product.getName())) {

				continue;
			}

			String curState = _getProductPurchaseState(productPurchase);

			if (_isHigherState(state, curState)) {
				state = curState;
			}
		}

		return state;
	}

	public int getSupportSeatCount(Account account) {
		Contact[] contacts = account.getCustomerContacts();

		if (contacts == null) {
			return 0;
		}

		int supportSeatCount = 0;

		for (Contact contact : contacts) {
			boolean employee = false;

			for (Team team : contact.getTeams()) {
				String name = team.getName();

				if (name.equals("Liferay, Inc.")) {
					employee = true;

					break;
				}
			}

			ContactRole[] contactRoles = contact.getContactRoles();

			if ((contactRoles == null) || employee) {
				continue;
			}

			for (ContactRole contactRole : contactRoles) {
				if (ArrayUtil.contains(
						ContactRoleConstants.SUPPORT_SEAT_CONTACT_ROLES,
						contactRole.getName())) {

					supportSeatCount++;

					break;
				}
			}
		}

		return supportSeatCount;
	}

	public boolean isEWSA(Account account) throws Exception {
		if (!ArrayUtil.isEmpty(account.getProductPurchases())) {
			for (ProductPurchase productPurchase :
					account.getProductPurchases()) {

				if (!_isActive(productPurchase)) {
					continue;
				}

				Product product = productPurchase.getProduct();

				String name = product.getName();

				if (name.equals(ProductConstants.NAME_DXP_EWSA) ||
					name.equals(ProductConstants.NAME_PORTAL_EWSA)) {

					return true;
				}
			}
		}

		if (Validator.isNotNull(account.getParentAccountKey())) {
			Account parentAccount = _accountWebService.fetchAccount(
				account.getParentAccountKey());

			if (isEWSA(parentAccount)) {
				return true;
			}
		}

		return false;
	}

	private String _getProductPurchaseState(ProductPurchase productPurchase) {
		if (productPurchase.getStatus() == ProductPurchase.Status.CANCELLED) {
			return ProductPurchaseConstants.STATE_CANCELLED;
		}

		Date now = new Date();

		if ((productPurchase.getEndDate() != null) &&
			now.after(productPurchase.getEndDate())) {

			return ProductPurchaseConstants.STATE_EXPIRED;
		}

		if ((productPurchase.getStartDate() != null) &&
			now.before(productPurchase.getStartDate())) {

			return ProductPurchaseConstants.STATE_UNACTIVATED;
		}

		return ProductPurchaseConstants.STATE_ACTIVE;
	}

	private int _getSLARank(Product product) {
		String name = product.getName();

		if (name.equals(ProductConstants.NAME_GOLD)) {
			return 3;
		}
		else if (name.equals(ProductConstants.NAME_LIMITED)) {
			return 1;
		}
		else if (name.equals(ProductConstants.NAME_PLATINUM)) {
			return 4;
		}
		else if (name.equals(ProductConstants.NAME_SILVER)) {
			return 2;
		}

		return 0;
	}

	private int _getStateRank(String state) {
		if (state.equals(ProductPurchaseConstants.STATE_ACTIVE)) {
			return 4;
		}
		else if (state.equals(ProductPurchaseConstants.STATE_CANCELLED)) {
			return 1;
		}
		else if (state.equals(ProductPurchaseConstants.STATE_EXPIRED)) {
			return 2;
		}
		else if (state.equals(ProductPurchaseConstants.STATE_UNACTIVATED)) {
			return 3;
		}

		return 0;
	}

	private Team _getTeam(Account account, String teamRoleName)
		throws Exception {

		Team[] teams = account.getAssignedTeams();

		if (teams != null) {
			for (Team team : teams) {
				TeamRole[] teamRoles = team.getTeamRoles();

				if (teamRoles == null) {
					List<TeamRole> teamRolesList =
						_teamRoleWebService.getTeamRoles(
							account.getKey(), team.getKey(), 1, 1000);

					teamRoles = teamRolesList.toArray(new TeamRole[0]);
				}

				for (TeamRole teamRole : teamRoles) {
					if (teamRoleName.equals(teamRole.getName())) {
						return team;
					}
				}
			}
		}

		return null;
	}

	private boolean _isActive(ProductPurchase productPurchase) {
		if (productPurchase.getStatus() == ProductPurchase.Status.CANCELLED) {
			return false;
		}

		Date now = new Date();

		if ((productPurchase.getEndDate() != null) &&
			now.after(productPurchase.getEndDate())) {

			return false;
		}

		if ((productPurchase.getStartDate() != null) &&
			now.before(productPurchase.getStartDate())) {

			return false;
		}

		return true;
	}

	private boolean _isHigherSLA(
		ProductPurchase curProductPurchase, ProductPurchase productPurchase) {

		if (curProductPurchase == null) {
			return true;
		}

		int curSLARank = _getSLARank(curProductPurchase.getProduct());
		int slaRank = _getSLARank(productPurchase.getProduct());

		if (slaRank > curSLARank) {
			return true;
		}

		if (slaRank < curSLARank) {
			return false;
		}

		if (productPurchase.getPerpetual() &&
			!curProductPurchase.getPerpetual()) {

			return true;
		}

		if (curProductPurchase.getPerpetual()) {
			return false;
		}

		Date curEndDate = curProductPurchase.getEndDate();
		Date endDate = productPurchase.getEndDate();

		if (endDate.after(curEndDate)) {
			return true;
		}

		return false;
	}

	private boolean _isHigherState(String curState, String newState) {
		if (_getStateRank(newState) > _getStateRank(curState)) {
			return true;
		}

		return false;
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

}