package crud.views.aside_bar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AsideBar extends JPanel{
  private JPanel userInfo;
  private List<JButton> actions = new ArrayList<>();
  private transient AsideBarLayoutStrategy layoutStrategy = new AsideBarLayoutStrategy() {
  };

  public AsideBar setUserInfo(JPanel userInfo){
    this.userInfo = Objects.requireNonNull(userInfo);

    return this;
  }

  public AsideBar setActions(List<JButton> actions){
    this.actions = Objects.requireNonNull(actions);

    return this;
  }

  public AsideBar addAction(JButton action){
    this.actions.add(action);

    return this;
  }

  public AsideBar setLayoutStrategy(AsideBarLayoutStrategy layoutStrategy){
    this.layoutStrategy = Objects.requireNonNull(layoutStrategy);

    return this;
  }

  public AsideBar build(){
    this.layoutStrategy.setUpLayout(this, this.userInfo, this.actions);

    return this;
  }

  public static void main(String[] args) {
    var fakeWindow = new JFrame();
    var asideBar = new AsideBar();

    asideBar
      .setUserInfo(new JPanel())
      .addAction(new JButton("Action 1"))
      .addAction(new JButton("Action 2"))
      .addAction(new JButton("Action 3"))
      .build();

    fakeWindow.add(asideBar);
    fakeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fakeWindow.setVisible(true);
  }
}
