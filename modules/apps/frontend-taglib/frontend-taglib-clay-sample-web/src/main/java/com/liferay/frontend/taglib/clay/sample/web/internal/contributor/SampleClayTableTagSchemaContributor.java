/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.sample.web.internal.contributor;

import com.liferay.frontend.taglib.clay.servlet.taglib.contributor.ClayTableTagSchemaContributor;
import com.liferay.frontend.taglib.clay.servlet.taglib.model.table.Field;
import com.liferay.frontend.taglib.clay.servlet.taglib.model.table.Schema;

import org.osgi.service.component.annotations.Component;

/**
 * @author Iván Zaera Avellón
 */
@Component(
	immediate = true,
	property = "clay.table.tag.schema.contributor.key=SampleTable",
	service = ClayTableTagSchemaContributor.class
)
public class SampleClayTableTagSchemaContributor
	implements ClayTableTagSchemaContributor {

	@Override
	public void populate(Schema schema) {
		schema.addField(new Field("name", "Name"));
		schema.addField(new Field("calories", "Calories", "number"));
		schema.addField(new Field("portion", "Portion", "number"));
	}

}