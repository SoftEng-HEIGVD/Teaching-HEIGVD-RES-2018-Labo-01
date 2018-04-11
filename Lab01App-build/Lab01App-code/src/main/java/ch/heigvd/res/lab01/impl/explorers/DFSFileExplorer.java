package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.beans.Visibility;
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
        // we apply the visit method for EVERY element in the file system tree
        visitor.visit(rootDirectory);

        // we stop the recursion if rootDirectory doesn't exist nor if it is a file
        if(!rootDirectory.exists() || rootDirectory.isFile()) return;

        // rootDirectory is a directory, so we go deeper in the recursion
        // with every element in this directory
        File[] listFile = rootDirectory.listFiles();
        for( File file : listFile){
            explore(file, visitor);
        }
    }

}
