package raf.dsw.classycraft.app.composite.factory;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.*;
import raf.dsw.classycraft.app.composite.implementation.Package;

public class FactoryUtil{
    private static ProjectExplorerFactory projectExplorer = null;
 /* private static ProjectFactory project = null;
    private static PackageFactory packagee = null;
    private static DiagramFactory diagram = null;*/
    public static ClassyNodeFactory createFactory(ClassyNode tip){
        if(tip instanceof ProjectExplorer){
            projectExplorer = new ProjectExplorerFactory();
            return projectExplorer;
        }
        else if(tip instanceof Project){
            return new ProjectFactory();
        }
        else if(tip instanceof Package){
            return new PackageFactory();
        }
        else if(tip instanceof Diagram){
            return new DiagramFactory();
        }
        else if(tip instanceof DiagramElements){
            return new DiagramElementsFactory();
        }
        return null;
    }
}
