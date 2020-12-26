package de.hexagonsoftware.engine.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hexagonsoftware.engine.HexagonEngine;

public class ErrorHandler {
	private static final Logger logger = LogManager.getLogger("HexagonErrorHandler");
	public static HexagonEngine engine = null;
	
	public static int ERR_HANDLING = 0;
	
	public static final int ERR_MSG_BOX = 0,
							ERR_TO_CONSOLE = 1,
							ERR_IGNORE = 2;
	
	public static void reportException(Exception e) {
		logger.error("An exception was reported to the ErrorHandler! ");
		
		StringWriter sWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(sWriter);
		
		e.printStackTrace(writer);
		writer.flush();

		if (ERR_HANDLING == 0)
			e.printStackTrace();
		
		handleError(sWriter.toString());
	}

	private static void handleError(String error) {
		switch (ERR_HANDLING) {
		case 0:
			JOptionPane.showConfirmDialog(engine.getHE_WINDOW().getCVS(), error, "An error occured | See the log for further information!", JOptionPane.CANCEL_OPTION);
			break;
		case 1:
			logger.error(error);
			break;
		case 2:
			break;
		}
	}
}
