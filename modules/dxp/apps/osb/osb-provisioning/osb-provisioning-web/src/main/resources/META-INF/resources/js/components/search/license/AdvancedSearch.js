/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayPanel from '@clayui/panel';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

import useClickOutside from '../../../hooks/useClickOutside';
import {NAMESPACE} from '../../../utilities/constants';
import {submitOnEnter} from '../../../utilities/helpers';
import CheckboxGroups from '../CheckboxGroups';
import Dates from './Dates';
import LicenseDetails from './LicenseDetails';

const AdvancedSearch = React.forwardRef(
	(
		{
			clickOutsideCallback,
			formAction,
			licenseTypes,
			productVersions,
			products
		},
		ref
	) => {
		const [isAndOperator, setIsAndOperator] = useState(true);

		const formRef = useRef();

		useClickOutside(clickOutsideCallback, ref);

		function handleOnCheck() {
			setIsAndOperator(!isAndOperator);
		}

		function handleOnKeyDown(event) {
			submitOnEnter(event, formRef);
		}

		return (
			<div className="advanced-search-container" id="advancedSearch">
				<form
					action={formAction}
					method="get"
					name="advancedSearch"
					onKeyDown={handleOnKeyDown}
					ref={formRef}
				>
					<input
						name="p_p_id"
						type="hidden"
						value="com_liferay_osb_provisioning_web_portlet_LicensesPortlet"
					/>
					<input name="p_p_lifecycle" type="hidden" value="0" />
					<input
						name={`${NAMESPACE}advancedSearch`}
						type="hidden"
						value="true"
					/>

					<div className="form-group search-match">
						<h5 className="form-check-inline">
							{Liferay.Language.get('match')}:
						</h5>

						<div className="form-check form-check-inline">
							<label className="form-check-label">
								<input
									checked={isAndOperator}
									className="form-check-input"
									name={`${NAMESPACE}andOperator`}
									onChange={() => handleOnCheck()}
									type="radio"
									value={true}
								/>
								<span className="form-check-label-text">
									{Liferay.Language.get('all')}
								</span>
							</label>
						</div>

						<div className="form-check form-check-inline">
							<label className="form-check-label">
								<input
									checked={!isAndOperator}
									className="form-check-input"
									name={`${NAMESPACE}andOperator`}
									onChange={() => handleOnCheck()}
									type="radio"
									value={false}
								/>
								<span className="form-check-label-text">
									{Liferay.Language.get('any')}
								</span>
							</label>
						</div>
					</div>

					<ClayPanel
						collapsable
						defaultExpanded={true}
						displayTitle={Liferay.Language.get('general-details')}
						displayType="secondary"
						showCollapseIcon={true}
					>
						<LicenseDetails />
					</ClayPanel>

					{!!licenseTypes.length && (
						<ClayPanel
							collapsable
							displayTitle={Liferay.Language.get('license-type')}
							displayType="secondary"
							showCollapseIcon={true}
						>
							<div className="panel-body">
								<div className="col-md-12 form-group">
									<CheckboxGroups
										columns={4}
										fieldValues={licenseTypes}
										inputName="types"
									/>
								</div>
							</div>
						</ClayPanel>
					)}

					{!!products.length && (
						<ClayPanel
							collapsable
							displayTitle={Liferay.Language.get('product')}
							displayType="secondary"
							showCollapseIcon={true}
						>
							<div className="panel-body">
								<div className="col-md-12 form-group">
									<CheckboxGroups
										columns={4}
										fieldValues={products}
										inputName="products"
									/>
								</div>
							</div>
						</ClayPanel>
					)}

					{!!productVersions.length && (
						<ClayPanel
							collapsable
							displayTitle={Liferay.Language.get(
								'product-version'
							)}
							displayType="secondary"
							showCollapseIcon={true}
						>
							<div className="panel-body">
								<div className="col-md-12 form-group">
									<CheckboxGroups
										columns={4}
										fieldValues={productVersions}
										inputName="productVersions"
									/>
								</div>
							</div>
						</ClayPanel>
					)}

					<ClayPanel
						collapsable
						displayTitle={Liferay.Language.get('dates')}
						displayType="secondary"
						showCollapseIcon={true}
					>
						<Dates />
					</ClayPanel>

					<div
						className="button-holder button-holder-lg"
						role="group"
					>
						<button
							className="btn btn-secondary"
							role="button"
							type="reset"
						>
							{Liferay.Language.get('clear')}
						</button>

						<button
							className="btn btn-primary"
							role="button"
							type="submit"
						>
							{Liferay.Language.get('search')}
						</button>
					</div>
				</form>
			</div>
		);
	}
);

AdvancedSearch.propTypes = {
	clickOutsideCallback: PropTypes.func.isRequired,
	formAction: PropTypes.string.isRequired,
	licenseTypes: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType[(PropTypes.number, PropTypes.string)]
		})
	).isRequired,
	productVersions: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType[(PropTypes.number, PropTypes.string)]
		})
	).isRequired,
	products: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType[(PropTypes.number, PropTypes.string)]
		})
	).isRequired
};

export default AdvancedSearch;
