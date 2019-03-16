package org.redquark.osgi.foundation.activators;

import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anirudh Sharma
 *
 */
public class Activator implements BundleActivator {

	// Logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// This variable will get all the bundle activators defined by manifest headers
	private List<BundleActivator> activators;

	/**
	 * This method is called when the bundle is started. All the initialization
	 * tasks should be performed here.
	 */
	@Override
	public void start(BundleContext context) throws Exception {

		// Getting all the bundle activators
		activators = getBundleActivators(context);
		log.info("Number of activators starting: {}", activators.size());

		// Starting all the activators one by one
		for (BundleActivator activator : activators) {
			activator.start(context);
		}
	}

	/**
	 * This method is called when the bundle is stopped. All the clean up tasks
	 * should be performed here.
	 */
	@Override
	public void stop(BundleContext context) throws Exception {

		log.info("Number of activators stopping: {}", activators.size());
		// Stop all the activators one by one
		for (BundleActivator activator : activators) {
			activator.stop(context);
		}
	}

	private List<BundleActivator> getBundleActivators(BundleContext context) throws Exception {

		// Get headers of the bundle
		Dictionary<String, String> headers = context.getBundle().getHeaders();

		// Get the all the comma separated activators
		String activators = (String) headers.get("Multi-Bundle-Activator");

		List<BundleActivator> activatorList = new LinkedList<>();

		if (activators != null) {

			StringTokenizer stringTokenizer = new StringTokenizer(activators, ",");

			while (stringTokenizer.hasMoreTokens()) {

				activatorList.add((BundleActivator) Class.forName(stringTokenizer.nextToken()).newInstance());
			}
		}

		return activatorList;
	}

}
