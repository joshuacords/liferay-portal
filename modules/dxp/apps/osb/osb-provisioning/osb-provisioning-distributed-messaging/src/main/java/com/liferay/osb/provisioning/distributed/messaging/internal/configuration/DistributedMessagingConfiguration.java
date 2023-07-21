/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Kyle Bischof
 */
@ExtendedObjectClassDefinition(generateUI = false)
@Meta.OCD(
	id = "com.liferay.osb.provisioning.distributed.messaging.internal.configuration.DistributedMessagingConfiguration"
)
public interface DistributedMessagingConfiguration {

	@Meta.AD(required = false)
	public String provisioningEmailAddressAustralia();

	@Meta.AD(required = false)
	public String provisioningEmailAddressBrazil();

	@Meta.AD(required = false)
	public String provisioningEmailAddressChina();

	@Meta.AD(required = false)
	public String provisioningEmailAddressGlobal();

	@Meta.AD(required = false)
	public String provisioningEmailAddressHungary();

	@Meta.AD(required = false)
	public String provisioningEmailAddressIndia();

	@Meta.AD(required = false)
	public String provisioningEmailAddressJapan();

	@Meta.AD(required = false)
	public String provisioningEmailAddressSpain();

	@Meta.AD(required = false)
	public String provisioningEmailAddressUS();

	@Meta.AD(required = false)
	public Long provisioningZendeskGroupId();

	@Meta.AD(required = false)
	public Long provisioningZendeskOrganizationId();

	@Meta.AD(required = false)
	public Long provisioningZendeskRequesterId();

	@Meta.AD(required = false)
	public Long zendeskCustomFieldOpportunityOwnerId();

	@Meta.AD(required = false)
	public Long zendeskCustomFieldPrimaryAddressCountryId();

	@Meta.AD(required = false)
	public Long zendeskCustomFieldProductId();

	@Meta.AD(required = false)
	public Long zendeskCustomFieldProvisioningComponentId();

	@Meta.AD(required = false)
	public Long zendeskCustomFieldSupportRegionId();

}