/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {renderHook} from '@testing-library/react-hooks';
import React, {useContext} from 'react';
import {act, create} from 'react-test-renderer';

import {
	ProcessStatusContext,
	ProcessStatusProvider,
	processStatusConstants,
	useProcessStatus
} from '../../../../../src/main/resources/META-INF/resources/js/components/process-metrics/filter/store/ProcessStatusStore.es';

describe('The selected process statuses should', () => {
	test('Be empty when there is no initial key', () => {
		const {result, unmount} = renderHook(() => useProcessStatus([]));

		const selectedProcessStatuses = result.current.getSelectedProcessStatuses();

		expect(selectedProcessStatuses.length).toBe(0);

		unmount();
	});

	test('Be "Completed" when the initial key is "Completed"', () => {
		const {result, unmount} = renderHook(() =>
			useProcessStatus([processStatusConstants.completed])
		);

		const selectedProcessStatuses = result.current.getSelectedProcessStatuses();

		expect(selectedProcessStatuses[0].key).toBe(
			processStatusConstants.completed
		);

		unmount();
	});

	test('Be "Completed" when the initial key is "Pending"', () => {
		const {result, unmount} = renderHook(() =>
			useProcessStatus([processStatusConstants.pending])
		);

		const selectedProcessStatuses = result.current.getSelectedProcessStatuses();

		expect(selectedProcessStatuses[0].key).toBe(
			processStatusConstants.pending
		);

		unmount();
	});

	test('Be "Completed" and "Pending" when the initial keys are "Completed" and "Pending"', () => {
		const {result, unmount} = renderHook(() =>
			useProcessStatus([
				processStatusConstants.completed,
				processStatusConstants.pending
			])
		);

		const selectedProcessStatuses = result.current.getSelectedProcessStatuses();

		expect(selectedProcessStatuses[0].key).toBe(
			processStatusConstants.completed
		);
		expect(selectedProcessStatuses[1].key).toBe(
			processStatusConstants.pending
		);

		unmount();
	});
});

describe('The process status store should', () => {
	let renderer;

	beforeEach(() => {
		renderer = renderHook(
			({processStatusKeys}) => useProcessStatus(processStatusKeys),
			{
				initialProps: {
					processStatusKeys: [processStatusConstants.completed]
				}
			}
		);
	});

	afterEach(() => {
		renderer.unmount();
		renderer = null;
	});

	test('Keep the selected process statuses when the keys are the same', () => {
		const {rerender, result} = renderer;

		rerender({
			processStatusKeys: [processStatusConstants.completed]
		});

		const selectedProcessStatuses = result.current.getSelectedProcessStatuses();

		expect(selectedProcessStatuses[0].key).toBe(
			processStatusConstants.completed
		);
	});

	test('Update the selected process statuses when the keys changed', () => {
		const {rerender, result} = renderer;

		rerender({
			processStatusKeys: [processStatusConstants.pending]
		});

		const selectedProcessStatuses = result.current.getSelectedProcessStatuses();

		expect(selectedProcessStatuses[0].key).toBe(
			processStatusConstants.pending
		);
	});

	test('Return "false" when the pending status is selected', () => {
		const {rerender, result} = renderer;

		rerender({
			processStatusKeys: [processStatusConstants.pending]
		});

		const isCompletedStatusSelected = result.current.isCompletedStatusSelected();

		expect(isCompletedStatusSelected).toBe(false);
	});

	test('Return "true" when the completed status is selected', () => {
		const {rerender, result} = renderer;

		rerender({
			processStatusKeys: [processStatusConstants.completed]
		});

		const isCompletedStatusSelected = result.current.isCompletedStatusSelected();

		expect(isCompletedStatusSelected).toBe(true);
	});
});

test('The process status provider should be rendered', () => {
	const {root, unmount} = create(
		<ProcessStatusProvider
			processStatusKeys={[processStatusConstants.completed]}
		>
			<MockProcessStatusConsumer />
		</ProcessStatusProvider>
	);

	let consumer;

	act(() => {
		consumer = root.findByType(MockProcessStatusConsumer);
	});

	expect(consumer.children[0]).toBe('Completed, Pending');

	unmount();
});

const MockProcessStatusConsumer = () => {
	const {processStatuses} = useContext(ProcessStatusContext);

	return processStatuses.map(processStatus => processStatus.key).join(', ');
};
