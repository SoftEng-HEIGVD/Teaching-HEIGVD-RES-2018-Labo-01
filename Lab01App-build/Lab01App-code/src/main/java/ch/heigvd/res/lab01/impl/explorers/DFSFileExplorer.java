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
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        //we visit rootDirectory
        vistor.visit(rootDirectory);

        //list all files/Directory from rootDirectory
        File[] files = rootDirectory.listFiles();
        if (files == null) {
            return;
        }

        //Sorting the arrays because window don't necessarily take them in alphabetical order
        Arrays.sort(files);
        //we go through all the file / directory of rootDirectory
        for (File file : files) {
            //if it's a file
            if (file.isFile()) {
                vistor.visit(file);
            }

            //if it's a directory
            if (file.isDirectory()) {
                //recursive call of explore with file
                this.explore(file, vistor);
            }
        }
    }
}
