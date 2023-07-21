/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.search.elasticsearch6.internal.io.StringOutputStream;

import java.io.IOException;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.io.stream.OutputStreamStreamOutput;

/**
 * @author Michael C. Han
 */
public class LogUtil {

	public static void logActionResponse(
		Log log, ActionResponse actionResponse) {

		if (!log.isInfoEnabled()) {
			return;
		}

		StringOutputStream stringOutputStream = new StringOutputStream();

		try {
			actionResponse.writeTo(
				new OutputStreamStreamOutput(stringOutputStream));
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}

		log.info(stringOutputStream);
	}

	public static void logActionResponse(Log log, BulkResponse bulkResponse) {
		if (bulkResponse.hasFailures()) {
			log.error(bulkResponse.buildFailureMessage());
		}

		logActionResponse(log, (ActionResponse)bulkResponse);
	}

}