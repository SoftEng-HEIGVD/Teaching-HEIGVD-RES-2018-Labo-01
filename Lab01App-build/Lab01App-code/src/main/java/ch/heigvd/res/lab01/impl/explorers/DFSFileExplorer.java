package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;

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

        // visit current node
        visitor.visit(rootDirectory);
        
        if (rootDirectory.isDirectory() == true) {
            
            // list of the subdirectories, then recursive call (directories) in order to explore further subdirectories
            for (File sub : rootDirectory.listFiles(directoryFilter)) {
                 
                explore(sub, visitor);
            }


            // list of the subfiles, then visit of subfiles
            for (File sub : rootDirectory.listFiles(fileFilter)) {
                
                visitor.visit(sub);
            }
            
        }
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
    
    // using anonymous class for a filter that returns directories (override of method accept to adapt to only directories)
    private FileFilter directoryFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    };
    
    // using anonymous class for a filter that returns files (override of method accept to adapt to only files)
    private FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isFile();
        }
    };
    
}