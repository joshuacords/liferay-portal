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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 */
public class AuditEntryUtil {

	public static long getDynamicClassNameId(String value) {
		ClassName className = ClassNameLocalServiceUtil.getClassName(
			AuditEntry.class.getName() + "#" + value);

		return className.getClassNameId();
	}

	public static AuditEntry toAuditEntry(
			com.liferay.osb.koroneiki.root.model.AuditEntry auditEntry)
		throws Exception {

		return new AuditEntry() {
			{
				action = Action.create(auditEntry.getAction());

				if (Validator.isNotNull(auditEntry.getAgentName())) {
					agentName = auditEntry.getAgentName();
				}
				else {
					agentName = "Auto";
				}

				agentUID = auditEntry.getAgentUID();
				auditSetId = auditEntry.getAuditSetId();
				dateCreated = auditEntry.getCreateDate();
				description = auditEntry.getDescription();
				field = auditEntry.getField();
				fieldClassLabel = _getDisplayName(
					auditEntry.getFieldClassNameId());
				fieldClassPK = auditEntry.getFieldClassPK();
				key = auditEntry.getAuditEntryKey();
				newValue = _getNewValue(auditEntry);
				oldValue = _getOldValue(auditEntry);
				summary = _getSummary(auditEntry);
			}
		};
	}

	private static String _getDisplayName(long classNameId) {
		if (classNameId == PortalUtil.getClassNameId(Account.class)) {
			return "Account";
		}
		else if (classNameId == PortalUtil.getClassNameId(AccountNote.class)) {
			return "Note";
		}
		else if (classNameId == PortalUtil.getClassNameId(Address.class)) {
			return "Address";
		}
		else if (classNameId == PortalUtil.getClassNameId(Contact.class)) {
			return "Contact";
		}
		else if (classNameId == PortalUtil.getClassNameId(ContactRole.class)) {
			return "Contact Role";
		}
		else if (classNameId == PortalUtil.getClassNameId(Entitlement.class)) {
			return "Entitlement";
		}
		else if (classNameId == PortalUtil.getClassNameId(ExternalLink.class)) {
			return "External Link";
		}
		else if (classNameId == PortalUtil.getClassNameId(
					ProductConsumption.class)) {

			return "Product Consumption";
		}
		else if (classNameId == PortalUtil.getClassNameId(ProductEntry.class)) {
			return "Product";
		}
		else if (classNameId == PortalUtil.getClassNameId(
					ProductPurchase.class)) {

			return "Product Purchase";
		}
		else if (classNameId == PortalUtil.getClassNameId(Team.class)) {
			return "Team";
		}
		else if (classNameId == PortalUtil.getClassNameId(TeamRole.class)) {
			return "Team Role";
		}
		else {
			String className = PortalUtil.getClassName(classNameId);

			if (className.startsWith(AuditEntry.class.getName() + "#")) {
				return className.substring(className.indexOf("#") + 1);
			}

			return "N/A";
		}
	}

	private static String _getNewValue(
		com.liferay.osb.koroneiki.root.model.AuditEntry auditEntry) {

		if (Validator.isNotNull(auditEntry.getNewLabel())) {
			return auditEntry.getNewLabel();
		}

		return auditEntry.getNewValue();
	}

	private static String _getOldValue(
		com.liferay.osb.koroneiki.root.model.AuditEntry auditEntry) {

		if (Validator.isNotNull(auditEntry.getOldLabel())) {
			return auditEntry.getOldLabel();
		}

		return auditEntry.getOldValue();
	}

	private static String _getPastTenseAction(String action) {
		if (action.endsWith("e")) {
			return action + "d";
		}

		return action + "ed";
	}

	private static String _getSummary(
			com.liferay.osb.koroneiki.root.model.AuditEntry auditEntry)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append(_getPastTenseAction(auditEntry.getAction()));
		sb.append(" ");

		if ((auditEntry.getFieldClassNameId() > 0) &&
			(auditEntry.getFieldClassNameId() != auditEntry.getClassNameId())) {

			sb.append(_getDisplayName(auditEntry.getFieldClassNameId()));
		}
		else {
			sb.append(_getDisplayName(auditEntry.getClassNameId()));
		}

		return sb.toString();
	}

}