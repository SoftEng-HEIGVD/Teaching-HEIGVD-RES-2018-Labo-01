package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * Modified by : Léo Cortès
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        // Exlpore the root and list every file inside
        vistor.visit(rootDirectory);
        File[] files = rootDirectory.listFiles();

        if (files != null) {
            Arrays.sort(files);
        }

        // files must be non null
        if (files != null) {
            // Going through each file
            for (File file : files) {
                // If the file is a directory, we can explore it
                //if(file.isDirectory())
                explore(file, vistor);
            }
        }
    }

}
