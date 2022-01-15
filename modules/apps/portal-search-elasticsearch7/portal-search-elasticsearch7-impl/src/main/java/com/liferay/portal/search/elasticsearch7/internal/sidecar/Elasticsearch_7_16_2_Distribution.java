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
public class Elasticsearch_7_16_2_Distribution implements Distribution {

	@Override
	public Distributable getElasticsearchDistributable() {
		return new DistributableImpl(
			"https://artifacts.elastic.co/downloads/elasticsearch" +
				"/elasticsearch-7.16.2-no-jdk-linux-x86_64.tar.gz",
			_ELASTICSEARCH_CHECKSUM);
	}

	@Override
	public List<Distributable> getPluginDistributables() {
		return Arrays.asList(
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-icu/analysis-icu-7.16.2.zip",
				_ICU_CHECKSUM),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-kuromoji/analysis-kuromoji-7.16.2.zip",
				_KUROMOJI_CHECKSUM),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-smartcn/analysis-smartcn-7.16.2.zip",
				_SMARTCN_CHECKSUM),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-stempel/analysis-stempel-7.16.2.zip",
				_STEMPEL_CHECKSUM));
	}

	private static final String _ELASTICSEARCH_CHECKSUM =
		"4e913e6657501a23830e138d0f7de7de57e80c0953078ecb10565770931bbba0eed3" +
			"7b38de0888f9618de579138d51ca16f0239048ff70de68330aba862aff8d";

	private static final String _ICU_CHECKSUM =
		"58cea3b004fba73aa67420cfdbe64dde05a515235ba0a8e281ea8c45737f9d79d765" +
			"3eb716a722eac5694b557235242daea4f1fd4361b2b1772e9305e6182119";

	private static final String _KUROMOJI_CHECKSUM =
		"c7c5b82a887ce9c11bf5d03835340482ad806ba382d9354a2f97283289060712e635" +
			"d75d889d20e1cdb643701cbd0178762e3a747c6dd0b6331d215a7e0376d8";

	private static final String _SMARTCN_CHECKSUM =
		"24c483e0b6a4dcf9b3b1c541906f817dd68fd9ca6928f5509235f8b362a66bfa5e50" +
			"e200c4ab39028af7d8493c56a79f6f8864516c8a3a3e6e030cf8bc00fc28";

	private static final String _STEMPEL_CHECKSUM =
		"fc3986d3e7c7f1e59c69d6abbb6389962c3ce1130905213e4761a099bc0913cc049d" +
			"84000488c1664ce9b8603d677b2489689840dcea00f2fdc33bf43b948870";

}