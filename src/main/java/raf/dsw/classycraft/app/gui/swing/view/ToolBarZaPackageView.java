package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;

public class ToolBarZaPackageView extends JToolBar {
    public ToolBarZaPackageView(){
        super(HORIZONTAL);
        setFloatable(false);



        add(MainFrame.getInstance().getActionManager().getSelekcijaButton());
        add(MainFrame.getInstance().getActionManager().getBrisanjeButton());
        add(MainFrame.getInstance().getActionManager().getDodavanjeVezaButton());
        add(MainFrame.getInstance().getActionManager().getDodavanjeInterclassObjektaButton());
        add(MainFrame.getInstance().getActionManager().getDodavanjeSadrzajaKlaseButton());
        add(MainFrame.getInstance().getActionManager().getMove());
        add(MainFrame.getInstance().getActionManager().getDuplicateButton());
        add(MainFrame.getInstance().getActionManager().getMenjanjeSadrzajaButton());
        add(MainFrame.getInstance().getActionManager().getZoomInButton());
        add(MainFrame.getInstance().getActionManager().getZoomOutButton());
    }
}
