package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.ArrayList;
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

    /**
     * @param rootDirectory the directory where to start the traversal
     * @param vistor        defines the operation to be performed on each file
     * @breief this method iterates on all the files of the file system, and apply a visit each encountered file
     */
    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        File[] files = rootDirectory.listFiles();
        ArrayList<File> filesToTreat = new ArrayList<File>();

        //visit of the root file
        vistor.visit(rootDirectory);

        if (files != null) {
            //the files must be sorted for the OS may not automatically order the files before processing them
            Arrays.sort(files);
            for (File f : files) {
                //first we iterate on the directories to explore the sub-directories
                if (f.isDirectory()) {
                    explore(f,vistor);
                }
            }
            //then we iterate on the files to visit them
            for (File f : files){
                if (f.isFile()) {
                    vistor.visit(f);
                }
            }
        }
    }
}
