/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.constants;

/**
 * @author Amos Fong
 */
public class SalesforceConstants {

	public static final String OPPORTUNITY_STAGE_CLOSED_LOST = "Closed Lost";

	public static final String OPPORTUNITY_STAGE_CLOSED_WON = "Closed Won";

	public static final int OPPORTUNITY_TYPE_EXISTING_BUSINESS = 1;

	public static final int OPPORTUNITY_TYPE_NEW_BUSINESS = 2;

	public static final int OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS = 3;

	public static final int OPPORTUNITY_TYPE_RENEWAL = 4;

	public static final String PRODUCT_TYPE_RENEWAL = "Renewal";

	public static final String PRODUCT_TYPE_RENEWAL_DOWNGRADE =
		"Renewal Downgrade";

	public static final String PRODUCT_TYPE_RENEWAL_MIGRATION =
		"Renewal Migration";

	public static final String PRODUCT_TYPE_RENEWAL_UPGRADE = "Renewal Upgrade";

	public static final String[] PRODUCT_TYPES_RENEWAL = {
		PRODUCT_TYPE_RENEWAL, PRODUCT_TYPE_RENEWAL_DOWNGRADE,
		PRODUCT_TYPE_RENEWAL_MIGRATION, PRODUCT_TYPE_RENEWAL_UPGRADE
	};

	public static String getOpportunityTypeLabel(int opportunityType) {
		if (opportunityType == OPPORTUNITY_TYPE_EXISTING_BUSINESS) {
			return "existing-business";
		}
		else if (opportunityType == OPPORTUNITY_TYPE_NEW_BUSINESS) {
			return "new-business";
		}
		else if (opportunityType ==
					OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS) {

			return "new-project-existing-business";
		}
		else if (opportunityType == OPPORTUNITY_TYPE_RENEWAL) {
			return "renewal";
		}

		return "unknown-opportunity";
	}

}