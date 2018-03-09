package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * 
 * @author Doriane kaffo
 * 
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        // First directory
        vistor.visit(rootDirectory);

        // If root isn't a directory, we do nothing
        if (rootDirectory.listFiles() == null) {
            return;
        }

        //Explore all the directory before explore the file
        for (File file : rootDirectory.listFiles()) {
            if (file.isDirectory())// if isn't a file
            {
                explore(file, vistor);
            } 
            {
                vistor.visit(file);
            }
        }
    }
}
