package crud.interfaces;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface LayoutStrategy {
   default void setUpLayout(JFrame mainApp, JComponent asideBarContainer, JComponent mainContentContainer) {
      mainApp.setLayout(new BorderLayout());
      mainApp.add(asideBarContainer, "West");
      mainApp.add(mainContentContainer, "Center");
   }

   default void addNewMainContent(JComponent mainContentContainer,  JPanel newMainContent) {
      mainContentContainer.removeAll();
      mainContentContainer.add(newMainContent, "Center");
      mainContentContainer.revalidate();
      mainContentContainer.repaint();
   }
}
