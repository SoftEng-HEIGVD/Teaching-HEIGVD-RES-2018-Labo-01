package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        // we must visit root directory
        vistor.visit(rootDirectory);
        // we get the list of the files in the root directory
        File[] filesDir = rootDirectory.listFiles();

        if (filesDir != null) {
            // must be done or else the files might be explored in a wrong order
            Arrays.sort(filesDir);
            for (File file : filesDir) {
                // we will explore each directory and visit each file
                if (file.isDirectory()) {
                    explore(file, vistor);
                } else {
                    vistor.visit(file);
                }
            }
        }
    }
}
