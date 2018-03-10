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
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {

        // say HI
        visitor.visit(rootDirectory);

        // is there something else to explore in here ?
        if(rootDirectory.isDirectory()) {

            // woups I shoulden't be here
            if(!rootDirectory.canRead()){
                throw new RuntimeException("Access denies to this directory: " + rootDirectory );
            }

            // access granted get me all you got
            File[] files = rootDirectory.listFiles();


            try {
                //first we say hi to all files in the root directory
                for (File file : files) {
                    if (file.isFile()) {
                        explore(file, visitor);
                    }
                }

                // then we recurse on the subfolders
                for (File file : files) {
                    if (!file.isFile()) {
                        explore(file, visitor);
                    }
                }
            } catch (NullPointerException npe) {
                // empty folder
            }
        }
    }
}
