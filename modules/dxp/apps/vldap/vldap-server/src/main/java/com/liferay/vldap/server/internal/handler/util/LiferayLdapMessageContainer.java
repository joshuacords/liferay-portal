/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler.util;

import org.apache.directory.api.ldap.codec.api.LdapMessageContainer;
import org.apache.directory.api.ldap.codec.standalone.StandaloneLdapApiService;
import org.apache.directory.api.ldap.model.message.Message;

/**
 * @author Minhchau Dang
 */
public class LiferayLdapMessageContainer extends LdapMessageContainer<Message> {

	public LiferayLdapMessageContainer() throws Exception {
		super(new StandaloneLdapApiService());

		setGrammar(new DnCorrectingGrammar<>());
	}

}