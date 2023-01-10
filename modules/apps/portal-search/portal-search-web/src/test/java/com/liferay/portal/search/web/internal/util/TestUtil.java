/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.web.internal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;
import com.liferay.portal.search.web.internal.facet.display.context.BucketDisplayContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mockito.Mockito;

/**
 * @author Amanda Costa
 */
public class TestUtil {

	public static String buildNameFrequencyString(
		List<BucketDisplayContext> bucketDisplayContexts) {

		StringBundler sb = new StringBundler(bucketDisplayContexts.size() * 4);

		for (BucketDisplayContext bucketDisplayContext :
				bucketDisplayContexts) {

			sb.append(bucketDisplayContext.getBucketText());
			sb.append(StringPool.COLON);
			sb.append(bucketDisplayContext.getFrequency());
			sb.append(StringPool.PIPE);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public static TermCollector createTermCollector(long id, int frequency) {
		TermCollector termCollector = Mockito.mock(TermCollector.class);

		Mockito.doReturn(
			frequency
		).when(
			termCollector
		).getFrequency();

		Mockito.doReturn(
			String.valueOf(id)
		).when(
			termCollector
		).getTerm();

		return termCollector;
	}

	public static TermCollector createTermCollector(
		String term, int frequency) {

		TermCollector termCollector = Mockito.mock(TermCollector.class);

		Mockito.doReturn(
			frequency
		).when(
			termCollector
		).getFrequency();

		Mockito.doReturn(
			term
		).when(
			termCollector
		).getTerm();

		return termCollector;
	}

	public static List<TermCollector> getTermCollectors(String... terms) {
		int[] frequencies = new int[terms.length];

		for (int i = 0; i < terms.length; i++) {
			frequencies[i] = i + 1;
		}

		return getTermCollectors(terms, frequencies);
	}

	public static List<TermCollector> getTermCollectors(
		String[] terms, int[] frequencies) {

		List<TermCollector> termCollectors = new ArrayList<>();

		for (int i = 0; i < terms.length; i++) {
			termCollectors.add(
				createTermCollector(terms[i], frequencies[i]));
		}

		return termCollectors;
	}

	public static void setUpMultipleTermCollectors(
		FacetCollector facetCollector, List<TermCollector> termCollectors) {

		Mockito.doReturn(
			termCollectors
		).when(
			facetCollector
		).getTermCollectors();
	}

	public static void setUpMultipleTermCollectors(
		FacetCollector facetCollector, String... terms) {

		int frequency = 1;

		for (String term : terms) {
			Mockito.doReturn(
				createTermCollector(term, frequency)
			).when(
				facetCollector
			).getTermCollector(
				term
			);

			frequency++;
		}
	}

	public static void setUpOneTermCollector(
		FacetCollector facetCollector, long id, int count) {

		Mockito.doReturn(
			Collections.singletonList(createTermCollector(id, count))
		).when(
			facetCollector
		).getTermCollectors();
	}

	public static void setUpOneTermCollector(
		FacetCollector facetCollector, String term, int frequency) {

		Mockito.doReturn(
			Collections.singletonList(createTermCollector(term, frequency))
		).when(
			facetCollector
		).getTermCollectors();
	}

}