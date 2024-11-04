package raf.dsw.classycraft.app.composite.factory;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.core.ApplicationFramework;

public class PackageFactory extends ClassyNodeFactory{
    private static int cnt = 1;
    private static int cnt2 = 1;
    @Override
    ClassyNode createChild(String ime) {
        ClassyNode classyNode = null;

            if(ime.toLowerCase().equals("diagram")){
                classyNode = new Diagram(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Diagram" + String.valueOf(cnt));
                cnt++;
            }
            else if(ime.toLowerCase().equals("package")){
                classyNode = new Package(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Package" + String.valueOf(ProjectFactory.getCnt()));
                ProjectFactory.setCnt(ProjectFactory.getCnt()+1);
            }



        return classyNode;
    }
}
