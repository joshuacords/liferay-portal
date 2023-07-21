/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-search-modified-facet-configuration',
	A => {
		var ModifiedFacetConfiguration = function(form) {
			var instance = this;

			instance.form = form;

			instance.form.on('submit', A.bind(instance._onSubmit, instance));
		};

		A.mix(ModifiedFacetConfiguration.prototype, {
			_onSubmit(event) {
				var instance = this;

				event.preventDefault();

				var ranges = [];

				var rangeFormRows = A.all('.range-form-row').filter(item => {
					return !item.get('hidden');
				});

				rangeFormRows.each(item => {
					var label = item.one('.label-input').val();

					var range = item.one('.range-input').val();

					ranges.push({
						label,
						range
					});
				});

				instance.form.one('.ranges-input').val(JSON.stringify(ranges));

				submitForm(instance.form);
			}
		});

		Liferay.namespace(
			'Search'
		).ModifiedFacetConfiguration = ModifiedFacetConfiguration;
	},
	'',
	{
		requires: ['aui-node']
	}
);
