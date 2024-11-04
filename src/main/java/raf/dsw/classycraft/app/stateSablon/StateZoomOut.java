package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

public class StateZoomOut implements State{

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        double newScaling = diagramView.getScale() * 0.8;
        if(newScaling <= 0.2) newScaling = 0.2;
        diagramView.setTr(newScaling,x,y);
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }
}
