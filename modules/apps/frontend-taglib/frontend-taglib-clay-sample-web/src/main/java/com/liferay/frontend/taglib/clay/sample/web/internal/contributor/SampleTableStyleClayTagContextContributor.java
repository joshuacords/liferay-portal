/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.sample.web.internal.contributor;

import com.liferay.frontend.taglib.clay.servlet.taglib.contributor.ClayTagContextContributor;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rodolfo Roza Miranda
 */
@Component(
	immediate = true,
	property = {
		"clay.tag.context.contributor.key=SampleTable",
		"service.ranking:Integer=1"
	},
	service = ClayTagContextContributor.class
)
public class SampleTableStyleClayTagContextContributor
	implements ClayTagContextContributor {

	@Override
	public void populate(Map<String, Object> context) {
		String tableClasses = GetterUtil.getString(context.get("tableClasses"));

		context.put("tableClasses", tableClasses + " sample-table-style");
	}

}