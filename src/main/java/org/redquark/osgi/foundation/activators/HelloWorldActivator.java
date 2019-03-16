package org.redquark.osgi.foundation.activators;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anirudh Sharma
 *
 */
public class HelloWorldActivator implements BundleActivator {

	// Logger to log events - SLF4J logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * This method is called when the bundle is started. All the initialization
	 * tasks should be performed here.
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		log.info("Hello, World!");
	}

	/**
	 * This method is called when the bundle is stopped. All the clean up tasks
	 * should be performed here.
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		log.info("Goodbye, World!");
	}

}
