package Logic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Layer {
	static private ExecutorService es;
	
	static {
		es = Executors.newCachedThreadPool();
	}
	
	protected final Future<?> run(Runnable r) {
		return es.submit(r);
	}
}
