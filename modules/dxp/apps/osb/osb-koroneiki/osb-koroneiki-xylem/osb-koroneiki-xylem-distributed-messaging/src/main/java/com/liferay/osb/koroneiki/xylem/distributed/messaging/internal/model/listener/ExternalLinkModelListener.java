/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.StringPool;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ExternalLinkModelListener
	extends BaseXylemModelListener<ExternalLink> {

	@Override
	protected Callable<Message> getCallable(ExternalLink externalLink)
		throws Exception {

		if (externalLink.getClassNameId() ==
				_classNameLocalService.getClassNameId(Account.class)) {

			Account account = _accountLocalService.getAccount(
				externalLink.getClassPK());

			return () -> messageFactory.create(account);
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(Contact.class)) {

			Contact contact = _contactLocalService.getContact(
				externalLink.getClassPK());

			return () -> messageFactory.create(contact);
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(
						ProductConsumption.class)) {

			ProductConsumption productConsumption =
				_productConsumptionLocalService.getProductConsumption(
					externalLink.getClassPK());

			Account account = _accountLocalService.getAccount(
				productConsumption.getAccountId());

			productConsumption.setAccountKey(account.getAccountKey());

			if (productConsumption.getProductPurchaseId() > 0) {
				ProductPurchase productPurchase =
					_productPurchaseLocalService.getProductPurchase(
						productConsumption.getProductPurchaseId());

				productConsumption.setProductPurchaseKey(
					productPurchase.getProductPurchaseKey());
			}
			else {
				productConsumption.setProductPurchaseKey(StringPool.BLANK);
			}

			return () -> messageFactory.create(productConsumption);
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(ProductEntry.class)) {

			ProductEntry productEntry =
				_productEntryLocalService.getProductEntry(
					externalLink.getClassPK());

			return () -> messageFactory.create(productEntry);
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(
						ProductPurchase.class)) {

			ProductPurchase productPurchase =
				_productPurchaseLocalService.getProductPurchase(
					externalLink.getClassPK());

			Account account = _accountLocalService.getAccount(
				productPurchase.getAccountId());

			productPurchase.setAccountKey(account.getAccountKey());

			return () -> messageFactory.create(productPurchase);
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(Team.class)) {

			Team team = _teamLocalService.getTeam(externalLink.getClassPK());

			Account account = _accountLocalService.getAccount(
				team.getAccountId());

			team.setAccountKey(account.getAccountKey());

			return () -> messageFactory.create(team);
		}

		return null;
	}

	@Override
	protected String getCreateTopic(ExternalLink externalLink) {
		return _getTopic(externalLink);
	}

	@Override
	protected String getPrimaryKey(ExternalLink externalLink) {
		return String.valueOf(externalLink.getClassPK());
	}

	@Override
	protected String getRemoveTopic(ExternalLink externalLink) {
		return _getTopic(externalLink);
	}

	@Override
	protected String getUpdateTopic(ExternalLink externalLink) {
		return _getTopic(externalLink);
	}

	private String _getTopic(ExternalLink externalLink) {
		if (externalLink.getClassNameId() ==
				_classNameLocalService.getClassNameId(Account.class)) {

			return "koroneiki.account.update";
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(Contact.class)) {

			return "koroneiki.contact.update";
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(
						ProductConsumption.class)) {

			return "koroneiki.productconsumption.update";
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(ProductEntry.class)) {

			return "koroneiki.productentry.update";
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(
						ProductPurchase.class)) {

			return "koroneiki.productpurchase.update";
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(Team.class)) {

			return "koroneiki.team.update";
		}

		return null;
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}