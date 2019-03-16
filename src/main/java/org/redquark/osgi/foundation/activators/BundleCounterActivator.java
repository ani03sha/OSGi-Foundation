/**
 * 
 */
package org.redquark.osgi.foundation.activators;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Admin
 *
 */
public class BundleCounterActivator implements BundleActivator, BundleListener {
	
	// Default logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// BundleContext
	private BundleContext bundleContext;

	/**
	 * This method is called when the bundle is started. All the initialization
	 * tasks should be performed here.
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		
		// Initializing the bundle context
		this.bundleContext = context;
		
		// Registering the bundle listening event
		bundleContext.addBundleListener(this);
		
		// Logging the total number of bundles
		log.info("Total bundles: {}", bundleCount());
	}

	/**
	 * This method is called when the bundle is stopped. All the clean up tasks
	 * should be performed here.
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		bundleContext.removeBundleListener(this);
	}
	
	/**
	 * Receives notification that a bundle has had a lifecycle change.
	 */
	@Override
	public void bundleChanged(BundleEvent event) {	
		
		// Switch case for each state of bundle
		switch (event.getType()) {
		
		case BundleEvent.INSTALLED:
			log.info("Bundle installed: {}", bundleCount());
			break;

		case BundleEvent.UNINSTALLED:
			log.info("Bundle uninstalled: {}", bundleCount());
			break;
		}
	}
	
	/**
	 * 
	 * @return bundle count
	 */
	private int bundleCount() {
		return bundleContext.getBundles().length;
	}
}
