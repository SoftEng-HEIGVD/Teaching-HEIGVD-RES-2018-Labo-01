package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import javassist.bytecode.AnnotationsAttribute;
import javassist.compiler.ast.Visitor;

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

       
        vistor.visit(rootDirectory);
        

        if (rootDirectory.isDirectory()) {
            File[] fliles = rootDirectory.listFiles();
            Arrays.sort(fliles);
            for (File o : fliles) {
                if (o.isFile()) {
                    vistor.visit(o);
                } else if (o.isDirectory()) {
                    explore(o, vistor);
                }
            }

        }

        /*vistor.visit(rootDirectory);

        if (rootDirectory.isDirectory()) {
            for (File o : rootDirectory.listFiles()) {
                if (o.isFile()) {
                    vistor.visit(o);
                } else if (o.isDirectory()) {
                    explore(rootDirectory, vistor);
                }
            }

        }*/
    }

}
