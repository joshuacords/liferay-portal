/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.builder;

import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.similar.results.web.spi.contributor.SimilarResultsContributor;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.RouteHelper;

import java.util.Optional;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
@Component(service = SimilarResultsContributorsRegistry.class)
public class SimilarResultsContributorsRegistryImpl
	implements SimilarResultsContributorsRegistry {

	@Override
	public Optional<SimilarResultsRoute> detectRoute(String urlString) {
		if (Validator.isBlank(urlString)) {
			return Optional.empty();
		}

		String decodedURLString = _http.decodeURL(urlString);

		Stream<SimilarResultsContributor> stream =
			_similarResultsContributorsHolder.stream();

		return stream.map(
			similarResultsContributor -> _detectRoute(
				similarResultsContributor, decodedURLString)
		).filter(
			Optional::isPresent
		).map(
			Optional::get
		).findFirst();
	}

	@Reference(unbind = "-")
	public void setHttp(Http http) {
		_http = http;
	}

	@Reference(unbind = "-")
	public void setSimilarResultsContributorsHolder(
		SimilarResultsContributorsHolder similarResultsContributorsHolder) {

		_similarResultsContributorsHolder = similarResultsContributorsHolder;
	}

	private Optional<SimilarResultsRoute> _detectRoute(
		SimilarResultsContributor similarResultsContributor, String urlString) {

		RouteBuilderImpl routeBuilderImpl = new RouteBuilderImpl();

		RouteHelper routeHelper = () -> urlString;

		try {
			similarResultsContributor.detectRoute(
				routeBuilderImpl, routeHelper);
		}
		catch (RuntimeException runtimeException) {
			return Optional.empty();
		}

		if (routeBuilderImpl.hasNoAttributes()) {
			return Optional.empty();
		}

		routeBuilderImpl.contributor(similarResultsContributor);

		return Optional.of(routeBuilderImpl.build());
	}

	private Http _http;
	private SimilarResultsContributorsHolder _similarResultsContributorsHolder;

}