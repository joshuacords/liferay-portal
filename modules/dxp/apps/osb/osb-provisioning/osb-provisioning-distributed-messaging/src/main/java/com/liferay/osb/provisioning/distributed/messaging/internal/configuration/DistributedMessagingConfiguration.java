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

	@Meta.AD(deflt = "false", required = false)
	public boolean customerPortal2Enabled();

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