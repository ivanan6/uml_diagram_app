package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.stateSablon.Duplicate;

@Getter
@Setter
public class ActionManager{
    private Move move;
    private NewProjectAction newProjectAction;
    private ExitAction exitAction;
    private  AboutUs aboutUs;
    private DeleteNode deleteNode;
    private BrisanjeButton brisanjeButton;
    private DodavanjeSadrzajaKlaseButton dodavanjeSadrzajaKlaseButton;
    private DodavanjeInterclassObjektaButton dodavanjeInterclassObjektaButton;
    private DodavanjeVezaButton dodavanjeVezaButton;
    private SelekcijaButton selekcijaButton;
    private DuplicateButton duplicateButton;
    private MenjanjeSadrzajaButton menjanjeSadrzajaButton;
    private ZoomInButton zoomInButton;
    private ZoomOutButton zoomOutButton;
    private UndoAction undoAction;
    private RedoAction redoAction;

    public ActionManager() {
        redoAction = new RedoAction();
        undoAction = new UndoAction();
        zoomInButton = new ZoomInButton();
        zoomOutButton = new ZoomOutButton();
        menjanjeSadrzajaButton = new MenjanjeSadrzajaButton();
        duplicateButton = new DuplicateButton();
        move = new Move();
        newProjectAction = new NewProjectAction();
        exitAction = new ExitAction();
        aboutUs = new AboutUs();
        deleteNode = new DeleteNode();
        selekcijaButton = new SelekcijaButton();
        dodavanjeSadrzajaKlaseButton = new DodavanjeSadrzajaKlaseButton();
        dodavanjeVezaButton = new DodavanjeVezaButton();
        dodavanjeInterclassObjektaButton = new DodavanjeInterclassObjektaButton();
        brisanjeButton = new BrisanjeButton();
    }


}
