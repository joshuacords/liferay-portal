/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-search-custom-filter',
	A => {
		var FacetUtil = Liferay.Search.FacetUtil;

		var CustomFilter = function(form) {
			if (!form) {
				return;
			}

			var instance = this;

			instance.form = form;

			instance.form.on('submit', A.bind(instance._onSubmit, instance));

			instance.filterValueInput = instance.form.one(
				'.custom-filter-value-input'
			);

			var applyButton = instance.form.one('.custom-filter-apply-button');

			applyButton.on('click', A.bind(instance._onClick, instance));
		};

		A.mix(CustomFilter.prototype, {
			_onClick() {
				var instance = this;

				instance.search();
			},

			_onSubmit(event) {
				var instance = this;

				event.stopPropagation();

				instance.search();
			},

			getFilterValue() {
				var instance = this;

				var filterValue = instance.filterValueInput.val();

				return filterValue;
			},

			search() {
				var instance = this;

				var searchURL = instance.form.get('action');

				var queryString = instance.updateQueryString(
					document.location.search
				);

				document.location.href = searchURL + queryString;
			},

			updateQueryString(queryString) {
				var instance = this;

				var hasQuestionMark = false;

				if (queryString[0] === '?') {
					hasQuestionMark = true;
				}

				queryString = FacetUtil.updateQueryString(
					instance.filterValueInput.get('name'),
					[instance.getFilterValue()],
					queryString
				);

				if (!hasQuestionMark) {
					queryString = '?' + queryString;
				}

				return queryString;
			}
		});

		Liferay.namespace('Search').CustomFilter = CustomFilter;
	},
	'',
	{
		requires: ['liferay-search-facet-util']
	}
);
