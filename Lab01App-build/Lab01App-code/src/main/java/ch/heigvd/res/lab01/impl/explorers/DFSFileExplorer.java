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
	public void explore(File rootDirectory, IFileVisitor vistor) {
		
		vistor.visit(rootDirectory); // write the path of the directory
		
		File[] tabFiles = rootDirectory.listFiles(); // we list all the files in our directory
		
		if (tabFiles != null) {
			Arrays.sort(tabFiles); // we sort the files to have the same result on each OS
			
			// we explore the directories and the files in a DFS way
			for (File file : tabFiles) {
				if (file.isDirectory())
					explore(file, vistor);
				else if(file.isFile()){
					vistor.visit(file);
				}
			}
		}
	}
	
}
