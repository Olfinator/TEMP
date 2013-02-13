package MessageLibrary;

public interface MessageTester<T> {
	public boolean Test(T type, Object[] args);
	public String[] ConvertToString(T type, Object[] args);
}
