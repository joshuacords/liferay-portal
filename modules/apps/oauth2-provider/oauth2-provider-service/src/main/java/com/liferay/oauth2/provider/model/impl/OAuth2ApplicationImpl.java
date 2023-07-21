/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.model.impl;

import com.liferay.oauth2.provider.constants.GrantType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuth2ApplicationImpl extends OAuth2ApplicationBaseImpl {

	@Override
	public List<GrantType> getAllowedGrantTypesList() {
		Stream<String> stream = Arrays.stream(
			StringUtil.split(getAllowedGrantTypes()));

		return stream.map(
			GrantType::valueOf
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<String> getFeaturesList() {
		return Arrays.asList(StringUtil.split(getFeatures()));
	}

	@Override
	public List<String> getRedirectURIsList() {
		return Arrays.asList(
			StringUtil.split(getRedirectURIs(), StringPool.NEW_LINE));
	}

	@Override
	public void setAllowedGrantTypesList(
		List<GrantType> allowedGrantTypesList) {

		Stream<GrantType> stream = allowedGrantTypesList.stream();

		setAllowedGrantTypes(
			stream.map(
				GrantType::toString
			).collect(
				Collectors.joining(StringPool.COMMA)
			));
	}

	@Override
	public void setFeaturesList(List<String> featuresList) {
		setFeatures(StringUtil.merge(featuresList));
	}

	@Override
	public void setRedirectURIsList(List<String> redirectURIsList) {
		String redirectURIs = StringUtil.merge(
			redirectURIsList, StringPool.NEW_LINE);

		setRedirectURIs(redirectURIs);
	}

}