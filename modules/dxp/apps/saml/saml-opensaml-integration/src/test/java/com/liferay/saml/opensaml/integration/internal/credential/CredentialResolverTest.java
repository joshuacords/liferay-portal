/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.credential;

import com.liferay.saml.opensaml.integration.internal.BaseSamlTestCase;

import net.shibboleth.utilities.java.support.resolver.CriteriaSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.opensaml.core.criterion.EntityIdCriterion;
import org.opensaml.security.credential.Credential;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
public class CredentialResolverTest extends BaseSamlTestCase {

	@Test
	public void testResolveIdpCredential() throws Exception {
		prepareIdentityProvider(IDP_ENTITY_ID);
		testResolveCredential(IDP_ENTITY_ID);
	}

	@Test
	public void testResolveNonexistingCredential() throws Exception {
		EntityIdCriterion entityIDCriterion = new EntityIdCriterion("na");

		CriteriaSet criteriaSet = new CriteriaSet();

		criteriaSet.add(entityIDCriterion);

		Credential credential = credentialResolver.resolveSingle(criteriaSet);

		Assert.assertNull(credential);
	}

	@Test
	public void testResolveSpCredential() throws Exception {
		prepareServiceProvider(SP_ENTITY_ID);
		testResolveCredential(SP_ENTITY_ID);
	}

	protected void testResolveCredential(String spEntityId) throws Exception {
		EntityIdCriterion entityIDCriterion = new EntityIdCriterion(spEntityId);

		CriteriaSet criteriaSet = new CriteriaSet();

		criteriaSet.add(entityIDCriterion);

		Credential credential = credentialResolver.resolveSingle(criteriaSet);

		Assert.assertNotNull(credential);
	}

}