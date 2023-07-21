/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import mockPages from '../../../__mock__/mockPages.es';
import * as fieldEditedHandler from '../../../src/main/resources/META-INF/resources/js/components/LayoutProvider/handlers/fieldEditedHandler.es';
import * as focusedFieldUtil from '../../../src/main/resources/META-INF/resources/js/components/LayoutProvider/util/focusedField.es';

describe('LayoutProvider/handlers/fieldEditedHandler', () => {
	describe('handleFieldEdited(state, event)', () => {
		it('calls updateFocusedField()', () => {
			const event = {
				propertyName: 'dataType',
				propertyValue: 'string'
			};
			const state = {
				focusedField: {},
				pages: mockPages,
				rules: []
			};

			const updateFocusedFieldSpy = jest.spyOn(
				focusedFieldUtil,
				'updateFocusedField'
			);

			updateFocusedFieldSpy.mockImplementation(() => ({}));

			fieldEditedHandler.handleFieldEdited(state, event);

			expect(updateFocusedFieldSpy).toHaveBeenCalled();

			updateFocusedFieldSpy.mockRestore();
		});

		it('does not call updateFocusedField() when changing name to an empty string', () => {
			const event = {
				propertyName: 'name',
				propertyValue: ''
			};
			const state = {
				focusedField: {},
				pages: mockPages,
				rules: []
			};

			const updateFocusedFieldSpy = jest.spyOn(
				focusedFieldUtil,
				'updateFocusedField'
			);

			updateFocusedFieldSpy.mockImplementation(() => ({}));

			fieldEditedHandler.handleFieldEdited(state, event);

			expect(updateFocusedFieldSpy).not.toHaveBeenCalled();

			updateFocusedFieldSpy.mockRestore();
		});
	});
});
