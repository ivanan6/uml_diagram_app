package raf.dsw.classycraft.app.composite.factory;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ApplicationFramework;

public class ProjectFactory extends ClassyNodeFactory{
    private static int cnt = 1;

    @Override
    ClassyNode createChild(String ime) {
        ClassyNode classyNode = null;
        classyNode = new Package(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Package" + String.valueOf(cnt));
        cnt++;
        return classyNode;
    }

    public static int getCnt() {
        return cnt;
    }

    public static void setCnt(int cnt) {
        ProjectFactory.cnt = cnt;
    }
}
