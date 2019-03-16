package org.redquark.osgi.foundation.activators;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Anirudh Sharma
 *
 */
public class HeartbeatActivator implements BundleActivator {

	// Default logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// Heartbeat thread
	private Thread heartbeat;

	/**
	 * This method is called when the bundle is started. All the initialization
	 * tasks should be performed here.
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		
		log.info("Starting thread");

		// Runnable job that will log the message after five seconds
		Runnable job = new Runnable() {

			@Override
			public void run() {
				try {
					while (!Thread.currentThread().isInterrupted()) {
						Thread.sleep(5000);
						log.info("Heartbeating...");
					}
				} catch (Exception e) {
					log.info("Heartbeat is interrupted!!!");
				}
			}
		};

		// Wrapping the Runnable job into Thread object
		heartbeat = new Thread(job);

		// Starting the thread
		heartbeat.start();
	}

	/**
	 * This method is called when the bundle is stopped. All the clean up tasks
	 * should be performed here.
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		log.info("Interrupting thread");
		heartbeat.interrupt();
	}
}
