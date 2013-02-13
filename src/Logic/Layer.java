package Logic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Layer {
	private static final ExecutorService es;
	private static final Logger logger;
	private static boolean logging;

	static {
		es = Executors.newCachedThreadPool();
		logger = Logger.getLogger("TEMP");
		logger.setLevel(Level.ALL);
	}
	
	public static void setLogging(boolean active) {
		logging = active;
	}

	public static boolean getLogging() {
		return logging;
	}

	protected final Future<?> run(Runnable r) {
		return es.submit(r);
	}

	protected final void Log(String s) {
		if (logging)
			logger.info(s);
	}
}
