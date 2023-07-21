/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.spi.model.index.contributor;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeIndexer;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentContributor;
import com.liferay.portal.search.spi.model.index.contributor.ExpandoBridgeRetriever;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = DocumentContributor.class)
public class ExpandoBridgeDocumentContributor implements DocumentContributor {

	@Override
	public void contribute(Document document, BaseModel baseModel) {
		ExpandoBridgeRetriever expandoBridgeRetriever =
			_expandoBridgeIndexers.getService(baseModel.getModelClassName());

		ExpandoBridge expandoBridge = null;

		if (expandoBridgeRetriever != null) {
			expandoBridge = expandoBridgeRetriever.getExpandoBridge(baseModel);
		}
		else {
			expandoBridge = baseModel.getExpandoBridge();
		}

		expandoBridgeIndexer.addAttributes(document, expandoBridge);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_expandoBridgeIndexers = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ExpandoBridgeRetriever.class, "indexer.class.name");
	}

	@Deactivate
	protected void deactivate() {
		_expandoBridgeIndexers.close();
	}

	@Reference
	protected ExpandoBridgeIndexer expandoBridgeIndexer;

	private ServiceTrackerMap<String, ExpandoBridgeRetriever>
		_expandoBridgeIndexers;

}