package org.example;
import org.example.IGraficas.MainFrame;
import org.example.controller.AdminController;
import org.example.menu.MenuMain;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        /////////////////////////////////////////////////////////////////
        ///////////////////////SECTOR PRUEBA
        //////////////////////////////////////////////////////////////////
       /* AdminRepository adminRepository = new AdminRepository();
        AdminController adminController =  new AdminController(adminRepository);
        MenuMain menuMain = new MenuMain(adminController);
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(menuMain);
            mainFrame.setVisible(true);
        });
*/
        AdminController adminController =  new AdminController();
        MenuMain menuMain = new MenuMain(adminController);
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(menuMain);
            mainFrame.setVisible(true);
        });


        ///////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////



///verificar el ingreso de datos en rating del libro , si ingresa punto y no coma rompe
        ///admin : busca un libro fuera del indice y finaliza session.


        //MenuMain menu = new MenuMain();

        //menu.mainFlow();

    }

}