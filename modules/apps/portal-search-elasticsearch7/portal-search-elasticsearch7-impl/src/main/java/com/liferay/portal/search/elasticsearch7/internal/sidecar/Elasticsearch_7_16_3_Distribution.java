/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.elasticsearch7.internal.sidecar;

import java.util.Arrays;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class Elasticsearch_7_16_3_Distribution implements Distribution {

	@Override
	public Distributable getElasticsearchDistributable() {
		return new DistributableImpl(
			"https://artifacts.elastic.co/downloads/elasticsearch" +
				"/elasticsearch-7.16.3-no-jdk-linux-x86_64.tar.gz",
			_ELASTICSEARCH_CHECKSUM);
	}

	@Override
	public List<Distributable> getPluginDistributables() {
		return Arrays.asList(
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-icu/analysis-icu-7.16.3.zip",
				_ICU_CHECKSUM),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-kuromoji/analysis-kuromoji-7.16.3.zip",
				_KUROMOJI_CHECKSUM),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-smartcn/analysis-smartcn-7.16.3.zip",
				_SMARTCN_CHECKSUM),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-stempel/analysis-stempel-7.16.3.zip",
				_STEMPEL_CHECKSUM));
	}

	private static final String _ELASTICSEARCH_CHECKSUM =
		"ec9747694ba9f8b76b1620e42addd106afa3d3c207dd3836afe2482556f8a982fa03" +
			"f3536ebb762f12a6093c9a5eac63cdbf2f1941dde9e3ce69c41652749d4f";

	private static final String _ICU_CHECKSUM =
		"38e43ed433fde26a0f1b6fcb4c778a1b1b0c74250218590c7202c09703ba15d06179" +
			"7742425a5cfe142a37d7491526b978d2d31ed1a4e5d6cb52eb6479b45571";

	private static final String _KUROMOJI_CHECKSUM =
		"a7229c470d326bea0cda405f339a3c8f2b7a912d813eaae6d94f9c20960cd80002ba" +
			"a14cea533835d90a9581cb9182988dfb3d3045d511b4f79ccb88f25aaca5";

	private static final String _SMARTCN_CHECKSUM =
		"a31d19a98add866fdef33c83443194afd133109b1453dd5224b29734c1adef8564b2" +
			"33f929b18cd7fa3ee38676d9f8e851a3d54f15f55baef884f5a022577365";

	private static final String _STEMPEL_CHECKSUM =
		"b0a4fc7e6ee93b00dbad50abaecea229c87be71b48494add64ba10b4e052f55a36c8" +
			"4226a84ace8ce78492e2834ffbb4470dc13cba457c5910990ca611517d24";

}