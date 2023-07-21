/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.runtime.metadata;

import com.liferay.saml.runtime.SamlException;

import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * @author Michael C. Han
 */
public interface LocalEntityManager {

	public void deleteLocalEntityCertificate(CertificateUsage certificateUsage)
		throws KeyStoreException;

	public default String getEncodedLocalEntityCertificate()
		throws SamlException {

		return getEncodedLocalEntityCertificate(CertificateUsage.SIGNING);
	}

	public String getEncodedLocalEntityCertificate(
			CertificateUsage certificateUsage)
		throws SamlException;

	public default X509Certificate getLocalEntityCertificate()
		throws SamlException {

		return getLocalEntityCertificate(CertificateUsage.SIGNING);
	}

	public X509Certificate getLocalEntityCertificate(
			CertificateUsage certificateUsage)
		throws SamlException;

	public String getLocalEntityId();

	public boolean hasDefaultIdpRole();

	public void storeLocalEntityCertificate(
			PrivateKey privateKey, String certificateKeyPassword,
			X509Certificate x509Certificate, CertificateUsage certificateUsage)
		throws Exception;

	public static enum CertificateUsage {

		ENCRYPTION, SIGNING

	}

}