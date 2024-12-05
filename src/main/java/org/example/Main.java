package org.example;

import de.fhkiel.ki.cathedral.gui.CathedralGUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AgentBasic agentBasic = new AgentBasic();
        System.out.println(agentBasic.name());

        CathedralGUI.start(agentBasic);
    }
}