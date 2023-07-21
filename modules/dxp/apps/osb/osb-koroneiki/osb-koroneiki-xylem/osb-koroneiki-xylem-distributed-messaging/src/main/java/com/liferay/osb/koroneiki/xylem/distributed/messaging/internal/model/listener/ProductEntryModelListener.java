/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.product.create",
		"remove.topic=koroneiki.product.delete",
		"update.topic=koroneiki.product.update"
	},
	service = ModelListener.class
)
public class ProductEntryModelListener
	extends BaseXylemModelListener<ProductEntry> {

	@Override
	protected Callable<Message> getCallable(ProductEntry productEntry)
		throws Exception {

		return () -> messageFactory.create(productEntry);
	}

}