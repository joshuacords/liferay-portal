/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index.contributor;

import com.liferay.portal.search.spi.model.index.contributor.IndexContributor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Adam Brandizzi
 */
@Component(immediate = true, service = IndexContributorsHolder.class)
public class IndexContributorsHolder {

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addIndexContributor(IndexContributor indexContributor) {
		for (IndexContributorReceiver indexContributorReceiver :
				_indexContributorReceivers) {

			indexContributorReceiver.addIndexContributor(indexContributor);
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addIndexContributorReceiver(
		IndexContributorReceiver indexContributorReceiver) {

		_indexContributorReceivers.add(indexContributorReceiver);

		for (IndexContributor indexContributor : _indexContributors) {
			indexContributorReceiver.addIndexContributor(indexContributor);
		}
	}

	protected void removeIndexContributor(IndexContributor indexContributor) {
		_indexContributors.remove(indexContributor);

		for (IndexContributorReceiver indexContributorReceiver :
				_indexContributorReceivers) {

			indexContributorReceiver.removeIndexContributor(indexContributor);
		}
	}

	protected void removeIndexContributorReceiver(
		IndexContributorReceiver indexContributorReceiver) {

		_indexContributorReceivers.remove(indexContributorReceiver);
	}

	private final List<IndexContributorReceiver> _indexContributorReceivers =
		new CopyOnWriteArrayList<>();
	private final List<IndexContributor> _indexContributors =
		new CopyOnWriteArrayList<>();

}