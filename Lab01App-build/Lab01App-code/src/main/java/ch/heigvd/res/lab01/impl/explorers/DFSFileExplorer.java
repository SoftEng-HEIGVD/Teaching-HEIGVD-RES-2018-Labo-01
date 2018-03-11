package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @author modifiy by Yann Lederrey
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        // test is the root Directory exist.
        if (rootDirectory == null)
            return;

        // so we visit it.
        vistor.visit(rootDirectory);
        // we get all files/folder is the root.
        File[] depthFiles = rootDirectory.listFiles();
        // we test if these files exist
        if (depthFiles != null)
            // we sorts these files
            Arrays.sort(depthFiles);
        else
            return;

            // for every files in depthFiles we recursively call explore method.
        for (File file : depthFiles)
            explore(file, vistor);
    }
}
