package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first exploration of the file system and invokes the visitor for every encountered node (file and
 * directory). When the explorer reaches a directory, it visits all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {
	
	@Override
	public void explore(File rootDirectory, IFileVisitor visitor) {
		// visit rootDirectory
		visitor.visit(rootDirectory);
		
		// explore the folders or visit the files contained in rootDirectory
		
		File[] tabFiles = rootDirectory.listFiles();
		if (tabFiles != null) {
			Arrays.sort(tabFiles); // Sort the tabFiles to visit files and folder by alphabetical order
			for (File tabFile : tabFiles) {  // We start by explore all the directories
				if (tabFile.isDirectory()) {
					explore(tabFile, visitor);
				}
			}
			
			for (File tabFile : tabFiles) { // then we visit all the files
				if (tabFile.isFile()) {
					visitor.visit(tabFile);
				}
			}
		}
		
	}
	
}
