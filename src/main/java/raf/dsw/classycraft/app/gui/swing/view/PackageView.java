package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.Subscriber;
import raf.dsw.classycraft.app.observer.message.MessageType;
import raf.dsw.classycraft.app.stateSablon.StateManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

@Getter
@Setter

public class PackageView extends JPanel implements Subscriber {

    private Package aPackage;
    private Project aProject;
    private ProjectExplorer aProjectExplorer;
    private JLabel imeAutora;
    private JLabel imeProjekta;
    private JTabbedPane jTabbedPane;
    private JScrollPane jScrollPane;
    private JViewport jViewport;
    private BoxLayout box;
    private StateManager stateManager;
    private ClassyTreeItem classyTreeItem;


    public PackageView(LayoutManager layoutManager){
        super(layoutManager);
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setTabPlacement(JTabbedPane.TOP);
        jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane.setPreferredSize(new Dimension(500, 300));
        imeAutora = new JLabel();
        imeProjekta = new JLabel();
        box = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(box);
        ToolBarZaPackageView toolBar = new ToolBarZaPackageView();
        add(toolBar, BorderLayout.NORTH);


    }
    public void dodajLabele(String imeAutora, String imeParenta){
        this.imeProjekta.removeAll();
        this.imeAutora.removeAll();
        this.imeProjekta.setText("Ime projekta: "+imeParenta);
        this.imeAutora.setText("Ime autora: "+imeAutora);
        add(this.imeAutora);
        add(jTabbedPane);
        add(this.imeProjekta);
        revalidate();
        repaint();
    }


    public void setaPackage(Package aPackage, Project aProject, ProjectExplorer aProjectExplorer) {
        this.aPackage = aPackage;
        Package p = aPackage;
        while(!(p.getParent() instanceof Project)){
            Package pk = (Package) p.getParent();
            pk.addSubscriber(this);
            p = (Package) p.getParent();

        }
        aPackage.addSubscriber(this);
        aProject.addSubscriber(this);
        aProjectExplorer.addSubscriber(this);
        dodajTab();

    }

    private void dodajTab() {
        for(ClassyNode c : aPackage.getChildren()){
            if(c instanceof Diagram){
                DiagramView diagramView = new DiagramView((Diagram) c, this, classyTreeItem);

                jTabbedPane.addTab(diagramView.getImeTaba(),diagramView);

                jScrollPane = new JScrollPane(diagramView);
                jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                jViewport=jScrollPane.getViewport();
                jScrollPane.setViewportView(diagramView);
                diagramView.setViewport(jViewport);

                jTabbedPane.add(diagramView.getDiagram().getName(), jScrollPane);
            }
        }




//        jTabbedPane.revalidate();
//        jTabbedPane.repaint();
    }
    public void promenaImena(String novoIme, String staroIme){
        boolean f = false;
            if(novoIme == "null"){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("fefewfewfew", MessageType.NAME_ALREADY_TAKEN, LocalDateTime.now());
                f = true;
                return;
            }

            if(f == false){
            int b =0;
            for(int i = 0; i < jTabbedPane.getTabCount(); i++){
                if(staroIme.equals(jTabbedPane.getTitleAt(i))){
                    b = i;;
                    break;
                }

            }
            jTabbedPane.setTitleAt(b, novoIme);
        }
    }
    public void refreshTabs(Diagram var1){

        for (int i =0; i< jTabbedPane.getTabCount();i++){

            if(((DiagramView) jTabbedPane.getComponentAt(i)).getDiagram().getName().equals(var1.getName()))
                jTabbedPane.removeTabAt(i);

        }
    }

    @Override
    public void update(Object var1,String tekst) {
        if(var1 instanceof Project && tekst=="delete"){
            dodajLabele("","");
        }
        if(var1 instanceof Diagram && tekst=="delete"){

            refreshTabs((Diagram)var1);

        }
        else if(var1 instanceof Diagram && tekst=="add") {

                DiagramView diagramView = new DiagramView((Diagram) var1, this, classyTreeItem);

                jTabbedPane.addTab(diagramView.getImeTaba(),diagramView);
            jScrollPane = new JScrollPane(diagramView);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jViewport=jScrollPane.getViewport();
            jScrollPane.setViewportView(diagramView);
            diagramView.setViewport(jViewport);

            jTabbedPane.add(diagramView.getDiagram().getName(), jScrollPane);

            }
        }
    public void startBrisanje(String s){
        stateManager = new StateManager(s);
        this.stateManager.setBrisanje(s);
    }
    public void startMove(String s){
        stateManager = new StateManager(s);
        this.stateManager.setMoveState();
    }
    public void startDodavanjeInterclassObjekta(String s){
        stateManager = new StateManager(s);
        this.stateManager.setDodavanjeInterclassObjekata(s);
    }
    public void startDodavanjeSadrzajaKlase(String s){
        stateManager = new StateManager(s);
        this.stateManager.setDodavanjeSadzrajaKlase();
    }
    public void startDuplicate(){
        String s = null;
        stateManager = new StateManager(s);
        this.stateManager.setDuplicate();
    }
    public void startDodavanjeVeze(String s){
        stateManager = new StateManager(s);
        this.stateManager.setDodavanjeVeza(s);
    }
    public void startSelekcija(String s){
        stateManager = new StateManager(s);
        this.stateManager.setSelekcija();
    }
    public void startMenjanje(String s){
        stateManager = new StateManager(s);
        this.stateManager.setMenjanjeSadrzaja();
    }
    public void startZoomIn(String s){
        stateManager = new StateManager(s);
        this.stateManager.setStateZoomIn();
    }
    public void startZoomOut(String s){
        stateManager = new StateManager(s);
        this.stateManager.setStateZoomOut();
    }

    public void misKliknut(int x, int y, DiagramView diagramView){
    this.stateManager.getCurrentState().misKliknut(x, y, diagramView);
    }
    public void misPovucen(int x, int y, DiagramView diagramView){
        this.stateManager.getCurrentState().misPovucen(x, y, diagramView);
    }
    public void misOtpusten(int x, int y, DiagramView diagramView){
        this.stateManager.getCurrentState().misOtpusten(x, y, diagramView);
    }
}



