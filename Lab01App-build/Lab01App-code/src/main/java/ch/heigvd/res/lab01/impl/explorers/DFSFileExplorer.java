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
 *
 * @author Cedric Lankeu
 *
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) 
    {
        // check if files list is empty
        if (rootDirectory == null) {
            return;
        }
        
        // then, visit current directory
        vistor.visit(rootDirectory);
        
        File[] listeOfFile = rootDirectory.listFiles();
        if (listeOfFile == null) {// here, we are processing the files list check
            return;
        }
        // sorting the list of files 
        Arrays.sort(listeOfFile);
        //  here, we process the directories
        for (File file : listeOfFile) {
            explore(file, vistor);
        }
    }
}
