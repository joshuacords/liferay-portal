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
 * @author André de Oliveira
 */
public class Elasticsearch7110Distribution implements Distribution {

	@Override
	public Distributable getElasticsearchDistributable() {
		return new DistributableImpl(
			"https://artifacts.elastic.co/downloads/elasticsearch" +
				"/elasticsearch-7.11.0-no-jdk-linux-x86_64.tar.gz",
			"478e3313f806cba8b25dbc960443f2e62ffc9f374ddfdad7c2204970e2b60a85" +
				"1efc84eb50baa5e0c5afd7303cadc4e5d45b39011088e1165fe516c57aa1" +
					"88bf");
	}

	@Override
	public List<Distributable> getPluginDistributables() {
		return Arrays.asList(
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-icu/analysis-icu-7.11.0.zip",
				"546b36e6ab70f1d990d485c23228df0c7c20d0f8763299372bc2ffbb29a8" +
					"e4f966533207222cac08a8e1892ad706c8ac2c084a3daab814eb0504" +
						"9db78dc6853f"),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-kuromoji/analysis-kuromoji-7.11.0.zip",
				"786520718600a2937145eb58e2d7b77d1a035971d6c53947475c95a9b7a5" +
					"3bb612b5a7cb125bf205762c63aeb9c7c68d0ba019d570aabd7aff1a" +
						"49b63f7e0a4c"),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-smartcn/analysis-smartcn-7.11.0.zip",
				"b00e81b27fb9e1e4826fb988170ba7fce443d817f5129d55d2f1ce4f2482" +
					"49b64817bb29f4b71a11ae0da7093f27966ffd7588f78bdd307187a9" +
						"b52e1df56d84"),
			new DistributableImpl(
				"https://artifacts.elastic.co/downloads/elasticsearch-plugins" +
					"/analysis-stempel/analysis-stempel-7.11.0.zip",
				"de3838e6e28558ce2f9116d2322049c565fb5e3f1d1801122c9eb0f0c2cc" +
					"04de14410c77c407d9ac00f174f14dd2ec48c9a876372273742d2af4" +
						"ed9980818cdb"));
	}

}