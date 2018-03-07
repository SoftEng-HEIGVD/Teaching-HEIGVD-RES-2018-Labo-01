package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @version 2.0
 * modified by Antonio Cusanelli
 */
public class DFSFileExplorer implements IFileExplorer {

	@Override
	public void explore(File rootDirectory, IFileVisitor visitor) {
		//visit of the rootDirectory and all the sub-directories and files that are contained in
		//the function is called recursively for each sub-directories
		visitor.visit(rootDirectory);
		File[] files = rootDirectory.listFiles();

		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					explore(files[i], visitor);
				} else {
					visitor.visit(files[i]);
				}
			}
		}
	}
}
