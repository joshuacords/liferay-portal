/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.imageio.plugins.internal.activator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.spi.ServiceRegistry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Adolfo Pérez
 */
public class ImageIOPluginsBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) {
		_register(ImageReaderSpi.class, _imageReaderSpiSet);
		_register(ImageWriterSpi.class, _imageWriterSpiSet);
	}

	@Override
	public void stop(BundleContext bundleContext) {
		_unregister(_imageReaderSpiSet);
		_unregister(_imageWriterSpiSet);
	}

	private <T> void _register(Class<T> clazz, Set<T> registeredProviders) {
		IIORegistry iioRegistry = IIORegistry.getDefaultInstance();

		Iterator<T> providers = ServiceRegistry.lookupProviders(
			clazz, ImageIOPluginsBundleActivator.class.getClassLoader());

		while (providers.hasNext()) {
			registeredProviders.add(providers.next());
		}

		iioRegistry.registerServiceProviders(registeredProviders.iterator());
	}

	private <T> void _unregister(Set<T> registeredProviders) {
		IIORegistry iioRegistry = IIORegistry.getDefaultInstance();

		for (T provider : registeredProviders) {
			iioRegistry.deregisterServiceProvider(provider);
		}

		registeredProviders.clear();
	}

	private final Set<ImageReaderSpi> _imageReaderSpiSet = new HashSet<>();
	private final Set<ImageWriterSpi> _imageWriterSpiSet = new HashSet<>();

}