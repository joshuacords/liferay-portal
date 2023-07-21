/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-calendar-date-picker-sanitizer',
	A => {
		var AArray = A.Array;

		var DateMath = A.DataType.DateMath;

		var DatePickerSanitizer = A.Component.create({
			ATTRS: {
				datePickers: {},

				defaultDate: {},

				maximumDate: {},

				minimumDate: {}
			},

			EXTENDS: A.Base,

			NAME: 'date-picker-sanitizer',

			prototype: {
				_onDatePickerSelectionChange: function _onDatePickerSelectionChange(
					event
				) {
					var instance = this;

					var date = event.newSelection[0];

					var datePicker = event.currentTarget;

					var defaultDate = instance.get('defaultDate');

					var maximumDate = instance.get('maximumDate');

					var minimumDate = instance.get('minimumDate');

					if (
						date &&
						!DateMath.between(date, minimumDate, maximumDate)
					) {
						event.halt();
						event.newSelection.pop();

						datePicker.deselectDates();
						datePicker.selectDates([defaultDate]);
					}
				},

				bindUI() {
					var instance = this;

					var datePickers = instance.get('datePickers');

					instance.eventHandlers = A.map(datePickers, item => {
						return item.on(
							'selectionChange',
							A.bind(
								instance._onDatePickerSelectionChange,
								instance
							)
						);
					});
				},

				destructor() {
					var instance = this;

					instance.unlink();

					instance.eventHandlers = null;
				},

				initializer() {
					var instance = this;

					instance.eventHandlers = [];

					instance.bindUI();
				},

				unlink() {
					var instance = this;

					AArray.invoke(instance.eventHandlers, 'detach');
				}
			}
		});

		Liferay.DatePickerSanitizer = DatePickerSanitizer;
	},
	'',
	{
		requires: ['aui-base', 'aui-datatype']
	}
);
