/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.rest.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.segments.asah.rest.client.dto.v1_0.Experiment;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.test.util.SegmentsTestUtil;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class ExperimentResourceTest extends BaseExperimentResourceTestCase {

	@Ignore
	@Override
	@Test
	public void testGraphQLDeleteExperiment() {
	}

	@Override
	protected Experiment testDeleteExperiment_addExperiment() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(testGroup);

		SegmentsExperience segmentsExperience =
			SegmentsTestUtil.addSegmentsExperience(
				testGroup.getGroupId(),
				ClassNameLocalServiceUtil.getClassNameId(
					Layout.class.getName()),
				layout.getPlid());

		return _toExperiment(
			SegmentsTestUtil.addSegmentsExperiment(
				testGroup.getGroupId(),
				segmentsExperience.getSegmentsExperienceId(),
				segmentsExperience.getClassNameId(),
				segmentsExperience.getClassPK()));
	}

	@Override
	protected Experiment testGraphQLExperiment_addExperiment()
		throws Exception {

		return testDeleteExperiment_addExperiment();
	}

	private Experiment _toExperiment(SegmentsExperiment segmentsExperiment) {
		return new Experiment() {
			{
				dateCreated = segmentsExperiment.getCreateDate();
				dateModified = segmentsExperiment.getModifiedDate();
				id = segmentsExperiment.getSegmentsExperimentKey();
				name = segmentsExperiment.getName();
				siteId = segmentsExperiment.getGroupId();
			}
		};
	}

}