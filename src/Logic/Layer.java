package Logic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Layer {
	private static final ExecutorService es;
	protected static final Logger logger;
	private static boolean logging;

	static {
		es = Executors.newCachedThreadPool();
		logger = Logger.getLogger("TEMP");
		logger.setLevel(Level.OFF);
	}
	
	public static void setLogging(boolean active) {
		logger.setLevel(active ? Level.ALL : Level.OFF);
		logging = active;
	}

	public static boolean getLogging() {
		return logging;
	}

	protected final Future<?> run(Runnable r) {
		return es.submit(r);
	}
}
