package raf.dsw.classycraft.app.stateSablon;

public class StateManager {
    private MenjanjeSadrzaja menjanjeSadrzaja;
    private State currentState;
    private Duplicate duplicate;
    private Brisanje brisanje;
    private DodavanjeInterclassObjekata dodavanjeInterclassObjekata;
    private DodavanjeSadzrajaKlase dodavanjeSadzrajaKlase;
    private DodavanjeVeza dodavanjeVeza;
    private Selekcija selekcija;
    private MoveState moveState;
    private StateZoomIn stateZoomIn;
    private StateZoomOut stateZoomOut;

    public StateManager(String s) {
        inita(s);
    }

    private void inita(String s) {
        menjanjeSadrzaja = new MenjanjeSadrzaja();
        duplicate = new Duplicate();
        brisanje = new Brisanje(s);
        dodavanjeInterclassObjekata = new DodavanjeInterclassObjekata(s);
        dodavanjeSadzrajaKlase = new DodavanjeSadzrajaKlase(s);
        dodavanjeVeza = new DodavanjeVeza(s);
        selekcija = new Selekcija();
        moveState = new MoveState();
        stateZoomIn = new StateZoomIn();
        stateZoomOut = new StateZoomOut();
    }

    public void setBrisanje(String s) {
        currentState = this.brisanje;
    }
    public void setMenjanjeSadrzaja(){currentState = this.menjanjeSadrzaja;}
    public void setDuplicate(){currentState = this.duplicate;}

    public void setDodavanjeInterclassObjekata(String s) {
        currentState = this.dodavanjeInterclassObjekata;
    }

    public void setDodavanjeSadzrajaKlase() {
        currentState = this.dodavanjeSadzrajaKlase;
    }

    public void setDodavanjeVeza(String s) {
        currentState = this.dodavanjeVeza;
    }

    public void setSelekcija() {
        currentState = this.selekcija;
    }
    public void setMoveState(){currentState = this.moveState;}

    public void setStateZoomIn() {
        currentState = this.stateZoomIn;
    }

    public void setStateZoomOut() {
        currentState = this.stateZoomOut;

    }

    public State getCurrentState() {
        return currentState;
    }
}
