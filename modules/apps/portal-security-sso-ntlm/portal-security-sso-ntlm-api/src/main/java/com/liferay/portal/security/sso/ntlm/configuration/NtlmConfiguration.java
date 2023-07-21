/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Defines the configuration property keys and sensible default values.
 *
 * <p>
 * This class also defines the identity of the configuration schema which, among
 * other things, defines the filename (minus the .cfg extension) for setting
 * values via a file.
 * </p>
 *
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(category = "sso")
@Meta.OCD(
	id = "com.liferay.portal.security.sso.ntlm.configuration.NtlmConfiguration",
	localization = "content/Language", name = "ntlm-configuration-name"
)
public interface NtlmConfiguration {

	@Meta.AD(
		deflt = "false", description = "enabled-help", name = "enabled",
		required = false
	)
	public boolean enabled();

	@Meta.AD(deflt = "127.0.0.1", name = "domain-controller", required = false)
	public String domainController();

	@Meta.AD(
		deflt = "EXAMPLE", name = "domain-controller-name", required = false
	)
	public String domainControllerName();

	@Meta.AD(deflt = "EXAMPLE", name = "domain", required = false)
	public String domain();

	@Meta.AD(
		deflt = "LIFERAY$@EXAMPLE.COM", name = "service-account",
		required = false
	)
	public String serviceAccount();

	@Meta.AD(deflt = "test", name = "service-password", required = false)
	public String servicePassword();

	@Meta.AD(
		deflt = "0x600FFFFF", description = "negotiate-flags-help",
		name = "negotiate-flags", required = false
	)
	public String negotiateFlags();

}