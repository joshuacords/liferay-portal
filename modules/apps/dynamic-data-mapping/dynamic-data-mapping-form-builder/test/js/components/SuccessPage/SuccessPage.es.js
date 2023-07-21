/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import dom from 'metal-dom';
import JSXComponent from 'metal-jsx';

import SuccessPage from '../../../../src/main/resources/META-INF/resources/js/components/SuccessPage/SuccessPage.es';
import SuccessPageSettings from '../../__mock__/mockSuccessPage.es';

let component;
let successPageSettings;

const withStore = context => {
	return class WithContext extends JSXComponent {
		getChildContext() {
			return {
				store: this,
				...context
			};
		}

		render() {
			const SuccessPageTag = SuccessPage;

			return <SuccessPageTag {...this.props} />;
		}
	};
};

describe('SuccessPage', () => {
	beforeEach(() => {
		successPageSettings = JSON.parse(JSON.stringify(SuccessPageSettings));

		jest.useFakeTimers();
	});

	afterEach(() => {
		if (component) {
			component.dispose();
		}

		successPageSettings = null;
	});

	it('renders the component', () => {
		const Component = withStore({});

		component = new Component({
			contentLabel: 'Content',
			successPageSettings,
			titleLabel: 'Title'
		});

		jest.runAllTimers();

		expect(component).toMatchSnapshot();
	});

	it('emits event when page title is changed', () => {
		const dispatch = jest.fn();

		const Component = withStore({dispatch});

		component = new Component({
			editingLanguageId: 'en_US',
			successPageSettings: {}
		});

		const titleNode = component.element.querySelector(
			'input[data-setting="title"]'
		);

		titleNode.value = 'Some title';
		dom.triggerEvent(titleNode, 'input', {});

		expect(dispatch).toHaveBeenCalledWith('successPageChanged', {
			title: {
				en_US: 'Some title'
			}
		});
	});

	it('emits event when page body is changed', () => {
		const dispatch = jest.fn();

		const Component = withStore({dispatch});

		component = new Component({
			editingLanguageId: 'en_US',
			successPageSettings: {}
		});

		const bodyNode = component.element.querySelector(
			'input[data-setting="body"]'
		);

		bodyNode.value = 'Some description';
		dom.triggerEvent(bodyNode, 'input', {});

		expect(dispatch).toHaveBeenCalledWith('successPageChanged', {
			body: {
				en_US: 'Some description'
			}
		});
	});
});
