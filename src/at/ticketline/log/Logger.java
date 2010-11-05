package at.ticketline.log;

public interface Logger {
	public boolean isTraceEnabled();

	public boolean isDebugEnabled();

	public boolean isInfoEnabled();

	public boolean isWarnEnabled();

	public boolean isErrorEnabled();

	public boolean isFatalEnabled();

	public void trace(Object obj, Object... params);

	public void trace(Object obj, Throwable t, Object... params);

	public void debug(Object obj, Object... params);

	public void debug(Object obj, Throwable t, Object... params);

	public void info(Object obj, Object... params);

	public void info(Object obj, Throwable t, Object... params);

	public void warn(Object obj, Object... params);

	public void warn(Object obj, Throwable t, Object... params);

	public void error(Object obj, Object... params);

	public void error(Object obj, Throwable t, Object... params);

	public void fatal(Object obj, Object... params);

	public void fatal(Object obj, Throwable t, Object... params);

}
