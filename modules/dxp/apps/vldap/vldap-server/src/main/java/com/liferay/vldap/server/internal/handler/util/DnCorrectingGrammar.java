/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler.util;

import org.apache.directory.api.asn1.ber.grammar.AbstractGrammar;
import org.apache.directory.api.asn1.ber.grammar.GrammarTransition;
import org.apache.directory.api.asn1.ber.tlv.UniversalTag;
import org.apache.directory.api.ldap.codec.LdapMessageGrammar;
import org.apache.directory.api.ldap.codec.LdapStatesEnum;

/**
 * @author Minhchau Dang
 */
public class DnCorrectingGrammar<E extends LiferayLdapMessageContainer>
	extends AbstractGrammar<E> {

	@Override
	public GrammarTransition<E> getTransition(Enum<?> state, int tag) {
		GrammarTransition<E> grammarTransition = _abstractGrammar.getTransition(
			state, tag);

		Enum<?> currentState = grammarTransition.getCurrentState();

		if (currentState == LdapStatesEnum.NAME_STATE) {
			grammarTransition = new GrammarTransition<>(
				grammarTransition.getPreviousState(), currentState,
				UniversalTag.OCTET_STRING, _dnCorrectingStoreName);
		}

		return grammarTransition;
	}

	protected DnCorrectingGrammar() {
		_abstractGrammar = (AbstractGrammar<E>)LdapMessageGrammar.getInstance();

		_dnCorrectingStoreName = new DnCorrectingStoreName<>();
	}

	private final AbstractGrammar<E> _abstractGrammar;
	private final DnCorrectingStoreName<E> _dnCorrectingStoreName;

}