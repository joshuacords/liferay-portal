/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.highlight;

import com.liferay.portal.search.highlight.FieldConfig;
import com.liferay.portal.search.highlight.FieldConfigBuilder;

/**
 * @author Michael C. Han
 * @author André de Oliveira
 */
public class FieldConfigImpl implements FieldConfig {

	public FieldConfigImpl() {
	}

	public FieldConfigImpl(FieldConfigImpl fieldConfigImpl) {
		_field = fieldConfigImpl._field;
		_fragmentOffset = fieldConfigImpl._fragmentOffset;
		_fragmentSize = fieldConfigImpl._fragmentSize;
		_numFragments = fieldConfigImpl._numFragments;
	}

	@Override
	public String getField() {
		return _field;
	}

	@Override
	public Integer getFragmentOffset() {
		return _fragmentOffset;
	}

	@Override
	public Integer getFragmentSize() {
		return _fragmentSize;
	}

	@Override
	public Integer getNumFragments() {
		return _numFragments;
	}

	public static final class FieldConfigBuilderImpl
		implements FieldConfigBuilder {

		@Override
		public FieldConfig build() {
			return new FieldConfigImpl(_fieldConfigImpl);
		}

		@Override
		public FieldConfigBuilder field(String field) {
			_fieldConfigImpl._field = field;

			return this;
		}

		@Override
		public FieldConfigBuilder fragmentOffset(Integer fragmentOffset) {
			_fieldConfigImpl._fragmentOffset = fragmentOffset;

			return this;
		}

		@Override
		public FieldConfigBuilder fragmentSize(Integer fragmentSize) {
			_fieldConfigImpl._fragmentSize = fragmentSize;

			return this;
		}

		@Override
		public FieldConfigBuilder numFragments(Integer numFragments) {
			_fieldConfigImpl._numFragments = numFragments;

			return this;
		}

		private final FieldConfigImpl _fieldConfigImpl = new FieldConfigImpl();

	}

	private String _field;
	private Integer _fragmentOffset;
	private Integer _fragmentSize;
	private Integer _numFragments;

}