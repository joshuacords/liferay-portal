/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;

/**
 * @author Amos Fong
 */
public class BaseWebService {

	protected void validateResponse(HttpInvoker.HttpResponse httpResponse)
		throws Problem.ProblemException {

		int statusCode = httpResponse.getStatusCode();

		if (statusCode >= 300) {
			Problem problem = null;

			try {
				problem = Problem.toDTO(httpResponse.getContent());
			}
			catch (Exception exception) {
				problem = new Problem();

				problem.setTitle(httpResponse.getContent());
			}

			throw new Problem.ProblemException(problem);
		}
	}

}