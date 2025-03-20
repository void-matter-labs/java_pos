package crud;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import crud.interfaces.LayoutStrategy;
import crud.views.aside_bar.AsideBar;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.util.Objects;


class MainAppLegacy extends JFrame {
  private JComponent asideBarContainer = new JPanel();
  private JComponent mainContentContainer = new JPanel();

  private transient LayoutStrategy layoutStrategy = new LayoutStrategy() {

  };

  public MainAppLegacy(String name) {
    super(name);

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.setSize(new Dimension(800, 600));
    this.setExtendedState(Frame.MAXIMIZED_BOTH);
  }

  public MainAppLegacy setLayoutStrategy(LayoutStrategy layoutStrategy){
    this.layoutStrategy = layoutStrategy;

    return this;
  }

  public MainAppLegacy setAsideBar(JPanel asideBar) {
    this.asideBarContainer.add(Objects.requireNonNull(asideBar));

    return this;
  }

  public MainAppLegacy setMainContent(JPanel mainContent) {
    this.mainContentContainer.add(Objects.requireNonNull(mainContent));

    return this;
  }


  @Override
  public void setVisible(boolean isVisible){
    this.layoutStrategy.setUpLayout(
      this,
      this.asideBarContainer,
      this.mainContentContainer
    );

    super.setVisible(isVisible);
  }

  public MainAppLegacy switchMainContent(JPanel newMainContent){
    this.layoutStrategy.addNewMainContent(mainContentContainer, newMainContent);

    return this;
  }


  public static void main(String[] args) {
    MainAppLegacy app = new MainAppLegacy("MainApp");

    JPanel fakeContent = new JPanel();
    fakeContent.add(new Label("Main content"));

    JPanel newFakeContent = new JPanel();
    newFakeContent.add(new Label("New Content"));

    AsideBar asideBar = new AsideBar();

    JButton showFakeContent = new JButton("Show Fake Content");
    showFakeContent.addActionListener(e -> {
      app.switchMainContent(fakeContent);
    });

    JButton showNewContent = new JButton("Show New Content");
    showNewContent.addActionListener(e -> {
      app.switchMainContent(newFakeContent);
    });

    asideBar
      .setUserInfo(new JPanel())
      .addAction(showFakeContent)
      .addAction(showNewContent)
      .build();

    app
      .setAsideBar(asideBar)
      .setMainContent(fakeContent)
      .setVisible(true);


  }
}
