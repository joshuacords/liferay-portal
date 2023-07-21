/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as columnResizedHandler from '../../../src/main/resources/META-INF/resources/js/components/LayoutProvider/handlers/columnResizedHandler.es';

describe('LayoutProvider/handlers/columnResized', () => {
	describe('handleColumnResized(state, source, column, direction)', () => {
		it('resizes columns when pulling with the left handle', () => {
			const source = {
				dataset: {
					ddmFieldColumn: 1,
					ddmFieldPage: 0,
					ddmFieldRow: 0
				}
			};
			const state = {
				pages: [
					{
						rows: [
							{
								columns: [
									{
										fields: [{}],
										size: 4
									},
									{
										fields: [{}],
										size: 8
									}
								]
							}
						]
					}
				]
			};

			const result = columnResizedHandler.handleColumnResized(
				state,
				source,
				6,
				'left'
			);

			expect(result.pages[0].rows[0].columns[0].size).toEqual(6);
			expect(result.pages[0].rows[0].columns[1].size).toEqual(6);
		});

		it("removes column to the left if it's empty and cornered", () => {
			const source = {
				dataset: {
					ddmFieldColumn: 1,
					ddmFieldPage: 0,
					ddmFieldRow: 0
				}
			};
			const state = {
				pages: [
					{
						rows: [
							{
								columns: [
									{
										fields: [],
										size: 1
									},
									{
										fields: [{}],
										size: 11
									}
								]
							}
						]
					}
				]
			};

			const result = columnResizedHandler.handleColumnResized(
				state,
				source,
				0,
				'left'
			);

			expect(result.pages[0].rows[0].columns.length).toEqual(1);
		});

		it('adds a column when pulling to the right with the left handle', () => {
			const source = {
				dataset: {
					ddmFieldColumn: 0,
					ddmFieldPage: 0,
					ddmFieldRow: 0
				}
			};
			const state = {
				pages: [
					{
						rows: [
							{
								columns: [
									{
										fields: [],
										size: 4
									},
									{
										fields: [{}],
										size: 8
									}
								]
							}
						]
					}
				]
			};

			const result = columnResizedHandler.handleColumnResized(
				state,
				source,
				1,
				'left'
			);

			expect(result.pages[0].rows[0].columns.length).toEqual(3);
		});

		it('removes column when pulling to the right edge with the right handle', () => {
			const source = {
				dataset: {
					ddmFieldColumn: 0,
					ddmFieldPage: 0,
					ddmFieldRow: 0
				}
			};
			const state = {
				pages: [
					{
						rows: [
							{
								columns: [
									{
										fields: [{}],
										size: 11
									},
									{
										fields: [],
										size: 1
									}
								]
							}
						]
					}
				]
			};

			const result = columnResizedHandler.handleColumnResized(
				state,
				source,
				12,
				'right'
			);

			expect(result.pages[0].rows[0].columns.length).toEqual(1);
		});
	});
});
