package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyRepository;

public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer projectExplorer;

    public ClassyRepositoryImplementation() {
        this.projectExplorer = new ProjectExplorer("Project Explorer");
    }

    @Override
    public ProjectExplorer getRoot() {
        return projectExplorer;
    }
}
