/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.bootstrap;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import org.opensaml.core.config.ConfigurationService;
import org.opensaml.xmlsec.DecryptionConfiguration;
import org.opensaml.xmlsec.EncryptionConfiguration;
import org.opensaml.xmlsec.SignatureSigningConfiguration;
import org.opensaml.xmlsec.SignatureValidationConfiguration;
import org.opensaml.xmlsec.config.DefaultSecurityConfigurationBootstrap;
import org.opensaml.xmlsec.impl.BasicDecryptionConfiguration;
import org.opensaml.xmlsec.impl.BasicEncryptionConfiguration;
import org.opensaml.xmlsec.impl.BasicSignatureSigningConfiguration;
import org.opensaml.xmlsec.impl.BasicSignatureValidationConfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(service = SecurityConfigurationBootstrap.class)
public class SecurityConfigurationBootstrap {

	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
		BasicDecryptionConfiguration basicDecryptionConfiguration =
			DefaultSecurityConfigurationBootstrap.
				buildDefaultDecryptionConfiguration();
		BasicEncryptionConfiguration basicEncryptionConfiguration =
			DefaultSecurityConfigurationBootstrap.
				buildDefaultEncryptionConfiguration();
		BasicSignatureSigningConfiguration basicSignatureSigningConfiguration =
			DefaultSecurityConfigurationBootstrap.
				buildDefaultSignatureSigningConfiguration();
		BasicSignatureValidationConfiguration
			basicSignatureValidationConfiguration =
				DefaultSecurityConfigurationBootstrap.
					buildDefaultSignatureValidationConfiguration();

		Object blacklistedAlgorithmsObject = properties.get(
			"blacklisted.algorithms");

		if (blacklistedAlgorithmsObject instanceof String[]) {
			basicDecryptionConfiguration.setBlacklistedAlgorithms(
				_combine(
					basicDecryptionConfiguration.getBlacklistedAlgorithms(),
					(String[])blacklistedAlgorithmsObject));

			basicEncryptionConfiguration.setBlacklistedAlgorithms(
				_combine(
					basicEncryptionConfiguration.getBlacklistedAlgorithms(),
					(String[])blacklistedAlgorithmsObject));

			basicSignatureSigningConfiguration.setBlacklistedAlgorithms(
				_combine(
					basicSignatureSigningConfiguration.
						getBlacklistedAlgorithms(),
					(String[])blacklistedAlgorithmsObject));

			basicSignatureValidationConfiguration.setBlacklistedAlgorithms(
				_combine(
					basicSignatureValidationConfiguration.
						getBlacklistedAlgorithms(),
					(String[])blacklistedAlgorithmsObject));
		}

		ConfigurationService.register(
			DecryptionConfiguration.class, basicDecryptionConfiguration);
		ConfigurationService.register(
			EncryptionConfiguration.class, basicEncryptionConfiguration);
		ConfigurationService.register(
			SignatureSigningConfiguration.class,
			basicSignatureSigningConfiguration);
		ConfigurationService.register(
			SignatureValidationConfiguration.class,
			basicSignatureValidationConfiguration);
	}

	private Collection<String> _combine(
		Collection<String> collection, String... strings) {

		Collection<String> combinedCollection = new HashSet<>(collection);

		Collections.addAll(combinedCollection, strings);

		return combinedCollection;
	}

	@Reference
	private OpenSamlBootstrap _openSamlBootstrap;

}