package app;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import parse.QtUiFileParser;

public class WebApplicationView {

	private static Logger LOGGER = Logger.getLogger(WebApplicationView.class.getName());
	private static FileHandler FILEHANDLER = null;

	public static void init(){
		try {
			FILEHANDLER = new FileHandler("logs.log", true);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		Logger l = Logger.getLogger("");
		FILEHANDLER.setFormatter(new SimpleFormatter());
		l.addHandler(FILEHANDLER);
		l.setLevel(Level.CONFIG);
	}
	
	public static void main(String[] args) {
		
		init();
		
		LOGGER.info("Started to work");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter uiFilter = new FileNameExtensionFilter("Ui Files", "ui");
		fileChooser.setFileFilter(uiFilter);
		File selectedFile = null;
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getName());
		}
		if(selectedFile != null){
			LOGGER.info("File " + selectedFile.getName() + " is chosen");
			QtUiFileParser uiParser = new QtUiFileParser();
			uiParser.parse(selectedFile);
		}
		else{
			LOGGER.info("File is not chosen");
		}
		LOGGER.info("Finished work");
		
	}

}
