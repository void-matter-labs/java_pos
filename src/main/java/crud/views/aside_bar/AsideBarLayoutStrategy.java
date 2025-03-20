package crud.views.aside_bar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public interface AsideBarLayoutStrategy {
  public  default void setUpLayout(JPanel asidePanel, JPanel userInfo, java.util.List<JButton> actions){
    asidePanel.setLayout(new BorderLayout());
    asidePanel.add(userInfo, "North");
    var actionsHolder = new JPanel();
    actionsHolder.setLayout(new BoxLayout(actionsHolder, BoxLayout.Y_AXIS));
    actions.forEach(actionsHolder::add);
    asidePanel.add(actionsHolder, "Center");
  }
}
