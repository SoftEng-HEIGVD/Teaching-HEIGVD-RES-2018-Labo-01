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

        vistor.visit(rootDirectory);

        // If the rootDirectory is not a directory, exit the function. Required to pass the noFileTest.
        if (!rootDirectory.isDirectory()) {
            return;
        }

        // get the content of the rootDirectory and sort it
        File[] listOfContent = rootDirectory.listFiles();
        Arrays.sort(listOfContent);

        // iterate over the list, if the current element is a file, apply the vistor.visit method to it
        // otherwise, it's a directory so call the explore method over it
        for (File f : listOfContent) {
            if (f.isFile()) {
                vistor.visit(f);
            }

            if (f.isDirectory()) {
                explore(f, vistor);
            }
        }
    }
}
