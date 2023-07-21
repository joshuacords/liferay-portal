/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.resiliency.spi.agent;

import com.liferay.portal.kernel.resiliency.spi.agent.annotation.Direction;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.Distributed;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.DistributedRegistry;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.MatchType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 */
public class RequestAttributes {

	@Distributed(direction = Direction.DUPLEX, matchType = MatchType.EXACT)
	public static final String ATTRIBUTE_1 = "ATTRIBUTE_1";

	@Distributed(direction = Direction.REQUEST, matchType = MatchType.EXACT)
	public static final String ATTRIBUTE_2 = "ATTRIBUTE_2";

	@Distributed(direction = Direction.RESPONSE, matchType = MatchType.EXACT)
	public static final String ATTRIBUTE_3 = "ATTRIBUTE_3";

	public static void setRequestAttributes(
		HttpServletRequest httpServletRequest) {

		DistributedRegistry.registerDistributed(RequestAttributes.class);

		httpServletRequest.setAttribute(ATTRIBUTE_1, ATTRIBUTE_1);
		httpServletRequest.setAttribute(ATTRIBUTE_2, ATTRIBUTE_2);
		httpServletRequest.setAttribute(ATTRIBUTE_3, ATTRIBUTE_3);
	}

}