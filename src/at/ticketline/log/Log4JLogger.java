package at.ticketline.log;

import org.apache.log4j.Level;

public class Log4JLogger implements Logger {
	private org.apache.log4j.Logger log = null;
	protected static char sign = '#';

	public Log4JLogger(String name) {
		this.log = org.apache.log4j.Logger.getLogger(name);
	}

	public Log4JLogger(Class<?> clazz) {
		this.log = org.apache.log4j.Logger.getLogger(clazz);
	}

	@Override
	public void debug(Object obj, Object... params) {
		if (this.isDebugEnabled()) {
			this.log.debug(this.buildLogMessage(obj,params));
		}
	}

	@Override
	public void debug(Object obj, Throwable t, Object... params) {
		if (this.isDebugEnabled()) {
			this.log.debug(this.buildLogMessage(obj,params),t);
		}
	}

	@Override
	public void error(Object obj, Object... params) {
		if (this.isErrorEnabled()) {
			this.log.error(this.buildLogMessage(obj,params));
		}
	}

	@Override
	public void error(Object obj, Throwable t, Object... params) {
		if (this.isErrorEnabled()) {
			this.log.error(this.buildLogMessage(obj,params),t);
		}
	}

	@Override
	public void fatal(Object obj, Object... params) {
		if (this.isFatalEnabled()) {
			this.log.fatal(this.buildLogMessage(obj,params));
		}
	}

	@Override
	public void fatal(Object obj, Throwable t, Object... params) {
		if (this.isFatalEnabled()) {
			this.log.fatal(this.buildLogMessage(obj,params),t);
		}
	}

	@Override
	public void info(Object obj, Object... params) {
		if (this.isInfoEnabled()) {
			this.log.info(this.buildLogMessage(obj,params));
		}
	}

	@Override
	public void info(Object obj, Throwable t, Object... params) {
		if (this.isInfoEnabled()) {
			this.log.info(this.buildLogMessage(obj,params),t);
		}
	}

	@Override
	public boolean isDebugEnabled() {
		return this.log.isEnabledFor(Level.DEBUG);
	}

	@Override
	public boolean isErrorEnabled() {
		return this.log.isEnabledFor(Level.ERROR);
	}

	@Override
	public boolean isFatalEnabled() {
		return this.log.isEnabledFor(Level.FATAL);
	}

	@Override
	public boolean isInfoEnabled() {
		return this.log.isEnabledFor(Level.INFO);
	}

	@Override
	public boolean isTraceEnabled() {
		return this.log.isEnabledFor(Level.TRACE);
	}

	@Override
	public boolean isWarnEnabled() {
		return this.log.isEnabledFor(Level.WARN);
	}

	@Override
	public void trace(Object obj, Object... params) {
		if (this.isTraceEnabled()) {
			this.log.trace(this.buildLogMessage(obj,params));
		}
	}

	@Override
	public void trace(Object obj, Throwable t, Object... params) {
		if (this.isTraceEnabled()) {
			this.log.trace(this.buildLogMessage(obj,params),t);
		}
	}

	@Override
	public void warn(Object obj, Object... params) {
		if (this.isWarnEnabled()) {
			this.log.warn(this.buildLogMessage(obj,params));
		}
	}

	@Override
	public void warn(Object obj, Throwable t, Object... params) {
		if (this.isWarnEnabled()) {
			this.log.warn(this.buildLogMessage(obj,params),t);
		}
	}

	private Object buildLogMessage(Object obj, Object[] params) {
		String msg = obj.toString();
		if ((params == null) || (params.length == 0)) {
			return msg;
		}

		StringBuilder sb = new StringBuilder(obj.toString().length());
		boolean variable = false;
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) == Log4JLogger.sign) {
				if (variable) {
					sb.append(Log4JLogger.sign); // output sign if multiple signs after each other
				}
				variable = true;
			}
			else if (variable) {
				variable = false;
				char c = msg.charAt(i);
				if (Character.isDigit(c)) {
					int pos = Character.getNumericValue(c);
					if (pos < params.length) {
						sb.append(params[pos]); 
					}
					else {
						sb.append(Log4JLogger.sign).append(c);
					}
				}
				else {
					sb.append(Log4JLogger.sign).append(c);
				}
			}
			else {
				sb.append(msg.charAt(i));
			}
		}
		return sb.toString();
	}
}
