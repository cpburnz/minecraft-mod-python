package cpburnz.minecraft.pymod;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

/**
 * A wrapper around the FML logger.
 */
public class Logger {

	/**
	 * The name to use when logging.
	 */
	private String logName;

	/**
	 * Initializes the instance.
	 *
	 * *logName* is the name to use when logging.
	 */
	public Logger(String logName) {
		this.logName = logName;
	}

	/**
	 * Logs the message.
	 *
	 * *level* is the logging level.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public log(Level level, String format, Object... args) {
		FMLLog.log(this.logName, level, format, args)
	}

	/**
	 * Logs the message along with an exception.
	 *
	 * *level* is the logging level.
	 *
	 * *ex* is the exception.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public log(Level level, Throwable ex, String format, Object... args) {
		FMLLog.log(this.logName, level, ex, format, args);
	}

	/**
	 * Logs the error message.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public severe(String format, Object... args) {
		this.log(Level.SEVERE, format, args);
	}

	/**
	 * Logs the error message along with an exception.
	 *
	 * *ex* is the exception.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public severe(Throwable ex, String format, Object... args) {
		this.log(Level.SEVERE, ex, format, args);
	}

	/**
	 * Logs the warning message.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public warning(String format, Object... args) {
		this.log(Level.WARNING, format, args);
	}

	/**
	 * Logs the warning message along with an exception.
	 *
	 * *ex* is the exception.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public warning(Throwable ex ,String format, Object... args) {
		this.log(Level.WARNING, ex, format, args);
	}

	/**
	 * Logs the general info message.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public info(String format, Object... args) {
		this.log(Level.INFO, format, args);
	}

	/**
	 * Logs the fine info message.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public fine(String format, Object... args) {
		this.log(Level.FINE, format, args);
	}

	/**
	 * Logs the finer info message.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public finer(String format, Object... args) {
		this.log(Level.FINER, format, args);
	}

	/**
	 * Logs the finest info message.
	 *
	 * *format* is the message to format.
	 *
	 * *args* contains the message format arguments.
	 */
	public finest(String format, Object... args) {
		this.log(Level.FINEST, format, args);
	}

};
