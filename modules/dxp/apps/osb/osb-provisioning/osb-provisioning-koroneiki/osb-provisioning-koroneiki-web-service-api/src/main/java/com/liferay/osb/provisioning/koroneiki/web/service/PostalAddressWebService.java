/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface PostalAddressWebService {

	public PostalAddress addPostalAddress(
			String agentName, String agentUID, String accountKey,
			PostalAddress postalAddress)
		throws Exception;

	public void deletePostalAddress(
			String agentName, String agentUID, Long postalAddressId)
		throws Exception;

	public List<PostalAddress> getAccountPostalAddresss(String accountKey)
		throws Exception;

	public PostalAddress updatePostalAddress(
			String agentName, String agentUID, Long postalAddressId,
			PostalAddress postalAddress)
		throws Exception;

}