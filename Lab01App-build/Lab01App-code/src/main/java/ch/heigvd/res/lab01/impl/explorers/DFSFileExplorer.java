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
    public void explore(File rootDirectory, IFileVisitor visitor) {
        if (rootDirectory != null) {
            visitor.visit(rootDirectory);
            exploration(rootDirectory, visitor);
        }

    }

    private void exploration(File directory, IFileVisitor visitor) {
        if (directory == null) {
            return;
        }
        // Retrieve the list of the files
        File[] filesDirectory = directory.listFiles();

        if (filesDirectory != null) {
            // sort the files
            Arrays.sort(filesDirectory);
            // browse and visit the differents files
            for (File f : filesDirectory) {

                visitor.visit(f);
                if (f.isDirectory()) {
                    exploration(f, visitor);
                }

            }
        }
    }

}
