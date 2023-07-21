/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import LicenseDates from '../../src/main/resources/META-INF/resources/js/components/LicenseDates';
import {PermissionsProvider} from '../../src/main/resources/META-INF/resources/js/hooks/permissions';

function renderLicenseDates({permission, props}) {
	return render(
		<table>
			<tbody>
				<tr>
					<PermissionsProvider
						permissions={{updateDatePermission: permission}}
					>
						<LicenseDates
							detached
							expirationDate={new Date('2022-07-09')}
							restricted
							startDate={new Date('2021-07-09')}
							updateExpirationDate={jest.fn()}
							updateStartDate={jest.fn()}
							updateValidation={jest.fn()}
							validDates
							{...props}
						/>
					</PermissionsProvider>
				</tr>
			</tbody>
		</table>
	);
}

describe('LicenseDates', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderLicenseDates({permission: true});

		expect(container).toBeTruthy();
	});

	describe('datepicker display', () => {
		it('always displays a date picker for Start Date', () => {
			let {container} = renderLicenseDates({permission: true});

			const fullPrivilegeStartDate = container.querySelectorAll(
				'input[name="startDate"][type="hidden"]'
			);

			expect(fullPrivilegeStartDate.length).toBe(1);

			container = renderLicenseDates({permission: false}).container;

			const limitedPrivilegeStartDate = container.querySelectorAll(
				'input[name="startDate"][type="hidden"]'
			);

			expect(limitedPrivilegeStartDate.length).toBe(1);
		});

		describe('Full Privilege', () => {
			it('always displays Expiration Date datepicker', () => {
				const {container} = renderLicenseDates({permission: true});

				const expDateDatepicker = container.querySelectorAll(
					'input[name="expirationDate"][type="hidden"]'
				);

				expect(expDateDatepicker.length).toBe(1);
			});
		});

		describe('Limited Privilege', () => {
			describe('Detached Section', () => {
				describe('when Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster', () => {
					it('displays the Expiration Date datepicker', () => {
						const {container} = renderLicenseDates({
							permission: false,
							props: {
								restricted: false
							}
						});

						const expDateDatepickers = container.querySelectorAll(
							'input[name="expirationDate"][type="hidden"]'
						);

						expect(expDateDatepickers.length).toBe(1);
					});
				});

				describe('when Type is Enterpirse, Limited, OEM, or Virtual Cluster', () => {
					it('displays the Expiration Date datepicker ', () => {
						const {container} = renderLicenseDates({
							permission: false
						});

						const expDateDatepickers = container.querySelectorAll(
							'input[name="expirationDate"][type="hidden"]'
						);

						expect(expDateDatepickers.length).toBe(1);
					});
				});
			});

			describe('Non Detached Section', () => {
				describe('when Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster', () => {
					it('displays the Expiration Date datepicker ', () => {
						const {container} = renderLicenseDates({
							permission: false,
							props: {
								detached: false,
								restricted: false
							}
						});

						const expDateDatepickers = container.querySelectorAll(
							'input[name="expirationDate"][type="hidden"]'
						);

						expect(expDateDatepickers.length).toBe(1);
					});
				});

				describe('when Type is Enterpirse, Limited, OEM, or Virtual Cluster', () => {
					it('does not display the Expiration Date datepicker ', () => {
						const {container} = renderLicenseDates({
							permission: false,
							props: {
								detached: false
							}
						});

						const expDateDatepickers = container.querySelectorAll(
							'input[name="expirationDate"][type="hidden"]'
						);

						expect(expDateDatepickers.length).toBe(0);
					});
				});
			});
		});
	});
});
