package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

public interface State {
    public void misKliknut(int x, int y, DiagramView diagramView);
    public void misPovucen(int x, int y, DiagramView diagramView);
    public void misOtpusten(int x, int y, DiagramView diagramView);

}
