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

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.concurrent.TimeUnit;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author Gustavo Lima
 */
public class SearchExecutorUtil {

	protected static long getMinutes(String timeString) {
		int num = GetterUtil.getInteger(
			timeString.substring(0, timeString.length() - 1));
		String unitString = timeString.substring(timeString.length() - 1);

		if (unitString.equals("s")) {
			return TimeUnit.SECONDS.toMinutes(num);
		}
		else if (unitString.equals("m")) {
			return num;
		}
		else if (unitString.equals("h")) {
			return TimeUnit.HOURS.toMinutes(num);
		}

		throw new IllegalArgumentException("Invalid time unit " + unitString);
	}

	protected static String toString(
		SearchSourceBuilder searchSourceBuilder, Log log) {

		try {
			return searchSourceBuilder.toString();
		}
		catch (ElasticsearchException elasticsearchException) {
			if (log.isDebugEnabled()) {
				log.debug(elasticsearchException);
			}

			return elasticsearchException.getMessage();
		}
	}

}