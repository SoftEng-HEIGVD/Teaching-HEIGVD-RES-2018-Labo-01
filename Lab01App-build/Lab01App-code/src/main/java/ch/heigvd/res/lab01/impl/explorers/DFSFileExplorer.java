package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @author Patrick Neto
 */
public class DFSFileExplorer implements IFileExplorer {
    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        try {
            vistor.visit(rootDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Don't execute exploration if the argument isn't a directory)
        if(rootDirectory.isDirectory()) {
            File[] fileList = rootDirectory.listFiles();

            Arrays.sort(fileList);

            for (File file : fileList) {
                if (file.isDirectory()) {
                    explore(file, vistor);
                }
            }
        }//end if(rootDirectory.isDirectory())
    }

}
