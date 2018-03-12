package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (rootDirectory != null) {
            vistor.visit(rootDirectory); // visit Directories

            File[] files = rootDirectory.listFiles();
            if (files != null) {
                if (files.length != 0) {
                    Arrays.sort(files);  // sort the array of file to get the same result on every OS
                    for (File file : files) {
                        if (file.isDirectory()) {
                            explore(file, vistor); // recursive call for the directories
                        } else {
                            vistor.visit(file); // visit files
                        }
                    }
                }
            }
        }
    }

}
