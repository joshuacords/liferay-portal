/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.cluster;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Artur Aquino
 */
public class ElasticsearchClusterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		_replicasClusterContext = createReplicasClusterContext();
	}

	@Test
	public void testReplicaIndexNamesIncludeSystemCompanyId() {
		long[] companyIds = {42, 142857};

		setUpCompanyLocalService(getCompanies(companyIds));

		String[] targetIndexNames =
			_replicasClusterContext.getTargetIndexNames();

		Arrays.sort(targetIndexNames);

		Assert.assertEquals(
			"[cid-0, cid-142857, cid-42]", Arrays.toString(targetIndexNames));
	}

	protected ReplicasClusterContext createReplicasClusterContext() {
		ElasticsearchCluster elasticsearchCluster = new ElasticsearchCluster();

		elasticsearchCluster.companyLocalService = _companyLocalService;

		elasticsearchCluster.indexNameBuilder = new IndexNameBuilder() {

			@Override
			public String getIndexName(long companyId) {
				return getTestIndexName(companyId);
			}

		};

		return elasticsearchCluster.new ReplicasClusterContextImpl();
	}

	protected List<Company> getCompanies(long[] companyIds) {
		List<Company> companies = new ArrayList<>(companyIds.length);

		for (long companyId : companyIds) {
			companies.add(getCompany(companyId));
		}

		return companies;
	}

	protected Company getCompany(long companyId) {
		Company company = Mockito.mock(Company.class);

		Mockito.when(
			company.getCompanyId()
		).thenReturn(
			companyId
		);

		return company;
	}

	protected String getTestIndexName(long companyId) {
		return "cid-" + companyId;
	}

	protected void setUpCompanyLocalService(List<Company> companies) {
		Mockito.when(
			_companyLocalService.getCompanies()
		).thenReturn(
			companies
		);
	}

	@Mock
	private CompanyLocalService _companyLocalService;

	private ReplicasClusterContext _replicasClusterContext;

}