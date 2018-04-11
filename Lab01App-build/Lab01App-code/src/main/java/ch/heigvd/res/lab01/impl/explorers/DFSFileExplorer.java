package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

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
        File[] filesList = rootDirectory.listFiles();
        
        //pre-ordre
        visitor.visit(rootDirectory);
        
        //critere d'arret de la recursion
        if(filesList == null) 
            return;
        
        for(File f : filesList) {
            //on ne visite que si f est un fichier
            if(f.isFile())
                visitor.visit(f);
            
            if(f.isDirectory())
                explore(f, visitor);
        }
    }

}
